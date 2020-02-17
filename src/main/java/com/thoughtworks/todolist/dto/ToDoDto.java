package com.thoughtworks.todolist.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@ApiModel
public class ToDoDto {

    @ApiModelProperty(required = true)
    @Getter @Setter
    String description;


    @JsonIgnore
    @Getter @Setter
    public LocalDateTime timestamp;


    @Getter @Setter
    boolean isCompleted;

    public ToDoDto() {
    }

    public ToDoDto(String description) {
        this.description = description;
    }
}
