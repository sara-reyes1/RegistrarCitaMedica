/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ControladorCitaMedica;
import Modelo.Cita;
import Modelo.Medico;
import Modelo.Paciente;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
/**
 *Vista
 * @author saram
 */
public class VistaCitaMedica extends JFrame{
    private final JTextField txtNss = new JTextField(18);
    private final JButton btnBuscar = new JButton("Buscar paciente");
    private final JLabel lblEstado = new JLabel("Ingrese el NSS para comenzar");
    private final JTextArea areaPaciente = crearArea();
    private final JList<Medico> listaMedicos = new JList<>();
    private final JTextArea areaDetalle = crearArea();
    private final JTextField txtFecha = new JTextField("2026-10-13", 10);
    private final JComboBox<String> comboHora = new JComboBox<>();
    private final JButton btnApartar = new JButton("Apartar cita");
    private final JButton btnLimpiar = new JButton("Nueva búsqueda");

    private ControladorCitaMedica controlador;

    public VistaCitaMedica() {
        super("Sistema de citas médicas | Registrar cita");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 620));
        setSize(1050, 680);
        setLocationRelativeTo(null);
        construirInterfaz();
    }

    private static JTextArea crearArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("SansSerif", Font.PLAIN, 13));
        area.setBackground(new Color(248, 249, 252));
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        return area;
    }

    private void construirInterfaz() {
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(18, 18, 18, 18));
        root.setBackground(new Color(242, 244, 248));
        setContentPane(root);

        JLabel titulo = new JLabel("Registrar cita médica");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 25));
        JLabel subtitulo = new JLabel("Busque al paciente, seleccione un médico y aparte un horario");
        subtitulo.setForeground(new Color(90, 98, 112));
        JPanel encabezado = new JPanel(new BorderLayout(3, 5));
        encabezado.setOpaque(false);
        encabezado.add(titulo, BorderLayout.NORTH);
        encabezado.add(subtitulo, BorderLayout.CENTER);

        JPanel busqueda = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        busqueda.setBackground(Color.WHITE);
        busqueda.setBorder(BorderFactory.createTitledBorder("1. Identificación del paciente"));
        busqueda.add(new JLabel("Número de Seguridad Social: "));
        busqueda.add(txtNss);
        busqueda.add(btnBuscar);
        busqueda.add(btnLimpiar);
        JPanel top = new JPanel(new BorderLayout(0, 10));
        top.setOpaque(false);
        top.add(encabezado, BorderLayout.NORTH);
        top.add(busqueda, BorderLayout.CENTER);
        root.add(top, BorderLayout.NORTH);

        JPanel pacientePanel = new JPanel(new BorderLayout(5, 5));
        pacientePanel.setBackground(Color.WHITE);
        pacientePanel.setBorder(BorderFactory.createTitledBorder("Datos del paciente"));
        pacientePanel.add(new JScrollPane(areaPaciente), BorderLayout.CENTER);
        pacientePanel.setPreferredSize(new Dimension(280, 170));

        JPanel medicosPanel = new JPanel(new BorderLayout(5, 5));
        medicosPanel.setBackground(Color.WHITE);
        medicosPanel.setBorder(BorderFactory.createTitledBorder("Lista de medicos"));
        listaMedicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaMedicos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        medicosPanel.add(new JScrollPane(listaMedicos), BorderLayout.CENTER);

        JPanel izquierda = new JPanel(new GridLayout(2, 1, 0, 10));
        izquierda.setOpaque(false);
        izquierda.add(pacientePanel);
        izquierda.add(medicosPanel);

        JPanel detallePanel = new JPanel(new BorderLayout(5, 8));
        detallePanel.setBackground(Color.WHITE);
        detallePanel.setBorder(BorderFactory.createTitledBorder("2. Medico seleccionado / datos de la cita"));
        detallePanel.add(new JScrollPane(areaDetalle), BorderLayout.CENTER);

        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(Color.WHITE);
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx = 0; gc.gridy = 0;
        formulario.add(new JLabel("Fecha (AAAA-MM-DD): "), gc);
        gc.gridx = 1; formulario.add(txtFecha, gc);
        gc.gridx = 0; gc.gridy = 1;
        formulario.add(new JLabel("Hora disponible: "), gc);
        gc.gridx = 1; formulario.add(comboHora, gc);
        gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 2; gc.fill = GridBagConstraints.HORIZONTAL;
        formulario.add(btnApartar, gc);
        detallePanel.add(formulario, BorderLayout.SOUTH);

        JPanel centro = new JPanel(new GridLayout(1, 2, 12, 0));
        centro.setOpaque(false);
        centro.add(izquierda);
        centro.add(detallePanel);
        root.add(centro, BorderLayout.CENTER);

        lblEstado.setBorder(new EmptyBorder(5, 2, 0, 2));
        lblEstado.setForeground(new Color(65, 75, 90));
        root.add(lblEstado, BorderLayout.SOUTH);
    }

    public void setControlador(ControladorCitaMedica controlador) {
        this.controlador = controlador;
        btnBuscar.addActionListener(e -> controlador.buscarPaciente(txtNss.getText()));
        listaMedicos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) controlador.seleccionarMedico(listaMedicos.getSelectedValue());
        });
        btnApartar.addActionListener(e -> controlador.apartarCita(txtFecha.getText(), (String) comboHora.getSelectedItem()));
        btnLimpiar.addActionListener(e -> controlador.limpiar());
        txtNss.addActionListener(e -> controlador.buscarPaciente(txtNss.getText()));
    }

    public void mostrarPaciente(Paciente p) {
        areaPaciente.setText("Nombre completo: " + p.getNombre()
                + "\nNSS: " + p.getNumeroSeguroSocial()
                + "\nID paciente: " + p.getIdPaciente()
                + "\nCorreo: " + p.getCorreo()
                + "\nTeléfono: " + p.getTelefono());
    }

    public void mostrarMedicos(List<Medico> medicos) {
        listaMedicos.setListData(medicos.toArray(new Medico[0]));
        if (!medicos.isEmpty()) listaMedicos.setSelectedIndex(0);
    }

    public void mostrarDetalleMedico(Medico m) {
        if (m == null) {
            areaDetalle.setText("Seleccione un médico para consultar sus datos");
            return;
        }
        areaDetalle.setText("INFORMACIÓN DEL MÉDICO\n\n"
                + "Nombre: " + m.getNombreCompleto()
                + "\nEspecialidad: " + m.getEspecialidad()
                + "\nConsultorio: " + m.getConsultorio()
                + "\nDías de consulta: " + String.join(", ", m.getDiasConsulta())
                + "\nHoras de consulta: " + String.join(", ", m.getHorasConsulta())
                + "\n\nSeleccione una fecha y una hora para apartar la cita");
        comboHora.setModel(new DefaultComboBoxModel<>(m.getHorasConsulta().toArray(new String[0])));
        btnApartar.setEnabled(true);
    }

    public void mostrarCita(Cita cita) {
        areaDetalle.setText("CITA APARTADA CORRECTAMENTE!\n\n"
                + "Folio: " + cita.getIdCita()
                + "\nEstado: " + cita.getEstado()
                + "\n\nINFORMACIÓN DEL PACIENTE"
                + "\nNombre: " + cita.getPaciente().getNombre()
                + "\nNSS: " + cita.getPaciente().getNumeroSeguroSocial()
                + "\n\nINFORMACIÓN DE LA CITA"
                + "\nMédico: " + cita.getMedico().getNombreCompleto()
                + "\nEspecialidad: " + cita.getMedico().getEspecialidad()
                + "\nConsultorio: " + cita.getMedico().getConsultorio()
                + "\nFecha: " + cita.getFechaFormateada()
                + "\nHora: " + cita.getHoraFormateada());
        btnApartar.setEnabled(false);
        lblEstado.setText("Cita registrada en memoria. En esta versión de demostración no se usa una base de datos.");
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Citas medicas",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "No se pudo completar",
                JOptionPane.ERROR_MESSAGE);
    }

    public String getNss() { return txtNss.getText().trim(); }

    public void limpiarVista() {
        txtNss.setText("");
        areaPaciente.setText("");
        areaDetalle.setText("Ingrese el NSS y seleccione un medico");
        listaMedicos.setListData(new Medico[0]);
        comboHora.setModel(new DefaultComboBoxModel<>());
        txtFecha.setText("2026-10-13");
        btnApartar.setEnabled(false);
        lblEstado.setText("Ingrese el NSS para comenzar");
    }
    public void setEstado(String texto) { lblEstado.setText(texto); }
}
