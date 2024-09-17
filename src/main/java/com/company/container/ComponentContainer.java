package com.company.container;
import com.company.controller.*;
import com.company.repository.*;
import com.company.service.*;

import java.util.Scanner;

public class ComponentContainer {
    public static int currentProfileId;
    public static Scanner scannerForStr =  new Scanner(System.in);
    public static Scanner scannerForDigit = new Scanner(System.in);

    public static MainController mainController = new MainController();
    public static AdminController adminController = new AdminController();
    public static CategoryController categoryController = new CategoryController();
    public static BookController bookController = new BookController();
    public static StudentController studentController = new StudentController();
    public static ProfileController profileController = new ProfileController();
    public static StudentProfileController studentProfileController = new StudentProfileController();
    

    public static InitService initService = new InitService();
    public static AuthService authService = new AuthService();
    public static CategoryService categoryService = new CategoryService();
    public static BookService bookService = new BookService();
    public static ProfileService profileService = new ProfileService();
    public static StudentBookService studentBookService = new StudentBookService();


    public static TableRepository tableRepository = new TableRepository();
    public static ProfileRepository profileRepository = new ProfileRepository();
    public static CategoryRepository categoryRepository = new CategoryRepository();
    public static BookRepository bookRepository = new BookRepository();
    public static StudentBookRepository studentBookRepository = new StudentBookRepository();
}
