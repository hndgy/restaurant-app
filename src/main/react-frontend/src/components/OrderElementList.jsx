import React from 'react'

function OrderElementList({title,choicesByElement, mealCategory, handleDelete, handleAddChoice,handleAddOne}) {

  return (
    <div className='col-sm-6'>
                    <h2>{title}</h2>
                    <table className="table table-hover">
                    <tbody>
                        {choicesByElement && Object.keys(choicesByElement).map(
                            elId => (
                                <tr key={elId}>
                                    <th scope="row">
                                        {choicesByElement[elId].name} ({choicesByElement[elId].choices.length})
                                    </th>
                                    <td>
                                        <ul>
                                        {choicesByElement[elId].choices.map(c => {
                                            if(c.comment)
                                                return (<li key={c.orderChoiceId.value}>{c.comment}</li>);
                                            })}
                                        </ul>

                                    </td>
                                    <td>
                                        <button className='btn btn-light btn-sm' onClick={() =>{ handleAddOne(elId,mealCategory)}} >
                                            +1
                                        </button>
                                        <button className='btn btn-danger btn-sm' onClick={() => handleDelete(choicesByElement[elId].choices[0].orderChoiceId.value)}>
                                            -1
                                        </button> 
                                    </td>
                                </tr>
                            )
                        )}
                            <tr className='table-light'>
                                <td colSpan={4} onClick={() => handleAddChoice(mealCategory)}> Ajouter +</td>
                            </tr>
                    </tbody>
                    </table>
                </div>
  )
}

export default OrderElementList