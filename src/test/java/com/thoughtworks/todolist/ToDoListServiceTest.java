package com.thoughtworks.todolist;


import com.thoughtworks.todolist.repository.ToDoRepository;
import com.thoughtworks.todolist.service.ToDoListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToDoListServiceTest {

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoListService toDoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenARequestToShowAllToDos_WhenDbHasNoToDosStored_ShouldReturnAListOfToDoNotesSizeZero() {
        List<Object> expectedListOfToDos = new ArrayList();
        when(toDoRepository.findAll()).thenReturn(expectedListOfToDos);
        List toDos = toDoListService.getToDoList();
        Assert.assertEquals(0, toDos.size());
    }

    @Test
    public void givenARequestToShowAllToDos_WhenDbHasThreeToDosStored_ShouldReturnListOfThreeToDos() {
        List<Object> expectedListOfToDos = new ArrayList();
        Object dummyObject = new Object();
        expectedListOfToDos.add(dummyObject);
        expectedListOfToDos.add(dummyObject);
        expectedListOfToDos.add(dummyObject);
        when(toDoRepository.findAll()).thenReturn(expectedListOfToDos);
        List toDoList = toDoListService.getToDoList();
        Assert.assertEquals(3,toDoList.size());
    }
}
