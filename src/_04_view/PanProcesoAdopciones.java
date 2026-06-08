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

public class PanProcesoAdopciones extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtBuscar;
    private JTable tableAdopciones;
    private DefaultTableModel model;
    private JLabel lblContador;

    public PanProcesoAdopciones() {
        setBackground(new Color(245, 246, 248));
        setSize(824, 571);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Control de Procesos de Adopción");
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(25, 20, 400, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Gestione las solicitudes de adopción, vinculación de familias y estados de trámites.");
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

        JButton btnNuevo = new JButton("+ Iniciar Trámite");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBounds(494, 95, 150, 35);
        add(btnNuevo);

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                DiagRegistrarAdopcion dialogo = new DiagRegistrarAdopcion();
                dialogo.setVisible(true);
                cargarDatosAdopciones(); 
            }
        });

        JButton btnEditar = new JButton("Cambiar Estado");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(241, 196, 15));
        btnEditar.setFocusPainted(false);
        btnEditar.setBounds(654, 95, 140, 35);
        add(btnEditar);

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int filaSeleccionada = tableAdopciones.getSelectedRow();
                
                if (filaSeleccionada == -1) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Por favor, seleccione un expediente de la tabla para cambiar su estado.", 
                        "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int modelRow = tableAdopciones.convertRowIndexToModel(filaSeleccionada);

                int codAdopcion = Integer.parseInt(model.getValueAt(modelRow, 0).toString());
                String estadoActual = model.getValueAt(modelRow, 4).toString();
                String obsActuales = model.getValueAt(modelRow, 5).toString();

                DiagCambiarEstadoAdopcion diag = new DiagCambiarEstadoAdopcion(codAdopcion, estadoActual, obsActuales);
                diag.setVisible(true);
                
                cargarDatosAdopciones();
            }
        });

        // ==========================================
        // 🛠️ CORRECCIÓN: CREACIÓN DEL SCROLLPANE Y MODELO
        // ==========================================
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 155, 769, 340);
        add(scrollPane);

        String[] columnas = {"Trámite Nº", "Adoptante", "Mascota", "Fecha Apertura", "Estado Trámite", "Observaciones"};
        
        model = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableAdopciones = new JTable(model);
        tableAdopciones.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableAdopciones.setRowHeight(25);
        tableAdopciones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableAdopciones.getTableHeader().setReorderingAllowed(false);
        
        // Ahora sí, asociamos la tabla al scrollPane que acabamos de crear arriba
        scrollPane.setViewportView(tableAdopciones);

        lblContador = new JLabel("Accediendo al histórico relacional de trámites...");
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

        cargarDatosAdopciones();
    }

    private void filtrarTabla() {
        String query = txtBuscar.getText().toLowerCase().trim();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = 
            new javax.swing.table.TableRowSorter<>(model);
        tableAdopciones.setRowSorter(sorter);
        
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + query, 1, 2));
        }
    }

    public void cargarDatosAdopciones() {
        try {
            _03_model_dao.AdopcionDAO dao = new _03_model_dao.AdopcionDAO();
            java.util.List<_02_modelo_entidad.Adopcion> lista = dao.listarTodos();
            
            model.setRowCount(0);
            
            if (lista != null && !lista.isEmpty()) {
                for (_02_modelo_entidad.Adopcion ad : lista) {
                    Object[] fila = {
                        ad.getCodAdopcion(),
                        ad.getNomAdoptante(),
                        ad.getNomAnimal(),
                        ad.getFechaAdopcion(),
                        ad.getEstadoTramite(),
                        ad.getObservaciones() == null || ad.getObservaciones().isEmpty() ? "Ninguna" : ad.getObservaciones()
                    };
                    model.addRow(fila);
                }
                lblContador.setText("Mostrando " + lista.size() + " expedientes de adopción activos.");
            } else {
                lblContador.setText("No hay expedientes de adopción registrados en el sistema.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar los expedientes de adopción: " + e.getMessage());
            lblContador.setText("Error crítico al conectar con el servidor de adopciones.");
        }
    }
}