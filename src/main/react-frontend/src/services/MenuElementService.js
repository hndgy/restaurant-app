class MenuElementService{
    getAllElement(){
        return fetch('/api/menuElement');
    }
}

export default new MenuElementService();