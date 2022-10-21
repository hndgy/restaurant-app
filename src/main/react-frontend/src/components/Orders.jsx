import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import { Stomp } from '@stomp/stompjs';
import SockJs from "sockjs-client/dist/sockjs"
import { Link } from 'react-router-dom';
import OrderService from '../services/OrderService';

const mealCategoryTitles = {
    "STARTERS" : "Entrées",
    "DISHES" : "Plats",
    "DESSERTS" : "Desserts"

}

function Orders() {

    const [isConnected, setConnected] = useState(false);
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetch("/api/order")
            .then(res => res.json())
            .then(data => {
                console.log(data);
                setOrders(data);
            });

            var socket = new SockJs("http://localhost:8080/ws-orders");

            var ws = Stomp.over(socket);
            ws.connect({}, function(frame) {
                setConnected(true);
                ws.subscribe("/topic/orders", function (msg) {
                    if (msg.body) {
                        var newOrder = JSON.parse(msg.body);
                        var newList = orders.concat(newOrder);
                        console.log(newList);
                        setOrders(newList);
                    }
                }); 
                
                ws.subscribe("/topic/choicesDeleted", function (msg) {
                    if (msg.body) {
                        var choiceDeleted = JSON.parse(msg.body);
                        //Do something
                    }
                });
            }, function(error) {
                alert("STOMP error " + error);
            });
    },[]);
    


    const handleDelete = (orderId) => {
        if(!window.confirm("êtes-vous sûr ?")) return;

        OrderService.removeOrder(orderId)
            .then(
                () => {
                    setOrders(orders.filter(o => o.orderId.value !== orderId))
                }
            );
    }


  return (
    <div className='container-fluid mt-5 text-center'>
        <div className="row mb-3">
            <div className="col-sm-6">
                <h3>Commandes en cours: {orders.length}</h3>
            </div>
            <div className="col-sm-6">
                <Link to={'/newOrder'} className='btn btn-dark'>Nouvelle commande +</Link>
            </div>
        </div>
        <div className="" style={{display : "flex", justifyContent: "space-between", overflow: "scroll"}}>
        {isConnected && orders
            &&
            orders.map(order => {
                return (
                    <div className="card border-dark mb-3 col-sm-4 " key={order.orderId.value}>
                    <div className="card-header">
                        <h6>{order.nbOfGuests} personne(s)</h6>
                        {order.table.name} ({new Date(order.creationDateTime).toLocaleTimeString()})
                        <Link className='btn btn-warning mr-2' to={`/editOrder/${order.orderId.value}`}> Modifier </Link> <br/>
                        <button className='btn btn-secondary' onClick={() => {handleDelete(order.orderId.value)}}>Annuler</button>
                    </div>
                    <div className="card-body">

                        {OrderService.getKitchenMealCategories().map(
                            mealCategory => {
                                const mealCategoryElements = order.choices.filter(x => x.mealCategory === mealCategory);

                                if(mealCategoryElements.length === 0) return null;

                                const choicesByElement = OrderService.getChoicesByMenuElement(mealCategoryElements)

                                return (
                                    <>
                                        <u><h4>{mealCategoryTitles[mealCategory]}</h4></u>
                                        {Object.keys(choicesByElement).map(
                                        elId => (
                                            <p>
                                                <b>{choicesByElement[elId].choices.length} </b>
                                                {choicesByElement[elId].name}
                                                <ul>
                                                    {choicesByElement[elId].choices.map( choice => {
                                                        if(choice.comment) return (<li>{choice.comment}</li>)
                                                        else return null;
                                                    }
                                                    )}
                                                </ul>
                                                
                                            </p>
                                        ))}
                                    </>
                                )
                            }
                        )}
                    </div>
                </div>
                );
            })
        }
        </div>
        
        {!isConnected && (
                <div className="spinner-border text-dark" role="status">
                    <span className="sr-only"></span>
                </div>
            )
        }
    </div>
  )
}

export default Orders