/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * clase para las citas entre pacientes y medicos
 * @author saram
 */
public class Cita {
    private final String idCita;
    private final Paciente paciente;
    private final Medico medico;
    private final LocalDate fecha;
    private final LocalTime hora;
    private String estado;

    public Cita(String idCita, Paciente paciente, Medico medico,
                LocalDate fecha, LocalTime hora) {
        this(idCita, paciente, medico, fecha, hora, "Apartada");
    }

    public Cita(String idCita, Paciente paciente, Medico medico,
                LocalDate fecha, LocalTime hora, String estado) {

        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public String getIdCita() {
        return idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaFormateada() {
        return fecha.format(
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );
    }

    public String getHoraFormateada() {
        return hora.format(
            DateTimeFormatter.ofPattern("HH:mm")
        );
    }
}
