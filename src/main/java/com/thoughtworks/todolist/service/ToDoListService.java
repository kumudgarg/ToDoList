package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.exception.NoteNotFoundException;
import com.thoughtworks.todolist.exception.Response;
import com.thoughtworks.todolist.model.ToDoNote;
import com.thoughtworks.todolist.model.ToDoNoteUpdateDto;
import com.thoughtworks.todolist.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    @Autowired
    private Environment environment;

    @Autowired
    private ToDoRepository toDoRepository;

    public List<ToDoNote> getToDoList() {
        List<ToDoNote> toDoNotes = toDoRepository.findAll();
        if(toDoNotes.isEmpty()) {
            throw new NoteNotFoundException(environment.getProperty("status.toDo.notesNotFound"),HttpStatus.NOT_FOUND);
        }
        return toDoNotes;
    }

    public Response addToDo(ToDoNote toDo) {
        toDoRepository.save(toDo);
        return new Response(HttpStatus.OK.value(),environment.getProperty("status.toDo.noteAddSucceed"));
    }

    public Response updateToDo(Long id, ToDoNoteUpdateDto updatedToDoNote) {
        ToDoNote toDoNote = toDoRepository.findToDoNoteByToDoId(id);
        if(toDoNote == null){
            throw new NoteNotFoundException(environment.getProperty("status.toDo.noteNotFound"),HttpStatus.NOT_FOUND);
        }
        toDoNote.setToDoDescription(updatedToDoNote.getToDoDescription());
        toDoRepository.save(toDoNote);
        return new Response(HttpStatus.OK.value(),environment.getProperty("status.toDo.noteUpdatedSucceed"));
    }


}
