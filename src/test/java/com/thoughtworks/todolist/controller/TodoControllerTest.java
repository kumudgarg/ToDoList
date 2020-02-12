package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.exception.NoteNotFoundException;
import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoNoteUpdateDto;
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
    void givenARequestToGetAllToDoNotes_WhenNotAvailable_ShouldThrowException() {
        try {
            when(toDoListService.getToDoList()).thenThrow(new NoteNotFoundException("No Notes!!", HttpStatus.NOT_FOUND));
            ResponseEntity<List<ToDoNote>> allToDoNotes = controller.getAllToDoNotes();
        } catch (NoteNotFoundException e) {
            Assert.assertEquals(404,e.statusCode.value());
        }
    }

    @Test
    void givenAToDoNote_WhenAdded_ShouldReturnStatuscode200() {
        ToDoNote toDoNote = new ToDoNote();
        when(toDoListService.addToDo(toDoNote)).thenReturn(new Response(HttpStatus.OK.value(),"Note created successfully!!"));
        ResponseEntity<Response> responseEntity = controller.addToDoNote(toDoNote);
        Assert.assertEquals(200,responseEntity.getStatusCode().value());
    }


    @Test
    void givenAnUpdatedToDoNote_WhenExistsInDb_ShouldGetStoredInTheDb() {
        Long toDoId = 1L;
        ToDoNoteUpdateDto updatedToDo = new ToDoNoteUpdateDto();
        when(toDoListService.updateToDo(toDoId,updatedToDo)).thenReturn(new Response(HttpStatus.OK.value(),"Note created successfully!!"));
        ResponseEntity<Response> responseEntity = controller.updateToDo(toDoId,updatedToDo);
        Assert.assertEquals(200,responseEntity.getStatusCode().value());
    }

    @Test
    void givenAToDoNoteID_whenDeleted_ShouldReturnStatusCode200() {
        Long toDoId = 1L;
        ToDoNote toDoNote =new ToDoNote();
        when(toDoListService.deleteToDo(toDoId)).thenReturn(new Response(HttpStatus.OK.value(),"Note deleted successfully!!"));
        ResponseEntity<Response> responseEntity = controller.deleteToDoNote(toDoId);
        Assert.assertEquals(200,responseEntity.getStatusCode().value());
    }
}