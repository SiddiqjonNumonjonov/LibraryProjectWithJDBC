package com.company.repository;

import com.company.dto.ProfileDTO;
import com.company.dto.ProfileForResultDTO;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.util.ConnectionUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepository {
    public ProfileDTO getById(Integer id) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM profile WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setLogin(resultSet.getString("login"));
                profileDTO.setName(resultSet.getString("name"));
                profileDTO.setSurname(resultSet.getString("surname"));
                profileDTO.setPassword(resultSet.getString("password"));
                profileDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profileDTO.setCreatedAt((resultSet.getTimestamp("created_at")).toLocalDateTime());
                profileDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileDTO.setId(resultSet.getInt("id"));
                profileDTO.setPhoneNumber(resultSet.getString("phoneNumber"));
                return profileDTO;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean create(ProfileDTO admin) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "insert into profile(name,surname,login,password,role,created_at,status,phoneNumber) " +
                "values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getSurname());
            preparedStatement.setString(3, admin.getLogin());
            preparedStatement.setString(4, admin.getPassword());
            preparedStatement.setString(5, admin.getRole().toString());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(admin.getCreatedAt()));
            preparedStatement.setString(7, admin.getStatus().toString());
            preparedStatement.setString(8, admin.getPhoneNumber());
            int isAdded = preparedStatement.executeUpdate();
            return isAdded != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ProfileDTO getByLogin(String login) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM profile WHERE login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setLogin(resultSet.getString("login"));
                profileDTO.setName(resultSet.getString("name"));
                profileDTO.setSurname(resultSet.getString("surname"));
                profileDTO.setPassword(resultSet.getString("password"));
                profileDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profileDTO.setCreatedAt((resultSet.getTimestamp("created_at")).toLocalDateTime());
                profileDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileDTO.setId(resultSet.getInt("id"));
                profileDTO.setPhoneNumber(resultSet.getString("phoneNumber"));
                return profileDTO;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ProfileForResultDTO> search(String query) {
        Connection connection = ConnectionUtil.getConnection();
        List<ProfileForResultDTO> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profile where role <> 'STUDENT' " +
                " and ( name like ? or surname like ? or login like ? or phoneNumber like ?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setString(3, "%" + query + "%");
            preparedStatement.setString(4, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProfileForResultDTO profileForResultDTO = new ProfileForResultDTO();
                profileForResultDTO.setId(resultSet.getInt("id"));
                profileForResultDTO.setName(resultSet.getString("name"));
                profileForResultDTO.setSurname(resultSet.getString("surname"));
                profileForResultDTO.setLogin(resultSet.getString("login"));
                profileForResultDTO.setPhone(resultSet.getString("phoneNumber"));
                profileForResultDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profileForResultDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileForResultDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                profiles.add(profileForResultDTO);
            }
            return profiles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Boolean changeStatus(Integer id, ProfileStatus profileStatus) {
        Connection connection = ConnectionUtil.getConnection();
        String sql = "UPDATE profile SET status = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, profileStatus.name());
            preparedStatement.setInt(2, id);
           int isChanged = preparedStatement.executeUpdate();
           return isChanged != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ProfileForResultDTO> getAllProfiles(ProfileRole ... roles) {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (ProfileRole role : roles) {
            sb.append("'");
            sb.append(role);
            sb.append("'");
            if(counter != roles.length - 1) {
                sb.append(",");
            }
            counter++;
        }
        Connection connection = ConnectionUtil.getConnection();
        String sql = "SELECT * FROM profile where role in (%s)";
        List<ProfileForResultDTO> profiles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            sql = String.format(sql, sb.toString());
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ProfileForResultDTO profileForResultDTO = new ProfileForResultDTO();
                profileForResultDTO.setId(resultSet.getInt("id"));
                profileForResultDTO.setName(resultSet.getString("name"));
                profileForResultDTO.setSurname(resultSet.getString("surname"));
                profileForResultDTO.setLogin(resultSet.getString("login"));
                profileForResultDTO.setPhone(resultSet.getString("phoneNumber"));
                profileForResultDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profileForResultDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileForResultDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                profiles.add(profileForResultDTO);
            }
            return profiles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ProfileForResultDTO> searchStudent(String query) {
        Connection connection = ConnectionUtil.getConnection();
        List<ProfileForResultDTO> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profile where role = 'STUDENT' " +
                " and ( name like ? or surname like ? or login like ? or phoneNumber like ?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setString(3, "%" + query + "%");
            preparedStatement.setString(4, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProfileForResultDTO profileForResultDTO = new ProfileForResultDTO();
                profileForResultDTO.setId(resultSet.getInt("id"));
                profileForResultDTO.setName(resultSet.getString("name"));
                profileForResultDTO.setSurname(resultSet.getString("surname"));
                profileForResultDTO.setLogin(resultSet.getString("login"));
                profileForResultDTO.setPhone(resultSet.getString("phoneNumber"));
                profileForResultDTO.setRole(ProfileRole.valueOf(resultSet.getString("role")));
                profileForResultDTO.setStatus(ProfileStatus.valueOf(resultSet.getString("status")));
                profileForResultDTO.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
                profiles.add(profileForResultDTO);
            }
            return profiles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
