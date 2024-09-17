package com.company.repository;

import com.company.dto.CategoryDTO;
import com.company.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public CategoryDTO getCategoryByName(String categoryName) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from category where name=? and visible = true";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoryName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(resultSet.getInt("id"));
                categoryDTO.setName(resultSet.getString("name"));
                categoryDTO.setVisible(resultSet.getBoolean("visible"));
                categoryDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                return categoryDTO;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean create(CategoryDTO category) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into category(name,visible,created_at) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setBoolean(2, category.getVisible());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(category.getCreatedAt()));
           int isAdded = preparedStatement.executeUpdate();
           return isAdded != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CategoryDTO> categoryLists() {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from category where visible = true";
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()) {
               CategoryDTO categoryDTO = new CategoryDTO();
               categoryDTO.setId(resultSet.getInt("id"));
               categoryDTO.setName(resultSet.getString("name"));
               categoryDTO.setVisible(resultSet.getBoolean("visible"));
               categoryDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
               categoryDTOList.add(categoryDTO);
           }
           return categoryDTOList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CategoryDTO getCategoryById(Integer id) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "select * from category where id =? and visible = true";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(resultSet.getInt("id"));
                categoryDTO.setName(resultSet.getString("name"));
                categoryDTO.setVisible(resultSet.getBoolean("visible"));
                categoryDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                return categoryDTO;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean delete(Integer id) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "update category set visible=false where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int isDeleted = preparedStatement.executeUpdate();
            return isDeleted != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
