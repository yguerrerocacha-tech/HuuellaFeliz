package _04_view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.SystemColor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelContenedor; 

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    try {
                        javax.swing.UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
                    } catch (Exception e) {
                        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
                    }
                    
                    FrmMenuPrincipal frame = new FrmMenuPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmMenuPrincipal() {
        setTitle("HuellaFeliz - Sistema de Gestión de Adopciones v1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 680); 
        setLocationRelativeTo(null); 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

     
        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(new Color(34, 49, 63)); 
        panelMenu.setBounds(0, 0, 260, 641);
        contentPane.add(panelMenu);
        panelMenu.setLayout(null);

        JLabel lblLogo = new JLabel("HuellaFeliz");
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblLogo.setBounds(10, 25, 240, 30);
        panelMenu.add(lblLogo);

        JLabel lblRol = new JLabel("MÓDULO ADMINISTRATIVO");
        lblRol.setHorizontalAlignment(SwingConstants.CENTER);
        lblRol.setForeground(new Color(149, 165, 166)); 
        lblRol.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblRol.setBounds(10, 55, 240, 15);
        panelMenu.add(lblRol);
        
        // BOTONES DEL MENÚ
        JButton btnMascotas = new JButton(" Gestión de Mascotas");
        btnMascotas.setHorizontalAlignment(SwingConstants.LEFT);
        btnMascotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnMascotas.setForeground(Color.WHITE);
        btnMascotas.setBackground(new Color(44, 62, 80)); 
        btnMascotas.setFocusPainted(false);
        btnMascotas.setBorderPainted(false);
        btnMascotas.setBounds(15, 120, 230, 40);
        panelMenu.add(btnMascotas);

        JButton btnAdoptantes = new JButton(" Registro de Adoptantes");
        btnAdoptantes.setHorizontalAlignment(SwingConstants.LEFT);
        btnAdoptantes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnAdoptantes.setForeground(Color.WHITE);
        btnAdoptantes.setBackground(new Color(44, 62, 80));
        btnAdoptantes.setFocusPainted(false);
        btnAdoptantes.setBorderPainted(false);
        btnAdoptantes.setBounds(15, 175, 230, 40);
        panelMenu.add(btnAdoptantes);

        JButton btnRescates = new JButton(" Control de Rescates");
        btnRescates.setHorizontalAlignment(SwingConstants.LEFT);
        btnRescates.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRescates.setForeground(Color.WHITE);
        btnRescates.setBackground(new Color(44, 62, 80));
        btnRescates.setFocusPainted(false);
        btnRescates.setBorderPainted(false);
        btnRescates.setBounds(15, 230, 230, 40);
        panelMenu.add(btnRescates);

        JButton btnAdopciones = new JButton(" Proceso de Adopción");
        btnAdopciones.setHorizontalAlignment(SwingConstants.LEFT);
        btnAdopciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnAdopciones.setForeground(Color.WHITE);
        btnAdopciones.setBackground(new Color(44, 62, 80));
        btnAdopciones.setFocusPainted(false);
        btnAdopciones.setBorderPainted(false);
        btnAdopciones.setBounds(15, 285, 230, 40);
        panelMenu.add(btnAdopciones);
        
        JButton btnRevisiones = new JButton(" Revisiones Médicas");
        btnRevisiones.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnRevisiones.setHorizontalAlignment(SwingConstants.LEFT);
        btnRevisiones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRevisiones.setForeground(Color.WHITE);
        btnRevisiones.setBackground(new Color(44, 62, 80));
        btnRevisiones.setFocusPainted(false);
        btnRevisiones.setBorderPainted(false);
        btnRevisiones.setBounds(15, 340, 230, 40);
        panelMenu.add(btnRevisiones);

        JButton btnCerrarSesion = new JButton(" Cerrar Sesión");
        btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
        btnCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCerrarSesion.setForeground(new Color(231, 76, 60)); 
        btnCerrarSesion.setBackground(new Color(44, 62, 80));
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorderPainted(false);
        btnCerrarSesion.setBounds(15, 570, 230, 40);
        panelMenu.add(btnCerrarSesion);

 
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(Color.WHITE);
        panelHeader.setBounds(260, 0, 824, 70);
        contentPane.add(panelHeader);
        panelHeader.setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido al Sistema, Usuario Organizador");
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblBienvenida.setForeground(new Color(50, 50, 50));
        lblBienvenida.setBounds(25, 23, 400, 25);
        panelHeader.add(lblBienvenida);
        
        JLabel lblFecha = new JLabel("Sede Central - Lima - San Miguel");
        lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblFecha.setForeground(SystemColor.textInactiveText);
       lblFecha.setBounds(600, 25, 200, 20);
        panelHeader.add(lblFecha);

        panelContenedor = new JPanel(); 
        panelContenedor.setBackground(new Color(245, 246, 248)); 
        panelContenedor.setBounds(260, 70, 824, 571);
        contentPane.add(panelContenedor);
        panelContenedor.setLayout(new CardLayout(0, 0));
        
        JPanel panelInicio = new JPanel();
        panelInicio.setBackground(new Color(245, 246, 248));
        panelContenedor.add(panelInicio, "PanelInicio");
        panelInicio.setLayout(null);
        
        JLabel lblTxtInicio = new JLabel("Seleccione una opción del menú izquierdo para comenzar");
        lblTxtInicio.setHorizontalAlignment(SwingConstants.CENTER);
        lblTxtInicio.setForeground(SystemColor.textInactiveText);
        lblTxtInicio.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblTxtInicio.setBounds(162, 250, 500, 30);
        panelInicio.add(lblTxtInicio);

        javax.swing.JPanel vistaMascotas = new _04_view.PanGestionAnimales(); 
        javax.swing.JPanel vistaAdoptantes = new _04_view.PanGestionAdoptantes();
 
        javax.swing.JPanel vistaRescates = new _04_view.PanGestionRescates();
    
        javax.swing.JPanel vistaAdopciones = new _04_view.PanProcesoAdopciones();
        javax.swing.JPanel vistaHistorial = new _04_view.PanHistorialMedico();
        
        panelContenedor.add(vistaMascotas, "PanelMascotas");
        panelContenedor.add(vistaAdoptantes, "PanelAdoptantes");
        panelContenedor.add(vistaRescates, "PanelRescates");
        panelContenedor.add(vistaAdopciones, "PanelAdopciones");
        panelContenedor.add(vistaHistorial, "PanelHistorial");
        
        btnMascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                java.awt.CardLayout card = (java.awt.CardLayout) panelContenedor.getLayout();
                card.show(panelContenedor, "PanelMascotas");
            }
        });

        btnAdoptantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                java.awt.CardLayout card = (java.awt.CardLayout) panelContenedor.getLayout();
                card.show(panelContenedor, "PanelAdoptantes");
            }
        });
     
        btnRevisiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                
                java.awt.CardLayout card = (java.awt.CardLayout) panelContenedor.getLayout();
                
                card.show(panelContenedor, "PanelHistorial");
            }
        });

        btnRescates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                java.awt.CardLayout card = (java.awt.CardLayout) panelContenedor.getLayout();
                card.show(panelContenedor, "PanelRescates");
            }
        });

        btnAdopciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                java.awt.CardLayout card = (java.awt.CardLayout) panelContenedor.getLayout();
                card.show(panelContenedor, "PanelAdopciones");
            }
        });
        
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose(); // Destruye y cierra la ventana actual del Menú Principal
                
                _04_view.FrmLogin login = new _04_view.FrmLogin();
                login.setVisible(true); 
            }
        });
    }
}