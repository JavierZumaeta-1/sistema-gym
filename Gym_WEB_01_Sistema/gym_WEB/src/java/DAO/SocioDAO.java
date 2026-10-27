package DAO;

import Conexion.Conectar;
import Modelo.Socio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SocioDAO extends Conectar {

    public boolean crear(Socio s) {
        conectar();
        String sql = "INSERT INTO socio (dni, nombres, apellidos, telefono, correo, fecha_registro, estado) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, s.getDni());
            ps.setString(2, s.getNombres());
            ps.setString(3, s.getApellidos());
            ps.setString(4, s.getTelefono());
            ps.setString(5, s.getCorreo());
            ps.setDate(6, s.getFechaRegistro());
            ps.setString(7, s.getEstado());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public List<Socio> obtenerTodos() {
        conectar();
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socio ORDER BY id_socio DESC";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Socio(
                    rs.getInt("id_socio"),
                    rs.getString("dni"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getDate("fecha_registro"),
                    rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }

    public List<Socio> buscar(String dato) {
        conectar();
        List<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socio WHERE dni LIKE ? OR nombres LIKE ? OR apellidos LIKE ? ORDER BY id_socio DESC";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            String valor = "%" + dato + "%";
            ps.setString(1, valor);
            ps.setString(2, valor);
            ps.setString(3, valor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Socio(
                        rs.getInt("id_socio"),
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getDate("fecha_registro"),
                        rs.getString("estado")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }

    public boolean actualizar(Socio s) {
        conectar();
        String sql = "UPDATE socio SET dni=?, nombres=?, apellidos=?, telefono=?, correo=?, fecha_registro=?, estado=? WHERE id_socio=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, s.getDni());
            ps.setString(2, s.getNombres());
            ps.setString(3, s.getApellidos());
            ps.setString(4, s.getTelefono());
            ps.setString(5, s.getCorreo());
            ps.setDate(6, s.getFechaRegistro());
            ps.setString(7, s.getEstado());
            ps.setInt(8, s.getIdSocio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public boolean borrar(int idSocio) {
        conectar();
        String sql = "UPDATE socio SET estado='Inactivo' WHERE id_socio=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public Socio obtenerPorId(int idSocio) {
        conectar();
        Socio s = null;
        String sql = "SELECT * FROM socio WHERE id_socio=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s = new Socio(
                        rs.getInt("id_socio"),
                        rs.getString("dni"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getDate("fecha_registro"),
                        rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return s;
    }

    public Date fechaActual() {
        return new Date(System.currentTimeMillis());
    }
}