package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import _02_modelo_entidad.Adoptante;

public class DiagModificarAdoptante extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JComboBox<String> cboVerificacion;
    private int idAdoptanteActual;

    public DiagModificarAdoptante(Adoptante adoptante) {
        this.idAdoptanteActual = adoptante.getCodAdoptante();
        
        setTitle("HuellaFeliz - Modificar Datos de Adoptante");
        setBounds(100, 100, 450, 530);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBounds(0, 0, 434, 491);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Modificar Información de Adoptante");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 350, 25);
        panelForm.add(lblTitulo);

        // Campo: DNI
        JLabel lblDni = new JLabel("DNI (8 dígitos) *");
        lblDni.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDni.setForeground(Color.GRAY);
        lblDni.setBounds(30, 65, 150, 15);
        panelForm.add(lblDni);

        txtDni = new JTextField(adoptante.getDni());
        txtDni.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDni.setBounds(30, 85, 170, 35);
        panelForm.add(txtDni);

        // Campo: Teléfono
        JLabel lblTelefono = new JLabel("Teléfono / Celular");
        lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTelefono.setForeground(Color.GRAY);
        lblTelefono.setBounds(230, 65, 150, 15);
        panelForm.add(lblTelefono);

        txtTelefono = new JTextField(adoptante.getTelefono());
        txtTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTelefono.setBounds(230, 85, 170, 35);
        panelForm.add(txtTelefono);

        // Campo: Nombre
        JLabel lblNombre = new JLabel("Nombres *");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNombre.setForeground(Color.GRAY);
        lblNombre.setBounds(30, 135, 150, 15);
        panelForm.add(lblNombre);

        txtNombre = new JTextField(adoptante.getNombre());
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setBounds(30, 155, 370, 35);
        panelForm.add(txtNombre);

        // Campo: Apellidos
        JLabel lblApellidos = new JLabel("Apellidos *");
        lblApellidos.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblApellidos.setForeground(Color.GRAY);
        lblApellidos.setBounds(30, 205, 150, 15);
        panelForm.add(lblApellidos);

        txtApellidos = new JTextField(adoptante.getApellidos());
        txtApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtApellidos.setBounds(30, 225, 370, 35);
        panelForm.add(txtApellidos);

        // Campo: Dirección
        JLabel lblDireccion = new JLabel("Dirección Domiciliaria *");
        lblDireccion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDireccion.setForeground(Color.GRAY);
        lblDireccion.setBounds(30, 275, 150, 15);
        panelForm.add(lblDireccion);

        txtDireccion = new JTextField(adoptante.getDireccion());
        txtDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDireccion.setBounds(30, 295, 370, 35);
        panelForm.add(txtDireccion);

        // Campo: Correo Electrónico
        JLabel lblCorreo = new JLabel("Correo Electrónico");
        lblCorreo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblCorreo.setForeground(Color.GRAY);
        lblCorreo.setBounds(30, 345, 150, 15);
        panelForm.add(lblCorreo);

        txtCorreo = new JTextField(adoptante.getCorreo());
        txtCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCorreo.setBounds(30, 365, 370, 35);
        panelForm.add(txtCorreo);

        // Campo: Estado de Verificación
        JLabel lblVerificacion = new JLabel("Estado de Verificación de Perfil *");
        lblVerificacion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblVerificacion.setForeground(Color.GRAY);
        lblVerificacion.setBounds(30, 415, 250, 15);
        panelForm.add(lblVerificacion);

        cboVerificacion = new JComboBox<>(new String[]{"Pendiente", "Aprobado", "Rechazado"});
        cboVerificacion.setSelectedItem(adoptante.getEstadoVerificacion());
        cboVerificacion.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboVerificacion.setBounds(30, 435, 370, 35);
        panelForm.add(cboVerificacion);

        // Botón: Guardar Cambios
        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(241, 196, 15)); 
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 485, 370, 40);
        panelForm.add(btnGuardar);

    
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
                    javax.swing.JOptionPane.showMessageDialog(null, "Complete los campos obligatorios (*).", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Adoptante a = new Adoptante();
                a.setCodAdoptante(idAdoptanteActual);
                a.setDni(dni);
                a.setNombre(nombre);
                a.setApellidos(apellidos);
                a.setTelefono(txtTelefono.getText().trim());
                a.setDireccion(direccion);
                a.setCorreo(txtCorreo.getText().trim());
                a.setEstadoVerificacion(cboVerificacion.getSelectedItem().toString());

                _03_model_dao.AdoptanteDAO dao = new _03_model_dao.AdoptanteDAO();
                boolean exito = dao.modificar(a);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Datos del adoptante actualizados correctamente.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar los datos en MySQL.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}