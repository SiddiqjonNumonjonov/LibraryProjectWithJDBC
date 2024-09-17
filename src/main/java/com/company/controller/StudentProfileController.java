package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.enums.ProfileRole;
import com.company.util.ActionUtil;

public class StudentProfileController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = ActionUtil.getAction();
            switch (command) {
                case 1:
                    studentLists();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    changeStatus();
                    break;
                case 4:
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.err.println("invalid command");
            }
        }
    }
    public void printMenu() {
        System.out.println("***Student Profile Menu***");
        System.out.println("1=>Student lists");
        System.out.println("2=>Search student");
        System.out.println("3=>Change status");
        System.out.println("4=>Student by Book");
        System.out.println("0=>exit");
    }
    public void  search() {
        String value = ComponentContainer.scannerForStr.nextLine();
        ComponentContainer.profileService.searchStudent(value);
    }
    public void studentLists() {
       var list = ComponentContainer.profileService.getAllStudents(ProfileRole.STUDENT);
       if(list.isEmpty()) {
           System.out.println("No students found");
       }else {
           list.forEach(System.out::println);
       }
    }
    public void changeStatus() {
        System.out.println("enter id : ");
        int id = ComponentContainer.scannerForDigit.nextInt();
        ComponentContainer.profileService.changeStatus(id);
    }
}
/*
*** StudentProfile ***
1. Student list:
		id, name, surname, login, phone, status, createdDate
2. Search student
		Enter query: (id,name,surname,login,phone)
		id, name, surname, login, phone, status, createdDate
3. Block student:
		Enter id;
4. Activate student:
		Enter id:
5. Student by book:
		id, name, surname, login, phone, status,  takenBookCount, BookOnHand


 */