/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Collections;
import java.util.List;

/**
 * clase medico
 * @author saram
 */
public class Medico {
    private int idMedico;
    private String nombreCompleto;
    private String especialidad;
    private String consultorio;
    private List<String> diasConsulta;
    private List<String> horasConsulta;

    public Medico(int idMedico, String nombreCompleto, String especialidad, String consultorio, List<String> diasConsulta, List<String> horasConsulta) {
        this.idMedico = idMedico;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.consultorio = consultorio;
        this.diasConsulta = diasConsulta == null ? Collections.emptyList() : List.copyOf(diasConsulta);
        this.horasConsulta = horasConsulta == null ? Collections.emptyList() : List.copyOf(horasConsulta);
    }

    public int getIdMedico() {
        return idMedico;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public List<String> getDiasConsulta() {
        return diasConsulta;
    }

    public List<String> getHorasConsulta() {
        return horasConsulta;
    }
    
    @Override
    public String toString() {
        return nombreCompleto + " - " + especialidad;
    }    
}
