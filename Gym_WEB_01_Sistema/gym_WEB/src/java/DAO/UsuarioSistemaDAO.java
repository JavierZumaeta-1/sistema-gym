package DAO;

import Conexion.Conectar;
import Modelo.UsuarioSistema;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioSistemaDAO extends Conectar {

    public UsuarioSistema validar(String usuario, String clave) {
        conectar();
        UsuarioSistema u = null;
        String sql = "SELECT u.*, r.nombre_rol FROM usuario_sistema u INNER JOIN rol r ON u.id_rol = r.id_rol WHERE u.usuario=? AND u.clave=? AND u.estado='Activo'";
        try (PreparedStatement ps = getConexion().prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, clave);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new UsuarioSistema(
                        rs.getInt("id_usuario"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("usuario"),
                        rs.getString("clave"),
                        rs.getString("correo"),
                        rs.getString("estado"),
                        rs.getInt("id_rol"),
                        rs.getString("nombre_rol")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return u;
    }
}