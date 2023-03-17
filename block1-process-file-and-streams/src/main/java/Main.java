
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, Exception {

        Path path = Paths.get("./people.txt");
        List<String> listString = new ArrayList<>();
        List<Person> finalList = new ArrayList<>();
        listString = list(path);
        //Lista final
        finalList = personList(listString);
        //Lista con campos opcionales a 'unknown'
        List<Person> listUnknowns = unknowns(finalList);

        //Apartado 1 - Imprimir lista
        System.out.println(finalList);
        System.out.println("----------");

        //Apartado 2 a) - Imprimir sólo personas menores 25 años de la lista
        filter25(listUnknowns);
        System.out.println("----------");

        //Apartado 2 b) - Eliminar personas cuyo nombre empiece letra 'A'
        filterA(listUnknowns);
        System.out.println("----------");

        //Apartado 2 c) - Mostrar el primer elemento cuya ciudad sea Madrid
        filterMadrid(finalList);
        System.out.println("----------");

        //Apartado 2 d) - Mostrar el primer elemento cuya ciudad sea Barcelona
        filterBarcelona(finalList);

    }

    /**
     * Método para guardar cada linea en una lista de tipo String
     *
     * @param path
     * @return List<String>
     */
    public static List<String> list(Path path) {

        List<String> list = new ArrayList<>();
        try {
            int numLines = Files.readAllLines(path).size();
            for (int i = 0; i < numLines; i++) {
                String line = Files.readAllLines(path).get(i);
                list.add(line);
            }
            //System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Método para coger cada propiedad de cada elemento de la lista y guardarlo en objetos Person;
     * a su vez cada objeto se guarda en una List <Person>
     *
     * @param list
     * @return List <Person>
     */
    public static List<Person> personList(List<String> list) throws Exception {

        String[] persona;
        List<Person> listPerson = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

            String l = list.get(i);

            //Falta nombre
            if (l.indexOf(":") == 0)
                throw new InvalidLineFormatException ("Error en la línea " + (i + 1) + ". El nombre es obligatorio");
            //Falta segundo delimitador y edad
            if (l.indexOf(":") == l.lastIndexOf(":"))
                throw new InvalidLineFormatException ("Error en linea " + (i + 1) + ". Falta el segundo delimitador");

            persona = l.split(":", -1);

            Person p = new Person();
            p.setName(persona[0]);
            p.setTown(persona[1]);
            if (persona[2].equals("")) {
                p.setAge("0");
            } else {
                p.setAge(persona[2]);
            }

            listPerson.add(p);
        }
        return listPerson;
    }

    public static List<Person> unknowns(List<Person> people) {
        for (Person p : people) {
            if (p.getTown() == "") {
                p.setTown("unknown");
            }
            if (p.getAge() == "") {
                p.setAge("unknown");
            }
        }
        return people;
    }
    public static void filter25(List<Person> people) {
        List<Person> sp = people.stream()
                .filter(p -> Integer.parseInt(p.getAge()) < 25 && Integer.parseInt(p.getAge()) > 0)
                .toList();
        sp.forEach(System.out::println);
    }
    public static void filterA(List<Person> people) {
        Stream<Person> sp = people.stream()
                //Devuelve un Stream con las personas que tengan 'A' en el nombre
                .flatMap(person -> person.getName().contains("A") ? Stream.empty() : Stream.of(person));
        sp.forEach(System.out::println);
    }
    public static void filterMadrid(List<Person> people) {
        Stream<Person> sp = people.stream();
        Optional<Person> per = sp
                .filter(p -> p.getTown().equals("Madrid"))
                .findFirst();
        if(per.isPresent())
            System.out.println(per.get());

    }
    public static void filterBarcelona(List<Person> people) {
        Stream<Person> sp = people.stream();
        Optional<Person> per = sp
                .filter(p -> p.getTown().equals("Barcelona"))
                .findFirst();
        if(per.isPresent())
        System.out.println(per.get());

    }
}
