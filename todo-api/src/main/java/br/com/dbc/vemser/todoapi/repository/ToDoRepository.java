package br.com.dbc.vemser.todoapi.repository;

import br.com.dbc.vemser.todoapi.entity.Done;
import br.com.dbc.vemser.todoapi.entity.ToDo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class ToDoRepository {
    private static List<ToDo> listaToDo = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ToDoRepository() {
        }
    @PostConstruct
    public void init(){
        listaToDo.add(new ToDo(COUNTER.incrementAndGet() /*1*/, "ExemploTitulo", "ExemploCorpo", Done.F));
    }

    public ToDo create(ToDo toDo) {
        toDo.setIdToDo(COUNTER.incrementAndGet());
        listaToDo.add(toDo);
        return toDo;
    }

    public List<ToDo> list() {
        return listaToDo;
    }

    public void delete(ToDo toDo) throws Exception {
        listaToDo.remove(toDo);
    }

    public List<ToDo> listById(Integer idPessoa) {
        return listaToDo.stream()
                .filter(toDo -> toDo.getIdToDo().equals(idPessoa))
                .collect(Collectors.toList());
    }
}
