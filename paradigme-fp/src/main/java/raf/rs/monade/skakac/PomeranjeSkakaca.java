package raf.rs.monade.skakac;

import raf.rs.customstructures.RekList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class PomeranjeSkakaca {
	
	public static void main(String[] args) {
		Pozicija pocetna = new Pozicija('a', 1);
		Pozicija krajnja = new Pozicija('b', 3);
		
		// test
		// System.out.println(pomeriSkakaca(pocetna).collect(Collectors.toList()));		
		// System.out.println(uTriPoteza(pocetna));
		
		System.out.println(mozeUTri(pocetna, krajnja));
		System.out.println(mozeUTriRekList(pocetna, krajnja));

	}
	
	public static List<Pozicija> vratiPozicije(Pozicija p){
		List<Pozicija> retVal = new ArrayList<Pozicija>();
		retVal.add(p.vratiSledecu(2, -1));
		retVal.add(p.vratiSledecu(2, 1));
		retVal.add(p.vratiSledecu(-2, -1));
		retVal.add(p.vratiSledecu(-2, 1));
		retVal.add(p.vratiSledecu(1, -2));
		retVal.add(p.vratiSledecu(1, 2));
		retVal.add(p.vratiSledecu(-1, -2));
		retVal.add(p.vratiSledecu(-1, 2));
		return retVal;		
	}
	
	public static Stream<Pozicija> pomeriSkakaca(Pozicija p){
		return vratiPozicije(p).stream().filter(poz -> poz.isValidna());		
	}

	public static RekList<Pozicija> pomeriSkakacaRekList(Pozicija p){
		return RekList.createFromList(vratiPozicije(p)).filter(poz -> poz.isValidna());
	}
	
	public static List<Pozicija> uTriPoteza(Pozicija pocetna){
		Stream<Pozicija> uTriPoteza = Stream.of(pocetna)
				.flatMap(PomeranjeSkakaca::pomeriSkakaca)
				.flatMap(PomeranjeSkakaca::pomeriSkakaca)
				.flatMap(PomeranjeSkakaca::pomeriSkakaca);
		return uTriPoteza.collect(Collectors.toList());
	}

	public static List<Pozicija> uTriPotezaRekList(Pozicija pocetna){
		RekList<Pozicija> uTriPoteza = RekList.of(pocetna)
				.flatMap(PomeranjeSkakaca::pomeriSkakacaRekList)
				.flatMap(PomeranjeSkakaca::pomeriSkakacaRekList)
				.flatMap(PomeranjeSkakaca::pomeriSkakacaRekList);
		return uTriPoteza.toList();
	}
	
	public static boolean mozeUTri(Pozicija p1, Pozicija p2) {
		return uTriPoteza(p1).contains(p2);
	}

	public static boolean mozeUTriRekList(Pozicija p1, Pozicija p2) {
		return uTriPotezaRekList(p1).contains(p2);
	}
	
}
