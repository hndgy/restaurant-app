import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import { Stomp } from '@stomp/stompjs';
import SockJs from "sockjs-client/dist/sockjs"

function KitchenOrders() {

    const [isConnected, setConnected] = useState(false);
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        fetch("/api/order")
            .then(res => res.json())
            .then(data => setOrders(data));
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


  return (
    <div className='container mt-5 text-center'>
        {isConnected && orders &&
            orders.map(order => {
                return (
                    <div className="card border-dark mb-3" key={order.orderId.value}>
                    <div className="card-header">{order.table.name} ({order.creationDateTime})</div>
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
        {!isConnected && (
                <div className="spinner-border text-dark" role="status">
                    <span className="sr-only"></span>
                </div>
            )
        }
    </div>
  )
}

export default KitchenOrders