package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.Todo;
import ra.academy.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping
    public String listTodo(Model model){
        model.addAttribute("list",todoService.findAll());
        return "listTodo";
    }
    @PostMapping
    public String doAdd(@ModelAttribute Todo todo){
        todoService.save(todo);
        return "redirect:/todo";
    }
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute Todo todo){
        todoService.save(todo);
        return "redirect:/todo";
    }

    @GetMapping("/{id}")
    public  String delete(@PathVariable int id){
        todoService.delete(id);
        return "redirect:/todo";
    }
}
