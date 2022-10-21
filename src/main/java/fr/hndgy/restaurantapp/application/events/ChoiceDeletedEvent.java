package fr.hndgy.restaurantapp.application.events;

import org.springframework.context.ApplicationEvent;

import fr.hndgy.restaurantapp.application.port.in.RemoveChoiceCommand;
import lombok.Getter;

@Getter
public class ChoiceDeletedEvent extends ApplicationEvent{

    private final RemoveChoiceCommand removeChoice;
    public ChoiceDeletedEvent(Object source, RemoveChoiceCommand removeChoice) {
        super(source);
        this.removeChoice = removeChoice;
    }

}
