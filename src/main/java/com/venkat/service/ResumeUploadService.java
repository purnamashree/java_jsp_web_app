package com.venkat.service;

import com.venkat.common.DBConnection;
import com.venkat.enity.Resume;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResumeUploadService implements Serializable {

    public boolean addResume(Resume resume) throws SQLException, ClassNotFoundException {

        String sql = "INSERT INTO upload_resumes (name, resume_content) VALUES (?, ?)";
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, resume.getName());
        statement.setBlob(2, resume.getContent());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        connection.close();
        return rowInserted;
    }

    public List<Resume> fetchResumes() throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getInstance().getConnection();
        List<Resume> resumes = new ArrayList<>();
        String sql = "SELECT * FROM upload_resumes";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            Resume resume = new Resume();
            resume.setId(resultSet.getInt("resume_id"));
            resume.setName(resultSet.getString("name"));
            resume.setContent(resultSet.getBlob("resume_content").getBinaryStream());
            resumes.add(resume);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return resumes;
    }

    public boolean deleteResume(Resume resume) throws SQLException, ClassNotFoundException {

        String sql = "DELETE FROM upload_resumes where resume_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, resume.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        connection.close();
        return rowDeleted;
    }

    public Resume getResume(int id) throws SQLException, ClassNotFoundException {
        Resume resume = new Resume();
        String sql = "SELECT * FROM upload_resumes WHERE resume_id = ?";

        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            resume.setId(resultSet.getInt("resume_id"));
            resume.setName(resultSet.getString("name"));
            resume.setContent(resultSet.getBlob("resume_content").getBinaryStream());
        }

        resultSet.close();
        statement.close();
        connection.close();

        return resume;
    }
}
