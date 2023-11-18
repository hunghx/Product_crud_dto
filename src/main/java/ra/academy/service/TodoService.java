package ra.academy.service;

import org.springframework.stereotype.Service;
import ra.academy.model.Todo;

import java.util.ArrayList;
import java.util.List;
@Service
public class TodoService {
    private List<Todo> list = new ArrayList<>();
    public List<Todo> findAll(){
        return list;
    }
    public Todo findById(int id){
        return list.stream().filter(t->t.getId()==id).findFirst().orElse(null);
    }
    public void save(Todo todo){
        if (findById(todo.getId())==null){
            // thêm mới
            list.add(todo);
        }else {
            list.set(list.indexOf(findById(todo.getId())),todo);
        }
    }
    public  void delete(int id){
        list.remove(findById(id));
    }
}
