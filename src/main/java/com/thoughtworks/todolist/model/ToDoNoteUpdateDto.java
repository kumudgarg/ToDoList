package com.thoughtworks.todolist.model;

import lombok.Getter;
import lombok.Setter;

public class ToDoNoteUpdateDto {

    @Getter @Setter
    String toDoDescription;

    public ToDoNoteUpdateDto() {
    }

    public ToDoNoteUpdateDto(String updated_note) {
        this.toDoDescription = updated_note;
    }
}
