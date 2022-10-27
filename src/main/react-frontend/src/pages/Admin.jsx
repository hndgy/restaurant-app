import React, { useMemo } from 'react'
import { useEffect } from 'react';
import { useState } from 'react'
import MenuElementService from '../services/MenuElementService';
import TableService from '../services/TableService';
import { AgGridReact } from 'ag-grid-react';

import 'ag-grid-community/styles/ag-grid.css'; // Core grid CSS, always needed
import 'ag-grid-community/styles/ag-theme-alpine.css'; // Optional theme CSS

function Admin() {
  const [menuElements, setMenuElements] = useState([]);
  const [tables, setTables] = useState([]);

  useEffect(() => {
    MenuElementService.getAllElement().then(res => res.json())
      .then(data => setMenuElements(data)); 
      
      TableService.getAll().then(res => res.json())
      .then(data => setTables(data));
  },[]);

  const [menuElementsColumnDefs] = useState([
    {field : "menuElementId.value"},
    {field : "name"},
    {field : "type"},
    {field : "price"}
  ]);

  const [tablesColumnDefs] = useState([
    {field : "tableId.value"},
    {field : "name"}
  ]);

  
    const defaultColDef = useMemo(() => {
    return {
      sortable: true,
    };
  }, []);


  const handleAddElement = (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    console.log(formData.get("name"));
    MenuElementService.create(
      {
        name : formData.get("name"),
        price : formData.get("price"),
        type : formData.get("type")
    }
    ).then(res => res.json())
      .then(
        (data) => {
          const copy = [...menuElements];
          copy.push(data);
          setMenuElements(copy);
        }
      );
  }

  const handleAddTable = (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    TableService.create(formData.get("name")).then(res => res.json())
      .then(
        data => {
          const copy = [...tables];
          copy.push(data);
          setTables(copy);
        }
      )
  
  }
  return (
    <div className='container-fuild'>
  
        <div className="row">
          <div className="col-sm-8">
          <h3>Menu</h3>
          {(menuElements &&
                <div className="ag-theme-alpine" style={{height: "50vh", width: "66vw"}}>
                <AgGridReact
                    rowData={menuElements}
                    columnDefs={menuElementsColumnDefs}
                    defaultColDef={defaultColDef}
                    >
                </AgGridReact>
            </div>
            ) || (
              <div className="spinner-border text-dark" role="status">
                  <span className="sr-only"></span>
              </div>
            )}
          </div>
          <div className="col-sm-4">
            <h4>Ajouter</h4>
            <form onSubmit={e => handleAddElement(e)}>
            <div className="form-group">
                <div className="form-floating mb-3">
                <input className='form-control' name="name"/>
                    <label >Nom</label>
                </div>
            </div>
            <div className="form-group">
                <div className="form-floating mb-3">
                <input className='form-control' name="price"/>
                    <label >Prix</label>
                </div>
            </div>

            <div className="form-group mt-1">
              <select className='form-select' name="type">
                  <option value="FOOD">FOOD</option>
                  <option value="DRINK">DRINK</option>
                </select>
            </div>
            
              <button className='btn btn-primary btn-sm mt-1' type='submit' >Ajouter +</button>
            </form>
          </div>

        </div>
     
        <div className="row ">

          <div className="col-sm-8">
          <h3>Tables</h3>

        {(tables &&
              <div className="ag-theme-alpine" style={{height: "50vh", width: "30vw"}}>
              <AgGridReact
                  rowData={tables}
                  columnDefs={tablesColumnDefs}
                  defaultColDef={defaultColDef}
                  >
              </AgGridReact>
          </div>
          ) || (
            <div className="spinner-border text-dark" role="status">
                <span className="sr-only"></span>
            </div>
          )}
          </div>

          <div className="col-sm-4">
          <h4>Ajouter</h4>
            <form onSubmit={e => handleAddTable(e)}>
            <div className="form-group">
                <div className="form-floating mb-3">
                <input className='form-control' name="name"/>
                    <label >Nom</label>
                </div>
            </div>
            <button className='btn btn-primary btn-sm mt-1' type='submit' >Ajouter +</button>
            </form>

          </div>
         
        </div>
        
    </div>
  )
}

export default Admin