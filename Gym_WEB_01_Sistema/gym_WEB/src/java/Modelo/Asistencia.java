package Modelo;

import java.sql.Date;
import java.sql.Time;

public class Asistencia {
    private int idAsistencia;
    private int idSocio;
    private int idUsuario;
    private Date fecha;
    private Time horaIngreso;
    private String estadoAcceso;
    private String observacion;
    private String dni;
    private String socio;
    private String usuario;

    public Asistencia() {
    }

    public Asistencia(int idAsistencia, int idSocio, int idUsuario, Date fecha, Time horaIngreso, String estadoAcceso, String observacion) {
        this.idAsistencia = idAsistencia;
        this.idSocio = idSocio;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
        this.estadoAcceso = estadoAcceso;
        this.observacion = observacion;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getEstadoAcceso() {
        return estadoAcceso;
    }

    public void setEstadoAcceso(String estadoAcceso) {
        this.estadoAcceso = estadoAcceso;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}