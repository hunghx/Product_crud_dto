package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","Hồ xuân Hùng");
        return "hello";
    }
    // tham số uri
    @GetMapping("/param/{id}/{name}") // ex... khớp với /home/param/5
    public String getUri(@PathVariable String id,@PathVariable String name, Model model){
        model.addAttribute("uri","/home/param/"+id+"/"+name);
        return "home";
    }
    // biểu thức chính qui cho đường dẫn
    @GetMapping("/pattern/{name:\\w{4}}")
    public String pattern(@PathVariable String name,Model model){
        model.addAttribute("uri","/home/param/"+name);
        return "redirect:home/hello"; // chuyển hướng theo đường dẫn
        // trả về helloo với thuộc tính name : hồ xuân hùng
    }
}
