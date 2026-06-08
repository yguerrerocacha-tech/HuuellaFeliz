package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class DiagActualizarRescate extends JDialog {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cboEstado;
    private JTextArea txtCondicion;
    private int idRescateActual;

    public DiagActualizarRescate(int codRescate, String estadoActual, String condicionActual) {
        this.idRescateActual = codRescate;

        setTitle("HuellaFeliz - Actualizar Caso de Rescate");
        setBounds(100, 100, 420, 360);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 404, 321);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Actualizar Estado de Rescate N° " + codRescate);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(25, 20, 350, 25);
        panelForm.add(lblTitulo);

        JLabel lblEstado = new JLabel("Estado del Caso *");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEstado.setForeground(Color.GRAY);
        lblEstado.setBounds(25, 65, 200, 15);
        panelForm.add(lblEstado);

        cboEstado = new JComboBox<>(new String[]{"En Evaluación", "En Tratamiento", "Listo para Adopción", "Caso Cerrado"});
        cboEstado.setSelectedItem(estadoActual);
        cboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboEstado.setBounds(25, 85, 350, 35);
        panelForm.add(cboEstado);

        JLabel lblCondicion = new JLabel("Evolución Médica / Condición Actual *");
        lblCondicion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCondicion.setForeground(Color.GRAY);
        lblCondicion.setBounds(25, 140, 300, 15);
        panelForm.add(lblCondicion);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 160, 350, 80);
        panelForm.add(scrollPane);

        txtCondicion = new JTextArea(condicionActual);
        txtCondicion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCondicion.setLineWrap(true);
        txtCondicion.setWrapStyleWord(true);
        scrollPane.setViewportView(txtCondicion);

     
        JButton btnGuardar = new JButton("ACTUALIZAR BITÁCORA");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(241, 196, 15)); 
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(25, 260, 350, 40);
        panelForm.add(btnGuardar);

  
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String nuevoEstado = cboEstado.getSelectedItem().toString();
                String condicion = txtCondicion.getText().trim();

                if (condicion.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Escriba la condición actual del animal.", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                _03_model_dao.RescateDAO dao = new _03_model_dao.RescateDAO();
                boolean exito = dao.modificarEstado(idRescateActual, nuevoEstado, condicion);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Caso de bitácora actualizado correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar en la base de datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}