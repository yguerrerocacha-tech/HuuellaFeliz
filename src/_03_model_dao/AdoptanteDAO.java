package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.Adoptante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdoptanteDAO {

    // METODO DE INSERCIÓN (El que te está pidiendo el JDialog)
    public boolean insertar(Adoptante adoptante) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO ADOPTANTE (dni, nombre, apellidos, telefono, direccion, correo, estadoVerificacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, adoptante.getDni());
            pstm.setString(2, adoptante.getNombre());
            pstm.setString(3, adoptante.getApellidos());
            pstm.setString(4, adoptante.getTelefono());
            pstm.setString(5, adoptante.getDireccion());
            pstm.setString(6, adoptante.getCorreo());
            pstm.setString(7, adoptante.getEstadoVerificacion());

            if (pstm.executeUpdate() > 0) {
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en AdoptanteDAO.insertar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return insertado;
    }

    // METODO PARA LISTAR LOS ADOPTANTES EN TU TABLA PRINCIPAL
    public List<Adoptante> listarTodos() {
        List<Adoptante> lista = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT codAdoptante, dni, nombre, apellidos, telefono, direccion, correo, estadoVerificacion FROM ADOPTANTE";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Adoptante a = new Adoptante();
                a.setCodAdoptante(rs.getInt("codAdoptante"));
                a.setDni(rs.getString("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setApellidos(rs.getString("apellidos"));
                a.setTelefono(rs.getString("telefono"));
                a.setDireccion(rs.getString("direccion"));
                a.setCorreo(rs.getString("correo"));
                a.setEstadoVerificacion(rs.getString("estadoVerificacion"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error en AdoptanteDAO.listarTodos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return lista;
    }

    // METODO PARA MODIFICAR LOS DATOS (El botón amarillo)
    public boolean modificar(Adoptante adoptante) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean actualizado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE ADOPTANTE SET dni=?, nombre=?, apellidos=?, telefono=?, direccion=?, correo=?, estadoVerificacion=? WHERE codAdoptante=?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, adoptante.getDni());
            pstm.setString(2, adoptante.getNombre());
            pstm.setString(3, adoptante.getApellidos());
            pstm.setString(4, adoptante.getTelefono());
            pstm.setString(5, adoptante.getDireccion());
            pstm.setString(6, adoptante.getCorreo());
            pstm.setString(7, adoptante.getEstadoVerificacion());
            pstm.setInt(8, adoptante.getCodAdoptante());

            if (pstm.executeUpdate() > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en AdoptanteDAO.modificar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return actualizado;
    }
}