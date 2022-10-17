import React from 'react'

function OrderElementList({title,order, mealCategory, handleDelete}) {


    const handleAddChoice = () => {
        
    }

  return (
    <div className='col-sm-6'>
                    <h2>{title}</h2>
                    <table className="table table-hover">
                    <tbody>
                        {order.choices.filter(c => c.mealCategory === mealCategory).map(
                            choice => (
                                <tr key={choice.orderChoiceId.value}>
                                    <th scope="row">
                                        <button className='btn btn-light btn-sm'>+1</button> 
                                        {choice.menuElement.name}
                                    </th>
                                    <td>
                                        {choice.comment}
                                    </td>
                                    <td>
                                        {choice.creationDateTime}
                                    </td>  
                                    <td>

                                    <button className='btn btn-danger btn-sm' onClick={() => handleDelete(choice.orderChoiceId.value)}>
                                            Supprimer
                                        </button>
                                    </td>
                                </tr>
                            )
                        )}
                            <tr className='table-light'>
                                <td colSpan={4} onClick={handleAddChoice}> Ajouter +</td>
                            </tr>
                    </tbody>
                    </table>
                </div>
  )
}

export default OrderElementList