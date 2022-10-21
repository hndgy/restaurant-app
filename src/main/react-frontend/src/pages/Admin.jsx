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

  return (
    <div className='container'>
        <div className="row my-5">
          <h3>Menu</h3>
          {(menuElements &&
                <div className="ag-theme-alpine" style={{height: "50vh", width: "70vw"}}>
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
        <div className="row">
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
        
    </div>
  )
}

export default Admin