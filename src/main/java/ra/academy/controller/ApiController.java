package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.Customer;
import ra.academy.model.Todo;
import ra.academy.service.CustomerService;
import ra.academy.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TodoService todoService;
    @GetMapping(value = "/customers",produces = {MediaType.APPLICATION_JSON_VALUE})// cho phép trả về json
    @ResponseBody // cho phép trả về data trong phần thân
    public List<Customer> list(){
        return customerService.findAll();
    }
    @GetMapping(value = "/todo/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Todo todo(@PathVariable int id){
        return todoService.findById(id);
    }
}
