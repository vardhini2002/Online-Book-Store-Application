package com.Bookstore.controller;

import com.Bookstore.entity.Book;
import com.Bookstore.entity.MyBookList;
import com.Bookstore.service.BookService;
import com.Bookstore.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class bookcontroller {
    @Autowired
    private BookService service;

    @Autowired
    private MyBookService myservice;

    @GetMapping("/")
    public String home()
    {
        return "homepage";
    }
    @GetMapping("book_register")
    public String bookRegister()
    {
        return "bookRegisterpage";
    }

    @GetMapping("available_book")
    public ModelAndView bookAvailable()
    {
        List<Book> list=service.returnbooks();
//        ModelAndView m=new ModelAndView();
//        m.setViewName("bookListpage");
//        m.addObject("book" ,list);
        return new ModelAndView("bookListpage","book",list);
    }

    @PostMapping("save")
    public String addBook(@ModelAttribute Book b)
    {
        service.save(b);
        return "redirect:/available_book";
    }

//    @GetMapping("my_books")
//    public ModelAndView my_book()
//    {
//            List<Book> list=service.returnbooks();
//            ModelAndView m=new ModelAndView();
//           m.setViewName("bookListpage");
//           m.addObject("book" ,list);
//           return "MyBooks";
//    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model)
    {
        List<MyBookList>list=myservice.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBookiee";
    }


//    @GetMapping("/bookielist/{id}")
//    public String getMyList(@PathVariable("id") int id){
//        System.out.println("Received ID: tgvhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh" +
//                "/n hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh " + id); // Debugging line
//        Book b=service.getBookById(id);
//        MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
//        myservice.saveMyBooks(mb);
//        return "redirect:/MyBookiee";
//    }

    ////YOU CAN RETURN A HTML PAGE BUT NOT REDIRECT IT CAN ONLY REDIRECT URI

    @GetMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        System.out.println("Received ID: " + id); // Debugging line
        Book b = service.getBookById(id);
        if (b != null) {
            MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
            myservice.saveMyBooks(mb);
        } else {
            System.out.println("Book not found with ID: " + id); // Debugging line
        }
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model)
    {
        Book b= service.getBookById(id);
        model.addAttribute("edit",b);
        return "bookEditpage";
    }

    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id)
    {
        service.deleteById(id);
        return "redirect:/available_book";
    }


}
