package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.dto.BookDTO;
import com.company.service.BookService;
import com.company.util.ActionUtil;

import java.util.List;

public class BookController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = ActionUtil.getAction();
            switch (command) {
                case 1:
                    bookLists();
                    break;
                case 2:
                    searchBook();
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    removeBook();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.err.println("invalid command");
            }
        }
    }
    public void removeBook() {
        System.out.println("enter id : ");
        Integer id = ComponentContainer.scannerForStr.nextInt();

       var isDeleted = ComponentContainer.bookService.delete(id);
       if(isDeleted) {
           System.out.println("book deleted successfully");
       }else {
           System.out.println("book could not be deleted");
       }
    }

    private void addBook() {
        System.out.println("enter the book title :");
        String title = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter the book author :");
        String author = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter the category id : ");
        int categoryId = ComponentContainer.scannerForDigit.nextInt();
        System.out.println("enter the published Date : ");
        String date = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter available days : ");
        int availableDays = ComponentContainer.scannerForDigit.nextInt();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
        bookDTO.setCategory_id(categoryId);
        bookDTO.setAvailableDays(availableDays);
        var isAdded = ComponentContainer.bookService.addBook(bookDTO,date);
        if(isAdded) {
            System.out.println("book added successfully");
        }else {
            System.out.println("book not added");
        }
    }

    public void printMenu() {
        System.out.println("***Book Menu****");
        System.out.println("1=>Book lists");
        System.out.println("2=>Search book");
        System.out.println("3=>Add book");
        System.out.println("4=>Remove book");
        System.out.println("5=>Books on hand");
        System.out.println("6=>Best books");
        System.out.println("0=>exit");
    }
    public void bookLists() {
       var list = ComponentContainer.bookService.bookLists();
       if(list == null) {
           System.out.println("no books found");
       }else {
           list.forEach(System.out::println);
       }
    }

    public void searchBook() {
        System.out.println("enter query : ");
        String query = ComponentContainer.scannerForStr.nextLine();

       var list = ComponentContainer.bookService.search(query);
       if(list.isEmpty()) {
           System.out.println("no books found");
       }else {
           list.forEach(System.out::println);
       }
    }
}
// id, title, author, category_id, publishDate, availableDay, visible, createdDate