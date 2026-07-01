package _04_view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import _02_modelo_entidad.Animal;
import _03_model_dao.AnimalDAO;

public class PanGestionAnimales extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtBuscar;
    private JTable tablaAnimales;
    private DefaultTableModel modelo;
    private List<Animal> listaLocalAnimales = new ArrayList<>();
    
    // Componentes del Panel Lateral Derecho
    private JLabel lblFotoMascota;
    private JLabel lblNombreLateral;
    private JLabel lblDetallesLateral;
    private String fotoSeleccionadaRuta = "default.png";

    public PanGestionAnimales() {
        setBackground(new Color(245, 246, 248));
        setSize(824, 571);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Gestión y Control de Mascotas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setBounds(25, 20, 400, 30);
        add(lblTitulo);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(25, 70, 260, 35);
        add(txtBuscar);

        JButton btnNuevo = new JButton("+ Registrar");
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setBounds(295, 70, 110, 35);
        add(btnNuevo);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(241, 196, 15));
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnModificar.setBounds(415, 70, 110, 35);
        add(btnModificar);

        // Tabla Principal
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 120, 500, 400);
        add(scrollPane);

        String[] cabecera = { "ID", "Nombre", "Especie", "Raza", "Estado" };
        modelo = new DefaultTableModel(null, cabecera) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaAnimales = new JTable(modelo);
        scrollPane.setViewportView(tablaAnimales);

        // =========================================================
        // DISEÑO DEL PANEL LATERAL DE VISTA PREVIA (FOTO + INFO)
        // =========================================================
        JPanel panelPreview = new JPanel();
        panelPreview.setBackground(Color.WHITE);
        panelPreview.setBorder(new LineBorder(new Color(220, 224, 230), 1));
        panelPreview.setBounds(545, 120, 250, 400);
        panelPreview.setLayout(null);
        add(panelPreview);

        lblFotoMascota = new JLabel();
        lblFotoMascota.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        lblFotoMascota.setBounds(50, 25, 150, 150);
        lblFotoMascota.setHorizontalAlignment(JLabel.CENTER);
        panelPreview.add(lblFotoMascota);

        lblNombreLateral = new JLabel("Seleccione un animal", JLabel.CENTER);
        lblNombreLateral.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombreLateral.setForeground(new Color(44, 62, 80));
        lblNombreLateral.setBounds(10, 195, 230, 25);
        panelPreview.add(lblNombreLateral);

        lblDetallesLateral = new JLabel("<html><body><center>Haga clic en la tabla para ver la ficha técnica y su fotografía.</center></body></html>", JLabel.CENTER);
        lblDetallesLateral.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDetallesLateral.setForeground(Color.GRAY);
        lblDetallesLateral.setBounds(15, 230, 220, 100);
        panelPreview.add(lblDetallesLateral);

        // Cargar imagen por defecto al inicio
        desplegarFoto("default.png");

        // EVENTO CLIC SOBRE LA TABLA (Muestra foto e información al instante)
        tablaAnimales.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int fila = tablaAnimales.getSelectedRow();
                if (fila != -1) {
                    Animal a = listaLocalAnimales.get(fila);
                    lblNombreLateral.setText(a.getNomAnimal());
                    
                    String detallesHtml = "<html><body>"
                                        + "<b>Edad:</b> " + a.getEdad() + " años<br>"
                                        + "<b>Sexo:</b> " + (a.getSexo().equals("M") ? "Macho" : "Hembra") + "<br>"
                                        + "<b>Raza:</b> " + a.getRaza() + "<br>"
                                        + "<b>Estado:</b> " + a.getEstado() + "<br>"
                                        + "<b>Ingreso:</b> " + a.getFechaIngreso()
                                        + "</body></html>";
                    lblDetallesLateral.setText(detallesHtml);
                    fotoSeleccionadaRuta = a.getFoto();
                    desplegarFoto(fotoSeleccionadaRuta);
                }
            }
        });

        // EVENTO DEL BOTÓN MODIFICAR
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int fila = tablaAnimales.getSelectedRow();
                if (fila == -1) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Seleccione una mascota de la tabla para editar.", "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                // Abrimos el formulario de modificación pasándole el objeto Animal completo
                Animal animalSeleccionado = listaLocalAnimales.get(fila);
                DiagModificarAnimal diag = new DiagModificarAnimal(animalSeleccionado);
                diag.setVisible(true);
                
                cargarDatosAnimales(); // Refrescar la JTable al cerrar
            }
        });

        cargarDatosAnimales();
    }

    private void desplegarFoto(String nombreArchivo) {
        try {
            String ruta = "src/resources/" + nombreArchivo;
            File f = new File(ruta);
            if(!f.exists()) {
                ruta = "src/resources/default.png"; // Respaldo por si no encuentra el archivo
            }
            ImageIcon imgIcon = new ImageIcon(ruta);
            Image imgEscalada = imgIcon.getImage().getScaledInstance(148, 148, Image.SCALE_SMOOTH);
            lblFotoMascota.setIcon(new ImageIcon(imgEscalada));
        } catch (Exception e) {
            System.out.println("Error al pintar foto: " + e.getMessage());
        }
    }

    public void cargarDatosAnimales() {
        AnimalDAO dao = new AnimalDAO();
        listaLocalAnimales = dao.listarTodos();
        modelo.setRowCount(0);
        for (Animal a : listaLocalAnimales) {
            modelo.addRow(new Object[]{ a.getCodAnimal(), a.getNomAnimal(), a.getEspecie(), a.getRaza(), a.getEstado() });
        }
    }
}