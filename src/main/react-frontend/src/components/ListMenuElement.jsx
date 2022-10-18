import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import { useParams } from 'react-router-dom';
import MenuElementService from '../services/MenuElementService';
import OrderService from '../services/OrderService';

function ListMenuElement({categorySelected, onAdded, onCancel}) {

    const params = useParams();
    const [menuElements, setMenuElements] = useState([]);
    const [comment, setComment] = useState("");

    useEffect(() => {
        MenuElementService.getAllElement().then(res => res.json())
            .then((data) => {
                setMenuElements(data)
            });
    },[])

    const handleClick = (elementId) => {
        const orderId = params.orderId;
        OrderService.addChoice(orderId,elementId, comment, categorySelected)
            .then(res => res.json())
            .then((data) => {
                console.log(data)
                onAdded(data);
                setComment("");
            });
    }


  return (
    <div className='row'>
        <input className='form-control' value={comment} onChange={e => setComment(e.target.value)}/>
        {(menuElements && menuElements.map(
            el => (
                <button key={el.menuElementId.value} className='btn btn-light' onClick={() => handleClick(el.menuElementId.value)}>{el.name}</button>
            )
        )) || (
            <div className="spinner-border text-dark" role="status">
                <span className="sr-only"></span>
            </div>
        )}
        <button className='btn btn-warning' onClick={() => onCancel()}> Annuler </button>
    </div>
  )
}

export default ListMenuElement