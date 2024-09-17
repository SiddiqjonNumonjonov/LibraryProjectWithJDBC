package com.company.service;

import com.company.container.ComponentContainer;
import com.company.dto.*;
import com.company.enums.ProfileRole;
import com.company.enums.StudentBookStatus;

import java.time.LocalDateTime;
import java.util.List;

public class StudentBookService {
    public Boolean takeBook(int bookId, int currentProfileId) {
        BookForResultDTO bookForResultDTO = ComponentContainer.bookRepository.getBookById(bookId);
        if(bookForResultDTO == null) {
            System.out.println("no such book");
            return false;
        }

        StudentBookDTO studentBookDTO = new StudentBookDTO();
        studentBookDTO.setBookId(bookId);
        studentBookDTO.setStudentId(currentProfileId);
        studentBookDTO.setStatus(StudentBookStatus.TOOK);
        studentBookDTO.setCreatedAt(LocalDateTime.now());
        return ComponentContainer.studentBookRepository.takeBook(studentBookDTO);
    }

    public Boolean returnBook(int bookId, int currentProfileId) {
        StudentBookDTO studentBookDTO = ComponentContainer.studentBookRepository.
                getByBookIdAndStudentId(bookId,currentProfileId);
        if(studentBookDTO == null || studentBookDTO.getStatus().equals(StudentBookStatus.RETURNED)) {
            System.out.println("something went wrong");
            return false;
        }

        return ComponentContainer.studentBookRepository.returnBook(bookId,currentProfileId);
    }

    public List<StudentBookDTO> booksOnHand() {
        return ComponentContainer.studentBookRepository.booksOnHand(ComponentContainer.currentProfileId);
    }
}
