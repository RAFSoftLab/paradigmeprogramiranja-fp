package raf.rs.testresults;

public class StudentRez {

    private String studProgram;
    private int godina;
    private int broj;

    private double poeni;

    public StudentRez(String studProgram, int godina, int broj) {
        this.studProgram = studProgram;
        this.godina = godina;
        this.broj = broj;
    }

    public String getStudProgram() {
        return studProgram;
    }

    public void setStudProgram(String studProgram) {
        this.studProgram = studProgram;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public double getPoeni() {
        return poeni;
    }

    public void setPoeni(double poeni) {
        this.poeni = poeni;
    }

    @Override
    public String toString() {
        return "StudentRez{" +
                "studProgram='" + studProgram + '\'' +
                ", godina=" + godina +
                ", broj=" + broj +
                ", poeni=" + poeni +
                '}';
    }
}
