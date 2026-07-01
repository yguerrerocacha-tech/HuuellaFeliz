package _04_view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import _02_modelo_entidad.Animal;
import _03_model_dao.AnimalDAO;

public class DiagModificarAnimal extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtDescripcion;
    private JComboBox<String> cboSexo;
    private JComboBox<String> cboEstado;
    private JLabel lblNombreFoto;
    
    private Animal animalActual;
    private String nombreFotoFinal;

    public DiagModificarAnimal(Animal animal) {
        this.animalActual = animal;
        this.nombreFotoFinal = animal.getFoto();

        setTitle("HuellaFeliz - Modificar Ficha de Mascota");
        setBounds(100, 100, 420, 460);
        setLocationRelativeTo(null);
        setModal(true);
        setResizable(false);
        getContentPane().setLayout(null);

        JPanel panelForm = new JPanel();
        panelForm.setBackground(Color.WHITE);
        panelForm.setBounds(0, 0, 404, 421);
        getContentPane().add(panelForm);
        panelForm.setLayout(null);

        JLabel lblTitulo = new JLabel("Editar Mascota: " + animal.getNomAnimal());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(25, 15, 350, 25);
        panelForm.add(lblTitulo);

        // Nombre
        JLabel lblNom = new JLabel("Nombre de la Mascota *");
        lblNom.setBounds(25, 55, 150, 15);
        panelForm.add(lblNom);

        txtNombre = new JTextField(animal.getNomAnimal());
        txtNombre.setBounds(25, 75, 160, 35);
        panelForm.add(txtNombre);

        // Edad
        JLabel lblEdad = new JLabel("Edad (Años) *");
        lblEdad.setBounds(210, 55, 150, 15);
        panelForm.add(lblEdad);

        txtEdad = new JTextField(String.valueOf(animal.getEdad()));
        txtEdad.setBounds(210, 75, 165, 35);
        panelForm.add(txtEdad);

        // Sexo
        JLabel lblSexo = new JLabel("Sexo *");
        lblSexo.setBounds(25, 125, 100, 15);
        panelForm.add(lblSexo);

        cboSexo = new JComboBox<>(new String[] { "M", "F" });
        cboSexo.setSelectedItem(animal.getSexo());
        cboSexo.setBounds(25, 145, 160, 35);
        panelForm.add(cboSexo);

        // Estado
        JLabel lblEstado = new JLabel("Estado Clínico / Adopción *");
        lblEstado.setBounds(210, 125, 180, 15);
        panelForm.add(lblEstado);

        cboEstado = new JComboBox<>(new String[] { "Disponible", "Adoptado", "En Tratamiento", "Reservado" });
        cboEstado.setSelectedItem(animal.getEstado());
        cboEstado.setBounds(210, 145, 165, 35);
        panelForm.add(cboEstado);

        // Descripción
        JLabel lblDesc = new JLabel("Descripción de Hallazgo o Cuidados");
        lblDesc.setBounds(25, 195, 300, 15);
        panelForm.add(lblDesc);

        txtDescripcion = new JTextField(animal.getDescripcion());
        txtDescripcion.setBounds(25, 215, 350, 35);
        panelForm.add(txtDescripcion);

        // Control de Foto
        JLabel lblFotoTitle = new JLabel("Fotografía de Identificación");
        lblFotoTitle.setBounds(25, 265, 200, 15);
        panelForm.add(lblFotoTitle);

        JButton btnExaminar = new JButton("Cambiar Foto");
        btnExaminar.setBounds(25, 285, 130, 35);
        panelForm.add(btnExaminar);

        lblNombreFoto = new JLabel(animal.getFoto() == null ? "Ninguna foto" : animal.getFoto());
        lblNombreFoto.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNombreFoto.setForeground(Color.GRAY);
        lblNombreFoto.setBounds(165, 295, 210, 15);
        panelForm.add(lblNombreFoto);

        // Botón Guardar
        JButton btnGuardar = new JButton("GUARDAR CAMBIOS EN FICHA");
        btnGuardar.setBackground(new Color(241, 196, 15));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setBounds(25, 350, 350, 45);
        panelForm.add(btnGuardar);

        // INTERACCIÓN SUBIR FOTO NUEVA Y COPIARLA FÍSICAMENTE
        btnExaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png", "jpeg");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File fileOrigen = chooser.getSelectedFile();
                        nombreFotoFinal = fileOrigen.getName(); // Ejemplo: "balto.jpg"
                        
                        // Crear la carpeta de destino dentro del proyecto si no existe
                        File carpetaDestino = new File("src/resources");
                        if (!carpetaDestino.exists()) {
                            carpetaDestino.mkdirs();
                        }
                        
                        // Definir la ruta final del archivo copiado
                        File fileDestino = new File(carpetaDestino, nombreFotoFinal);
                        
                        // Copiar el archivo físico al proyecto reemplazándolo si ya existe
                        Files.copy(fileOrigen.toPath(), fileDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        
                        lblNombreFoto.setText(nombreFotoFinal);
                    } catch (Exception ex) {
                        System.out.println("Error al copiar la imagen física: " + ex.getMessage());
                        javax.swing.JOptionPane.showMessageDialog(null, "No se pudo cargar físicamente la foto.", "Error de Archivo", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // ACCIÓN DE GUARDADO DE MODIFICACIÓN
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (txtNombre.getText().trim().isEmpty() || txtEdad.getText().trim().isEmpty()) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Complete los campos obligatorios.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    animalActual.setNomAnimal(txtNombre.getText().trim());
                    animalActual.setEdad(Integer.parseInt(txtEdad.getText().trim()));
                    animalActual.setSexo(cboSexo.getSelectedItem().toString());
                    animalActual.setEstado(cboEstado.getSelectedItem().toString());
                    animalActual.setDescripcion(txtDescripcion.getText().trim());
                    animalActual.setFoto(nombreFotoFinal); 

                    AnimalDAO dao = new AnimalDAO();
                    if (dao.modificar(animalActual)) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Ficha modificada con éxito en SQL Server.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        javax.swing.JOptionPane.showMessageDialog(null, "Error al intentar modificar el registro.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "La edad debe ser un número entero.", "Error de Formato", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}