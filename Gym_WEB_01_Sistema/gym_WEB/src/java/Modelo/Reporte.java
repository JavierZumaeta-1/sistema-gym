package Modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class Reporte {
    private int idReporte;
    private int idUsuario;
    private String tipoReporte;
    private Date fechaInicio;
    private Date fechaFin;
    private Timestamp fechaGeneracion;
    private String descripcion;
    private String usuario;

    public Reporte() {
    }

    public Reporte(int idReporte, int idUsuario, String tipoReporte, Date fechaInicio, Date fechaFin, Timestamp fechaGeneracion, String descripcion) {
        this.idReporte = idReporte;
        this.idUsuario = idUsuario;
        this.tipoReporte = tipoReporte;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaGeneracion = fechaGeneracion;
        this.descripcion = descripcion;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Timestamp getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Timestamp fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }   

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}