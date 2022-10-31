package br.com.dbc.vemser.pessoaapi.controller;

import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.service.ToDoService;
import br.com.dbc.vemser.pessoaapi.dto.ToDoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ToDoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
@Validated
public class ToDoController {

    private final ToDoService toDoService;


    @Operation(summary = "listar pessoas", description = "Lista todas as pessoas do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista de pessoas"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public List<ToDoDTO> list() throws RegraDeNegocioException {
        return toDoService.list();
    }

    @Operation(summary = "listar ToDos por nome", description = "Lista um toDo do banco por nome")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a pessoa"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("{idToDo}")
    public List<ToDoDTO> listById(@PathVariable("idToDo") Integer idToDo) throws RegraDeNegocioException{
        return toDoService.listById(idToDo);
    }

    @Operation(summary = "criar nova ToDo", description = "Cria nova Todo")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Cria novo ToDo"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<ToDoDTO> create(@RequestBody @Valid ToDoCreateDTO toDo) throws RegraDeNegocioException {
        log.info("Criando ToDo . . .");

        ToDoDTO toDoDTO = toDoService.create(toDo);

        log.info("ToDo criado");
        return new ResponseEntity<>(toDoDTO, HttpStatus.OK);
    }

    @Operation(summary = "modificar ToDo por id", description = "Modifica uma TDo selecionada por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "ToDo modificada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idToDo}")
    public ResponseEntity<ToDoDTO> update(@PathVariable("idToDo") Integer id,
                                          @RequestBody @Valid ToDoCreateDTO toDoCreateDTO) throws RegraDeNegocioException {

        log.info("Atualizando ToDo . . .");

        ToDoDTO toDoDTO = toDoService.update(id, toDoCreateDTO);

        log.info("ToDo atualizada com sucesso");

        return new ResponseEntity<>(toDoDTO, HttpStatus.OK);
    }

    @Operation(summary = "deletar ToDo selecionada por id", description = "Deleta uma ToDo selecionada por id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Deleta uma ToDo selecionada"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idToDo}")
    public ResponseEntity<Void> delete(@PathVariable("idToDo") Integer id) throws Exception {

        log.info("Deletando ToDo . . .");

        toDoService.delete(id);

        log.info("ToDo deletada com sucesso");

        return ResponseEntity.noContent().build();
    }
}
