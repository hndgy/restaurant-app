import React from 'react'
import { useState } from 'react';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom'
import OrderElementList from '../components/OrderElementList';
import OrderService from '../services/OrderService';

function EditOrder() {

    const params = useParams();
    const [order, setOrder] = useState();

    useEffect(
        () => {
            OrderService.getById(params.orderId).then(res => res.json())
                .then(data => {
                    setOrder(data)
                });
        },[params]
    );
    const handleDelete =  (choiceId) => {
        OrderService.removeChoice(order.orderId.value,choiceId)
            .then(() => {
                order.choices = order.choices.filter(x => x.orderChoiceId.value !== choiceId);
                setOrder(order)
            });
        
    }
  return (
    <div className='container mt-2 text-center'>
        {(order && (
            <div>
                <div className='row'>
                    <OrderElementList title={"Entrées"} order={order} mealCategory={"STARTERS"} handleDelete={handleDelete}/>
                    <OrderElementList title={"Plats"} order={order} mealCategory={"DISHES"} handleDelete={handleDelete}/>
                </div>
                <div className='row'>
                    <OrderElementList title={"Apéritifs"} order={order} mealCategory={"STARTER_DRINKS"} handleDelete={handleDelete}/>
                    <OrderElementList title={"Boissons"} order={order} mealCategory={"MEAL_DRINKS"} handleDelete={handleDelete}/>
                </div>
                <div className='row'>
                    <OrderElementList title={"Desserts"} order={order} mealCategory={"DESSERTS"} handleDelete={handleDelete}/>
            </div>
        </div>

        )) || (
            <div className="spinner-border text-dark" role="status">
                <span className="sr-only"></span>
            </div>
        )}
    </div>
  )
}

export default EditOrder