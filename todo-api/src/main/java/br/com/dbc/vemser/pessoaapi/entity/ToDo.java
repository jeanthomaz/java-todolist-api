package br.com.dbc.vemser.pessoaapi.entity;

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
