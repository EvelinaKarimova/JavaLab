package ru.itis.generators;

import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class StudentApplicationForLeavingITISGenerator implements PdfGenerator {
    private static String CURRENT_ITIS_RECTOR = "Mikhail Abramskiy";
    private static String FTL_PATH = "C:\\Users\\PC\\Desktop\\EvelinaKarimova\\JavaLab\\RabbitMQTask2\\src\\ru\\itis\\templates\\applicationForLeavingITIS.ftl";
    private static String PDF_PATH = "C:\\Users\\PC\\Desktop\\EvelinaKarimova\\JavaLab\\RabbitMQTask2\\src\\ru\\itis\\documents\\StudentApplicationForLeavingITIS.pdf";

    private String lastName;
    private String firstName;
    private String dayOfBirth;
    private String passportID;
    private String passportDayOfIssue;


    public StudentApplicationForLeavingITISGenerator(String lastName, String firstName, String dayOfBirth, String passportID, String passportDayOfIssue) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dayOfBirth = dayOfBirth;
        this.passportID = passportID;
        this.passportDayOfIssue = passportDayOfIssue;
    }

    @Override
    public void generatePDF() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        Map<String,Object> map = new HashMap<>();
        map.put("rector", CURRENT_ITIS_RECTOR);
        map.put("lastName", lastName);
        map.put("firstName", firstName);
        map.put("dayOfBirth", dayOfBirth);
        map.put("passportID", passportID);
        map.put("passportDayOfIssue", passportDayOfIssue);
        try {
            Template template = cfg.getTemplate(FTL_PATH);
            Writer out = new OutputStreamWriter(System.out);
            template.process(map, out);
            OutputStream pdfOutput = new FileOutputStream(PDF_PATH);
            HtmlConverter.convertToPdf(FTL_PATH, pdfOutput);
        } catch (IOException | TemplateException e) {
            throw new IllegalArgumentException(e);
        }

    }
}