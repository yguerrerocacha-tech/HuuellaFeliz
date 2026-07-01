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

public class DiagRegistrarAdopcion extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtCodAdoptante;
    private JTextField txtCodAnimal;
    private JTextArea txtObservaciones;

    public DiagRegistrarAdopcion() {
        setTitle("HuellaFeliz - Apertura de Expediente de Adopción");
        setBounds(100, 100, 450, 460);
        setLocationRelativeTo(null);
        setModal(true); 
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 434, 421);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Iniciar Trámite de Adopción");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 300, 25);
        panelForm.add(lblTitulo);

        JLabel lblCodAdoptante = new JLabel("Código del Adoptante (ID) *");
        lblCodAdoptante.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCodAdoptante.setForeground(Color.GRAY);
        lblCodAdoptante.setBounds(30, 65, 180, 15);
        panelForm.add(lblCodAdoptante);

        txtCodAdoptante = new JTextField();
        txtCodAdoptante.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCodAdoptante.setBounds(30, 85, 170, 35);
        panelForm.add(txtCodAdoptante);

        JLabel lblCodAnimal = new JLabel("Código de la Mascota (ID) *");
        lblCodAnimal.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCodAnimal.setForeground(Color.GRAY);
        lblCodAnimal.setBounds(230, 65, 180, 15);
        panelForm.add(lblCodAnimal);

        txtCodAnimal = new JTextField();
        txtCodAnimal.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCodAnimal.setBounds(230, 85, 170, 35);
        panelForm.add(txtCodAnimal);

        JLabel lblObservaciones = new JLabel("Observaciones / Acuerdos del Trámite");
        lblObservaciones.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblObservaciones.setForeground(Color.GRAY);
        lblObservaciones.setBounds(30, 140, 300, 15);
        panelForm.add(lblObservaciones);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 160, 370, 120);
        panelForm.add(scrollPane);

        txtObservaciones = new JTextArea();
        txtObservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        scrollPane.setViewportView(txtObservaciones);

        JButton btnGuardar = new JButton("APERTURAR TRÁMITE");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 315, 370, 45);
        panelForm.add(btnGuardar);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String strAdoptante = txtCodAdoptante.getText().trim();
                String strAnimal = txtCodAnimal.getText().trim();

                if (strAdoptante.isEmpty() || strAnimal.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Por favor, complete los IDs obligatorios (*).", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int codAdoptante = Integer.parseInt(strAdoptante);
                    int codAnimal = Integer.parseInt(strAnimal);

                    _02_modelo_entidad.Adopcion nuevaAdopcion = new _02_modelo_entidad.Adopcion();
                    nuevaAdopcion.setCodAdoptante(codAdoptante);
                    nuevaAdopcion.setCodAnimal(codAnimal);
                    nuevaAdopcion.setEstadoTramite("En Proceso");
                    nuevaAdopcion.setObservaciones(txtObservaciones.getText().trim());
                    nuevaAdopcion.setFechaAdopcion(new java.sql.Date(System.currentTimeMillis())); 
                    
                    _03_model_dao.AdopcionDAO dao = new _03_model_dao.AdopcionDAO();
                    boolean exito = dao.insertar(nuevaAdopcion);

                    if (exito) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Expediente de adopción iniciado de forma exitosa.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Error al guardar: Verifique que ambos IDs existan en el sistema.", "Error de Integridad", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Los códigos (IDs) ingresados deben ser valores numéricos.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}