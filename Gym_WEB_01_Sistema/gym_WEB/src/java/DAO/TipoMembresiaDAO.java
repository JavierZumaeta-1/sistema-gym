package DAO;

import Conexion.Conectar;
import Modelo.TipoMembresia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoMembresiaDAO extends Conectar {

    public List<TipoMembresia> obtenerTodos() {
        conectar();
        List<TipoMembresia> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_membresia ORDER BY nombre";
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new TipoMembresia(
                    rs.getInt("id_tipo_membresia"),
                    rs.getString("nombre"),
                    rs.getInt("duracion_dias"),
                    rs.getBigDecimal("precio"),
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
}