package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoDto;
import com.thoughtworks.todolist.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDoNote")
public class ToDoController {

    @Autowired
    private ToDoListService toDoService;

    public ToDoController(ToDoListService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ToDoNote>> getAllToDoNotes() {
        List<ToDoNote> toDoList = toDoService.getToDoList();
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addToDoNote(@RequestBody ToDoDto toDoNote) {
        Response response = toDoService.addToDo(toDoNote);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/update/{toDoId}")
    public ResponseEntity<Response> updateToDo(@PathVariable Long toDoId, @RequestBody ToDoDto updatedToDo) {
        Response response = toDoService.updateToDo(toDoId, updatedToDo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{toDoId}")
    public ResponseEntity<Response> deleteToDoNote(@PathVariable Long toDoId) {
        Response response = toDoService.deleteToDo(toDoId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
