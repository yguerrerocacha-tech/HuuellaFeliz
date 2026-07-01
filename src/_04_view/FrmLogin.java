package _04_view;

import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import _02_modelo_entidad.Usuario;
import _03_model_dao.UsuarioDAO;

public class FrmLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField txtDni;          // Campo: Documento de Identidad
    private JPasswordField txtPassword;  // Campo: Contraseña
    private JTextField txtUsername;      // Campo: Username de Red (admin)
    private JComboBox<String> cboCargo;  // Campo: Cargo Corporativo

    public FrmLogin() {
        setTitle("HuellaFeliz - Control de Acceso Avanzado");
        setBounds(100, 100, 400, 530);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        
        JPanel panelContenedor = new JPanel();
        panelContenedor.setBackground(new Color(30, 30, 36)); // Gris oscuro industrial
        getContentPane().add(panelContenedor);
        panelContenedor.setLayout(null);

        JLabel lblLogo = new JLabel("   HuellaFeliz");
        lblLogo.setForeground(new Color(46, 204, 113)); // Verde esmeralda brillante
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblLogo.setBounds(40, 25, 310, 35);
        panelContenedor.add(lblLogo);

        JLabel lblSubtitulo = new JLabel("Autenticación de Seguridad Multicapa");
        lblSubtitulo.setForeground(new Color(150, 150, 160));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setBounds(40, 62, 310, 20);
        panelContenedor.add(lblSubtitulo);

        JLabel lblDni = new JLabel("DOCUMENTO DE IDENTIDAD (DNI) *");
        lblDni.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblDni.setForeground(new Color(180, 180, 190));
        lblDni.setBounds(40, 105, 310, 15);
        panelContenedor.add(lblDni);

        txtDni = new JTextField();
        txtDni.setCaretColor(Color.WHITE);
        txtDni.setForeground(Color.WHITE);
        txtDni.setBackground(new Color(42, 42, 53));
        txtDni.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDni.setBorder(new LineBorder(new Color(60, 60, 75), 1, true));
        txtDni.setBounds(40, 123, 310, 35);
        panelContenedor.add(txtDni);

        JLabel lblUsername = new JLabel("USERNAME / ALIAS DE RED *");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblUsername.setForeground(new Color(180, 180, 190));
        lblUsername.setBounds(40, 175, 310, 15);
        panelContenedor.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setBackground(new Color(42, 42, 53));
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtUsername.setBorder(new LineBorder(new Color(60, 60, 75), 1, true));
        txtUsername.setBounds(40, 193, 310, 35);
        panelContenedor.add(txtUsername);

        JLabel lblPassword = new JLabel("CONTRASEÑA DE ACCESO *");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblPassword.setForeground(new Color(180, 180, 190));
        lblPassword.setBounds(40, 245, 310, 15);
        panelContenedor.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setBackground(new Color(42, 42, 53));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtPassword.setBorder(new LineBorder(new Color(60, 60, 75), 1, true));
        txtPassword.setBounds(40, 263, 310, 35);
        panelContenedor.add(txtPassword);

        JLabel lblCargo = new JLabel("CARGO ASIGNADO EN SISTEMA *");
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblCargo.setForeground(new Color(180, 180, 190));
        lblCargo.setBounds(40, 315, 310, 15);
        panelContenedor.add(lblCargo);

        cboCargo = new JComboBox<>(new String[]{"Director General", "Administrador", "Veterinario", "Asistente"});
        cboCargo.setForeground(Color.WHITE);
        cboCargo.setBackground(new Color(42, 42, 53));
        cboCargo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboCargo.setBounds(40, 333, 310, 35);
        panelContenedor.add(cboCargo);

        JButton btnIngresar = new JButton("INGRESAR AL SISTEMA");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnIngresar.setForeground(new Color(30, 30, 36)); // Texto oscuro contrastante
        btnIngresar.setBackground(new Color(46, 204, 113));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorder(null);
        btnIngresar.setBounds(40, 410, 310, 45);
        panelContenedor.add(btnIngresar);

        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String dni = txtDni.getText().trim();
                String username = txtUsername.getText().trim();
                String pass = new String(txtPassword.getPassword()).trim();
                String cargo = cboCargo.getSelectedItem().toString();

                if (dni.isEmpty() || username.isEmpty() || pass.isEmpty() || cargo.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Todos los campos de validación son obligatorios.", "Faltan Credenciales", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                UsuarioDAO dao = new UsuarioDAO();
                Usuario userLogueado = dao.loginEstricto(dni, pass, username, cargo);

                if (userLogueado != null) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "¡Autenticación Exitosa!\nBienvenido, " + userLogueado.getNombreCompleto() + ".", 
                        "Acceso Autorizado", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    
                    FrmMenuPrincipal menu = new FrmMenuPrincipal();
                    menu.setVisible(true);
                    dispose(); 
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Acceso Denegado: Verifique que su DNI, Usuario, Clave y Cargo coincidan.", 
                        "Error de Credenciales", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmLogin frame = new FrmLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}