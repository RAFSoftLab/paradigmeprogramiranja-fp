package raf.rs.monade;

import raf.rs.monade.model.Grupa;
import raf.rs.monade.model.Student;

import java.util.List;

public class PrimerStreamStudentiGrupa {

    public static void main(String[] args) {
        List<Student> studenti1 = List.of(new Student(21,"SI",2024),
                                          new Student(23,"SI",2024),
                                          new Student(24,"SI",2024),
                                          new Student(31,"SI",2025));

        List<Student> studenti2 = List.of(new Student(45,"SI",2024),
                new Student(46,"SI",2024),
                new Student(48,"SI",2023),
                new Student(51,"SI",2025));

        List<Student> studenti3 = List.of(new Student(21,"RN",2024),
                new Student(23,"RN",2024),
                new Student(20,"RN",2024),
                new Student(31,"RN",2025));
        Grupa grupa1 = new Grupa("311","SI");
        grupa1.setStudenti(studenti1);
        Grupa grupa2 = new Grupa("312","SI");
        grupa2.setStudenti(studenti2);
        Grupa grupa3 = new Grupa("301","RN");
        grupa3.setStudenti(studenti3);
        List<Grupa> grupe = List.of(grupa1, grupa2, grupa3);

        // studenti sa SI

        grupe.stream().filter(g->g.getStudProgram().equals("SI")).flatMap(g->g.getStudenti().stream()).forEach(System.out::println);

        // studenti upisani 2025

        grupe.stream().flatMap(g->g.getStudenti().stream()).filter(s->s.getGodinaUpisa()==2025).forEach(System.out::println);


    }
}
