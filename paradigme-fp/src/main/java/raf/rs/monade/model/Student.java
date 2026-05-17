package raf.rs.monade.model;

public class Student {

    private int broj;
    private String studProgram;
    private int godinaUpisa;

    public Student(int broj, String studProgram, int godinaUpisa) {
        this.broj = broj;
        this.studProgram = studProgram;
        this.godinaUpisa = godinaUpisa;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getStudProgram() {
        return studProgram;
    }

    public void setStudProgram(String studProgram) {
        this.studProgram = studProgram;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(int godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "broj=" + broj +
                ", studProgram='" + studProgram + '\'' +
                ", godinaUpisa=" + godinaUpisa +
                '}';
    }
}
