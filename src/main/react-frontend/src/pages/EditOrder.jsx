import React from 'react'
import { useState } from 'react';
import { useEffect } from 'react';
import { useParams } from 'react-router-dom'
import ListMenuElement from '../components/ListMenuElement';
import OrderElementList from '../components/OrderElementList';
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

    useEffect(() => {
       console.log("ORDER CHANGED", order)
    },[order])


    const handleDelete =  (choiceId) => {
        OrderService.removeChoice(order.orderId.value,choiceId)
            .then(() => {
                var newOrder = {...order};

                newOrder.choices = order.choices.filter(x => x.orderChoiceId.value !== choiceId);
                setOrder(newOrder)
            });
        
    }

   

    const handleAddChoice = (mealCategory) => {
        setMealCategorySelected(mealCategory)
    }

    const handleOnAdded = (newChoice) => {
        setMealCategorySelected(null);

        var newOrder = {...order};
        newOrder.choices.push(newChoice);
        setOrder(newOrder);
    }

    const handleAddOne = (elementId, mealCategory) => {
        OrderService.addChoice(order.orderId.value,elementId, "",mealCategory)
            .then(res => res.json())
            .then(data => {
                handleOnAdded(data);
            });
    }

    const handles = {handleAddChoice, handleAddOne, handleDelete}

  return (
    <div className='container mt-2 text-center'>
        
        {(order && (
            <div>
                <div className='row text-center'>
                    <h3>Table : {order.table.name}</h3>
                    <h4>Couverts : {order.nbOfGuests}</h4>
                    <hr/>
                </div>
                <div className='row'>
                    <OrderElementList
                            title={"Entrées"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "STARTERS"))}
                            mealCategory={"STARTERS"}
                            {...handles}
                            />
                    <OrderElementList
                            title={"Plats"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "DISHES"))}
                            mealCategory={"DISHES"}
                            {...handles}

                            />
                </div>
                <div className='row'>
                    <OrderElementList
                            title={"Apéritifs"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "STARTER_DRINKS"))}
                            mealCategory={"STARTER_DRINKS"}
                            {...handles}

                            />
                    <OrderElementList
                            title={"Boissons"}
                            choicesByElement={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "MEAL_DRINKS"))}
                            mealCategory={"MEAL_DRINKS"}
                            {...handles}

                            />
                </div>
                <div className='row'>
                    <OrderElementList
                            title={"Desserts"}
                            choices={OrderService.getChoicesByMenuElement(order.choices.filter(x => x.mealCategory === "DESSERTS"))}
                            mealCategory={"DESSERTS"}
                            {...handles}

                            />
            </div>
        </div>

        )) || (
            <div className="spinner-border text-dark mt-5" role="status">
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