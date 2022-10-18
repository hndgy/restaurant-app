import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import { Stomp } from '@stomp/stompjs';
import SockJs from "sockjs-client/dist/sockjs"
import { Link } from 'react-router-dom';
import OrderService from '../services/OrderService';

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
    },[]);
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
    }, function(error) {
        alert("STOMP error " + error);
    });


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
                    <div className="card-header">{order.table.name} ({order.creationDateTime}) 
                        <Link className='btn btn-warning mr-2' to={`/editOrder/${order.orderId.value}`}> Modifier </Link>
                        <button className='btn btn-secondary' onClick={() => {handleDelete(order.orderId.value)}}>Annuler</button>
                    </div>
                    <div className="card-body">
                        <h6>{order.nbOfGuests} personne(s)</h6>

                        {order.choices.map(choice => {
                            return (
                                <div key={choice.orderChoiceId.value}>
                                    <h4 className="card-title">{choice.menuElement.name}</h4>
                                    <p className="text-danger">Commentaires : {choice.comment}</p>
                                </div>
                            );
                        })}
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