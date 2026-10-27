package DAO;

import Conexion.Conectar;
import Modelo.Asistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO extends Conectar {

    public boolean crear(Asistencia a) {
        conectar();
        String sql = "INSERT INTO asistencia (id_socio, id_usuario, fecha, hora_ingreso, estado_acceso, observacion) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, a.getIdSocio());
            ps.setInt(2, a.getIdUsuario());
            ps.setDate(3, a.getFecha());
            ps.setTime(4, a.getHoraIngreso());
            ps.setString(5, a.getEstadoAcceso());
            ps.setString(6, a.getObservacion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public String registrarPorDni(String dni, int idUsuario) {
        conectar();
        String mensaje = "Socio no registrado";
        int idSocio = 0;
        String sqlSocio = "SELECT id_socio FROM socio WHERE dni=? AND estado='Activo'";
        String sqlMembresia = "SELECT id_membresia FROM membresia WHERE id_socio=? AND estado='Activo' AND CURDATE() BETWEEN fecha_inicio AND fecha_fin ORDER BY fecha_fin DESC LIMIT 1";
        String sqlInsert = "INSERT INTO asistencia (id_socio, id_usuario, fecha, hora_ingreso, estado_acceso, observacion) VALUES (?,?,CURDATE(),CURTIME(),?,?)";
        try {
            try (PreparedStatement ps = getConexion().prepareStatement(sqlSocio)) {
                ps.setString(1, dni);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idSocio = rs.getInt("id_socio");
                    }
                }
            }
            if (idSocio == 0) {
                return mensaje;
            }
            boolean activo = false;
            try (PreparedStatement ps = getConexion().prepareStatement(sqlMembresia)) {
                ps.setInt(1, idSocio);
                try (ResultSet rs = ps.executeQuery()) {
                    activo = rs.next();
                }
            }
            try (PreparedStatement ps = getConexion().prepareStatement(sqlInsert)) {
                ps.setInt(1, idSocio);
                ps.setInt(2, idUsuario);
                if (activo) {
                    ps.setString(3, "Permitido");
                    ps.setString(4, "Ingreso registrado");
                    mensaje = "Acceso permitido";
                } else {
                    ps.setString(3, "Denegado");
                    ps.setString(4, "Membresia vencida o inactiva");
                    mensaje = "Acceso denegado";
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo registrar la asistencia";
        } finally {
            desconectar();
        }
        return mensaje;
    }

    public List<Asistencia> obtenerTodos() {
        conectar();
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT a.*, s.dni, CONCAT(s.nombres,' ',s.apellidos) AS socio, u.usuario FROM asistencia a INNER JOIN socio s ON a.id_socio=s.id_socio INNER JOIN usuario_sistema u ON a.id_usuario=u.id_usuario ORDER BY a.id_asistencia DESC";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Asistencia a = new Asistencia(
                    rs.getInt("id_asistencia"),
                    rs.getInt("id_socio"),
                    rs.getInt("id_usuario"),
                    rs.getDate("fecha"),
                    rs.getTime("hora_ingreso"),
                    rs.getString("estado_acceso"),
                    rs.getString("observacion")
                );
                a.setDni(rs.getString("dni"));
                a.setSocio(rs.getString("socio"));
                a.setUsuario(rs.getString("usuario"));
                lista.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }

    public List<Asistencia> obtenerRecientes(int limite) {
        conectar();
        List<Asistencia> lista = new ArrayList<>();
        String sql = "SELECT a.*, s.dni, CONCAT(s.nombres,' ',s.apellidos) AS socio, u.usuario FROM asistencia a INNER JOIN socio s ON a.id_socio=s.id_socio INNER JOIN usuario_sistema u ON a.id_usuario=u.id_usuario ORDER BY a.id_asistencia DESC LIMIT ?";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asistencia a = new Asistencia(
                        rs.getInt("id_asistencia"),
                        rs.getInt("id_socio"),
                        rs.getInt("id_usuario"),
                        rs.getDate("fecha"),
                        rs.getTime("hora_ingreso"),
                        rs.getString("estado_acceso"),
                        rs.getString("observacion")
                    );
                    a.setDni(rs.getString("dni"));
                    a.setSocio(rs.getString("socio"));
                    a.setUsuario(rs.getString("usuario"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }

    public Date fechaActual() {
        return new Date(System.currentTimeMillis());
    }

    public Time horaActual() {
        return new Time(System.currentTimeMillis());
    }
}