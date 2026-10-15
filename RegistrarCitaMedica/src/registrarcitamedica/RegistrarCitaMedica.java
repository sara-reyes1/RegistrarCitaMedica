/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registrarcitamedica;

import Controlador.ControladorCitaMedica;
import Vista.VistaCitaMedica;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author saram
 */
public class RegistrarCitaMedica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            VistaCitaMedica vista = new VistaCitaMedica();
            new ControladorCitaMedica(vista);
            vista.setVisible(true);
        });
    }
    
}
