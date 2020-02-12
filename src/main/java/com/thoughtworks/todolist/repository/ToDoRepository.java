package com.thoughtworks.todolist.repository;

import com.thoughtworks.todolist.model.ToDoNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoNote,Long> {

    public ToDoNote findToDoNoteByToDoId(Long id);

}
