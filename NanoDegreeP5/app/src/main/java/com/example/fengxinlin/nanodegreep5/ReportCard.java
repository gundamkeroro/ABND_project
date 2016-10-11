package com.example.fengxinlin.nanodegreep5;

/**
 * Created by fengxinlin on 10/4/16.
 */
public class ReportCard {
    private String className;
    private String grade;
    private int subjectImage;

    public ReportCard() {
        super();
    }

    public ReportCard(int subjectImage, String className, String grade) {
        this.className = className;
        this.grade = grade;
        this.subjectImage = subjectImage;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSubjectImage() {
        return this.subjectImage;
    }

    public void setSubjectImage(int subjectImage) {
        this.subjectImage = subjectImage;
    }

    @Override
    public String toString() {
        return "Your child get \n" + grade + " in " + className;
    }
}
