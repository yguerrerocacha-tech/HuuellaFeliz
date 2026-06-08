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

public class PanGestionAdoptantes extends javax.swing.JPanel {
    

    private static final long serialVersionUID = 1L;
    private JTextField txtBuscar;
    private JTable tableAdoptantes;
    private DefaultTableModel model;
    private JLabel lblContador;

    public PanGestionAdoptantes() {
        setBackground(new Color(245, 246, 248));
        setSize(824, 571);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Registro y Control de Adoptantes");
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(25, 20, 400, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Gestione el padrón de adoptantes, verifique datos de contacto y estado de aprobación.");
        lblSubtitulo.setForeground(new Color(127, 141, 141));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setBounds(25, 48, 550, 20);
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

        JButton btnNuevo = new JButton("+ Registrar Adoptante");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBounds(494, 95, 160, 35);
        add(btnNuevo);

        JButton btnEditar = new JButton("Modificar Datos");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(241, 196, 15));
        btnEditar.setFocusPainted(false);
        btnEditar.setBounds(664, 95, 130, 35);
        add(btnEditar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 155, 769, 340);
        add(scrollPane);

        String[] columnas = {"Código", "DNI", "Nombre Completo", "Teléfono", "Dirección", "Correo", "Verificación"};
        
        model = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableAdoptantes = new JTable(model);
        tableAdoptantes.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableAdoptantes.setRowHeight(25);
        tableAdoptantes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableAdoptantes.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(tableAdoptantes);

        lblContador = new JLabel("Iniciando conexión con el padrón de adoptantes...");
        lblContador.setForeground(Color.GRAY);
        lblContador.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblContador.setBounds(25, 505, 450, 15);
        add(lblContador);

  
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                filtrarTabla();
            }
        });

        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                filtrarTabla();
            }
        });

     
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                DiagRegistrarAdoptante dialogo = new DiagRegistrarAdoptante();
                dialogo.setVisible(true);
                cargarDatosAdoptantes(); 
            }
        });

        cargarDatosAdoptantes();
    }

    private void filtrarTabla() {
        String query = txtBuscar.getText().toLowerCase().trim();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = 
            new javax.swing.table.TableRowSorter<>(model);
        tableAdoptantes.setRowSorter(sorter);
        
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + query, 1, 2));
        }
    }

    public void cargarDatosAdoptantes() {
        try {
            _03_model_dao.AdoptanteDAO dao = new _03_model_dao.AdoptanteDAO();
            java.util.List<_02_modelo_entidad.Adoptante> lista = dao.listarTodos();
            
            model.setRowCount(0); // Limpia remanentes
            
            if (lista != null && !lista.isEmpty()) {
                for (_02_modelo_entidad.Adoptante a : lista) {
                    Object[] fila = {
                        a.getCodAdoptante(),
                        a.getDni(),
                        a.getNombre() + " " + a.getApellidos(),
                        a.getTelefono() == null || a.getTelefono().isEmpty() ? "Sin número" : a.getTelefono(),
                        a.getDireccion(),
                        a.getCorreo(),
                        a.getEstadoVerificacion()
                    };
                    model.addRow(fila);
                }
                lblContador.setText("Mostrando " + lista.size() + " adoptantes registrados en el sistema.");
            } else {
                lblContador.setText("No hay adoptantes registrados en el padrón actualmente.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la tabla de adoptantes: " + e.getMessage());
            lblContador.setText("Error crítico al intentar conectar con la tabla ADOPTANTE.");
        }
    }
}