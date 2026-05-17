package raf.rs.monade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamMonad {

	public static void main(String[] args) {
		List<String> listaStringova = new ArrayList<String>();
		listaStringova.add("ddd2");
		listaStringova.add("aa a2");
		listaStringova.add("b bb1");
		listaStringova.add("aa a1");
		listaStringova.add("b b b3");
		listaStringova.add("c c c");
		listaStringova.add("b b b2");
		listaStringova.add("d d d1");
		
		listaStringova.stream()
		    .flatMap(s -> Arrays.asList(s.split(" ")).stream())	
		    .map(String::length)
			.forEach(System.out::println);
			

		// trnasformacija u drugi tip podataka 
		
		listaStringova.stream()
			.flatMap(s -> strToCharList(s).stream())
		    .forEach(System.out::println);   
		
	}
	
	private static List<Character> strToCharList(String str){
		List<Character> chList = new ArrayList<Character>();
		for(char ch:str.toCharArray()) {
			chList.add(ch);
		}
		return chList;
	}

}
