package ru.itis.producers;

import ru.itis.exchangers.TopicExchanger;
import ru.itis.models.Student;
import ru.itis.repositories.StudentsRepository;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;

public class TopicProducer {
    private final static String STUD_KEY = "documents.itis.study";
    private final static String LEAVE_KEY = "documents.itis.leave";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Connection connection = helpers.DataBaseConnection.getConnection();
        StudentsRepository repository = new StudentsRepository(connection);
        TopicExchanger exchanger = new TopicExchanger();
        while (true) {
            System.out.println("Enter your passport id: ");
            String passportId = scanner.nextLine();
            Optional<Student> studentOptional = repository.find(passportId);
            if (studentOptional.isPresent()) {
                String message = studentOptional.get().toString();
                System.out.println("Enter 'leave' if you want to create leaving application or 'study' if you want to create studying application: ");
                String key = scanner.nextLine();
                if (key.equals("study")) {
                    exchanger.exchange(message, STUD_KEY);
                } else if (key.equals("leave")){
                    exchanger.exchange(message, LEAVE_KEY);
                } else {
                    throw new Exception("Wrong key");
                }
            } else {
                throw new Exception("User is not found");
            }
        }
    }
}
