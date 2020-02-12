package com.thoughtworks.todolist;


import com.thoughtworks.todolist.exception.NoteNotFoundException;
import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoNoteUpdateDto;
import com.thoughtworks.todolist.repository.ToDoRepository;
import com.thoughtworks.todolist.service.ToDoListService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ToDoListServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private Environment env;

    List<ToDoNote> listOfToDos;

    @InjectMocks
    private ToDoListService toDoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.listOfToDos = new ArrayList();
        ToDoNote dummyToDo = new ToDoNote();
        listOfToDos.add(dummyToDo);
        listOfToDos.add(dummyToDo);
        listOfToDos.add(dummyToDo);
    }

    @Test
    void givenARequestToShowAllToDos_WhenDbHasNoToDosStored_ShouldReturnAListOfToDoNotesSizeZero() {
        try {
            List<ToDoNote> expectedListOfToDos = new ArrayList();
            when(toDoRepository.findAll()).thenReturn(expectedListOfToDos);
            when(env.getProperty("status.toDo.notesNotFound")).thenReturn("No Notes!!");
            List toDos = toDoListService.getToDoList();
        } catch (NoteNotFoundException e) {
            Assert.assertEquals(404, e.statusCode.value());
        }
    }

    @Test
    void givenARequestToShowAllToDos_WhenDbHasThreeToDosStored_ShouldReturnListOfThreeToDos() {
        when(toDoRepository.findAll()).thenReturn(listOfToDos);
        List toDoList = toDoListService.getToDoList();
        Assert.assertEquals(3, toDoList.size());
    }

    @Test
    void givenANewToDo_ShouldGetAddedToTheDb() {
        ToDoNote dummyTodo = new ToDoNote();
        Response response = toDoListService.addToDo(dummyTodo);
        verify(toDoRepository).save(dummyTodo);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    void givenAnIdAndAToDoNoteThatHasBeenEdited_WhenPresentInTheDb_ShouldGetUpdated() {
        Long toDoId = 1L;
        ToDoNoteUpdateDto updatedNote = new ToDoNoteUpdateDto("updated note");
        ToDoNote existingNote = new ToDoNote(1L,"exisiting message");
        when(toDoRepository.findToDoNoteByToDoId(toDoId)).thenReturn(existingNote);
        Response response = toDoListService.updateToDo(toDoId, updatedNote);
        verify(toDoRepository).save(existingNote);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    void givenAnIdAndAToDoNoteThatHasBeenEdited_WhenNotPresentInTheDb_ShouldThrowException() {
        try {
            Long toDoId = 1L;
            ToDoNoteUpdateDto updatedNote = new ToDoNoteUpdateDto("updated note");
            when(toDoRepository.findToDoNoteByToDoId(toDoId)).thenReturn(null);
            when(env.getProperty("status.toDo.noteNotFound")).thenReturn("Note Not Found!!");
            Response response = toDoListService.updateToDo(toDoId, updatedNote);
        }catch (NoteNotFoundException e){
            e.printStackTrace();
            System.out.println(e.statusCode.value());
            System.out.println(e.getMessage());
            Assert.assertEquals(404,e.statusCode.value());
        }
    }
}
