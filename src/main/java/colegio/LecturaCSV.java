/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Cris
 */
public class LecturaCSV {

    //Método el cual quita las comillas del principio y el final de la palabra
    private static String comilla(String s) {
        String quitar = s.substring(1, s.length() - 1);
        return quitar;
    }

    //Método el cual lee los ficheros y guarda los vehiculos en una lista de objetos
    public static ArrayList<Horario> leerFicheros(String idFichero) {

        System.out.println("Leyendo el fichero: " + idFichero);

        String[] tokens;
        String linea;
        ArrayList<Horario> listaHorario = new ArrayList<>();

        try (Scanner datosFichero = new Scanner(new File(idFichero), "UTF-8")) {

            datosFichero.nextLine();

            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();
                tokens = linea.split(";");
                Horario tmp = new Horario();

                tmp.setNumFila(Integer.parseInt(tokens[0].substring(1)));
                tmp.setCurso(comilla(comilla(tokens[1])));
                tmp.setInicialesProfesor(comilla(tokens[2].substring(1, tokens[2].length() - 3)));
                tmp.setAsigantura(comilla(tokens[3].substring(1, tokens[3].length() - 1)));
                if (!comilla(comilla(tokens[4].substring(0, tokens[4].length() - 2))).contains("  ")) {
                    tmp.setAula(comilla(comilla(tokens[4].substring(0, tokens[4].length() - 1))));
                } else {
                    tmp.setAula("Sin aula");
                }

                //Según el número dice qué dia de la semana es
                switch (Integer.parseInt(tokens[5])) {
                    case 1:
                        tmp.setDiaSemana("Lunes");
                        break;
                    case 2:
                        tmp.setDiaSemana("Martes");
                        break;
                    case 3:
                        tmp.setDiaSemana("Miércoles");
                        break;
                    case 4:
                        tmp.setDiaSemana("Jueves");
                        break;
                    case 5:
                        tmp.setDiaSemana("Viernes");
                        break;
                }

                //Según el número dice qué hora es
                switch (Integer.parseInt(tokens[6].substring(0, tokens[6].length() - 1))) {
                    case 1:
                        tmp.setHora("1ª hora");
                        break;
                    case 2:
                        tmp.setHora("2ª hora");
                        break;
                    case 3:
                        tmp.setHora("3ª hora");
                        break;
                    case 5:
                        tmp.setHora("4ª hora");
                        break;
                    case 6:
                        tmp.setHora("5ª hora");
                        break;
                    case 7:
                        tmp.setHora("6ª hora");
                        break;
                    default:
                        tmp.setHora("Turno de Tarde");
                }

                listaHorario.add(tmp);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return listaHorario;
    }

}
