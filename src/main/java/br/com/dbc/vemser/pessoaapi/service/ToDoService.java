package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.entity.ToDo;
import br.com.dbc.vemser.pessoaapi.exception.RegraDeNegocioException;
import br.com.dbc.vemser.pessoaapi.repository.ToDoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.dbc.vemser.pessoaapi.dto.ToDoCreateDTO;
import br.com.dbc.vemser.pessoaapi.dto.ToDoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ObjectMapper objectMapper;

    public ToDoDTO create(ToDoCreateDTO toDo) throws RegraDeNegocioException{

        ToDo toDoEntity = objectMapper.convertValue(toDo, ToDo.class);

        ToDoDTO toDoDTO = objectMapper.convertValue(toDoRepository.create(toDoEntity), ToDoDTO.class);

        return toDoDTO;
    }

    public List<ToDoDTO> list(){
        return toDoRepository.list().stream()
                .map(toDo -> objectMapper.convertValue(toDo, ToDoDTO.class))
                .toList();
    }


    public ToDo findById(Integer id) throws RegraDeNegocioException {
        ToDo toDoRecuperada = toDoRepository.list().stream()
                .filter(toDo -> toDo.getIdToDo().equals(id))
                .findFirst()
                .orElseThrow(()  -> new RegraDeNegocioException("Pessoa não encontrada"));
        return toDoRecuperada;
    }

    public ToDoDTO update(Integer id, ToDoCreateDTO toDoAtualizar) throws RegraDeNegocioException {

        ToDo toDoRecuperada = findById(id);

        ToDo toDoEntity = objectMapper.convertValue(toDoAtualizar, ToDo.class);

        toDoRecuperada.setTitle(toDoAtualizar.getTitle());
        toDoRecuperada.setBody(toDoAtualizar.getBody());
        toDoRecuperada.setDone(toDoAtualizar.getDone());
        ToDoDTO toDoDTO = objectMapper.convertValue(toDoEntity, ToDoDTO.class);

        return toDoDTO;
    }
    public void delete(Integer id) throws Exception {
        ToDo toDoDeletada = findById(id);
        ToDoDTO toDoDTO = objectMapper.convertValue(toDoDeletada, ToDoDTO.class);
        toDoRepository.delete(toDoDeletada);

    }

    public List<ToDoDTO> listById(Integer idPessoa) {
        return toDoRepository.listById(idPessoa).stream()
                .map(toDo -> objectMapper.convertValue(toDo, ToDoDTO.class))
                .toList();
    }

}
