///*
//package com.thoughtworks.todolist.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thoughtworks.todolist.dto.ToDoDto;
//import com.thoughtworks.todolist.model.ToDoNote;
//import com.thoughtworks.todolist.service.ToDoService;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@RunWith(SpringRunner.class)
//public class ToDoControllerReTest {
//
//    private MockMvc mvc ;
//
//
//    @Mock
//    private ToDoService toDoService;
//    @InjectMocks
//    private ToDoController toDoController;
//
//    private ToDoNote toDoNote;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(toDoController).build();
//    }
//
//    @Test
//     public void getAllToDo() throws Exception {
//        List<ToDoNote> toDoNoteList;
//        toDoNoteList = new ArrayList<>();
//        toDoNoteList.add(toDoNote);
//       toDoNoteList.add(toDoNote);
//       toDoNoteList.add(toDoNote);
//
//
//        MvcResult mvcResult = mvc.perform(get("/toDo/").contentType(MediaType.APPLICATION_JSON)).andReturn();
//        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
//
//
//    }
//
//    @Test
//    public void createToDo() throws Exception {
//
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
//                .post("/toDo/add")
//                .content(asJsonString(new ToDoNote("Apple")))
//                .contentType(MediaType.APPLICATION_JSON)).andReturn();
//        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);
//    }
//
//    @Test
//    public void updateToDo() throws Exception {
//
//         mvc.perform(MockMvcRequestBuilders
//                .put("/toDo/update/{id}",1)
//                .content(asJsonString(new ToDoNote(1,"Mango")))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Mango"));
//
//    }
//
//
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//*/
