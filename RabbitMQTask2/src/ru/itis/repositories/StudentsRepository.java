package ru.itis.repositories;

import ru.itis.models.Student;

import java.sql.*;
import java.util.Optional;

public class StudentsRepository {
    private static final String SELECT = "select * from student where passport_id = ?";
    private Connection connection;

    public StudentsRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Student> find(String passportId) {
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, passportId);
            ResultSet set = statement.executeQuery();
            set.next();
            Student student = new Student(set.getString(2), set.getString(3),
                    set.getString(4), set.getString(5),
                    set.getString(6), set.getString(7), set.getString(8), set.getString(9));
            statement.close();
            return Optional.ofNullable(student);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
