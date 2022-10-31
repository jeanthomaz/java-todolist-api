package br.com.dbc.vemser.todoapi.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ToDo {

    private Integer idToDo;

    private String title;

    private String body;

    private Done done;

}
