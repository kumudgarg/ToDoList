package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.dto.ToDoDto;
import com.thoughtworks.todolist.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/toDo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ToDoNote>> getAllToDoNotes() {
        List<ToDoNote> toDoList = toDoService.getToDoList();
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addToDoNote(@RequestBody ToDoDto toDoNote) {
        Response response = toDoService.addToDo(toDoNote);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateToDo(@PathVariable Long id, @RequestBody ToDoDto updatedToDo) {
        Response response = toDoService.updateToDo(id, updatedToDo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteToDoNote(@PathVariable Long id) {
        Response response = toDoService.deleteToDo(id);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
