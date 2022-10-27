

class TableService{
    
    getAll(){
        return fetch('/api/table');
    }

    getAllDispo(){
        return fetch('/api/table/available');
    }

    create(name){
        return fetch('/api/table',
        {
            method: 'POST',
            body: JSON.stringify({name : name}),
            headers : {
                'Content-Type': 'application/json'
            }
        });
    }
}

export default new TableService();