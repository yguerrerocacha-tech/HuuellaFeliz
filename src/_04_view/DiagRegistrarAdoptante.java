package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DiagRegistrarAdoptante extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtCorreo;

    public DiagRegistrarAdoptante() {
        setTitle("HuellaFeliz - Registrar Nuevo Adoptante");
        setBounds(100, 100, 450, 500);
        setLocationRelativeTo(null);
        setModal(true); 
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 434, 461);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Registro de Adoptante");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 250, 25);
        panelForm.add(lblTitulo);

        JLabel lblDni = new JLabel("DNI (8 dígitos) *");
        lblDni.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDni.setForeground(Color.GRAY);
        lblDni.setBounds(30, 65, 150, 15);
        panelForm.add(lblDni);

        txtDni = new JTextField();
        txtDni.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDni.setBounds(30, 85, 170, 35);
        panelForm.add(txtDni);

        JLabel lblTelefono = new JLabel("Teléfono / Celular");
        lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTelefono.setForeground(Color.GRAY);
        lblTelefono.setBounds(230, 65, 150, 15);
        panelForm.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTelefono.setBounds(230, 85, 170, 35);
        panelForm.add(txtTelefono);

        JLabel lblNombre = new JLabel("Nombres *");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNombre.setForeground(Color.GRAY);
        lblNombre.setBounds(30, 135, 150, 15);
        panelForm.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setBounds(30, 155, 370, 35);
        panelForm.add(txtNombre);

        JLabel lblApellidos = new JLabel("Apellidos *");
        lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblApellidos.setForeground(Color.GRAY);
        lblApellidos.setBounds(30, 205, 150, 15);
        panelForm.add(lblApellidos);

        txtApellidos = new JTextField();
        txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtApellidos.setBounds(30, 225, 370, 35);
        panelForm.add(txtApellidos);

        JLabel lblDireccion = new JLabel("Dirección Domiciliaria *");
        lblDireccion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDireccion.setForeground(Color.GRAY);
        lblDireccion.setBounds(30, 275, 150, 15);
        panelForm.add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDireccion.setBounds(30, 295, 370, 35);
        panelForm.add(txtDireccion);

        JLabel lblCorreo = new JLabel("Correo Electrónico");
        lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCorreo.setForeground(Color.GRAY);
        lblCorreo.setBounds(30, 345, 150, 15);
        panelForm.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCorreo.setBounds(30, 365, 370, 35);
        panelForm.add(txtCorreo);

        JButton btnGuardar = new JButton("REGISTRAR ADOPTANTE");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(46, 204, 113));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 415, 370, 40);
        panelForm.add(btnGuardar);

        // LOGICA DE RECOGIDA DE DATOS
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String dni = txtDni.getText().trim();
                String nombre = txtNombre.getText().trim();
                String apellidos = txtApellidos.getText().trim();
                String direccion = txtDireccion.getText().trim();

              
                if (dni.length() != 8) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El DNI debe tener exactamente 8 dígitos.", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (nombre.isEmpty() || apellidos.isEmpty() || direccion.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Por favor, complete los campos obligatorios (*).", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                _02_modelo_entidad.Adoptante nuevoAdoptante = new _02_modelo_entidad.Adoptante();
                nuevoAdoptante.setDni(dni);
                nuevoAdoptante.setNombre(nombre);
                nuevoAdoptante.setApellidos(apellidos);
                nuevoAdoptante.setTelefono(txtTelefono.getText().trim());
                nuevoAdoptante.setDireccion(direccion);
                nuevoAdoptante.setCorreo(txtCorreo.getText().trim());
                nuevoAdoptante.setEstadoVerificacion("Pendiente"); 

                _03_model_dao.AdoptanteDAO dao = new _03_model_dao.AdoptanteDAO();
                boolean exito = dao.insertar(nuevoAdoptante);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Adoptante guardado correctamente en el sistema.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al registrar: Compruebe si el DNI ya existe.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}