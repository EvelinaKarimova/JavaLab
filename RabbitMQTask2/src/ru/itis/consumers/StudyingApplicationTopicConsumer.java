package ru.itis.consumers;

import ru.itis.generators.StudentApplicationForStudyingInITISGenerator;

public class StudyingApplicationTopicConsumer {
    public static final String ROUTING = "documents.itis.study";

    public static void main(String[] args) {
        String message = UserInfoTopicExchangeConsumer.getMessage(ROUTING);
        String str[] = message.split("/");
        StudentApplicationForStudyingInITISGenerator generator =
                new StudentApplicationForStudyingInITISGenerator(str[0], str[1], str[2], str[3], str[4]);
        generator.generatePDF();
    }

}
