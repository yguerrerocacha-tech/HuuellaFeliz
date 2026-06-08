package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import _02_modelo_entidad.Animal;

public class DiagModificarAnimal extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtEspecie;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JComboBox<String> cboSexo;
    private JComboBox<String> cboEstado;
    private int idAnimalActual; // Guardamos el ID de la mascota a modificar

    public DiagModificarAnimal(Animal animal) {
        this.idAnimalActual = animal.getCodAnimal();
        
        setTitle("HuellaFeliz - Editar Datos de Mascota");
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

        JLabel lblTitulo = new JLabel("Modificar Información de Mascota");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(30, 20, 350, 25);
        panelForm.add(lblTitulo);

        // Campo: Nombre
        JLabel lblNombre = new JLabel("Nombre de la Mascota *");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNombre.setForeground(Color.GRAY);
        lblNombre.setBounds(30, 65, 150, 15);
        panelForm.add(lblNombre);

        txtNombre = new JTextField(animal.getNomAnimal());
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombre.setBounds(30, 85, 370, 35);
        panelForm.add(txtNombre);

        // Campo: Especie
        JLabel lblEspecie = new JLabel("Especie *");
        lblEspecie.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEspecie.setForeground(Color.GRAY);
        lblEspecie.setBounds(30, 135, 150, 15);
        panelForm.add(lblEspecie);

        txtEspecie = new JTextField(animal.getEspecie());
        txtEspecie.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtEspecie.setBounds(30, 155, 170, 35);
        panelForm.add(txtEspecie);

        // Campo: Raza
        JLabel lblRaza = new JLabel("Raza *");
        lblRaza.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblRaza.setForeground(Color.GRAY);
        lblRaza.setBounds(230, 135, 150, 15);
        panelForm.add(lblRaza);

        txtRaza = new JTextField(animal.getRaza());
        txtRaza.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtRaza.setBounds(230, 155, 170, 35);
        panelForm.add(txtRaza);

        // Campo: Edad
        JLabel lblEdad = new JLabel("Edad Estimada (Años) *");
        lblEdad.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEdad.setForeground(Color.GRAY);
        lblEdad.setBounds(30, 205, 150, 15);
        panelForm.add(lblEdad);

        txtEdad = new JTextField(String.valueOf(animal.getEdad()));
        txtEdad.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtEdad.setBounds(30, 225, 170, 35);
        panelForm.add(txtEdad);

        // Campo: Sexo
        JLabel lblSexo = new JLabel("Sexo *");
        lblSexo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblSexo.setForeground(Color.GRAY);
        lblSexo.setBounds(230, 205, 150, 15);
        panelForm.add(lblSexo);

        cboSexo = new JComboBox<>(new String[]{"M", "F"});
        cboSexo.setSelectedItem(animal.getSexo());
        cboSexo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboSexo.setBounds(230, 225, 170, 35);
        panelForm.add(cboSexo);

        // Campo: Estado Actual
        JLabel lblEstado = new JLabel("Estado de Disponibilidad *");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEstado.setForeground(Color.GRAY);
        lblEstado.setBounds(30, 275, 250, 15);
        panelForm.add(lblEstado);

        cboEstado = new JComboBox<>(new String[]{"Disponible", "En Tratamiento", "Adoptado", "Reservado"});
        cboEstado.setSelectedItem(animal.getEstado());
        cboEstado.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboEstado.setBounds(30, 295, 370, 35);
        panelForm.add(cboEstado);

        // Botón: Actualizar
        JButton btnGuardar = new JButton("GUARDAR CAMBIOS");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setBackground(new Color(241, 196, 15)); // Color amarillo para identificar edición
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBounds(30, 365, 370, 40);
        panelForm.add(btnGuardar);

   
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                String especie = txtEspecie.getText().trim();
                String raza = txtRaza.getText().trim();
                String edadStr = txtEdad.getText().trim();

                if (nombre.isEmpty() || especie.isEmpty() || raza.isEmpty() || edadStr.isEmpty()) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios.", "Validación", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int edad = Integer.parseInt(edadStr);

                    Animal a = new Animal();
                    a.setCodAnimal(idAnimalActual);
                    a.setNomAnimal(nombre);
                    a.setEspecie(especie);
                    a.setRaza(raza); 
                    a.setEdad(edad);
                    a.setSexo(cboSexo.getSelectedItem().toString());
                    a.setEstado(cboEstado.getSelectedItem().toString());

                    _03_model_dao.AnimalDAO dao = new _03_model_dao.AnimalDAO();
                    boolean exito = dao.modificar(a);

                    if (exito) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Datos de la mascota actualizados con éxito.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Error al actualizar en la base de datos.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La edad debe ser un número entero.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}