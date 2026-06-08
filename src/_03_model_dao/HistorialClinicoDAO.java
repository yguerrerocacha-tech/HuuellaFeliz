package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.HistorialClinico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistorialClinicoDAO {

    // Método para listar todas las revisiones médicas combinadas con el nombre de la mascota
    public List<HistorialClinico> listarTodos() {
        List<HistorialClinico> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT h.codHistorial, h.codAnimal, h.fechaRevision, h.veterinario, h.diagnostico, h.tratamiento, h.proximaCita, "
                       + "m.nomAnimal "
                       + "FROM HISTORIAL_CLINICO h "
                       + "INNER JOIN ANIMAL m ON h.codAnimal = m.codAnimal "
                       + "ORDER BY h.fechaRevision DESC";
            
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                HistorialClinico h = new HistorialClinico();
                h.setCodHistorial(rs.getInt("codHistorial"));
                h.setCodAnimal(rs.getInt("codAnimal"));
                h.setFechaRevision(rs.getDate("fechaRevision"));
                h.setVeterinario(rs.getString("veterinario"));
                h.setDiagnostico(rs.getString("diagnostico"));
                h.setTratamiento(rs.getString("tratamiento"));
                h.setProximaCita(rs.getDate("proximaCita"));
                h.setNomAnimal(rs.getString("nomAnimal")); // Nombre recuperado del JOIN
                lista.add(h);
            }
        } catch (SQLException e) {
            System.out.println("Error en HistorialClinicoDAO.listarTodos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return lista;
    }

    // Método para registrar una nueva revisión veterinaria en MySQL
    public boolean insertar(HistorialClinico historial) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO HISTORIAL_CLINICO (codAnimal, fechaRevision, veterinario, diagnostico, tratamiento, proximaCita) VALUES (?, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, historial.getCodAnimal());
            pstm.setDate(2, historial.getFechaRevision());
            pstm.setString(3, historial.getVeterinario());
            pstm.setString(4, historial.getDiagnostico());
            pstm.setString(5, historial.getTratamiento());
            pstm.setDate(6, historial.getProximaCita()); // Puede ser null si no se programa cita de control

            if (pstm.executeUpdate() > 0) {
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en HistorialClinicoDAO.insertar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return insertado;
    }
}