package raf.rs.testresults;

public class NalogPoeni {

    private String nalog;
    private double poeni;

    public NalogPoeni(String nalog, double poeni) {
        this.nalog = nalog;
        this.poeni = poeni;
    }

    public String getNalog() {
        return nalog;
    }

    public void setNalog(String nalog) {
        this.nalog = nalog;
    }

    public double getPoeni() {
        return poeni;
    }

    public void setPoeni(double poeni) {
        this.poeni = poeni;
    }

    @Override
    public String toString() {
        return nalog + " poeni:" + poeni;
    }
}
