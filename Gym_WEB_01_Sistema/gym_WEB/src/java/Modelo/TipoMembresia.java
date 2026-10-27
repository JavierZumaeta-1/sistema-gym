package Modelo;

import java.math.BigDecimal;

public class TipoMembresia {
    private int idTipoMembresia;
    private String nombre;
    private int duracionDias;
    private BigDecimal precio;
    private String estado;

    public TipoMembresia() {
    }

    public TipoMembresia(int idTipoMembresia, String nombre, int duracionDias, BigDecimal precio, String estado) {
        this.idTipoMembresia = idTipoMembresia;
        this.nombre = nombre;
        this.duracionDias = duracionDias;
        this.precio = precio;
        this.estado = estado;
    }

    public int getIdTipoMembresia() {
        return idTipoMembresia;
    }

    public void setIdTipoMembresia(int idTipoMembresia) {
        this.idTipoMembresia = idTipoMembresia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracionDias() {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias) {
        this.duracionDias = duracionDias;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}