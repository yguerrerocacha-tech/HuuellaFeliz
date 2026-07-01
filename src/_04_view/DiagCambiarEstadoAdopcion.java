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

public class DiagCambiarEstadoAdopcion extends JDialog {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cboEstado;
    private JTextArea txtObservaciones;
    private int idAdopcionActual;

    public DiagCambiarEstadoAdopcion(int idAdopcion) {
        this.idAdopcionActual = idAdopcion;

        setTitle("HuellaFeliz - Actualizar Estado del Trámite");
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

        JLabel lblTitulo = new JLabel("Actualizar Estado del Expediente N° " + idAdopcion);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(25, 20, 350, 25);
        panelForm.add(lblTitulo);

        JLabel lblEstado = new JLabel("Nuevo Estado del Trámite *");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEstado.setForeground(Color.GRAY);
        lblEstado.setBounds(25, 65, 200, 15);
        panelForm.add(lblEstado);

        cboEstado = new JComboBox<>(new String[]{"En Proceso", "Aprobada", "Finalizada", "Rechazada"});
        cboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboEstado.setBounds(25, 85, 350, 35);
        panelForm.add(cboEstado);

        JLabel lblObservaciones = new JLabel("Observaciones / Notas de Evaluación");
        lblObservaciones.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblObservaciones.setForeground(Color.GRAY);
        lblObservaciones.setBounds(25, 140, 300, 15);
        panelForm.add(lblObservaciones);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 160, 350, 80);
        panelForm.add(scrollPane);

        txtObservaciones = new JTextArea("");
        txtObservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        scrollPane.setViewportView(txtObservaciones);

        JButton btnGuardar = new JButton("ACTUALIZAR EXPEDIENTE");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(241, 196, 15)); 
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(25, 260, 350, 40);
        panelForm.add(btnGuardar);

        // LÓGICA DE ACTUALIZACIÓN CONECTADA AL DAO
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String nuevoEstado = cboEstado.getSelectedItem().toString();
                String observaciones = txtObservaciones.getText().trim();

                _03_model_dao.AdopcionDAO dao = new _03_model_dao.AdopcionDAO();
                boolean exito = dao.actualizarEstado(idAdopcionActual, nuevoEstado, observaciones);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El estado del trámite fue actualizado con éxito.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar actualizar el estado en el servidor SQL Server.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}