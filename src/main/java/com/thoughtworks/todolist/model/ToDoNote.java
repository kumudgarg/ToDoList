package com.thoughtworks.todolist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ToDoNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long toDoId;

    @Getter
    @Setter
    String toDoDescription;

    public ToDoNote() {
    }

    public ToDoNote(long toDoId, String toDoDescription) {
        this.toDoId = toDoId;
        this.toDoDescription = toDoDescription;
    }
}
