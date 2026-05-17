package raf.rs.monade.model;

import java.util.List;

public class Grupa {
    private String oznaka;
    private String studProgram;
    private List<Student> studenti;

    public Grupa(String oznaka, String studProgram) {
        this.oznaka = oznaka;
        this.studProgram = studProgram;
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getStudProgram() {
        return studProgram;
    }

    public void setStudProgram(String studProgram) {
        this.studProgram = studProgram;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }
}
