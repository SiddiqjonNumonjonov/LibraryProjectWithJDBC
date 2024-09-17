package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.util.ActionUtil;

public class CategoryController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = ActionUtil.getAction();
            switch (command) {
                case 1:
                    categoryLists();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    addCategory();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.out.println("invalid command");
            }
        }
    }

    private void categoryLists() {
        var list = ComponentContainer.categoryService.getCategoryLists();
        if (list == null) {
            System.out.println("No category found");
        }else {
            for (var category : list) {
                System.out.println(category);
            }
        }
    }
    public void printMenu() {
        System.out.println("***Category Menu***");
        System.out.println("1=>Category lists");
        System.out.println("2=>Delete Category");
        System.out.println("3=>Add Category");
        System.out.println("0=>exit");
    }
    public void addCategory() {
        System.out.println("Enter Category name");
        String name = ComponentContainer.scannerForStr.nextLine().trim();
        var isAdded = ComponentContainer.categoryService.add(name);
        if (isAdded) {
            System.out.println("Category added");
        }else {
            System.out.println("Category not added");
        }
    }
    public void delete() {
        System.out.println("enter category id : ");
        Integer id = ComponentContainer.scannerForDigit.nextInt();
       var isDeleted = ComponentContainer.categoryService.delete(id);
       if(isDeleted) {
           System.out.println("Category deleted");
       }else {
           System.out.println("Category not deleted");
       }
    }
}