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

    addChoice(orderId, elementId){

    }

    removeChoice(orderId, elementId){

    }

    getAllInProgress(){

    }

    getAll(){
        return fetch("/api/order");
    }
}

export default new OrderService();