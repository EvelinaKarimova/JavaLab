package ru.itis;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.kernel.font.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentApplicationForStudyingInITISGenerator implements PdfGenerator {
    private static String CURRENT_ITIS_RECTOR = "Mikhail Abramskiy";
    private static String SPECIALITY = "Software engineering";
    private String lastName;
    private String firstName;
    private String dayOfBirth;
    private String passportID;
    private String passportDayOfIssue;

    public StudentApplicationForStudyingInITISGenerator(String lastName, String firstName, String dayOfBirth, String passportID, String passportDayOfIssue) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.dayOfBirth = dayOfBirth;
        this.passportID = passportID;
        this.passportDayOfIssue = passportDayOfIssue;
    }

    @Override
    public void generatePDF() {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\Users\\user\\Desktop\\doc1.pdf"));
            document.open();
            document.add(new Paragraph("Application ", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
            document.add(new Paragraph("Enrollee " + lastName + " " + firstName + ", date of birth: " + dayOfBirth +
                    ", passport info: " + passportID + ", passport date of issue: " + passportDayOfIssue + ". ", FontFactory.getFont(FontFactory.COURIER, 14)));
            document.add(new Paragraph(CURRENT_ITIS_RECTOR + ", please let me learn programming in ITIS on "
                    + SPECIALITY + " speciality. ", FontFactory.getFont(FontFactory.COURIER, 14)));
            document.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Нет такова файла");
        } catch (DocumentException e) {
            throw new IllegalArgumentException("чето в гетИнстанс");
        }
    }
}
