/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import static colegio.LecturaCSV.leerFicheros;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Cris
 */
public class Prueba {

    public static void ordenarTituloCantante(ArrayList<Horario> lista) {

        Comparator<Horario> criterioDia = (Horario c1, Horario c2) -> c1.getDiaSemana().compareTo(c2.getDiaSemana());
        Comparator<Horario> criterioHora = (Horario c1, Horario c2) -> c1.getHora().compareTo(c2.getHora());
        Comparator<Horario> criterioDiaHora = criterioDia.thenComparing(criterioHora);

        Collections.sort(lista, criterioDiaHora);
    }

    public static void menu(Set<String> listaGrupos, Set<String> listaProfes) {
        /*
        Si el usuario selecciona a)el programa mostrará las iniciales del profesorado, 
        para que el usuario elija una. 
        Una vez proporcionada la inicial por parte del usuario, el programa guardará en un fichero json, 
        en la raíz del proyecto, el horario seleccionado, si existe, volcando el objeto POJO en el archivo, 
        al igual que hicimos con el ejercicio de las app. El nombre del fichero estará compuesto por las 
        iniciales del profesor en cuestión y la extensión del archivo. Por ejemplo, para el profesor JCF,
        el archivo se llamará JCF.json. 
         */

        //Scanner para usarlo en las opciones
        Scanner teclado = new Scanner(System.in);
        try {

            System.out.println("Inserte 1, 2 o 3 dependiendo de la opción:"
                    + "\n1.Iniciales del profesorado"
                    + "\n2.Grupos"
                    + "\n3.Salir");

            int opciones = teclado.nextInt();

            switch (opciones) {
                case 1:
                    
                    System.out.println("");
                    for (String listaGrupo : listaGrupos) {
                        System.out.println(listaGrupo);
                    }
                    
                    System.out.println("\n\nInserte un grupo del que quiera un fichero CSV");
                    String grupo = teclado.next();
                    if(){
                    
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Hasta la próxima.");
                    break;
                default:
                    System.out.println("Introduzca 1 o 2 por favor");

            }

        } catch (Exception e) {

            System.out.println("Ha introducido una letra en lugar de un número");

        }
    }

    public static void main(String[] args) {

        //Estructuras set necesarios para el ejercicio
        Set<String> listaGrupos = new TreeSet<>();
        Set<String> listaProfes = new TreeSet<>();

        //Lista de objetos 
        ArrayList<Horario> listaHoras = leerFicheros("Horario.csv");
        ordenarTituloCantante(listaHoras);

        for (Horario listaHora : listaHoras) {
            listaGrupos.add(listaHora.getCurso());
            listaProfes.add(listaHora.getInicialesProfesor());
        }

        //PARTE A
        /*
        

        Si el usuario selecciona b), el programa mostrará los grupos que hay en el instituto, 
        para que el usuario elija uno. Una vez proporcionado el grupo, 
        el programa guardará en un fichero csv, en la raíz del proyecto, 
        el horario seleccionado, si existe. El nombre del fichero estará compuesto 
        por las iniciales del grupo en cuestión y la extensión del archivo. 
        Por ejemplo, para el grupo 1DAW, el archivo se llamará 1DAW.csv.
         */
        menu(listaGrupos, listaProfes);
    }

}
