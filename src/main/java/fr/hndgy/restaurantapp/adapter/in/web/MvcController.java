package fr.hndgy.restaurantapp.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MvcController {
    
    @GetMapping("/orders")
    public String orders(){
        return "orders-receiver";
    }
}
