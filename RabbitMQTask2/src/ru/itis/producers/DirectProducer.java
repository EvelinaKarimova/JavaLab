package ru.itis.producers;

import ru.itis.exchangers.DirectExchanger;
import ru.itis.models.Student;
import ru.itis.repositories.StudentsRepository;

import java.sql.Connection;
import java.util.Optional;
import java.util.Scanner;

public class DirectProducer {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Connection connection = helpers.DataBaseConnection.getConnection();
        StudentsRepository repository = new StudentsRepository(connection);
        DirectExchanger exchanger = new DirectExchanger();
        while (true){
            System.out.println("Enter your passport id: ");
            String passportId = scanner.nextLine();
            Optional<Student> studentOptional = repository.find(passportId);
            if (studentOptional.isPresent()) {
                String message = studentOptional.get().toString();
                System.out.println("Enter 'leave' if you want to create leaving application or 'study' if you want to create studying application: ");
                String key = scanner.nextLine();
                if (key.equals("study")||key.equals("leave")){
                    exchanger.exchange(message, key);
                } else {
                    throw new Exception("Wrong key");
                }
            } else {
                throw new Exception("User is not found");
            }
        }
    }
}
