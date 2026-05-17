package raf.rs.izuzeci;

import raf.rs.izuzeci.dataorexception1.DataOrException;
import raf.rs.testresults.NalogPoeni;
import raf.rs.testresults.StudentRez;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestResultsProcessing2 {

    public static void main(String[] args) {
        List<String> linije = null;

        try {
            File f = new File("test1-rez-g.csv");
            Scanner sc = new Scanner(f);
            linije = new ArrayList<>();
            while(sc.hasNext()){
                linije.add(sc.nextLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        List<DataOrException<StudentRez>> nalogPoeni = getStudentRezStream2(linije);//getNalogPoeni(linije);
        for(var rez:nalogPoeni){
            if(rez.isDataPresent())
                System.out.println(rez.getDataOrThrow());
            else
                System.out.println("Error:"+rez.getExceptionOrThrow().getMessage());
        }
        */
        getStudentRezStreamTry(linije).stream().
                map(rez -> switch(rez) {
                    case Success(StudentRez result) -> result;
                    case Failure(Throwable throwable) -> "Error: " +
                            throwable.getMessage();
                })
                .forEach(System.out::println);
    }






    private static NalogPoeni getNalogPoeniFromLine(String line) throws MissingDataException {
        String spplited[] = line.split(",");
        if(spplited.length<4)
            throw new MissingDataException("Missing data in line "+line);
        else
            return new NalogPoeni(spplited[2], Double.parseDouble(spplited[3]));
    }

    private static DataOrException<NalogPoeni> getNalogPoeniFromLineWrappedShort(String line){
        try {
            NalogPoeni np = getNalogPoeniFromLine(line);
            return DataOrException.of(np);
        }catch(MissingDataException ex){
            return DataOrException.of(ex);
        }
    }

    private static DataOrException<StudentRez> getStudentRezFromNalogPoeni(NalogPoeni np){
        try {
            StudentRez rez = parseEmail(np);
            return DataOrException.of(rez);
        }catch(IllegalArgumentException ex){
            return DataOrException.of(ex);
        }
    }



    public static List<DataOrException<StudentRez>> getStudentRezStream2(List<String> linije){
        return linije.stream().map(linija->
                getNalogPoeniFromLineWrappedShort(linija).map(TestResultsProcessing2::parseEmail)).toList();
    }



    public static List<Try<NalogPoeni>> getNalogPoeniStreamTry(List<String> linije) {
        return linije.stream()
                .map(str -> Try.of(() -> getNalogPoeniFromLine(str))).toList();
    }

    public static List<Try<NalogPoeni>> geNalogPoeniTry(List<String> linije) {
        return linije.stream()
                .map(str -> Try.of(() -> getNalogPoeniFromLine(str)))
                .toList();
    }

    public static List<Try<StudentRez>> getStudentRezStreamTry(List<String> linije) {
        return linije.stream()
                .map(str -> Try.of(() -> getNalogPoeniFromLine(str))
                        .flatMap(np->Try.of(()->parseEmail(np))))
                .toList();
    }


    private static StudentRez parseEmail(NalogPoeni np){
        String email = np.getNalog();
        if(!email.contains("@raf.rs"))
            throw new IllegalArgumentException("Problem with email format: "+email);
        char[] emailChars =email.toCharArray();
        int i = 1;
        while(!Character.isDigit(emailChars[i]) && emailChars[i]!='@'){
            i++;
        }
        String cifre = "";
        while(Character.isDigit(emailChars[i])){
            cifre += emailChars[i];
            i++;
        }
        if(cifre.length()<3)
            throw new IllegalArgumentException("Index number or year problem: "+email);
        String godina = cifre.substring(cifre.length()-2);
        String brojIndeksa = cifre.substring(0,cifre.length()-2);
        String studProgram = "";
        while(i<emailChars.length && emailChars[i]!='@'){
            studProgram+=emailChars[i];
            i++;
        }
        if(studProgram.length()<1)
            throw new IllegalArgumentException("Study program wrong format: "+email);
        int godinaInt = Integer.parseInt("20"+godina);
        int brojInt = Integer.parseInt(brojIndeksa);
        StudentRez sr = new StudentRez(studProgram.toUpperCase(),godinaInt,brojInt);
        sr.setPoeni(np.getPoeni());
        return sr;

    }

}
