package com.thoughtworks.todolist;


import com.thoughtworks.todolist.service.ToDoListService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

public class ToDoListServiceTest {

    private ToDoListService toDoListService = new ToDoListService();

    @Test
    public void givenARequestToShowAllToDos_WhenDbHasNoToDosStored_ShouldReturnAListOfSizeZero() {
        List toDos = toDoListService.getToDoList();
        Assert.assertEquals(0, toDos.size());
    }
}
