class MenuElementService{
    getAllElement(){
        return fetch('/api/menuElement');
    }  
    
    create(menuElement){
        return fetch('/api/menuElement',{
            method : "POST",
            body : JSON.stringify(menuElement),
            headers: {
                'Content-Type': 'application/json'
            }
        });
    }
}

export default new MenuElementService();