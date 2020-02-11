package com.thoughtworks.todolist.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoListService {

    public List getToDoList() {
        return new ArrayList();
    }
}
