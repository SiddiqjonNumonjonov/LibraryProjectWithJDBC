package com.company.controller;
import com.company.container.ComponentContainer;
import com.company.dto.ProfileDTO;
import com.company.util.ActionUtil;

public class MainController {
    public void start() {
        ComponentContainer.tableRepository.createTable();
        ComponentContainer.initService.init();
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
                    break;
                case 4:
                    login();
                    break;
                case 5:
                    registration();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.err.println("Invalid command");
            }
        }
    }
    public void printMenu() {
        System.out.println("****Main Menu****");
        System.out.println("1=>Book lists");
        System.out.println("2=>Book search");
        System.out.println("3=>By Category");
        System.out.println("4=>Login");
        System.out.println("5=>Registration");
        System.out.println("0=>exit");
    }
    public void login() {
        System.out.println("enter your login : ");
        String login = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter your password : ");
        String password = ComponentContainer.scannerForStr.nextLine();
        ComponentContainer.authService.login(login, password);
    }
    public void registration() {
        System.out.println("enter your name :");
        String name = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter your surname : ");
        String surname = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter your phone number : ");
        String phone = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter your login : ");
        String login = ComponentContainer.scannerForStr.nextLine();
        System.out.println("enter your password : ");
        String password = ComponentContainer.scannerForStr.nextLine();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(name);
        profileDTO.setSurname(surname);
        profileDTO.setPhoneNumber(phone);
        profileDTO.setLogin(login);
        profileDTO.setPassword(password);
       boolean isRegistered = ComponentContainer.authService.registration(profileDTO);
       if(isRegistered) {
           System.out.println("register success");
       }else {
           System.out.println("register failed");
       }
    }
}
