package ru.itis.consumers;

import ru.itis.generators.StudentApplicationForStudyingInITISGenerator;


public class LeavingApplicationFanoutConsumer {

    public static void main(String[] args) {
        String message = UserInfoFanoutExchangeConsumer.getMessage();
        String str[] = message.split("/");
        StudentApplicationForStudyingInITISGenerator generator =
                new StudentApplicationForStudyingInITISGenerator(str[0], str[1], str[2], str[3], str[4]);
        generator.generatePDF();
    }
}
