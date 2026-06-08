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

    // Método para listar todos los rescates desde MySQL
    public List<Rescate> listarTodos() {
        List<Rescate> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT codRescate, lugar, fechaRescate, condicionInicial, contactoReporte, estado FROM RESCATE";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Rescate r = new Rescate();
                r.setCodRescate(rs.getInt("codRescate"));
                r.setLugar(rs.getString("lugar"));
                r.setFechaRescate(rs.getDate("fechaRescate"));
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

    // Método para registrar un nuevo rescate en la base de datos
    public boolean insertar(Rescate rescate) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO RESCATE (lugar, fechaRescate, condicionInicial, contactoReporte, estado) VALUES (?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, rescate.getLugar());
            pstm.setDate(2, rescate.getFechaRescate());
            pstm.setString(3, rescate.getCondicionInicial());
            pstm.setString(4, rescate.getContactoReporte());
            pstm.setString(5, rescate.getEstado());

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
    public boolean modificarEstado(int codRescate, String nuevoEstado, String condicionActualizada) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean actualizado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE RESCATE SET estado = ?, condicionInicial = ? WHERE codRescate = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, nuevoEstado);
            pstm.setString(2, condicionActualizada);
            pstm.setInt(3, codRescate);

            if (pstm.executeUpdate() > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error técnico en RescateDAO.modificarEstado: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return actualizado;
    }
}