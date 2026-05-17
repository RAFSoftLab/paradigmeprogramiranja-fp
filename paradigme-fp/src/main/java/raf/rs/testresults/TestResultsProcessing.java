package raf.rs.testresults;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestResultsProcessing {

    public static void main(String[] args) {

           prepisiPoene("test1-rez.csv", "output.csv");

    }

    public static void prepisiPoene(String inputFile, String outputFile){
        File f = new File(inputFile);
        File fout = new File(outputFile);

        try {
            Scanner sc = new Scanner(f);
            PrintWriter pw = new PrintWriter(fout);
            List<NalogPoeni> poeni = new ArrayList<>();
            while(sc.hasNext()){
                String spplited[] = sc.nextLine().split(",");
                poeni.add(new NalogPoeni(spplited[2], Double.parseDouble(spplited[3])));
            }

            poeni.stream().map(TestResultsProcessing::parseEmail)
                    .forEach(s -> pw.printf("%s %d/%d,%.2f\n",s.getStudProgram(), s.getBroj(), s.getGodina(), s.getPoeni()));
            pw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public static StudentRez parseEmail(NalogPoeni np){
        char[] emailChars =np.getNalog().toCharArray();
        int i = 1;
        while(!Character.isDigit(emailChars[i]) && emailChars[i]!='@'){
            i++;
        }
        String cifre = "";
        while(Character.isDigit(emailChars[i])){
            cifre += emailChars[i];
            i++;
        }
        String godina = cifre.substring(cifre.length()-2);
        String brojIndeksa = cifre.substring(0,cifre.length()-2);
        String studProgram = "";
        while(i<emailChars.length && emailChars[i]!='@'){
            studProgram+=emailChars[i];
            i++;
        }
        int godinaInt = Integer.parseInt("20"+godina);
        int brojInt = Integer.parseInt(brojIndeksa);
        StudentRez sr = new StudentRez(studProgram.toUpperCase(),godinaInt,brojInt);
        sr.setPoeni(np.getPoeni());
        return sr;

    }

}
