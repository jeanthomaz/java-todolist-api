package br.com.dbc.vemser.todoapi.dto;

import br.com.dbc.vemser.todoapi.entity.Done;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ToDoCreateDTO {

    @NotBlank
    @Schema(description = "Titulo do ToDo")
    private String title;

    @Schema(description = "Corpo do Todo")
    private String body;

    @NotNull
    @Schema(description = "Done or not")
    private Done done;

}
