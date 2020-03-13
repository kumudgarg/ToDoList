//package com.thoughtworks.todolist.controller;
//
//import com.thoughtworks.todolist.exception.NoteNotFoundException;
//import com.thoughtworks.todolist.exception.Response;
//import com.thoughtworks.todolist.model.ToDoNote;
//import com.thoughtworks.todolist.dto.ToDoDto;
//import com.thoughtworks.todolist.service.ToDoService;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//class TodoControllerTest {
//
//    @Mock
//    private ToDoService toDoService;
//
//    @InjectMocks
//    private ToDoController controller;
//
//    private ToDoNote toDoNote;
//
//    private long toDoId;
//
//    private ToDoDto inputToDo;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        this.inputToDo = new ToDoDto();
//        this.toDoNote = new ToDoNote();
//        this.toDoId = 1L;
//
//    }
//
//    @Test
//    void givenARequestToGetAllToDoNotes_WhenAvailable_ShouldReturnListOfToDosWithStatusCode200() {
//        List<ToDoNote> toDoNoteList;
//        toDoNoteList = new ArrayList<>();
//        toDoNoteList.add(toDoNote);
//        toDoNoteList.add(toDoNote);
//        toDoNoteList.add(toDoNote);
//        when(toDoService.getToDoList()).thenReturn(toDoNoteList);
//        ResponseEntity<List<ToDoNote>> allToDoNotes = controller.getAllToDoNotes();
//        Assert.assertEquals(200,allToDoNotes.getStatusCode().value());
//    }
//
//
//    @Test
//    void givenARequestToGetAllToDoNotes_WhenNotAvailable_ShouldThrowExceptionWithStatusCode404() {
//        try {
//            when(toDoService.getToDoList()).thenThrow(new NoteNotFoundException("No Notes!!", HttpStatus.NOT_FOUND));
//            ResponseEntity<List<ToDoNote>> allToDoNotes = controller.getAllToDoNotes();
//        } catch (NoteNotFoundException e) {
//            Assert.assertEquals(404,e.statusCode.value());
//        }
//    }
//
//    @Test
//    void givenAToDoNote_WhenAddedToTheDb_ShouldReturnStatuscode200() {
//        when(toDoService.addToDo(inputToDo)).thenReturn(new Response(HttpStatus.OK.value(),"Note created successfully!!"));
//        ResponseEntity<Response> responseEntity = controller.addToDoNote(inputToDo);
//        Assert.assertEquals(200,responseEntity.getStatusCode().value());
//    }
//
//    @Test
//    void givenAnUpdatedToDoNote_WhenExistsInDb_ShouldGetStoredInTheDbAndReturnStatusCode200() {
//        ToDoDto updatedToDo = new ToDoDto();
//        when(toDoService.updateToDo(toDoId,updatedToDo)).thenReturn(new Response(HttpStatus.OK.value(),"Note created successfully!!"));
//        ResponseEntity<Response> responseEntity = controller.updateToDo(toDoId,updatedToDo);
//        Assert.assertEquals(200,responseEntity.getStatusCode().value());
//    }
//
//    @Test
//    void givenAnUpdatedToDoNote_WhenDoesNotExistsInDb_ShouldThrowAnExceptionWithStatusCode404() {
//        try {
//            ToDoDto updatedToDo = new ToDoDto();
//            when(toDoService.updateToDo(toDoId,updatedToDo)).thenThrow(new NoteNotFoundException("Note Not Found!!", HttpStatus.NOT_FOUND));
//            ResponseEntity<Response> responseEntity = controller.updateToDo(toDoId, updatedToDo);
//        } catch(NoteNotFoundException e) {
//            Assert.assertEquals(404,e.statusCode.value());
//        }
//    }
//
//    @Test
//    void givenAnIdToDeleteAToDo_whenExistsInDbAndGetsDeleted_ShouldReturnStatusCode200() {
//        when(toDoService.deleteToDo(toDoId)).thenReturn(new Response(HttpStatus.OK.value(),"Note deleted successfully!!"));
//        ResponseEntity<Response> responseEntity = controller.deleteToDoNote(toDoId);
//        Assert.assertEquals(200,responseEntity.getStatusCode().value());
//    }
//
//    @Test
//    void givenAnIdToDeleteAToDo_whenDoesNotExistInDb_ShouldReturnThrowExceptionWithStatusCode404() {
//        try {
//            when(toDoService.deleteToDo(toDoId)).thenThrow(new NoteNotFoundException("Note Not Found!!", HttpStatus.NOT_FOUND));
//            ResponseEntity<Response> responseEntity = controller.deleteToDoNote(toDoId);
//        } catch (NoteNotFoundException e) {
//            Assert.assertEquals(404,e.statusCode.value());
//        }
//    }
//}