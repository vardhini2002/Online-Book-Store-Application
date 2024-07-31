package com.Bookstore.controller;

import com.Bookstore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyBookController {

    @Autowired
    private MyBookService myservice_2;
    @GetMapping("/deleteMyList/{id}")
    public String deleteMyBook(@PathVariable("id") int id){
        myservice_2.deleteById(id);
        return "redirect:/my_books";
    }

}
