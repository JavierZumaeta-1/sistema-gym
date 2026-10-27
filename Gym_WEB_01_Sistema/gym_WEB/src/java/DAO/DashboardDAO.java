package DAO;

import Conexion.Conectar;
import Modelo.Dashboard;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardDAO extends Conectar {

    public Dashboard obtenerResumen() {
        Dashboard d = new Dashboard();
        d.setSociosActivos(contar("SELECT COUNT(*) FROM socio WHERE estado='Activo'"));
        d.setAsistenciasHoy(contar("SELECT COUNT(*) FROM asistencia WHERE fecha=CURDATE() AND estado_acceso='Permitido'"));
        d.setMembresiasVencidas(contar("SELECT COUNT(*) FROM membresia WHERE estado='Vencido' OR fecha_fin < CURDATE()"));
        d.setMembresiasProximas(contar("SELECT COUNT(*) FROM membresia WHERE fecha_fin BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)"));
        d.setIngresosEstimados(monto("SELECT IFNULL(SUM(t.precio),0) FROM membresia m INNER JOIN tipo_membresia t ON m.id_tipo_membresia=t.id_tipo_membresia WHERE m.estado='Activo' AND MONTH(m.fecha_inicio)=MONTH(CURDATE()) AND YEAR(m.fecha_inicio)=YEAR(CURDATE())"));
        return d;
    }

    private int contar(String sql) {
        conectar();
        int total = 0;
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return total;
    }

    private BigDecimal monto(String sql) {
        conectar();
        BigDecimal total = BigDecimal.ZERO;
        try (Statement st = getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return total;
    }
}