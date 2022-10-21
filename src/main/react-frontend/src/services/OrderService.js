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

    addChoice(orderId, elementId, comment, category){
        return fetch(`/api/order/${orderId}/choice`,
        {
            method: 'POST',
            body: JSON.stringify(
                {
                    "menuElementId" : elementId,
                    "comment" : comment,
                    "mealCategory" : category
                }),
            headers: {
                'Content-Type': 'application/json'
            },
        }
    );
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

    removeOrder(orderId){
        return fetch(`/api/order/${orderId}`,
            {
                method: 'DELETE'
            }
        );
    }


    getChoicesByMenuElement(choices) {
        const res = {};
        choices.forEach(
            (choice) => {
                console.log(choice)
                if(res[choice.menuElement.menuElementId.value]){
                    res[choice.menuElement.menuElementId.value]["choices"].push(choice);
                }else{
                    res[choice.menuElement.menuElementId.value] = choice.menuElement;
                    res[choice.menuElement.menuElementId.value]["choices"] = [choice];
                }
            }
        );

          console.log(res);
          return res;
    }

    mealCategories = ["STARTERS", "DISHES",  "DESSERTS","STARTER_DRINKS","MEAL_DRINKS"]
    kitchenMealCategories = ["STARTERS", "DISHES",  "DESSERTS"]

    getKitchenMealCategories(){
        return this.kitchenMealCategories;
    }
}

export default new OrderService();