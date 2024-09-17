package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.util.ActionUtil;

public class StudentController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = ActionUtil.getAction();
            switch (command) {
                case 1:
                    ComponentContainer.bookController.bookLists();
                    break;
                case 2:
                    ComponentContainer.bookController.searchBook();
                    break;
                case 3:
                    takeBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    booksOnHand();
                    break;
                case 6:
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    private void booksOnHand() {
        var list = ComponentContainer.studentBookService.booksOnHand();
        if(list.isEmpty()) {
            System.out.println("no books on hand");
        }else {
            list.forEach(System.out::println);
        }
    }

    public void returnBook() {
        System.out.println("enter book id : ");
        int bookId = ComponentContainer.scannerForDigit.nextInt();
       var isReturned = ComponentContainer.studentBookService.
               returnBook(bookId,ComponentContainer.currentProfileId);

       if(isReturned) {
           System.out.println("book returned");
       }else {
           System.out.println("book not returned");
       }
    }
    public void takeBook() {
        System.out.println("enter book id : ");
        int bookId = ComponentContainer.scannerForDigit.nextInt();

        var isTaken = ComponentContainer.studentBookService.takeBook(bookId,ComponentContainer.currentProfileId);
        if(isTaken) {
            System.out.println("added successfully");
        }else {
            System.out.println("not added successfully");
        }
    }
    public void printMenu() {
        System.out.println("***Student Menu***");
        System.out.println("1=>Book Lists");
        System.out.println("2=>Search book");
        System.out.println("3=>Take book");
        System.out.println("4=>Return book");
        System.out.println("5=>Books on hand");
        System.out.println("6=>Take book history");
        System.out.println("0=>exit");

    }
}
