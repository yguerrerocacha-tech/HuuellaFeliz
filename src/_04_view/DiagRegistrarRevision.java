package _04_view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon; // LIBRERÍA IMPORTADA CORRECTAMENTE PARA LA FOTO
import java.sql.Date;
import _02_modelo_entidad.Animal;
import _03_model_dao.AnimalDAO;

public class DiagRegistrarRevision extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtCodAnimal;
    private JTextField txtVeterinario;
    private JTextField txtCosto;
    private JTextField txtProximaCita;
    private JTextArea txtDiagnostico;
    private JTextArea txtTratamiento;
    
    private JLabel lblFotoPreview;
    private JLabel lblNombreMascotaConfirmada;

    public DiagRegistrarRevision() {
        setTitle("HuellaFeliz - Registrar Control Veterinario");
        setBounds(100, 100, 660, 580);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 644, 541);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Nueva Revisión Veterinaria");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(25, 15, 350, 25);
        panelForm.add(lblTitulo);

        // Código Mascota (ID)
        JLabel lblCodAnimal = new JLabel("Código de la Mascota (ID) *");
        lblCodAnimal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCodAnimal.setForeground(Color.GRAY);
        lblCodAnimal.setBounds(25, 55, 170, 15);
        panelForm.add(lblCodAnimal);

        txtCodAnimal = new JTextField();
        txtCodAnimal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCodAnimal.setBounds(25, 75, 170, 35);
        panelForm.add(txtCodAnimal);

        // Veterinario
        JLabel lblVeterinario = new JLabel("Médico Veterinario Tratante *");
        lblVeterinario.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblVeterinario.setForeground(Color.GRAY);
        lblVeterinario.setBounds(215, 55, 185, 15);
        panelForm.add(lblVeterinario);

        txtVeterinario = new JTextField();
        txtVeterinario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtVeterinario.setBounds(215, 75, 185, 35);
        panelForm.add(txtVeterinario);

        // Costo de Atención
        JLabel lblCosto = new JLabel("Costo de Atención (S/ ) *");
        lblCosto.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCosto.setForeground(Color.GRAY);
        lblCosto.setBounds(25, 125, 170, 15);
        panelForm.add(lblCosto);

        txtCosto = new JTextField();
        txtCosto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCosto.setBounds(25, 145, 170, 35);
        panelForm.add(txtCosto);

        // Próxima Cita
        JLabel lblProximaCita = new JLabel("Próxima Cita (AAAA-MM-DD)");
        lblProximaCita.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblProximaCita.setForeground(Color.GRAY);
        lblProximaCita.setBounds(215, 125, 185, 15);
        panelForm.add(lblProximaCita);

        txtProximaCita = new JTextField();
        txtProximaCita.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtProximaCita.setBounds(215, 145, 185, 35);
        panelForm.add(txtProximaCita);

        // Diagnóstico
        JLabel lblDiagnostico = new JLabel("Diagnóstico / Síntomas Clínicos *");
        lblDiagnostico.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDiagnostico.setForeground(Color.GRAY);
        lblDiagnostico.setBounds(25, 200, 300, 15);
        panelForm.add(lblDiagnostico);

        JScrollPane scrollDiag = new JScrollPane();
        scrollDiag.setBounds(25, 220, 375, 70);
        panelForm.add(scrollDiag);

        txtDiagnostico = new JTextArea();
        txtDiagnostico.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDiagnostico.setLineWrap(true);
        txtDiagnostico.setWrapStyleWord(true);
        scrollDiag.setViewportView(txtDiagnostico);

        // Tratamiento
        JLabel lblTratamiento = new JLabel("Tratamiento / Medicación Prescrita *");
        lblTratamiento.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTratamiento.setForeground(Color.GRAY);
        lblTratamiento.setBounds(25, 305, 300, 15);
        panelForm.add(lblTratamiento);

        JScrollPane scrollTrat = new JScrollPane();
        scrollTrat.setBounds(25, 325, 375, 70);
        panelForm.add(scrollTrat);

        txtTratamiento = new JTextArea();
        txtTratamiento.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setWrapStyleWord(true);
        scrollTrat.setViewportView(txtTratamiento);

        // PANEL DERECHO: CONFIRMACIÓN AUTOMÁTICA DE PACIENTE
        JLabel lblFotoTitle = new JLabel("Paciente Identificado", JLabel.CENTER);
        lblFotoTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFotoTitle.setForeground(new Color(44, 62, 80));
        lblFotoTitle.setBounds(435, 55, 180, 15);
        panelForm.add(lblFotoTitle);

        lblFotoPreview = new JLabel();
        lblFotoPreview.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        lblFotoPreview.setBounds(435, 75, 180, 180);
        lblFotoPreview.setHorizontalAlignment(JLabel.CENTER);
        panelForm.add(lblFotoPreview);
        
        lblNombreMascotaConfirmada = new JLabel("Digite un ID válido...", JLabel.CENTER);
        lblNombreMascotaConfirmada.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombreMascotaConfirmada.setForeground(Color.GRAY);
        lblNombreMascotaConfirmada.setBounds(435, 265, 180, 25);
        panelForm.add(lblNombreMascotaConfirmada);

        desplegarFotoPreview("default.png");

        // Botón Guardar
        JButton btnGuardar = new JButton("REGISTRAR CONTROL MÉDICO");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(46, 204, 113)); 
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(25, 430, 590, 45);
        panelForm.add(btnGuardar);

        // LÓGICA REACTIVA AL DIGITAR EL ID
        txtCodAnimal.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String idTexto = txtCodAnimal.getText().trim();
                if (idTexto.isEmpty()) {
                    lblNombreMascotaConfirmada.setText("Digite un ID válido...");
                    desplegarFotoPreview("default.png");
                    return;
                }
                
                try {
                    int idBuscar = Integer.parseInt(idTexto);
                    AnimalDAO dao = new AnimalDAO();
                    List<Animal> todos = dao.listarTodos();
                    
                    Animal encontrado = null;
                    for (Animal a : todos) {
                        if (a.getCodAnimal() == idBuscar) {
                            encontrado = a;
                            break;
                        }
                    }
                    
                    if (encontrado != null) {
                        lblNombreMascotaConfirmada.setText(encontrado.getNomAnimal());
                        lblNombreMascotaConfirmada.setForeground(new Color(41, 128, 185));
                        desplegarFotoPreview(encontrado.getFoto());
                    } else {
                        lblNombreMascotaConfirmada.setText("No registrado");
                        lblNombreMascotaConfirmada.setForeground(Color.RED);
                        desplegarFotoPreview("default.png");
                    }
                    
                } catch (NumberFormatException ex) {
                    lblNombreMascotaConfirmada.setText("ID inválido");
                    lblNombreMascotaConfirmada.setForeground(Color.RED);
                    desplegarFotoPreview("default.png");
                }
            }
        });

        // ACCIÓN DEL BOTÓN GUARDAR
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (txtCodAnimal.getText().isEmpty() || txtVeterinario.getText().isEmpty() || txtCosto.getText().isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos obligatorios (*)", "Campos Vacíos", javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (lblNombreMascotaConfirmada.getText().equals("No registrado") || lblNombreMascotaConfirmada.getText().equals("Digite un ID válido...")) {
                        javax.swing.JOptionPane.showMessageDialog(null, "No puede guardar una revisión para una mascota que no existe.", "Error de Validación", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    _02_modelo_entidad.HistorialClinico h = new _02_modelo_entidad.HistorialClinico();
                    h.setCodAnimal(Integer.parseInt(txtCodAnimal.getText().trim()));
                    h.setFechaRevision(new Date(System.currentTimeMillis())); 
                    h.setVeterinario(txtVeterinario.getText().trim());
                    h.setDiagnostico(txtDiagnostico.getText().trim());
                    h.setTratamiento(txtTratamiento.getText().trim());
                    h.setCostoAtencion(Double.parseDouble(txtCosto.getText().trim()));

                    String fechaCitaStr = txtProximaCita.getText().trim();
                    if (!fechaCitaStr.isEmpty()) {
                        h.setProximaCita(Date.valueOf(fechaCitaStr)); 
                    } else {
                        h.setProximaCita(null);
                    }

                    _03_model_dao.HistorialClinicoDAO dao = new _03_model_dao.HistorialClinicoDAO();
                    if (dao.insertar(h)) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Control veterinario registrado con éxito.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "No se pudo registrar. Verifique la conexión con SQL Server.", "Error SQL", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El ID de mascota y el Costo deben ser números válidos.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El formato de fecha correcto es AAAA-MM-DD (Ej: 2026-07-15)", "Error de Fecha", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void desplegarFotoPreview(String nombreArchivo) {
        try {
            String ruta = "src/resources/" + nombreArchivo;
            File f = new File(ruta);
            if (!f.exists()) {
                ruta = "src/resources/default.png";
            }
            ImageIcon imgIcon = new ImageIcon(ruta); // Línea 268 ya no fallará
            Image imgEscalada = imgIcon.getImage().getScaledInstance(178, 178, Image.SCALE_SMOOTH);
            lblFotoPreview.setIcon(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            System.out.println("Error al renderizar foto: " + e.getMessage());
        }
    }
}