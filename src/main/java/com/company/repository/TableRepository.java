package com.company.repository;

import com.company.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableRepository {
    public void createTable() {
        String queryForProfile = "create table if not exists profile(" +
                " id serial primary key," +
                " name varchar(25) not null," +
                " surname varchar(25) not null," +
                " login varchar(25) unique not null," +
                " password varchar(50) not null," +
                " phoneNumber varchar(13) not null," +
                " status varchar(25) not null," +
                " role varchar(25) not null," +
                " created_at TimeStamp default now()" +
                ")";

        String queryForCategory = "create table if not exists category(" +
                " id serial primary key," +
                " name varchar(25) unique not null," +
                " visible boolean not null," +
                " created_at TimeStamp default now()" +
                ")";
        String queryForBook = "create table if not exists book(" +
                "   id serial primary key," +
                "   title varchar(25) unique not null," +
                "  author varchar(25) unique not null," +
                " category_id int not null," +
                " published_date Date not null," +
                " available_days int not null," +
                " visible boolean not null," +
                " created_at TimeStamp default now()," +
                " foreign key(category_id) references category(id))";

        String sqlForStudentBook = "create table if not exists studentBook(" +
                "   id serial primary key," +
                " student_id int not null," +
                " book_id int not null," +
                " created_at TimeStamp default now()," +
                "returned_date date," +
                " status varchar(25) not null," +
                " note text," +
                " foreign key(student_id) references profile(id)," +
                " foreign key(book_id) references book(id))";
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryForProfile);
            PreparedStatement preparedStatementForBook = connection.prepareStatement(queryForBook);
            PreparedStatement preparedStatementForCategory = connection.prepareStatement(queryForCategory);
            PreparedStatement preparedStatementForStudentBook = connection.prepareStatement(sqlForStudentBook);
            preparedStatement.executeUpdate();
            preparedStatementForBook.executeUpdate();
            preparedStatementForCategory.executeUpdate();
            preparedStatementForStudentBook.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
// id, name (unique), createdDate, visible
// id, title, author, category_id, publishDate, availableDay, visible, createdDate
