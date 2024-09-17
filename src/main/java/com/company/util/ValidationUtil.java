package com.company.util;
import com.company.dto.BookDTO;
import com.company.dto.ProfileDTO;
public class ValidationUtil {
    public static Boolean checkProfile(ProfileDTO profile) {
        if(profile.getName() == null || profile.getName().isBlank() || profile.getName().trim().length() < 3) {
            return false;
        }
        if(profile.getSurname() == null || profile.getSurname().isBlank() || profile.getSurname().trim().length() < 3) {
            return false;
        }
        if(profile.getLogin() == null || profile.getLogin().isBlank() || profile.getLogin().trim().length() < 3) {
            return false;
        }
        if(profile.getPassword() == null || profile.getPassword().isBlank() || profile.getPassword().trim().length() < 5) {
            return false;
        }
        if(profile.getPhoneNumber() == null || profile.getPhoneNumber().isBlank() ||
                profile.getPhoneNumber().trim().length() != 13 || ! profile.getPhoneNumber().startsWith("+998")
              || !checkPhone(profile.getPhoneNumber()) ) {
            return false;
        }
        return true;
    }
    private static Boolean checkPhone(String phoneNumber) {
        char [] chars = phoneNumber.toCharArray();
        for (int i = 1; i <chars.length ; i++) {
            if(!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }
    public static Boolean checkBook(BookDTO book) {
        if(book.getTitle() == null || book.getTitle().isBlank() || book.getTitle().trim().length() < 3) {
            return false;
        }
        if(book.getAuthor() == null || book.getAuthor().isBlank() || book.getAuthor().trim().length() < 3) {
            return false;
        }
        if(book.getAvailableDays() < 0) {
            return false;
        }
        return true;

    }
    public static Boolean checkDate(String date) {
        String [] dates = date.split("-");
        if(dates.length != 3) {
            return false;
        }
        if(dates[0].length() != 4 || !isNumeric(dates[0])) {
            return false;
        }
        if(dates[1].length() != 2 || !isNumeric(dates[1])) {
            return false;
        }
        if(dates[2].length() != 2 || !isNumeric(dates[2])) {
            return false;
        }
        return true;

    }
    public static Boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
