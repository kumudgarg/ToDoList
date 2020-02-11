package com.thoughtworks.todolist;


import com.thoughtworks.todolist.repository.ToDoRepository;
import com.thoughtworks.todolist.service.ToDoListService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ToDoListServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    List<Object> listOfToDos;

    @InjectMocks
    private ToDoListService toDoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.listOfToDos = new ArrayList();
        Object dummyToDo = new Object();
        listOfToDos.add(dummyToDo);
        listOfToDos.add(dummyToDo);
        listOfToDos.add(dummyToDo);
    }

    @Test
    void givenARequestToShowAllToDos_WhenDbHasNoToDosStored_ShouldReturnAListOfToDoNotesSizeZero() {
        List<Object> expectedListOfToDos = new ArrayList();
        when(toDoRepository.findAll()).thenReturn(expectedListOfToDos);
        List toDos = toDoListService.getToDoList();
        Assert.assertEquals(0, toDos.size());
    }

    @Test
    void givenARequestToShowAllToDos_WhenDbHasThreeToDosStored_ShouldReturnListOfThreeToDos() {
        when(toDoRepository.findAll()).thenReturn(listOfToDos);
        List toDoList = toDoListService.getToDoList();
        Assert.assertEquals(3,toDoList.size());
    }

    @Test
    void givenANewToDo_ShouldGetAddedToTheDb() {
        Object dummyTodo = new Object();
        toDoListService.addToDo(dummyTodo);
        verify(toDoRepository).save(dummyTodo);
    }

    @Test
    void givenMultipleToDos_ShouldGetAddedToTheDb() {
        toDoListService.addToDo(listOfToDos);
        verify(toDoRepository).save(listOfToDos);
    }
}
