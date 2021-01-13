package org.Arc.Client;

public class Student {
    private String ID;
    private String firstName;
    private String lastName;

    private int grade;

    private EnrollmentStatus status;
    private Center center;

    public Student(String ID, String firstName, String lastName, int grade, EnrollmentStatus status, Center center) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.status = status;
        this.center = center;
    }

    public Student(String ID, String firstName, String lastName, Center center) {
        this(ID, firstName, lastName, 0, EnrollmentStatus.NONE, center);
    }

    public Student(String ID, String firstName, String lastName) {
        this(ID, firstName, lastName, Center.NONE);
    }

    public Student(){
        this(null, null, null, 0, null, null);
    }

    public boolean equals(Student other) {
        return this.ID.equals(other.getID());
    }

    // Getters and Setters for Student
    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public EnrollmentStatus getStatus() {
        return this.status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public Center getCenter() {
        return this.center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public String toString() {
        return "Student ID:\t" + ID + "\nFirst Name:\t" + firstName + "\nLast Name:\t" + lastName + "\nGrade:\t\t" + grade + "\n\n";
    }
    
}