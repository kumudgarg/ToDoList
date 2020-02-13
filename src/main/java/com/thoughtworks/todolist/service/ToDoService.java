package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.exception.NoteNotFoundException;
import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoDto;
import com.thoughtworks.todolist.repository.ToDoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {

    @Autowired
    private Environment environment;

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<ToDoNote> getToDoList() {
        List<ToDoNote> toDoNotes = toDoRepository.findAll();
        if(toDoNotes.isEmpty()) {
            throw new NoteNotFoundException(environment.getProperty("status.toDo.notesNotFound"),HttpStatus.NOT_FOUND);
        }
        return toDoNotes;
    }

    public Response addToDo(ToDoDto toDoDto) {
        ToDoNote toDoNote = mapper.map(toDoDto, ToDoNote.class);
        toDoRepository.save(toDoNote);
        return new Response(HttpStatus.OK.value(),environment.getProperty("status.toDo.noteAddSucceed"));
    }

    public Response updateToDo(Long id, ToDoDto updatedToDoNote) {
        ToDoNote toDoNote = toDoRepository.findToDoNoteByToDoId(id);
        if(toDoNote == null){
            throw new NoteNotFoundException(environment.getProperty("status.toDo.noteNotFound"),HttpStatus.NOT_FOUND);
        }
        toDoNote.setToDoDescription(updatedToDoNote.getToDoDescription());
        toDoRepository.save(toDoNote);
        return new Response(HttpStatus.OK.value(),environment.getProperty("status.toDo.noteUpdatedSucceed"));
    }


    public Response deleteToDo(Long toDoId) {
        ToDoNote toDoNote = toDoRepository.findToDoNoteByToDoId(toDoId);
        if(toDoNote == null){
            throw new NoteNotFoundException(environment.getProperty("status.toDo.noteNotFound"),HttpStatus.NOT_FOUND);
        }
        toDoRepository.delete(toDoNote);
        return new Response(HttpStatus.OK.value(),environment.getProperty("status.toDo.noteDeleteSucceed"));
    }
}
