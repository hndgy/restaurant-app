import React from 'react'
import { useState } from 'react';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom'
import ListMenuElement from '../components/ListMenuElement';
import OrderElementList from '../components/OrderElementList';
import Popup from '../components/Popup';
import OrderService from '../services/OrderService';

function EditOrder() {

    const params = useParams();
    const [order, setOrder] = useState();
    const [mealCategorySelected, setMealCategorySelected] = useState();
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

   

    const handleAddChoice = (mealCategory) => {
        setMealCategorySelected(mealCategory)
    }

    const handleOnAdded = (newChoice) => {
        setMealCategorySelected(null);
        order.choices.push(newChoice)
        setOrder(order);

    }

  return (
    <div className='container mt-2 text-center'>
        <Popup/>
        
        {(order && (
            <div>
                <div className='row text-center'>
                    <h3>Table : {order.table.name}</h3> 
                    <h4> Couverts : {order.nbOfGuests}</h4> 
                    <hr/>
                </div>
                <div className='row'>
                    <OrderElementList
                            title={"Entrées"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "STARTERS"))}
                            mealCategory={"STARTERS"}
                            handleDelete={handleDelete}
                            handleAddChoice={handleAddChoice}
                            />
                    <OrderElementList
                            title={"Plats"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "DISHES"))}
                            mealCategory={"DISHES"}
                            handleDelete={handleDelete}
                            handleAddChoice={handleAddChoice}
                            />
                </div>
                <div className='row'>
                    <OrderElementList
                            title={"Apéritifs"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "STARTER_DRINKS"))}
                            mealCategory={"STARTER_DRINKS"}
                            handleDelete={handleDelete}
                            handleAddChoice={handleAddChoice}
                            />
                    <OrderElementList
                            title={"Boissons"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "MEAL_DRINKS"))}
                            mealCategory={"MEAL_DRINKS"}
                            handleDelete={handleDelete}
                            handleAddChoice={handleAddChoice}
                            />
                </div>
                <div className='row'>
                    <OrderElementList
                            title={"Desserts"}
                            choices={null}
                            mealCategory={"DESSERTS"}
                            handleDelete={handleDelete}
                            handleAddChoice={handleAddChoice}
                            />
            </div>
        </div>

        )) || (
            <div className="spinner-border text-dark" role="status">
                <span className="sr-only"></span>
            </div>
        )}

        {
            mealCategorySelected &&
                <ListMenuElement
                    categorySelected={mealCategorySelected}
                    onAdded={handleOnAdded}
                    onCancel={() => setMealCategorySelected(null)}
                />

        }
        
    </div>
  )
}

export default EditOrder