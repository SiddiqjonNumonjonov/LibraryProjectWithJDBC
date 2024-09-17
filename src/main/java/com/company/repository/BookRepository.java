package com.company.repository;

import com.company.dto.BookDTO;
import com.company.dto.BookForResultDTO;
import com.company.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public Boolean addBook(BookDTO bookDTO) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into book(title,author,available_days,category_id," +
                " published_date,visible,created_at) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bookDTO.getTitle());
            preparedStatement.setString(2, bookDTO.getAuthor());
            preparedStatement.setInt(3, bookDTO.getAvailableDays());
            preparedStatement.setInt(4,bookDTO.getCategory_id());
            preparedStatement.setDate(5, Date.valueOf(bookDTO.getPublish_date()));
            preparedStatement.setBoolean(6, bookDTO.getVisible());
            preparedStatement.setTimestamp(7, Timestamp.valueOf(bookDTO.getCreatedAt()));
            int isCreated = preparedStatement.executeUpdate();
            return isCreated !=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<BookForResultDTO> bookLists() {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select b.id,b.title,b.author,c.name from book as b " +
                " inner join category as c " +
                " on c.id = b.category_id " +
                " where b.visible = true and c.visible = true";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
           ResultSet resultSet = preparedStatement.executeQuery();
           List<BookForResultDTO> list = new ArrayList<>();
           while (resultSet.next()) {
               BookForResultDTO bookForResultDTO = new BookForResultDTO();
               bookForResultDTO.setId(resultSet.getInt("id"));
               bookForResultDTO.setTitle(resultSet.getString("title"));
               bookForResultDTO.setAuthor(resultSet.getString("author"));
               bookForResultDTO.setCategoryName(resultSet.getString("name"));
               list.add(bookForResultDTO);
           }
           return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<BookForResultDTO> search(String query) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select b.id,b.title,b.author,c.name from book as b " +
                " inner join category as c " +
                " on c.id = b.category_id " +
                " where b.visible = true and c.visible = true " +
                " and (b.title like ?  or b.author like ? or c.name like ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setString(3, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<BookForResultDTO> list = new ArrayList<>();
            while (resultSet.next()) {
                BookForResultDTO bookForResultDTO = new BookForResultDTO();
                bookForResultDTO.setId(resultSet.getInt("id"));
                bookForResultDTO.setTitle(resultSet.getString("title"));
                bookForResultDTO.setAuthor(resultSet.getString("author"));
                bookForResultDTO.setCategoryName(resultSet.getString("name"));
                list.add(bookForResultDTO);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public BookForResultDTO getBookById(Integer id) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select b.id,b.title,b.author,c.name from book as b " +
                " inner join category as c " +
                " on c.id = b.category_id " +
                " where b.visible = true and c.visible = true " +
                " and b.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                BookForResultDTO dto = new BookForResultDTO();
                dto.setId(resultSet.getInt("id"));
                dto.setTitle(resultSet.getString("title"));
                dto.setAuthor(resultSet.getString("author"));
                dto.setCategoryName(resultSet.getString("name"));
                return dto;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean delete(Integer id) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "update book set visible = false where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int isDeleted = preparedStatement.executeUpdate();
            return isDeleted !=0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
