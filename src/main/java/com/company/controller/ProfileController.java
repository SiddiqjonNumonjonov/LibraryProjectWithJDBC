package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.util.ActionUtil;

public class ProfileController {
    public void start() {
        boolean loop = true;
        while (loop) {
            printMenu();
            int command = ActionUtil.getAction();
            switch (command) {
                case 1:
                    profileLists();
                    break;
                case 2:
                    searchProfile();
                    break;
                case 3:
                    changeProfileStatus();
                    break;
                case 4:
                    addProfile();
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    System.err.println("Invalid command");
            }
        }
    }
    public void changeProfileStatus() {
        System.out.println("enter id : ");
        Integer id = ComponentContainer.scannerForStr.nextInt();
        var isChanged = ComponentContainer.profileService.changeStatus(id);
        if(isChanged) {
            System.out.println("profile status changed successfully");
        }else {
            System.out.println("profile status change failed");
        }
    }
    public void searchProfile() {
        System.out.println("enter query : ");
        String query = ComponentContainer.scannerForStr.nextLine();
        var profiles  = ComponentContainer.profileService.search(query);
        if (profiles.isEmpty()) {
            System.out.println("No profiles found");
        }else {
            profiles.forEach(System.out::println);
        }
    }
    public void profileLists() {
       var profiles = ComponentContainer.profileService.allProfiles();
       if(profiles.isEmpty()) {
           System.out.println("No profiles found");
       }else {
           profiles.forEach(System.out::println);
       }
    }
    public void printMenu() {
        System.out.println("***Profile Menu***");
        System.out.println("1=>Profile lists");
        System.out.println("2=>Search Profile");
        System.out.println("3=>Change profile status");
        System.out.println("4=>Add profile");
        System.out.println("0=>exit");

    }
    public void addProfile() {
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
        System.out.println("enter role : ");
        String role = ComponentContainer.scannerForStr.nextLine().toUpperCase();

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setName(name);
        profileDTO.setSurname(surname);
        profileDTO.setPhoneNumber(phone);
        profileDTO.setLogin(login);
        profileDTO.setPassword(password);
        profileDTO.setRole(ProfileRole.valueOf(role));
        var isAdded = ComponentContainer.profileService.add(profileDTO);
        if(isAdded) {
            System.out.println("added successfully");
        }else {
            System.out.println("failed to add");
        }

    }
}
/*
*** Profile *** (only for admin)
1. Profile list:
		id, name, surname, login, phone, status, role, createdDate
2. Search profile
		Enter query: (id,name,surname,login,phone)
		id, name, surname, login, phone, status, createdDate
3. Change profile status:
		Enter id;
4. Profile add:
        Enter name:
        Enter surname;
        Enter login:
        Enter password:
        Enter phone:
        Enter role:
0. Exist
 */