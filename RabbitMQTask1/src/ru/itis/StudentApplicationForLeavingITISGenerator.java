package ru.itis;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StudentApplicationForLeavingITISGenerator implements PdfGenerator {
    private static String CURRENT_ITIS_RECTOR = "Mikhail Abramskiy";
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
        Document document = new Document();
        try{
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(
                    "C:\\Users\\user\\Desktop\\doc.pdf"));
            document.open();
            document.add(new Paragraph("Application",FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
            document.add(new Paragraph("Student " +lastName + " " + firstName + ", date of birth: " + dayOfBirth +
                    ", passport: " + passportID + ", passport date of issue: " + passportDayOfIssue + ". ", FontFactory.getFont(FontFactory.COURIER, 14)));
            document.add(new Paragraph(CURRENT_ITIS_RECTOR + ", please expel me from ITIS because i'm dumb .", FontFactory.getFont(FontFactory.COURIER, 14)));
            document.close();
            writer.close();
        } catch (FileNotFoundException e){
            throw new IllegalArgumentException("Нет такова файла");
        } catch (DocumentException e) {
            throw new IllegalArgumentException("чето в гетИнстанс");
        }
    }
}