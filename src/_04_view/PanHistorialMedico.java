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

public class PanHistorialMedico extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtBuscar;
    private JTable tableHistorial;
    private DefaultTableModel model;
    private JLabel lblContador;

    public PanHistorialMedico() {
        setBackground(new Color(245, 246, 248));
        setSize(824, 571);
        setLayout(null);

        // Cabecera del Panel Clínico
        JLabel lblTitulo = new JLabel("Historial Médico y Revisiones");
        lblTitulo.setForeground(new Color(44, 62, 80));
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setBounds(25, 20, 450, 30);
        add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Consulte los diagnósticos, tratamientos aplicados, costos médicos y próximas citas de control.");
        lblSubtitulo.setForeground(new Color(127, 141, 141));
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setBounds(25, 48, 650, 20);
        add(lblSubtitulo);

        // Barra de filtrado rápido
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

        // Botón para ingresar un nuevo chequeo médico
        JButton btnNuevo = new JButton("+ Nueva Revisión");
        btnNuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setBackground(new Color(46, 204, 113));
        btnNuevo.setFocusPainted(false);
        btnNuevo.setBounds(644, 95, 150, 35);
        add(btnNuevo);

        // Contenedor scroll para la tabla médica
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 155, 769, 340);
        add(scrollPane);

        // Columnas alineadas al diseño clínico de tu base de datos
        String[] columnas = {"ID", "Paciente (Mascota)", "Fecha Revisión", "Veterinario", "Diagnóstico", "Costo", "Próxima Cita"};
        
        model = new DefaultTableModel(null, columnas) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva la edición directa en las celdas
            }
        };

        tableHistorial = new JTable(model);
        tableHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableHistorial.setRowHeight(25);
        tableHistorial.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tableHistorial.getTableHeader().setReorderingAllowed(false);
        
        scrollPane.setViewportView(tableHistorial);

        lblContador = new JLabel("Estableciendo conexión con los expedientes médicos...");
        lblContador.setForeground(Color.GRAY);
        lblContador.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblContador.setBounds(25, 505, 450, 15);
        add(lblContador);

        // Lógica interactiva del buscador
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

        // Evento para añadir nueva revisión médica 
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                DiagRegistrarRevision dialogo = new DiagRegistrarRevision();
                dialogo.setVisible(true);
                cargarDatosHistorial(); 
            }
        });

        // Carga la data histórica automáticamente al abrir la vista
        cargarDatosHistorial();
    }

    private void filtrarTabla() {
        String query = txtBuscar.getText().toLowerCase().trim();
        javax.swing.table.TableRowSorter<javax.swing.table.DefaultTableModel> sorter = 
            new javax.swing.table.TableRowSorter<>(model);
        tableHistorial.setRowSorter(sorter);
        
        if (query.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            // Filtra por Mascota (columna 1), Veterinario (columna 3) o Diagnóstico (columna 4)
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + query, 1, 3, 4)); 
        }
    }

    public void cargarDatosHistorial() {
        try {
            _03_model_dao.HistorialClinicoDAO dao = new _03_model_dao.HistorialClinicoDAO();
            java.util.List<_02_modelo_entidad.HistorialClinico> lista = dao.listarTodos();
            
            model.setRowCount(0); 
            
            if (lista != null && !lista.isEmpty()) {
                for (_02_modelo_entidad.HistorialClinico h : lista) {
                    Object[] fila = {
                        h.getCodHistorial(),
                        h.getNombreAnimal(), 
                        h.getFechaRevision(),
                        h.getVeterinario(),
                        h.getDiagnostico(),
                        "S/ " + h.getCostoAtencion(),
                        h.getProximaCita() == null ? "No requiere" : h.getProximaCita()
                    };
                    model.addRow(fila);
                }
                lblContador.setText("Mostrando " + lista.size() + " registros en el historial médico.");
            } else {
                lblContador.setText("No se registran atenciones clínicas en la base de datos.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el historial clínico: " + e.getMessage());
            lblContador.setText("Error crítico al enlazar con el servidor médico.");
        }
    }
}