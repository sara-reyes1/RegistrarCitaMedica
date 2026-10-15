/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * clase del paciente
 * @author saram
 */
public class Paciente {
    private final int idPaciente;
    private final String nombre;
    private final String numeroSeguroSocial;
    private final String correo;
    private final String telefono;

    public Paciente(int idPaciente, String nombre,
                    String numeroSeguroSocial,
                    String correo, String telefono) {

        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.numeroSeguroSocial = numeroSeguroSocial;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumeroSeguroSocial() {
        return numeroSeguroSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
