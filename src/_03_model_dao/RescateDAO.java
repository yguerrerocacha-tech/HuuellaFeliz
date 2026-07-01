package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.Rescate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RescateDAO {

    public List<Rescate> listarTodos() {
        List<Rescate> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT codRescate, lugar, fechaRescate, codVoluntario, condicionInicial, contactoReporte, estado FROM RESCATE";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Rescate r = new Rescate();
                r.setCodRescate(rs.getInt("codRescate"));
                r.setLugar(rs.getString("lugar"));
                r.setFechaRescate(rs.getTimestamp("fechaRescate"));
                r.setCodVoluntario(rs.getInt("codVoluntario"));
                r.setCondicionInicial(rs.getString("condicionInicial"));
                r.setContactoReporte(rs.getString("contactoReporte"));
                r.setEstado(rs.getString("estado"));
                lista.add(r);
            }
        } catch (SQLException e) {
            System.out.println("Error en RescateDAO.listarTodos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return lista;
    }

    public boolean insertar(Rescate r) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO RESCATE (lugar, fechaRescate, condicionInicial, contactoReporte, estado) VALUES (?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, r.getLugar());
            pstm.setTimestamp(2, new java.sql.Timestamp(r.getFechaRescate().getTime()));
            pstm.setString(3, r.getCondicionInicial());
            pstm.setString(4, r.getContactoReporte());
            pstm.setString(5, r.getEstado());

            if (pstm.executeUpdate() > 0) {
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en RescateDAO.insertar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return insertado;
    }

    public boolean modificarEstado(int codRescate, String nuevoEstado, String observaciones) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean modificado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE RESCATE SET estado = ?, condicionInicial = ? WHERE codRescate = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, nuevoEstado);
            pstm.setString(2, observaciones);
            pstm.setInt(3, codRescate);

            if (pstm.executeUpdate() > 0) {
                modificado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en RescateDAO.modificarEstado: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return modificado;
    }
}