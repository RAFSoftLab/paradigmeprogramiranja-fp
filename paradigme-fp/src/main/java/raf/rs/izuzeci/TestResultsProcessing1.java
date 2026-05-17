package raf.rs.izuzeci;

import raf.rs.izuzeci.dataorexception.DataOrException;
import raf.rs.testresults.NalogPoeni;
import raf.rs.testresults.StudentRez;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestResultsProcessing1 {

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

            List<DataOrException<NalogPoeni>> nalogPoeni =  getNalogPoeniList(linije); // getNalogPoeniStream(linije);
            for(var rez:nalogPoeni){
                if(rez.isDataPresent())
                    System.out.println(rez.getDataOrThrow());
                else
                    System.out.println("Error:"+rez.getExceptionOrThrow().getMessage());
            }
*/


        // getNalogPoeniStream1(linije);


        List<DataOrException<StudentRez>> studentRez =  getStudentRezStream(linije); // getNalogPoeniStream(linije);
        for(var rez:studentRez){
            if(rez.isDataPresent())
                System.out.println(rez.getDataOrThrow());
            else
                System.out.println("Error:"+rez.getExceptionOrThrow().getMessage());
        }






    }

    // 1.
    public static List<DataOrException<NalogPoeni>> getNalogPoeniList(List<String> linije){
            List<DataOrException<NalogPoeni>> retVal = new ArrayList<>();
            for(String line:linije){
                try {
                    NalogPoeni np = getNalogPoeniFromLine(line);
                    retVal.add(DataOrException.of(np));
                }catch(MissingDataException ex){
                   retVal.add(DataOrException.of(ex));
                }
            }
            return retVal;
    }

    private static NalogPoeni getNalogPoeniFromLine(String line) throws MissingDataException {
        String spplited[] = line.split(",");
        if(spplited.length<4)
            throw new MissingDataException("Missing data in line "+line);
        else
            return new NalogPoeni(spplited[2], Double.parseDouble(spplited[3]));
    }



   // 2.
    public static List<NalogPoeni> getNalogPoeniStream1(List<String> linije){

        return linije.stream().map(linija->{
                try {
                    return getNalogPoeniFromLine(linija);
                }catch(MissingDataException ex) {
                    throw new RuntimeException(ex);
                }
        }).toList();

        // return  linije.stream().map(linija->getNalogPoeniFromLine(linija)).toList(); - ne kompajlira se

    }

    // 3.a

    private static DataOrException<NalogPoeni> getNalogPoeniFromLineWrapped(String line){
        String spplited[] = line.split(",");
        if(spplited.length<4)
            return DataOrException.of(new MissingDataException("Missing data in line "+line));
        else
            return DataOrException.of(new NalogPoeni(spplited[2], Double.parseDouble(spplited[3])));
    }

    // 3.b
    private static DataOrException<NalogPoeni> getNalogPoeniFromLineWrappedShort(String line){
        try {
            NalogPoeni np = getNalogPoeniFromLine(line);
            return DataOrException.of(np);
        }catch(MissingDataException ex){
            return DataOrException.of(ex);
        }
    }

    // 3.c
    public static List<DataOrException<NalogPoeni>> getNalogPoeniStream(List<String> linije){
        return linije.stream()
                .map(TestResultsProcessing1::getNalogPoeniFromLineWrappedShort)
                .toList();
    }

    // 4.a
    private static DataOrException<StudentRez> getStudentRezFromNalogPoeni(DataOrException<NalogPoeni> np){
        try {
            if(np.isDataPresent()) {
                StudentRez rez = parseEmail(np.getDataOrThrow());
                return DataOrException.of(rez);
            }else{
                return DataOrException.of(np.getExceptionOrThrow());
            }
        }catch(IllegalArgumentException ex){
            return DataOrException.of(ex);
        }
    }

    // 4b
    public static List<DataOrException<StudentRez>> getStudentRezStream(List<String> linije){
        return linije.stream()
                .map(TestResultsProcessing1::getNalogPoeniFromLineWrappedShort)
                .map(TestResultsProcessing1::getStudentRezFromNalogPoeni)
                .toList();
    }









    public static List<Try<NalogPoeni>> getNalogPoeniStreamTry(List<String> linije) {
        return linije.stream()
                .map(str -> Try.of(() -> getNalogPoeniFromLine(str))).toList();
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
