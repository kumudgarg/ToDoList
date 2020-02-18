package com.thoughtworks.todolist.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class ToDoNote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;


    @Getter
    @Setter
    String description;

    @Getter @Setter
    public LocalDateTime timestamp;

    @Getter @Setter
    boolean isCompleted;

    public ToDoNote() {
    }

    public ToDoNote(String description, LocalDateTime timestamp, boolean isCompleted) {
        this.description = description;
        this.timestamp = timestamp;
        this.isCompleted = isCompleted;
    }
}
