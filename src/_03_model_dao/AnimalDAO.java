package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    // METODO PARA LISTAR MASCOTAS CON FOTO
    public List<Animal> listarTodos() {
        List<Animal> listaAnimales = new ArrayList<>();
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT a.codAnimal, a.nomAnimal, a.edad, a.sexo, a.estado, a.fechaIngreso, a.descripcion, a.foto, "
                       + "r.nombreRaza AS raza, e.nombreEspecie AS especie, a.codRaza "
                       + "FROM ANIMAL a "
                       + "INNER JOIN RAZA r ON a.codRaza = r.codRaza "
                       + "INNER JOIN ESPECIE e ON r.codEspecie = e.codEspecie";
            
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Animal a = new Animal();
                a.setCodAnimal(rs.getInt("codAnimal"));
                a.setNomAnimal(rs.getString("nomAnimal"));
                a.setCodRaza(rs.getInt("codRaza"));
                a.setEspecie(rs.getString("especie"));
                a.setRaza(rs.getString("raza"));
                a.setEdad(rs.getInt("edad"));
                a.setSexo(rs.getString("sexo"));
                a.setEstado(rs.getString("estado"));
                a.setFechaIngreso(rs.getDate("fechaIngreso"));
                a.setDescripcion(rs.getString("descripcion")); // Usamos el método estándar
                a.setFoto(rs.getString("foto"));
                listaAnimales.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error en AnimalDAO.listarTodos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return listaAnimales;
    }

    // METODO PARA MODIFICAR MASCOTAS (CORREGIDO SIN EL ORIGINAL_DESCRIPCION)
    public boolean modificar(Animal a) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean modificado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE ANIMAL SET nomAnimal = ?, codRaza = ?, edad = ?, sexo = ?, estado = ?, descripcion = ?, foto = ? WHERE codAnimal = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, a.getNomAnimal());
            pstm.setInt(2, a.getCodRaza());
            pstm.setInt(3, a.getEdad());
            pstm.setString(4, a.getSexo());
            pstm.setString(5, a.getEstado());
            pstm.setString(6, a.getDescripcion()); // Corregido aquí
            pstm.setString(7, a.getFoto());
            pstm.setInt(8, a.getCodAnimal());

            if (pstm.executeUpdate() > 0) {
                modificado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en AnimalDAO.modificar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return modificado;
    }

    // MÉTODO RECUPERADO Y ASEGURADO PARA DIAREGISTRARANIMAL
    public boolean insertar(Animal a) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO ANIMAL (nomAnimal, codRaza, edad, sexo, estado, fechaIngreso, descripcion, foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, a.getNomAnimal());
            pstm.setInt(2, a.getCodRaza());
            pstm.setInt(3, a.getEdad());
            pstm.setString(4, a.getSexo());
            pstm.setString(5, a.getEstado());
            pstm.setDate(6, a.getFechaIngreso());
            pstm.setString(7, a.getDescripcion());
            pstm.setString(8, a.getFoto() == null ? "default.png" : a.getFoto()); // Si viene vacío, pone la de por defecto

            if (pstm.executeUpdate() > 0) {
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error en AnimalDAO.insertar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return insertado;
    }
}