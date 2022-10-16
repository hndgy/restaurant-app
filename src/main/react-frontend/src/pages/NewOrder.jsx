import React from 'react'
import { useState } from 'react';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import OrderService from '../services/OrderService';
import TableService from '../services/TableService';

function NewOrder() {

    const navigate = useNavigate();
    const [submiting, setSubmiting] = useState(false);
    const [tables, setTables] = useState([]);
    useEffect(() => {
         TableService.getAllDispo().then(res => res.json())
            .then(
                data => {
                    setTables(data);
                }
            );
    },[])


    const handleSubmit = (e) => {
        e.preventDefault();
        setSubmiting(true);
        const formData = new FormData(e.currentTarget);
        const tableId = formData.get("tableId")
        const nbPers = formData.get("nbPers")


        OrderService.create(tableId, nbPers).then(
            res => res.json()
        ).then(
            data => {
                navigate(`/editChoices/${data.orderId.value}`)
            }
        );
    }


  return (
    <div className='container mt-5'>
        <form onSubmit={e => handleSubmit(e)}>
            <div className="form-group mb-3">
                <label className="form-label mt-4">Table</label>
                <select className="form-select" name="tableId">
                    {tables && tables.map(
                        table => (
                            <option key={table.tableId.value} value={table.tableId.value}>{table.name}</option>
                        )
                    )}
                </select>
            </div>

            <div className="form-group">
                <div className="form-floating mb-3">
                    <input type="number" className="form-control" defaultValue={0} name="nbPers"/>
                    <label >Nombre de personnes</label>
                </div>
            </div>
            <button type='submit' className="btn btn-dark" disabled={submiting}>Créer
            {submiting &&
                    (
                        <div className="spinner-border spinner-border-sm text-light" role="status">
                            <span className="sr-only"></span>
                        </div>
                    )
                }
            </button>
          
        </form>

    </div>
    )
}

export default NewOrder