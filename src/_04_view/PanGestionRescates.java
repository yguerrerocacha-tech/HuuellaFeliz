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

public class PanGestionRescates extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtBuscar;
    private JTable tableRescates;
    private DefaultTableModel model;
    private JLabel lblContador;

    public PanGestionRescates() {
        setBackground(new Color(245, 246, 248));
        setSize(824, 571);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Control y Bitácora de Rescates");
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(25, 20, 400, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Monitoree los casos de rescate en curso, locaciones de hallazgo y estados clínicos.");
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

        JButton btnNuevo = new JButton("+ Registrar Caso");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBounds(494, 95, 150, 35);
        add(btnNuevo);

        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                DiagRegistrarRescate dialogo = new DiagRegistrarRescate();
                dialogo.setVisible(true);
                cargarDatosRescates(); 
            }
        });

        JButton btnEditar = new JButton("Actualizar Estado");
        btnEditar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEditar.setForeground(Color.WHITE);
        btnEditar.setBackground(new Color(241, 196, 15));
        btnEditar.setFocusPainted(false);
        btnEditar.setBounds(654, 95, 140, 35);
        add(btnEditar);

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int filaSeleccionada = tableRescates.getSelectedRow();
                
                if (filaSeleccionada == -1) {
                    javax.swing.JOptionPane.showMessageDialog(null, 
                        "Por favor, seleccione un caso de rescate de la tabla para actualizar.", 
                        "Aviso", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int modelRow = tableRescates.convertRowIndexToModel(filaSeleccionada);

                int id = Integer.parseInt(model.getValueAt(modelRow, 0).toString());
                String condicion = model.getValueAt(modelRow, 3).toString();
                String estado = model.getValueAt(modelRow, 5).toString();

                DiagActualizarRescate diag = new DiagActualizarRescate(id, estado, condicion);
                diag.setVisible(true);
                
                cargarDatosRescates();
            }
        });

        // ========================================================
        // SOLUCIÓN: INICIALIZACIÓN DE CONTENEDORES Y TABLA
        // ========================================================
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 155, 769, 340);
        add(scrollPane);

        String[] columnas = {"N° Caso", "Lugar de Hallazgo", "Fecha Rescate", "Condición Inicial", "Contacto Reporte", "Estado Actual"};
        
        model = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableRescates = new JTable(model);
        tableRescates.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableRescates.setRowHeight(25);
        tableRescates.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableRescates.getTableHeader().setReorderingAllowed(false);
        
        // Asociamos la tabla dentro del scrollPane que ya existe
        scrollPane.setViewportView(tableRescates);

        lblContador = new JLabel("Conectando con el registro histórico de rescates...");
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

        cargarDatosRescates();
    }

    private void filtrarTabla() {
        String query = txtBuscar.getText().toLowerCase().trim();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = 
            new javax.swing.table.TableRowSorter<>(model);
        tableRescates.setRowSorter(sorter);
        
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + query, 1, 3)); 
        }
    }

    public void cargarDatosRescates() {
        try {
            _03_model_dao.RescateDAO daoReal = new _03_model_dao.RescateDAO();
            java.util.List<_02_modelo_entidad.Rescate> lista = daoReal.listarTodos();
            
            model.setRowCount(0);
            
            if (lista != null && !lista.isEmpty()) {
                for (_02_modelo_entidad.Rescate r : lista) {
                    Object[] fila = {
                        r.getCodRescate(),
                        r.getLugar(),
                        r.getFechaRescate(),
                        r.getCondicionInicial(),
                        r.getContactoReporte() == null || r.getContactoReporte().isEmpty() ? "Anónimo" : r.getContactoReporte(),
                        r.getEstado()
                    };
                    model.addRow(fila);
                }
                lblContador.setText("Mostrando " + lista.size() + " casos de rescate registrados.");
            } else {
                lblContador.setText("No hay registros de rescates en la base de datos actualmente.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar la bitácora de rescates: " + e.getMessage());
            lblContador.setText("Error al conectar con el servidor de rescates.");
        }
    }
}