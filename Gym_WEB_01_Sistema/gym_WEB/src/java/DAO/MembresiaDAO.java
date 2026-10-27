package DAO;

import Conexion.Conectar;
import Modelo.Membresia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MembresiaDAO extends Conectar {

    public boolean crear(Membresia m) {
        conectar();
        String sql = "INSERT INTO membresia (id_socio, id_tipo_membresia, fecha_inicio, fecha_fin, estado) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, m.getIdSocio());
            ps.setInt(2, m.getIdTipoMembresia());
            ps.setDate(3, m.getFechaInicio());
            ps.setDate(4, m.getFechaFin());
            ps.setString(5, m.getEstado());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public List<Membresia> obtenerTodos() {
        conectar();
        List<Membresia> lista = new ArrayList<>();
        String sql = "SELECT m.*, s.dni, CONCAT(s.nombres,' ',s.apellidos) AS socio, t.nombre AS tipo_membresia, t.precio FROM membresia m INNER JOIN socio s ON m.id_socio=s.id_socio INNER JOIN tipo_membresia t ON m.id_tipo_membresia=t.id_tipo_membresia ORDER BY m.id_membresia DESC";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Membresia m = new Membresia(
                    rs.getInt("id_membresia"),
                    rs.getInt("id_socio"),
                    rs.getInt("id_tipo_membresia"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getString("estado")
                );
                m.setDni(rs.getString("dni"));
                m.setSocio(rs.getString("socio"));
                m.setTipoMembresia(rs.getString("tipo_membresia"));
                m.setPrecio(rs.getBigDecimal("precio"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }

    public Membresia obtenerPorId(int idMembresia) {
        conectar();
        Membresia m = null;
        String sql = "SELECT m.*, s.dni, CONCAT(s.nombres,' ',s.apellidos) AS socio, t.nombre AS tipo_membresia, t.precio FROM membresia m INNER JOIN socio s ON m.id_socio=s.id_socio INNER JOIN tipo_membresia t ON m.id_tipo_membresia=t.id_tipo_membresia WHERE m.id_membresia=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, idMembresia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m = new Membresia(
                        rs.getInt("id_membresia"),
                        rs.getInt("id_socio"),
                        rs.getInt("id_tipo_membresia"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getString("estado")
                    );
                    m.setDni(rs.getString("dni"));
                    m.setSocio(rs.getString("socio"));
                    m.setTipoMembresia(rs.getString("tipo_membresia"));
                    m.setPrecio(rs.getBigDecimal("precio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return m;
    }

    public boolean actualizar(Membresia m) {
        conectar();
        String sql = "UPDATE membresia SET id_socio=?, id_tipo_membresia=?, fecha_inicio=?, fecha_fin=?, estado=? WHERE id_membresia=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, m.getIdSocio());
            ps.setInt(2, m.getIdTipoMembresia());
            ps.setDate(3, m.getFechaInicio());
            ps.setDate(4, m.getFechaFin());
            ps.setString(5, m.getEstado());
            ps.setInt(6, m.getIdMembresia());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public boolean borrar(int idMembresia) {
        conectar();
        String sql = "UPDATE membresia SET estado='Inactivo' WHERE id_membresia=?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, idMembresia);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }
}