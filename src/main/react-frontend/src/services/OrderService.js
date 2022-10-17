class OrderService{

    create(tableId, nbPersonne){
        return fetch("/api/order",
            {
                method: 'POST',
                body: JSON.stringify(
                    {
                        tableId : tableId,
                        nbOfGuests : nbPersonne
                    }
                ),
                headers: {
                    'Content-Type': 'application/json'
                },
            }
        );
    }

    getById(orderId){
        return fetch(`/api/order/${orderId}`);
    }

    addChoice(orderId, elementId){

    }

    removeChoice(orderId, choiceId){
        return fetch(`/api/order/${orderId}/choice/${choiceId}`,
        {
            method: 'DELETE'
        });
    }

    getAllInProgress(){

    }

    getAll(){
        return fetch("/api/order");
    }
}

export default new OrderService();