/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Cita;
import Modelo.Medico;
import Modelo.Paciente;
import Vista.VistaCitaMedica;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *Controlador
 * @author saram
 */
public class ControladorCitaMedica{
    private final VistaCitaMedica vista;
    private final Map<String, Paciente> pacientes = new LinkedHashMap<>();
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Cita> citas = new ArrayList<>();
    private Paciente pacienteActual;
    private Medico medicoActual;
    private int consecutivoCita = 1;

    private void mostrarError(String mensaje) {
        if (vista != null) {
            vista.mostrarError(mensaje);
        }
    }

    private void mostrarPaciente(Paciente paciente) {
        if (vista != null) {
            vista.mostrarPaciente(paciente);
        }
    }

    private void mostrarMedicos() {
        if (vista != null) {
            vista.mostrarMedicos(medicos);
        }
    }

    private void setEstado(String texto) {
        if (vista != null) {
            vista.setEstado(texto);
        }
    }

    private void limpiarVista() {
        if (vista != null) {
            vista.limpiarVista();
        }
    }

    public ControladorCitaMedica(VistaCitaMedica vista) {
        this.vista = vista;
        cargarDatosDeDemostracion();
        if (vista != null) {
            vista.setControlador(this);
            vista.limpiarVista();
        }
    }
    
    private void cargarDatosDeDemostracion() {
        Paciente p1 = new Paciente(
            1, "Sara Marian Reyes Ruiz", "12345678901",
            "sara.reyes@correo.com", "6441234567"
        );

        Paciente p2 = new Paciente(
            2, "Avit Antonio Merino Felix", "12345678902",
            "avit.merino@correo.com", "6441234568"
        );

        Paciente p3 = new Paciente(
            3, "Bannia Merceder Miranda Lopez", "12345678903",
            "bannia.miranda@correo.com", "6441234569"
        );

        Paciente p4 = new Paciente(
            4, "Alejandra Ayala", "12345678904",
            "ale.ayala@correo.com", "6441234570"
        );

        Paciente p5 = new Paciente(
            5, "Valeria Torres Sánchez", "12345678905",
            "valeria.torres@correo.com", "6441234571"
        );

        Paciente p6 = new Paciente(
            6, "Diego Alejandro Ramírez", "12345678906",
            "diego.ramirez@correo.com", "6441234572"
        );

        Paciente p7 = new Paciente(
            7, "Camila Rodríguez Flores", "12345678907",
            "camila.rodriguez@correo.com", "6441234573"
        );

        Paciente p8 = new Paciente(
            8, "Miguel Ángel Sánchez", "12345678908",
            "miguel.sanchez@correo.com", "6441234574"
        );

        Paciente p9 = new Paciente(
            9, "Daniela Morales Ruiz", "12345678909",
            "daniela.morales@correo.com", "6441234575"
        );

        Paciente p10 = new Paciente(
            10, "Andrés Eduardo Castillo", "12345678910",
            "andres.castillo@correo.com", "6441234576"
        );

        pacientes.put(p1.getNumeroSeguroSocial(), p1);
        pacientes.put(p2.getNumeroSeguroSocial(), p2);
        pacientes.put(p3.getNumeroSeguroSocial(), p3);
        pacientes.put(p4.getNumeroSeguroSocial(), p4);
        pacientes.put(p5.getNumeroSeguroSocial(), p5);
        pacientes.put(p6.getNumeroSeguroSocial(), p6);
        pacientes.put(p7.getNumeroSeguroSocial(), p7);
        pacientes.put(p8.getNumeroSeguroSocial(), p8);
        pacientes.put(p9.getNumeroSeguroSocial(), p9);
        pacientes.put(p10.getNumeroSeguroSocial(), p10);

        medicos.add(new Medico(
            1, "Dra. Ana Martínez", "Medicina general",
            "Consultorio 101",
            Arrays.asList("Lunes", "Miércoles", "Viernes"),
            Arrays.asList("09:00", "10:00", "11:00", "12:00")
        ));

        medicos.add(new Medico(
            2, "Dr. Carlos Ramírez", "Cardiología",
            "Consultorio 204",
            Arrays.asList("Martes", "Jueves"),
            Arrays.asList("09:30", "10:30", "11:30", "12:30")
        ));

        medicos.add(new Medico(
            3, "Dra. Laura Hernández", "Pediatría",
            "Consultorio 305",
            Arrays.asList("Lunes", "Martes", "Jueves"),
            Arrays.asList("08:00", "09:00", "10:00", "11:00")
        ));
        
        medicos.add(new Medico(
            4, "Dra. Alexa Lopez", "Ginecologa",
            "Consultorio 306",
            Arrays.asList("Martes", "Miercoles", "Jueves"),
            Arrays.asList("10:00", "11:00", "12:00", "05:00")
        ));
    }

    public List<Medico> getMedicos() {
        return new ArrayList<>(medicos);
    }

    public Medico buscarMedicoPorId(int idMedico) {
        for (Medico medico : medicos) {
            if (medico.getIdMedico() == idMedico) {
                return medico;
            }
        }
        return null;
    }

    public List<Cita> getCitas() {
        return new ArrayList<>(citas);
    }

    public Map<String, Paciente> getPacientes() {
        return new LinkedHashMap<>(pacientes);
    }

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public Medico getMedicoActual() {
        return medicoActual;
    }

    public void buscarPaciente(String nss) {
        String clave = nss == null ? "" : nss.trim();
        if (clave.isEmpty()) {
            mostrarError("Escriba el Número de Seguridad Social");
            return;
        }
        pacienteActual = pacientes.get(clave);
        medicoActual = null;
        if (pacienteActual == null) {
            limpiarVista();
            mostrarError("No se encontró un paciente con ese NSS\n"
                    + "Para probar el sistema, use el NSS de demostración: 12345678901");
            return;
        }
        mostrarPaciente(pacienteActual);
        mostrarMedicos();
        setEstado("Paciente encontrado, seleccione un médico de la lista");
    }

    public void seleccionarMedico(Medico medico) {
        if (medico == null) return;
        medicoActual = medico;
        if (vista != null) {
            vista.mostrarDetalleMedico(medico);
            vista.setEstado("Médico seleccionado: " + medico.getNombreCompleto());
        }
    }

    public void apartarCita(String fechaTexto, String horaTexto) {
        if (pacienteActual == null) {
            mostrarError("Debe buscar un paciente antes de apartar una cita");
            return;
        }
        if (medicoActual == null) {
            mostrarError("Debe seleccionar un médico antes de apartar una cita");
            return;
        }
        if (fechaTexto == null || fechaTexto.trim().isEmpty()) {
            mostrarError("Debe ingresar una fecha válida en formato AAAA-MM-DD");
            return;
        }
        if (horaTexto == null || horaTexto.trim().isEmpty()) {
            mostrarError("Debe seleccionar una hora disponible");
            return;
        }

        try {
            LocalDate fecha = LocalDate.parse(fechaTexto.trim());
            LocalTime hora = LocalTime.parse(horaTexto.trim());

            if (fecha.isBefore(LocalDate.now())) {
                mostrarError("La fecha de la cita no puede ser anterior a la fecha actual");
                return;
            }

            String diaConsulta = traducirDia(fecha.getDayOfWeek());
            if (!medicoActual.getDiasConsulta().contains(diaConsulta)) {
                mostrarError(
                    "El médico seleccionado no atiende los " + diaConsulta.toLowerCase());
                return;
            }

            if (!medicoActual.getHorasConsulta().contains(hora.format(DateTimeFormatter.ofPattern("HH:mm")))) {
                mostrarError(
                    "La hora seleccionada no está disponible para este médico");
                return;
            }

            for (Cita citaExistente : citas) {
                if (citaExistente.getMedico().getIdMedico() == medicoActual.getIdMedico()
                        && citaExistente.getFecha().equals(fecha)
                        && citaExistente.getHora().equals(hora)) {
                    mostrarError("Ya existe una cita reservada para ese médico, fecha y horario");
                    return;
                }
            }

            Cita cita = new Cita(
                String.format("C%03d", consecutivoCita++),
                pacienteActual,
                medicoActual,
                fecha,
                hora
            );

            citas.add(cita);
            if (vista != null) {
                vista.mostrarCita(cita);
            }

        } catch (DateTimeParseException ex) {
            mostrarError(
                "La fecha debe tener el formato AAAA-MM-DD y la hora el formato HH:mm"
            );
        }

    }

    private String traducirDia(DayOfWeek dia) {
        switch (dia) {
            case MONDAY: return "Lunes";
            case TUESDAY: return "Martes";
            case WEDNESDAY: return "Miércoles";
            case THURSDAY: return "Jueves";
            case FRIDAY: return "Viernes";
            case SATURDAY: return "Sábado";
            default: return "Domingo";
        }
    }

    public void limpiar() {
        pacienteActual = null;
        medicoActual = null;
        vista.limpiarVista();
    }
}
