package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoNoteUpdateDto;
import com.thoughtworks.todolist.service.ToDoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toDoNotes")
public class ToDoController {

    @Autowired
    private ToDoListService toDoService;

    public ToDoController(ToDoListService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/getAllToDoNotes")
    public ResponseEntity<List<ToDoNote>> getAllToDoNotes() {
        List<ToDoNote> toDoList = toDoService.getToDoList();
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    @PostMapping("/addToDoNote")
    public ResponseEntity<Response> addToDoNote(@RequestBody ToDoNoteUpdateDto toDoNote) {
        Response response = toDoService.addToDo(toDoNote);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/updateToDo/{toDoId}")
    public ResponseEntity<Response> updateToDo(@PathVariable Long toDoId, @RequestBody ToDoNoteUpdateDto updatedToDo) {
        Response response = toDoService.updateToDo(toDoId, updatedToDo);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteToDo/{toDoId}")
    public ResponseEntity<Response> deleteToDoNote(@PathVariable Long toDoId) {
        Response response = toDoService.deleteToDo(toDoId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
