package com.main.productspring.controller;

import com.main.productspring.model.Product;
import com.main.productspring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
@Autowired
    IProductService iProductService;

    @GetMapping("")
    public String showList(Model model) {
        List<Product> productList = iProductService.findAll();
        model.addAttribute("products", productList);
        return "list";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("productCreate",new Product());
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product){
        product.setId((int) (Math.random()*1000));
        iProductService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("productEdit", iProductService.findById(id));

        System.out.println(iProductService.findById(id));
        return "edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Product product) {
        iProductService.update(product.getId(), product);
        return "redirect:/product";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        model.addAttribute("productDelete", iProductService.findById(id));
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Product product, RedirectAttributes redirect) {
        iProductService.remove(product.getId());
        redirect.addFlashAttribute("success", "Removed product successful!");
        return "redirect:/product";
    }

    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("productView", iProductService.findById(id));
        return "view";
    }

    @GetMapping("/search")
    public String searchByName(@RequestParam(value = "searchName") String name, Model model) {
        List<Product> productList = iProductService.search(name);
        model.addAttribute("products", productList);
        return "list";
    }
}
