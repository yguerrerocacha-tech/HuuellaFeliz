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

    // METODO PARA LISTAR EL HISTORIAL CLINICO EN TU JTABLE
    public List<HistorialClinico> listarTodos() {
        List<HistorialClinico> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT h.codHistorial, h.codAnimal, h.fechaRevision, h.veterinario, h.diagnostico, h.tratamiento, h.costoAtencion, h.proximaCita, "
                       + "a.nomAnimal FROM HISTORIAL_CLINICO h "
                       + "INNER JOIN ANIMAL a ON h.codAnimal = a.codAnimal";
            
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
                h.setCostoAtencion(rs.getDouble("costoAtencion"));
                h.setProximaCita(rs.getDate("proximaCita"));
                h.setNombreAnimal(rs.getString("nomAnimal"));
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

    // NUEVO MÉTODO: PERMITE REGISTRAR UNA NUEVA REVISIÓN DESDE DIAGREGISTRARREVISION
    public boolean insertar(HistorialClinico h) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO HISTORIAL_CLINICO (codAnimal, fechaRevision, veterinario, diagnostico, tratamiento, costoAtencion, proximaCita) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, h.getCodAnimal());
            pstm.setDate(2, h.getFechaRevision());
            pstm.setString(3, h.getVeterinario());
            pstm.setString(4, h.getDiagnostico());
            pstm.setString(5, h.getTratamiento());
            pstm.setDouble(6, h.getCostoAtencion());
            
            if (h.getProximaCita() != null) {
                pstm.setDate(7, h.getProximaCita());
            } else {
                pstm.setNull(7, java.sql.Types.DATE);
            }

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