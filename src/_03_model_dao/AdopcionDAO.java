package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.Adopcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdopcionDAO {

    public List<Adopcion> listarTodos() {
        List<Adopcion> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT a.codAdopcion, a.codAdoptante, a.codAnimal, a.fechaAdopcion, a.estadoTramite, a.observaciones, "
                       + "CONCAT(ad.nombre, ' ', ad.apellidos) AS nomAdoptante, m.nomAnimal "
                       + "FROM ADOPCION a "
                       + "INNER JOIN ADOPTANTE ad ON a.codAdoptante = ad.codAdoptante "
                       + "INNER JOIN ANIMAL m ON a.codAnimal = m.codAnimal";
            
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Adopcion ad = new Adopcion();
                ad.setCodAdopcion(rs.getInt("codAdopcion"));
                ad.setCodAdoptante(rs.getInt("codAdoptante"));
                ad.setCodAnimal(rs.getInt("codAnimal"));
                ad.setFechaAdopcion(rs.getDate("fechaAdopcion"));
                ad.setEstadoTramite(rs.getString("estadoTramite"));
                ad.setObservaciones(rs.getString("observaciones"));
                ad.setNomAdoptante(rs.getString("nomAdoptante"));
                ad.setNomAnimal(rs.getString("nomAnimal"));
                lista.add(ad);
            }
        } catch (SQLException e) {
            System.out.println("Error en AdopcionDAO.listarTodos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return lista;
    }

    public boolean insertar(Adopcion adopcion) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO ADOPCION (codAdoptante, codAnimal, fechaAdopcion, estadoTramite, observaciones) "
                       + "VALUES (?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, adopcion.getCodAdoptante());
            pstm.setInt(2, adopcion.getCodAnimal());
            pstm.setDate(3, adopcion.getFechaAdopcion());
            pstm.setString(4, adopcion.getEstadoTramite());
            pstm.setString(5, adopcion.getObservaciones());

            if (pstm.executeUpdate() > 0) {
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en AdopcionDAO.insertar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return insertado;
    }
    public boolean actualizarEstado(int codAdopcion, String nuevoEstado, String observaciones) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean actualizado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE ADOPCION SET estadoTramite = ?, observaciones = ? WHERE codAdopcion = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, nuevoEstado);
            pstm.setString(2, observaciones);
            pstm.setInt(3, codAdopcion);

            if (pstm.executeUpdate() > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en AdopcionDAO.actualizarEstado: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return actualizado;
    }
}