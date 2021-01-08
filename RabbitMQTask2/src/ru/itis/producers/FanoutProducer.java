package ru.itis.producers;


import ru.itis.exchangers.FanoutExchanger;
import ru.itis.models.Student;
import ru.itis.repositories.StudentsRepository;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;

public class FanoutProducer {
    private final static String EXCHANGE_NAME = "fanout_exchange";
    private final static String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Connection connection = helpers.DataBaseConnection.getConnection();
        StudentsRepository repository = new StudentsRepository(connection);
        FanoutExchanger exchanger = new FanoutExchanger();
        while (true) {
            System.out.println("Enter your passport id: ");
            String passportId = scanner.nextLine();
            Optional<Student> studentOptional = repository.find(passportId);
            if (studentOptional.isPresent()) {
                String message = studentOptional.get().toString();
                exchanger.exchange(message);
            } else {
                throw new Exception("User is not found");
            }
        }

    }
}
