package com.company.repository;

import com.company.dto.StudentBookDTO;
import com.company.enums.StudentBookStatus;
import com.company.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentBookRepository {

    public Boolean takeBook(StudentBookDTO studentBookDTO) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into studentBook(student_id,book_id,status,note,created_at,returned_date)" +
                " values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, studentBookDTO.getStudentId());
            preparedStatement.setInt(2, studentBookDTO.getBookId());
            preparedStatement.setString(3, String.valueOf(studentBookDTO.getStatus()));
            preparedStatement.setString(4,null);
            preparedStatement.setTimestamp(5, Timestamp.valueOf(studentBookDTO.getCreatedAt()));
            preparedStatement.setDate(6, null);
           int isTaken =  preparedStatement.executeUpdate();
           return isTaken != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public StudentBookDTO getByBookIdAndStudentId(int bookId, int currentProfileId) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from studentBook where book_id=? and student_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, currentProfileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                StudentBookDTO studentBookDTO = new StudentBookDTO();
                studentBookDTO.setStudentId(resultSet.getInt("student_id"));
                studentBookDTO.setBookId(resultSet.getInt("book_id"));
                studentBookDTO.setStatus(StudentBookStatus.valueOf(resultSet.getString("status")));
                studentBookDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                return studentBookDTO;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean returnBook(int bookId, int currentProfileId) {
        Connection connection = ConnectionUtil.getConnection();
        String sql  = "update studentBook set status = 'RETURNED' where book_id=? and student_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, currentProfileId);
            int isReturned = preparedStatement.executeUpdate();
            return isReturned != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<StudentBookDTO> booksOnHand(int currentProfileId) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from studentBook where student_id = ? and status = 'TOOK'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, currentProfileId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<StudentBookDTO> studentBookDTOList = new ArrayList<>();
            while(resultSet.next()) {
                StudentBookDTO studentBookDTO = new StudentBookDTO();
                studentBookDTO.setStudentId(resultSet.getInt("student_id"));
                studentBookDTO.setBookId(resultSet.getInt("book_id"));
                studentBookDTO.setStatus(StudentBookStatus.valueOf(resultSet.getString("status")));
                studentBookDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                studentBookDTOList.add(studentBookDTO);
            }
            return studentBookDTOList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
