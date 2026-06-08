package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DiaRegistrarAnimal extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JComboBox<String> cboEspecie;
    private JComboBox<String> cboSexo;
    private JTextArea txtDescripcion;

    public DiaRegistrarAnimal() {
        setTitle("HuellaFeliz - Registrar Nueva Mascota");
        setBounds(100, 100, 450, 520);
        setLocationRelativeTo(null); 
        setModal(true); 
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(new Color(255, 255, 255)); // Fondo blanco limpio
        panelForm.setBounds(0, 0, 434, 481);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

    
        JLabel lblTitulo = new JLabel("Formulario de Registro");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 250, 25);
        panelForm.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre del Animal *");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNombre.setForeground(Color.GRAY);
        lblNombre.setBounds(30, 65, 150, 15);
        panelForm.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setBounds(30, 85, 370, 35);
        panelForm.add(txtNombre);

        JLabel lblEspecie = new JLabel("Especie *");
        lblEspecie.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEspecie.setForeground(Color.GRAY);
        lblEspecie.setBounds(30, 135, 150, 15);
        panelForm.add(lblEspecie);

        cboEspecie = new JComboBox<>(new String[]{"Perro", "Gato", "Otro"});
        cboEspecie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboEspecie.setBounds(30, 155, 170, 35);
        panelForm.add(cboEspecie);

        JLabel lblSexo = new JLabel("Sexo *");
        lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSexo.setForeground(Color.GRAY);
        lblSexo.setBounds(230, 135, 150, 15);
        panelForm.add(lblSexo);

        cboSexo = new JComboBox<>(new String[]{"M", "H"}); // M = Macho, H = Hembra
        cboSexo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboSexo.setBounds(230, 155, 170, 35);
        panelForm.add(cboSexo);

        
        JLabel lblRaza = new JLabel("Raza o Cruza");
        lblRaza.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblRaza.setForeground(Color.GRAY);
        lblRaza.setBounds(30, 205, 150, 15);
        panelForm.add(lblRaza);

        txtRaza = new JTextField();
        txtRaza.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtRaza.setBounds(30, 225, 170, 35);
        panelForm.add(txtRaza);

      
        JLabel lblEdad = new JLabel("Edad (Años aproximados)");
        lblEdad.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEdad.setForeground(Color.GRAY);
        lblEdad.setBounds(230, 205, 150, 15);
        panelForm.add(lblEdad);

        txtEdad = new JTextField();
        txtEdad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtEdad.setBounds(230, 225, 170, 35);
        panelForm.add(txtEdad);

        JLabel lblDescripcion = new JLabel("Descripción o Estado Inicial");
        lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblDescripcion.setForeground(Color.GRAY);
        lblDescripcion.setBounds(30, 275, 200, 15);
        panelForm.add(lblDescripcion);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 295, 370, 80);
        panelForm.add(scrollPane);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        scrollPane.setViewportView(txtDescripcion);

        JButton btnGuardar = new JButton("GUARDAR ANIMAL");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "El nombre del animal es obligatorio.", 
                        "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String especie = cboEspecie.getSelectedItem().toString();
                String sexo = cboSexo.getSelectedItem().toString();
                String raza = txtRaza.getText().trim();
                
                int edad = 0;
                try {
                    if (!txtEdad.getText().trim().isEmpty()) {
                        edad = Integer.parseInt(txtEdad.getText().trim());
                    }
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "La edad debe ser un número entero válido.", 
                        "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String descripcion = txtDescripcion.getText().trim();

                _02_modelo_entidad.Animal nuevoAnimal = new _02_modelo_entidad.Animal();
                nuevoAnimal.setNomAnimal(nombre);
                nuevoAnimal.setEspecie(especie);
                nuevoAnimal.setSexo(sexo);
                nuevoAnimal.setRaza(raza.isEmpty() ? "Mestizo" : raza);
                nuevoAnimal.setEdad(edad);
                nuevoAnimal.setEstado("Disponible"); 
                nuevoAnimal.setDescripcion(descripcion);

                _03_model_dao.AnimalDAO dao = new _03_model_dao.AnimalDAO();
                boolean exito = dao.insertar(nuevoAnimal);

                if (exito) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "¡Mascota registrada exitosamente en HuellaFeliz!", 
                        "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    
                    dispose(); 
                } else {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Hubo un problema al guardar el registro en la base de datos.", 
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(46, 204, 113)); // Verde esmeralda exitoso
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 405, 370, 40);
        panelForm.add(btnGuardar);
    }
}