package _03_model_dao;

import _01_conexion.Conexion;
import _02_modelo_entidad.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public boolean insertar(Animal animal) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean insertado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "INSERT INTO ANIMAL (nomAnimal, especie, raza, edad, sexo, estado) VALUES (?, ?, ?, ?, ?, ?)";
            
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, animal.getNomAnimal());
            pstm.setString(2, animal.getEspecie());
            pstm.setString(3, animal.getRaza());
            pstm.setInt(4, animal.getEdad());
            pstm.setString(5, animal.getSexo());
            pstm.setString(6, animal.getEstado());

            if (pstm.executeUpdate() > 0) {
                insertado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error técnico en AnimalDAO.insertar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return insertado;
    }

    public boolean modificar(Animal animal) {
        Connection cn = null;
        PreparedStatement pstm = null;
        boolean actualizado = false;

        try {
            cn = Conexion.getConexion();
            String sql = "UPDATE ANIMAL SET nomAnimal=?, especie=?, raza=?, edad=?, sexo=?, estado=? WHERE codAnimal=?";
            
            pstm = cn.prepareStatement(sql);
            pstm.setString(1, animal.getNomAnimal());
            pstm.setString(2, animal.getEspecie());
            pstm.setString(3, animal.getRaza());
            pstm.setInt(4, animal.getEdad());
            pstm.setString(5, animal.getSexo());
            pstm.setString(6, animal.getEstado());
            pstm.setInt(7, animal.getCodAnimal());

            int filasAfectadas = pstm.executeUpdate();
            if (filasAfectadas > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            System.out.println("Error técnico en AnimalDAO.modificar: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return actualizado;
    }

    public List<Animal> buscarMascota(String nombre) {
        Connection cn = null;
        CallableStatement cstm = null;
        ResultSet rs = null;
        List<Animal> lista = new ArrayList<>();

        try {
            cn = Conexion.getConexion();
            String sql = "{call pa_animal_BuscarPorNombre(?)}";
            
            cstm = cn.prepareCall(sql);
            cstm.setString(1, nombre);
            
            rs = cstm.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setCodAnimal(rs.getInt("codAnimal"));
                animal.setNomAnimal(rs.getString("nomAnimal"));
                animal.setEspecie(rs.getString("especie"));
                animal.setRaza(rs.getString("raza"));
                animal.setEdad(rs.getInt("edad"));
                animal.setSexo(rs.getString("sexo"));
                animal.setEstado(rs.getString("estado"));
                
                lista.add(animal);
            }
        } catch (SQLException e) {
            System.out.println("Error técnico en AnimalDAO.buscarMascota: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (cstm != null) cstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return lista;
    }

	public List<Animal> listarTodos() {
        Connection cn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Animal> lista = new ArrayList<>();

        try {
            cn = Conexion.getConexion();
            String sql = "SELECT codAnimal, nomAnimal, especie, raza, edad, sexo, estado FROM ANIMAL";
            
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Animal animal = new Animal();
                animal.setCodAnimal(rs.getInt("codAnimal"));
                animal.setNomAnimal(rs.getString("nomAnimal"));
                animal.setEspecie(rs.getString("especie"));
                animal.setRaza(rs.getString("raza"));
                animal.setEdad(rs.getInt("edad"));
                animal.setSexo(rs.getString("sexo"));
                animal.setEstado(rs.getString("estado"));
                
                lista.add(animal);
            }
        } catch (SQLException e) {
            System.out.println("Error técnico en AnimalDAO.listarTodos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (cn != null) cn.close();
            } catch (SQLException e) { System.out.println(e.getMessage()); }
        }
        return lista;
    }
}