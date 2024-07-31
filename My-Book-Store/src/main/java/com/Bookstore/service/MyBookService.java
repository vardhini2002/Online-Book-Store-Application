package com.Bookstore.service;

import com.Bookstore.entity.MyBookList;
import com.Bookstore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookService {
    @Autowired
    private MyBookRepository mybRepo;

    public void saveMyBooks(MyBookList b_save){
        mybRepo.save(b_save);
    }

    public List<MyBookList> getAllMyBooks()
    {
        return mybRepo.findAll();
    }

    public void deleteById(int id){
        mybRepo.deleteById(id);
    }
}
