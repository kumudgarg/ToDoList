package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoListService {

    @Autowired
    private ToDoRepository toDoRepository;

    public List<Object> getToDoList() {
        return toDoRepository.findAll();
    }
}
