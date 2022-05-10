/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio;

import java.util.Objects;

/**
 *
 * @author Cris
 */
public class Horario {

    //atributos
    private int numFila;
    private String curso;
    private String inicialesProfesor;
    private String asigantura;
    private String aula;
    private String diaSemana;//Se tiene que cambiar a int
    private String hora;//Se tiene que cambiar a int

    //Constructores
    //Sin parámetros
    public Horario() {
    }

    //Parametrizado
    public Horario(int numFila, String curso, String inicialesProfesor, String asigantura, String aula, String diaSemana, String hora) {
        this.numFila = numFila;
        this.curso = curso;
        this.inicialesProfesor = inicialesProfesor;
        this.asigantura = asigantura;
        this.aula = aula;
        this.diaSemana = diaSemana;
        this.hora = hora;
    }

    //Getters y setters
    public int getNumFila() {
        return numFila;
    }

    public void setNumFila(int numFila) {
        this.numFila = numFila;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getInicialesProfesor() {
        return inicialesProfesor;
    }

    public void setInicialesProfesor(String inicialesProfesor) {
        this.inicialesProfesor = inicialesProfesor;
    }

    public String getAsigantura() {
        return asigantura;
    }

    public void setAsigantura(String asigantura) {
        this.asigantura = asigantura;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.numFila;
        hash = 59 * hash + Objects.hashCode(this.curso);
        hash = 59 * hash + Objects.hashCode(this.inicialesProfesor);
        hash = 59 * hash + Objects.hashCode(this.asigantura);
        hash = 59 * hash + Objects.hashCode(this.aula);
        hash = 59 * hash + Objects.hashCode(this.diaSemana);
        hash = 59 * hash + Objects.hashCode(this.hora);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Horario other = (Horario) obj;
        if (this.numFila != other.numFila) {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (!Objects.equals(this.inicialesProfesor, other.inicialesProfesor)) {
            return false;
        }
        if (!Objects.equals(this.asigantura, other.asigantura)) {
            return false;
        }
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        if (!Objects.equals(this.diaSemana, other.diaSemana)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }

    //toString
    @Override
    public String toString() {
        return numFila + ";" + curso + ";" + inicialesProfesor + ";" + asigantura
                + ";" + aula + ";" + diaSemana + ";" + hora;
    }

}
