package ru.itis.consumers;
import ru.itis.generators.StudentApplicationForLeavingITISGenerator;

public class StudyingApplicationFanoutConsumer {

    public static void main(String[] args) {
        String message = UserInfoFanoutExchangeConsumer.getMessage();
        String str[] = message.split("/");
        StudentApplicationForLeavingITISGenerator generator =
                new StudentApplicationForLeavingITISGenerator(str[0], str[1], str[2], str[3], str[4]);
        generator.generatePDF();
    }
}