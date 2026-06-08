package _04_view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import _02_modelo_entidad.Animal;
public class PanGestionAnimales extends javax.swing.JPanel {
 

    private static final long serialVersionUID = 1L;
    private JTextField txtBuscar;
    private JTable tableMascotas;
    private DefaultTableModel model;
    private JLabel lblContador;

    public PanGestionAnimales() {
        
        setBackground(new Color(245, 246, 248));
        setSize(824, 571); 
        setLayout(null);

        JLabel lblTitulo = new JLabel("Mantenimiento de Mascotas Rescatadas");
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(25, 20, 400, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Filtre, registre o modifique el estado de los animales del centro.");
        lblSubtitulo.setForeground(new Color(127, 141, 141));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setBounds(25, 48, 500, 20);
        add(lblSubtitulo);

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBounds(25, 95, 320, 35);
        add(txtBuscar);
        txtBuscar.setColumns(10);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setBackground(new Color(52, 152, 219)); 
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBounds(355, 95, 100, 35);
        add(btnBuscar);

        JButton btnNuevo = new JButton("+ Registrar Mascota");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(new Color(46, 204, 113)); 
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBounds(494, 95, 150, 35);
        add(btnNuevo);
    
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                DiaRegistrarAnimal dialogo = new DiaRegistrarAnimal();
                dialogo.setVisible(true); 
                cargarDatosMascotas(); 
            }
        });

        JButton btnEditar = new JButton("Modificar Datos");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(241, 196, 15)); 
        btnEditar.setFocusPainted(false);
        btnEditar.setBounds(654, 95, 140, 35);
        add(btnEditar);

    
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int filaSeleccionada = tableMascotas.getSelectedRow();
                
                if (filaSeleccionada == -1) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Por favor, seleccione una mascota de la tabla para modificar sus datos.", 
                        "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int modelRow = tableMascotas.convertRowIndexToModel(filaSeleccionada);

                int id = Integer.parseInt(model.getValueAt(modelRow, 0).toString());
                String nombre = model.getValueAt(modelRow, 1).toString();
                String especie = model.getValueAt(modelRow, 2).toString();
                String raza = model.getValueAt(modelRow, 3).toString();
                
                String edadTexto = model.getValueAt(modelRow, 4).toString().replace(" años", "").trim();
                int edad = edadTexto.equals("No especificado") ? 0 : Integer.parseInt(edadTexto);
                
                String sexo = model.getValueAt(modelRow, 5).toString();
                String estado = model.getValueAt(modelRow, 6).toString();

                Animal animalAEditar = new Animal();
                animalAEditar.setCodAnimal(id);
                animalAEditar.setNomAnimal(nombre);
                animalAEditar.setEspecie(especie);
                animalAEditar.setRaza(raza); // Verifica si tu método es setRaza o el que maneje tu entidad
                animalAEditar.setEdad(edad);
                animalAEditar.setSexo(sexo);
                animalAEditar.setEstado(estado);

                DiagModificarAnimal diag = new DiagModificarAnimal(animalAEditar);
                diag.setVisible(true);
                
                cargarDatosMascotas();
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 155, 769, 340);
        add(scrollPane);

        String[] columnas = {"Código", "Nombre", "Especie", "Raza", "Edad", "Sexo", "Estado Actual"};
        
        model = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tableMascotas = new JTable(model);
        tableMascotas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableMascotas.setRowHeight(25);
        tableMascotas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableMascotas.getTableHeader().setReorderingAllowed(false); 
        scrollPane.setViewportView(tableMascotas);

        lblContador = new JLabel("Iniciando conexión con la base de datos de HuellaFeliz...");
        lblContador.setForeground(Color.GRAY);
        lblContador.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblContador.setBounds(25, 505, 400, 15);
        add(lblContador);

        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                filtrarMascotas();
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarMascotas();
            }
        });

        cargarDatosMascotas();
    }

    private void filtrarMascotas() {
        String query = txtBuscar.getText().toLowerCase().trim();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = 
            new javax.swing.table.TableRowSorter<>(model);
        tableMascotas.setRowSorter(sorter);
        
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + query, 1));
        }
    }

    public void cargarDatosMascotas() {
        try {
            _03_model_dao.AnimalDAO dao = new _03_model_dao.AnimalDAO();
            java.util.List<_02_modelo_entidad.Animal> lista = dao.listarTodos();
            
            model.setRowCount(0); 
            
            if (lista != null && !lista.isEmpty()) {
                for (_02_modelo_entidad.Animal a : lista) {
                    Object[] fila = {
                        a.getCodAnimal(),
                        a.getNomAnimal(),
                        a.getEspecie(),
                        a.getRaza(),
                        a.getEdad() == 0 ? "No especificado" : a.getEdad() + " años",
                        a.getSexo(),
                        a.getEstado()
                    };
                    model.addRow(fila);
                }
                lblContador.setText("Mostrando " + lista.size() + " registros de animales en el centro.");
            } else {
                lblContador.setText("No hay animales registrados en la base de datos actualmente.");
            }
        } catch (Exception e) {
            System.out.println("Error en la carga dinámica de animales: " + e.getMessage());
            lblContador.setText("Error al conectar con el servidor de datos.");
        }
    }
}