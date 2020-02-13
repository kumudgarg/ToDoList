package com.thoughtworks.todolist.model;

import lombok.Getter;
import lombok.Setter;

public class ToDoDto {

    @Getter @Setter
    String toDoDescription;

    public ToDoDto() {
    }

    public ToDoDto(String updated_note) {
        this.toDoDescription = updated_note;
    }
}
