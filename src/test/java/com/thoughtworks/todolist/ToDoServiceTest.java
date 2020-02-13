package com.thoughtworks.todolist;


import com.thoughtworks.todolist.exception.NoteNotFoundException;
import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoDto;
import com.thoughtworks.todolist.repository.ToDoRepository;
import com.thoughtworks.todolist.service.ToDoService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private Environment env;

    List<ToDoNote> listOfToDos;

    @InjectMocks
    private ToDoService toDoService;

    private ToDoDto dummyTodo;
    private ToDoNote toDoNote;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.dummyTodo = new ToDoDto();
        this.toDoNote = new ToDoNote();
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
            List toDos = toDoService.getToDoList();
        } catch (NoteNotFoundException e) {
            Assert.assertEquals(404, e.statusCode.value());
        }
    }

    @Test
    void givenARequestToShowAllToDos_WhenDbHasThreeToDosStored_ShouldReturnListOfThreeToDos() {
        when(toDoRepository.findAll()).thenReturn(listOfToDos);
        List toDoList = toDoService.getToDoList();
        Assert.assertEquals(3, toDoList.size());
    }

    @Test
    void givenANewToDo_ShouldGetAddedToTheDb() {
        when(mapper.map(dummyTodo,ToDoNote.class)).thenReturn(toDoNote);
        Response response = toDoService.addToDo(dummyTodo);
        verify(toDoRepository).save(toDoNote);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    void givenAnIdAndAToDoNoteThatHasBeenEdited_WhenPresentInTheDb_ShouldGetUpdated() {
        Long toDoId = 1L;
        when(toDoRepository.findToDoNoteByToDoId(toDoId)).thenReturn(toDoNote);
        Response response = toDoService.updateToDo(toDoId, dummyTodo);
        verify(toDoRepository).save(toDoNote);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    void givenAnIdAndAToDoNoteThatHasBeenEdited_WhenNotPresentInTheDb_ShouldThrowException() {
        try {
            Long toDoId = 1L;
            when(toDoRepository.findToDoNoteByToDoId(toDoId)).thenReturn(null);
            when(env.getProperty("status.toDo.noteNotFound")).thenReturn("Note Not Found!!");
            Response response = toDoService.updateToDo(toDoId, dummyTodo);
        }catch (NoteNotFoundException e){
            Assert.assertEquals(404,e.statusCode.value());
        }
    }

    @Test
    void givenAnIdOfToDoNote_WhenPresentInTheDb_ShouldGetDeleted() {
        Long toDoId = 1L;
        when(toDoRepository.findToDoNoteByToDoId(1L)).thenReturn(toDoNote);
        when(env.getProperty("status.toDo.noteDeleteSucceed")).thenReturn("Note deleted successfully!!");
        Response response = toDoService.deleteToDo(toDoId);
        verify(toDoRepository).delete(toDoNote);
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    void givenAnIdOfToDoNote_WhenNotPresentInTheDb_ShouldThrowException() {
        try {
            Long toDoId = 1L;
            when(toDoRepository.findToDoNoteByToDoId(1L)).thenReturn(null);
            when(env.getProperty("status.toDo.noteNotFound")).thenReturn("Note Not Found!!");
            Response response = toDoService.deleteToDo(toDoId);
        } catch (NoteNotFoundException e) {
            Assert.assertEquals(404, e.statusCode.value());
        }
    }
}


