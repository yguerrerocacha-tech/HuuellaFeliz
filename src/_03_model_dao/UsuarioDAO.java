package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método de alta seguridad: Valida DNI, Password, Username y Cargo simultáneamente
    public Usuario loginEstricto(String dni, String pass, String username, String cargo) {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Usuario usuarioValidado = null;

        try {
            cn = Conexion.getConexion();
            // La consulta exige que los 4 campos coincidan exactamente en la BD
            String sql = "SELECT codUsuario, dni, username, nombreCompleto, cargo "
                       + "FROM USUARIO "
                       + "WHERE dni = ? AND password = ? AND username = ? AND cargo = ?";
            
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, dni);
            pstm.setString(2, pass);
            pstm.setString(3, username);
            pstm.setString(4, cargo);
            
            rs = pstm.executeQuery();

            if (rs.next()) {
                usuarioValidado = new Usuario();
                usuarioValidado.setCodUsuario(rs.getInt("codUsuario"));
                usuarioValidado.setDni(rs.getString("dni"));
                usuarioValidado.setUsername(rs.getString("username"));
                usuarioValidado.setNombreCompleto(rs.getString("nombreCompleto"));
                usuarioValidado.setCargo(rs.getString("cargo"));
            }
        } catch (SQLException e) {
            System.out.println("Error en UsuarioDAO.loginEstricto: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return usuarioValidado; 
    }
}