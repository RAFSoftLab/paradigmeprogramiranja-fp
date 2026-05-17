package raf.rs.model;

public class Nastavnik extends Zaposleni{
	
	private String zvanje;
	private int brojCasova;
	
	
	public Nastavnik(String ime, String prezime) {
		super(ime, prezime);		
	}
	
	public Nastavnik(String ime, String prezime, int brojCasova,String zvanje) {
		super(ime, prezime);
		this.brojCasova = brojCasova;
		this.zvanje = zvanje;
	}
	
	public Nastavnik(String ime, String prezime, int brojCasova) {
		super(ime, prezime);
		this.brojCasova = brojCasova;
	
	}
	
		
	public Nastavnik(String ime, String prezime, String jmbg) {
		super(ime, prezime, jmbg);
		
	}

	public String getZvanje() {
		return zvanje;
	}
	public void setZvanje(String zvanje) {
		this.zvanje = zvanje;
	}
	public int getBrojCasova() {
		return brojCasova;
	}
	public void setBrojCasova(int brojCasova) {
		this.brojCasova = brojCasova;
	}
	
	
	public  void ispisiOpterecenje(){
		System.out.println(this.brojCasova);
	}
	
	public  void ispisi(){
		System.out.println("Nastavnik: "+getIme()+" "+getPrezime()+","+this.zvanje);
	}
	
	/*
	@Override
	public boolean equals(Object obj) {		
		return this.getJmbg().equals(((Nastavnik)obj).getJmbg());		
	}
	*/
	
	@Override
	public String toString() {
		return getIme()+" "+getPrezime()+": "+this.getJmbg();
	}
	
	
	
	
	
	
}
