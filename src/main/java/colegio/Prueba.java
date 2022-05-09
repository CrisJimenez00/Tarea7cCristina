/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import static colegio.LecturaCSV.escribirFicheroHorario;
import static colegio.LecturaCSV.generarFicheroJSON;
import static colegio.LecturaCSV.leerFicheros;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author Cris
 */
public class Prueba {

    //Metodo el cual hace que haya dos criterios de ordenación
    public static void ordenarHoraDia(ArrayList<Horario> lista) {

        Comparator<Horario> criterioDia = (Horario c1, Horario c2) -> c1.getDiaSemana().compareTo(c2.getDiaSemana());
        Comparator<Horario> criterioHora = (Horario c1, Horario c2) -> c1.getHora().compareTo(c2.getHora());
        Comparator<Horario> criterioDiaHora = criterioDia.thenComparing(criterioHora);

        Collections.sort(lista, criterioDiaHora);
    }

    //Método el cual tiene el menú principal de la parte a
    public static void menu(ArrayList<Horario> lista, Set<String> listaGrupos, Set<String> listaProfes) {

        //Scanner para usarlo en las opciones
        Scanner teclado = new Scanner(System.in);
        boolean salir = false;
        do {
            try {

                System.out.println("Inserte 1, 2 o 3 dependiendo de la opción:"
                        + "\n1.Grupos"
                        + "\n2.Iniciales del profesorado"
                        + "\n3.Salir");

                int opciones = teclado.nextInt();

                switch (opciones) {
                    case 1:

                        String grupo;

                        for (String listaGrupo : listaGrupos) {
                            System.out.println(listaGrupo);
                        }

                        do {

                            System.out.println("\n\nInserte un grupo del que quiera un fichero CSV");
                            grupo = teclado.next();

                            if (existe(grupo, listaGrupos)) {

                                escribirFicheroHorario(lista, grupo);

                            } else {

                                System.out.println("No existe esa clase");

                            }

                        } while (!existe(grupo, listaGrupos));

                        break;

                    case 2:

                        String profe;

                        for (String listaProfe : listaProfes) {
                            System.out.println(listaProfe);
                        }

                        do {
                            System.out.println("\n\nInserte un grupo del que quiera un fichero CSV");
                            profe = teclado.next();

                            if (existe(profe, listaProfes)) {

                                generarFicheroJSON(horario(profe, lista), profe + ".json");

                            } else {

                                System.out.println("No existe ese profesor");

                            }
                        } while (!existe(profe, listaProfes));

                        break;

                    case 3:

                        System.out.println("Hasta la próxima.");
                        salir = true;
                        break;

                    default:
                        System.out.println("Introduzca 1 o 2 por favor");

                }

            } catch (Exception e) {

                System.out.println("Ha introducido una letra en lugar de un número");

            }
        } while (!salir);
    }

    //Método el cual verifica si un elemento existe en la lista
    private static boolean existe(String parametro, Set<String> lista) {

        boolean existe = false;

        if (lista.contains(parametro)) {
            existe = true;
        }

        return existe;
    }

    //Metodo para devolver una lista
    private static List horario(String eleccion, ArrayList<Horario> lista) {

        List<String> list = lista.stream()
                .filter(s -> s.getInicialesProfesor().equalsIgnoreCase(eleccion))
                .map(p -> p.toString())//Nos hemos quedado con un stream de string con los apellidos
                .collect(Collectors.toList());
        return list;
    }

    public static void main(String[] args) {

        //Estructuras set necesarios para el ejercicio
        Set<String> listaGrupos = new TreeSet<>();
        Set<String> listaProfes = new TreeSet<>();

        //Lista de objetos 
        ArrayList<Horario> listaHoras = leerFicheros("Horario.csv");
        ordenarHoraDia(listaHoras);

        for (Horario listaHora : listaHoras) {
            listaGrupos.add(listaHora.getCurso());
            listaProfes.add(listaHora.getInicialesProfesor());
        }

        //PARTE A
        menu(listaHoras, listaGrupos, listaProfes);

        //PARTE B
        //a) Obtener todos los registros de 1ESOA que no son de la asignatura MUS.
        System.out.println("Registro de 1EOSA");
        listaHoras.stream()
                .filter(h -> h.getCurso().contains("1ESOA") && !h.getAsigantura().contains("MuS"))
                .map(h -> h.toString())
                .forEach(System.out::println);

        //b) Contar las horas que se imparten de la asignatura PROGR
        long listaProg = listaHoras.stream()
                .filter(h -> h.getAsigantura().equalsIgnoreCase("PROGR"))
                .count();
        System.out.println("\n¿Cuántas horas de programación hay? " + listaProg);

        //c) Obtener una lista con las iniciales del profesorado que imparte 
        //la asignatura REL, ordenadas en orden inverso al orden alfabético.
        System.out.println("\n------------\n");
        listaHoras.stream()
                .filter(h -> h.getAsigantura().contains("REL"))
                .map(h -> h.getInicialesProfesor())
                .distinct()//Lo pongo para que no salga reptidos los nombres
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        //d) Obtener en una lista las aulas donde imparte clase el profesor "JFV"
        System.out.println("\n----PROFESOR JFV-----");
        listaHoras.stream()
                .filter(h -> h.getInicialesProfesor().equals("JFV"))
                .map(h -> h.getAula())
                .distinct()//Lo pongo para que no salga reptidos 3
                .forEach(System.out::println);

        //e) Contar el número de asignaturas distintas que hay
        long numAsignaturas = listaHoras.stream()
                .map(h -> h.getAsigantura())
                .distinct()
                .count();
        System.out.println("\n¿Cuántas asignaturas hay? " + numAsignaturas);

        //f) Contar el total de horas que se imparten a última hora de la mañana.
        long totalHoras = listaHoras.stream()
                .filter(h -> h.getHora().equals("6ª hora"))
                .count();
        System.out.println("\n¿Cuántas horas a última hay? " + totalHoras);

        //g) Mostrar por consola los profesores que tienen clase a primera hora de la mañana.
        System.out.println("\n------PROFESORES CON CLASE A PRIMERA HORA-----");
        listaHoras.stream()
                .filter(h -> h.getHora().equals("1ª hora"))
                .map(h -> h.getInicialesProfesor())
                .distinct()//Lo pongo para que no salga reptidos 3
                .forEach(System.out::println);

    }

}
