package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.BookDTO;
import com.company.dto.BookForResultDTO;
import com.company.dto.CategoryDTO;
import com.company.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookService {
    public Boolean addBook(BookDTO bookDTO, String date) {
        if(!ValidationUtil.checkBook(bookDTO)) {
            return false;
        }
        if(!ValidationUtil.checkDate(date)) {
            return false;
        }
        CategoryDTO categoryDTO = ComponentContainer.categoryRepository.getCategoryById(bookDTO.getCategory_id());
        if(categoryDTO == null) {
            return false;
        }
        LocalDate localDate = LocalDate.parse(date);
        bookDTO.setPublish_date(localDate);
        bookDTO.setVisible(true);
        bookDTO.setCreatedAt(LocalDateTime.now());
        return ComponentContainer.bookRepository.addBook(bookDTO);
    }

    public List<BookForResultDTO> bookLists() {
       var list = ComponentContainer.bookRepository.bookLists();
       if(list.isEmpty()) {
           return null;
       }
       return list;
    }

    public List<BookForResultDTO> search(String query) {
       return ComponentContainer.bookRepository.search(query);
    }

    public Boolean delete(Integer id) {
        BookForResultDTO dto = ComponentContainer.bookRepository.getBookById(id);
        if(dto == null) {
            return false;
        }
        return ComponentContainer.bookRepository.delete(id);
    }
}
