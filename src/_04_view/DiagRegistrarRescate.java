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

public class DiagRegistrarRescate extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtLugar;
    private JTextField txtContacto;
    private JTextArea txtCondicion;

    public DiagRegistrarRescate() {
        setTitle("HuellaFeliz - Registrar Caso de Rescate");
        setBounds(100, 100, 450, 480);
        setLocationRelativeTo(null);
        setModal(true); 
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 434, 441);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Registrar Nuevo Rescate");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 300, 25);
        panelForm.add(lblTitulo);

        JLabel lblLugar = new JLabel("Lugar / Dirección del Hallazgo *");
        lblLugar.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblLugar.setForeground(Color.GRAY);
        lblLugar.setBounds(30, 65, 250, 15);
        panelForm.add(lblLugar);

        txtLugar = new JTextField();
        txtLugar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtLugar.setBounds(30, 85, 370, 35);
        panelForm.add(txtLugar);

        JLabel lblContacto = new JLabel("Contacto de Reporte (Nombre / Teléfono)");
        lblContacto.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblContacto.setForeground(Color.GRAY);
        lblContacto.setBounds(30, 140, 300, 15);
        panelForm.add(lblContacto);

        txtContacto = new JTextField();
        txtContacto.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtContacto.setBounds(30, 160, 370, 35);
        panelForm.add(txtContacto);

        JLabel lblCondicion = new JLabel("Condición Inicial / Observaciones del Animal *");
        lblCondicion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCondicion.setForeground(Color.GRAY);
        lblCondicion.setBounds(30, 215, 300, 15);
        panelForm.add(lblCondicion);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 235, 370, 100);
        panelForm.add(scrollPane);

        txtCondicion = new JTextArea();
        txtCondicion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCondicion.setLineWrap(true);
        txtCondicion.setWrapStyleWord(true);
        scrollPane.setViewportView(txtCondicion);

        JButton btnGuardar = new JButton("REGISTRAR CASO");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 365, 370, 40);
        panelForm.add(btnGuardar);

        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String lugar = txtLugar.getText().trim();
                String condicion = txtCondicion.getText().trim();

                if (lugar.isEmpty() || condicion.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Por favor, complete los campos obligatorios (*).", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                _02_modelo_entidad.Rescate nuevoRescate = new _02_modelo_entidad.Rescate();
                nuevoRescate.setLugar(lugar);
                nuevoRescate.setCondicionInicial(condicion);
                nuevoRescate.setContactoReporte(txtContacto.getText().trim());
                nuevoRescate.setEstado("En Evaluación");
                
                nuevoRescate.setFechaRescate(new java.sql.Date(System.currentTimeMillis()));

                _03_model_dao.RescateDAO dao = new _03_model_dao.RescateDAO();
                boolean exito = dao.insertar(nuevoRescate);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El caso de rescate fue asentado correctamente en la bitácora.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Hubo un problema al guardar el rescate en el servidor de datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}