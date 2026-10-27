package DAO;

import Conexion.Conectar;
import Modelo.Reporte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO extends Conectar {

    public boolean crear(Reporte r) {
        conectar();
        String sql = "INSERT INTO reporte (id_usuario, tipo_reporte, fecha_inicio, fecha_fin, fecha_generacion, descripcion) VALUES (?,?,?,?,NOW(),?)";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setInt(1, r.getIdUsuario());
            ps.setString(2, r.getTipoReporte());
            ps.setDate(3, r.getFechaInicio());
            ps.setDate(4, r.getFechaFin());
            ps.setString(5, r.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            desconectar();
        }
    }

    public List<Reporte> obtenerTodos() {
        conectar();
        List<Reporte> lista = new ArrayList<>();
        String sql = "SELECT r.*, u.usuario FROM reporte r INNER JOIN usuario_sistema u ON r.id_usuario=u.id_usuario ORDER BY r.id_reporte DESC";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Reporte r = new Reporte(
                    rs.getInt("id_reporte"),
                    rs.getInt("id_usuario"),
                    rs.getString("tipo_reporte"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin"),
                    rs.getTimestamp("fecha_generacion"),
                    rs.getString("descripcion")
                );
                r.setUsuario(rs.getString("usuario"));
                lista.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return lista;
    }
}