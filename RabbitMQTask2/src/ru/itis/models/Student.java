package ru.itis.models;

public class Student {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String passportId;
    private String passportDateOfIssue;
    private String academicGroup;
    private String speciality;
    private String faculty;

    public Student(String firstName, String lastName, String dateOfBirth, String passportId, String passportDateOfIssue, String academicGroup, String speciality, String faculty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passportId = passportId;
        this.passportDateOfIssue = passportDateOfIssue;
        this.academicGroup = academicGroup;
        this.speciality = speciality;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return firstName + "/" + lastName + "/" + dateOfBirth + "/" + passportId + "/" + passportDateOfIssue + "/" + academicGroup
                + "/" + speciality + "/" + faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAcademicGroup() {
        return academicGroup;
    }

    public void setAcademicGroup(String academicGroup) {
        this.academicGroup = academicGroup;
    }

    public String getPassportDateOfIssue() {
        return passportDateOfIssue;
    }

    public void setPassportDateOfIssue(String passportDateOfIssue) {
        this.passportDateOfIssue = passportDateOfIssue;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
