package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.exception.NoteNotFoundException;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.service.ToDoListService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TodoControllerTest {

    @Mock
    private ToDoListService toDoListService;

    private List<ToDoNote> toDoNoteList;

    @InjectMocks
    private ToDoController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ToDoNote toDoNote = new ToDoNote();
        this.toDoNoteList = new ArrayList<>();
        toDoNoteList.add(toDoNote);
        toDoNoteList.add(toDoNote);
        toDoNoteList.add(toDoNote);
    }

    @Test
    void givenARequestToGetAllToDoNotes_WhenAvailable_ShouldReturnListOfToDos() {
        when(toDoListService.getToDoList()).thenReturn(toDoNoteList);
        ResponseEntity<List<ToDoNote>> allToDoNotes = controller.getAllToDoNotes();
        Assert.assertEquals(200,allToDoNotes.getStatusCode().value());
    }


    @Test
    void givenARequestToGetAllToDoNotes_WhenNotAvailable_ShouldReturnListOfToDos() {
        try {
            when(toDoListService.getToDoList()).thenThrow(new NoteNotFoundException("No Notes!!", HttpStatus.NOT_FOUND));
            ResponseEntity<List<ToDoNote>> allToDoNotes = controller.getAllToDoNotes();
        } catch (NoteNotFoundException e) {
            Assert.assertEquals(404,e.statusCode.value());
        }
    }


}