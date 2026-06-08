package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import _02_modelo_entidad.HistorialClinico;
import _03_model_dao.HistorialClinicoDAO;

public class DiagRegistrarRevision extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtCodAnimal;
    private JTextField txtVeterinario;
    private JTextArea txtDiagnostico;
    private JTextArea txtTratamiento;

    public DiagRegistrarRevision() {
        setTitle("HuellaFeliz - Registrar Control Veterinario");
        setBounds(100, 100, 460, 530);
        setLocationRelativeTo(null);
        setModal(true); // Bloquea la ventana de atrás
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 444, 491);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Nueva Revisión Veterinaria");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 300, 25);
        panelForm.add(lblTitulo);

        // Campo: Código de Mascota
        JLabel lblCodAnimal = new JLabel("Código de la Mascota (ID) *");
        lblCodAnimal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCodAnimal.setForeground(Color.GRAY);
        lblCodAnimal.setBounds(30, 65, 180, 15);
        panelForm.add(lblCodAnimal);

        txtCodAnimal = new JTextField();
        txtCodAnimal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCodAnimal.setBounds(30, 85, 160, 35);
        panelForm.add(txtCodAnimal);

        // Campo: Nombre del Veterinario
        JLabel lblVeterinario = new JLabel("Médico Veterinario Tratante *");
        lblVeterinario.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblVeterinario.setForeground(Color.GRAY);
        lblVeterinario.setBounds(210, 65, 200, 15);
        panelForm.add(lblVeterinario);

        txtVeterinario = new JTextField();
        txtVeterinario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtVeterinario.setBounds(210, 85, 200, 35);
        panelForm.add(txtVeterinario);

        // Campo: Diagnóstico
        JLabel lblDiagnostico = new JLabel("Diagnóstico / Síntomas Clínicos *");
        lblDiagnostico.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDiagnostico.setForeground(Color.GRAY);
        lblDiagnostico.setBounds(30, 140, 300, 15);
        panelForm.add(lblDiagnostico);

        JScrollPane scrollDiag = new JScrollPane();
        scrollDiag.setBounds(30, 160, 380, 80);
        panelForm.add(scrollDiag);

        txtDiagnostico = new JTextArea();
        txtDiagnostico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDiagnostico.setLineWrap(true);
        txtDiagnostico.setWrapStyleWord(true);
        scrollDiag.setViewportView(txtDiagnostico);

        // Campo: Tratamiento
        JLabel lblTratamiento = new JLabel("Tratamiento / Medicación Prescrita");
        lblTratamiento.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTratamiento.setForeground(Color.GRAY);
        lblTratamiento.setBounds(30, 260, 300, 15);
        panelForm.add(lblTratamiento);

        JScrollPane scrollTrat = new JScrollPane();
        scrollTrat.setBounds(30, 280, 380, 80);
        panelForm.add(scrollTrat);

        txtTratamiento = new JTextArea();
        txtTratamiento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setWrapStyleWord(true);
        scrollTrat.setViewportView(txtTratamiento);

        // Botón: Guardar
        JButton btnGuardar = new JButton("GUARDAR EXPEDIENTE MÉDICO");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 395, 380, 45);
        panelForm.add(btnGuardar);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String strAnimal = txtCodAnimal.getText().trim();
                String veterinario = txtVeterinario.getText().trim();
                String diagnostico = txtDiagnostico.getText().trim();

                if (strAnimal.isEmpty() || veterinario.isEmpty() || diagnostico.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Por favor, complete los campos obligatorios (*).", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int codAnimal = Integer.parseInt(strAnimal);

                    HistorialClinico h = new HistorialClinico();
                    h.setCodAnimal(codAnimal);
                    h.setVeterinario(veterinario);
                    h.setDiagnostico(diagnostico);
                    h.setTratamiento(txtTratamiento.getText().trim());
                    h.setFechaRevision(new java.sql.Date(System.currentTimeMillis())); // Fecha actual
                    h.setProximaCita(null); // Opcional para flujos de control posteriores

                    HistorialClinicoDAO dao = new HistorialClinicoDAO();
                    boolean exito = dao.insertar(h);

                    if (exito) {
                        javax.swing.JOptionPane.showMessageDialog(null, "La revisión médica fue registrada exitosamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar: Verifique que el ID de la mascota exista.", "Error de Integridad", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El código de la mascota debe ser un valor numérico entero.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}