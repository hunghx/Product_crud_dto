package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ra.academy.dto.request.ProductRequest;
import ra.academy.model.Product;
import ra.academy.service.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping
    public  String getAll(Model model){
        model.addAttribute("list",productService.findAll());
        return "list-product";
    }
    @GetMapping("/add")
    public  String add(){
        return "form-add";
    }
    // thêm mới
    @PostMapping
    public String doAdd( @ModelAttribute ProductRequest productRequest){
        productService.save(productRequest);
        return "redirect:/product"  ; // chuyển hướng theo đường dẫn
    }
    @PostMapping("/update")
    public String doAdd(@ModelAttribute Product product
            , @RequestParam MultipartFile image){
        productService.update(product,image);
        return "redirect:/product"  ; // chuyển hướng theo đường dẫn
    }
    @GetMapping("/delete/{id}") // uri
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/product";
    }
    @GetMapping("/edit/{id}") // uri
    public ModelAndView edit(@PathVariable Long id){
        return new ModelAndView("edit-product",
                "pro",productService.findById(id));
    }


}
