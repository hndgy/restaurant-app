package fr.hndgy.restaurantapp.adapter.in.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.hndgy.restaurantapp.adapter.in.web.dto.CreateTableDto;
import fr.hndgy.restaurantapp.application.port.in.CreateTableCommand;
import fr.hndgy.restaurantapp.application.port.in.TableService;
import lombok.RequiredArgsConstructor;
import fr.hndgy.restaurantapp.domain.Table;;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/table")
public class TableController {
    
    private final TableService tableService;

    @PostMapping
    public Table createTable(@RequestBody CreateTableDto createTableDto){
        var command = new CreateTableCommand();
        command.setName(createTableDto.getName());
        return this.tableService.createTable(command);
    }

    @GetMapping
    public List<Table> getAllTable(){
        return this.tableService.getAll();
    }

    @GetMapping("/available")
    public List<Table> getTablesDispo(){
        return this.tableService.getAllTableDispo();
    }

}
