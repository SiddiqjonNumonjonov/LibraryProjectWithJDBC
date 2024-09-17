package com.company.dto;

import com.company.enums.StudentBookStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StudentBookDTO {
    private int id;
    private int studentId;
    private int bookId;
    private LocalDateTime createdAt;
    private LocalDate returnedDate;
    private StudentBookStatus status;

    public StudentBookStatus getStatus() {
        return status;
    }

    public void setStatus(StudentBookStatus status) {
        this.status = status;
    }

    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentBookDTO{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", bookId=" + bookId +
                ", createdAt=" + createdAt +
                ", returnedDate=" + returnedDate +
                ", status=" + status +
                '}';
    }
}
// id, studentId, bookId, createdDate, Status(TOOK,RETURNED),note