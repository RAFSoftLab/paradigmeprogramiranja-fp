package raf.rs.monade.skakac;

import java.util.List;

public class Pozicija {
	
	private char kolona;
	private int vrsta;	
	
	public Pozicija(char kolona, int vrsta) {
		super();
		this.kolona = kolona;
		this.vrsta = vrsta;
	}
	public char getKolona() {
		return kolona;
	}
	public void setKolona(char kolona) {
		this.kolona = kolona;
	}
	public int getVrsta() {
		return vrsta;
	}
	public void setVrsta(int vrsta) {
		this.vrsta = vrsta;
	}
	
	public boolean isValidna() {
		return (vrsta <= 8) && (vrsta>=1) && "abcdefgh".indexOf((int)kolona)!=-1;   
	}
	
	public Pozicija vratiSledecu(int plusKolona, int plusVrsta) {
		return new Pozicija((char)(kolona+plusKolona), vrsta + plusVrsta);
		
	}
	
	@Override
	public String toString() {
		return "(" + kolona + "," + vrsta + ")";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kolona;
		result = prime * result + vrsta;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pozicija other = (Pozicija) obj;
		if (kolona != other.kolona)
			return false;
		if (vrsta != other.vrsta)
			return false;
		return true;
	}
	
	
	

}
