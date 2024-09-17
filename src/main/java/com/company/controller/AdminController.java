package com.company.controller;
import com.company.container.ComponentContainer;
import com.company.util.ActionUtil;
public class AdminController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = ActionUtil.getAction();
            switch (command) {
                case 1:
                    ComponentContainer.bookController.start();
                    break;
                case 2:
                    ComponentContainer.categoryController.start();
                    break;
                case 3:
                    ComponentContainer.studentProfileController.start();
                    break;
                case 4:ComponentContainer.profileController.start();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
    public void printMenu() {
        System.out.println("***Admin Menu***");
        System.out.println("1=>Book");
        System.out.println("2=>Category");
        System.out.println("3=>Student");
        System.out.println("4=>Profile");

    }
}
