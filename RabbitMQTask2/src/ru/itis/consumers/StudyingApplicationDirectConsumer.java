package ru.itis.consumers;

import ru.itis.generators.StudentApplicationForLeavingITISGenerator;
import ru.itis.generators.StudentApplicationForStudyingInITISGenerator;

public class StudyingApplicationDirectConsumer {
    public static final String QUEUE = "studying_application_queue";

    public static void main(String[] args) {
        String message = UserInfoDirectExchangeConsumer.getMessage(QUEUE);
        String str[] = message.split("/");
        StudentApplicationForStudyingInITISGenerator generator =
                new StudentApplicationForStudyingInITISGenerator(str[0], str[1], str[2], str[3], str[4]);
        generator.generatePDF();
    }
}
