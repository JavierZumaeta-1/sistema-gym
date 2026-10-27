package Modelo;

import java.math.BigDecimal;

public class Dashboard {
    private int sociosActivos;
    private int asistenciasHoy;
    private int membresiasVencidas;
    private int membresiasProximas;
    private BigDecimal ingresosEstimados;

    public Dashboard() {
    }

    public int getSociosActivos() {
        return sociosActivos;
    }

    public void setSociosActivos(int sociosActivos) {
        this.sociosActivos = sociosActivos;
    }

    public int getAsistenciasHoy() {
        return asistenciasHoy;
    }

    public void setAsistenciasHoy(int asistenciasHoy) {
        this.asistenciasHoy = asistenciasHoy;
    }

    public int getMembresiasVencidas() {
        return membresiasVencidas;
    }

    public void setMembresiasVencidas(int membresiasVencidas) {
        this.membresiasVencidas = membresiasVencidas;
    }

    public int getMembresiasProximas() {
        return membresiasProximas;
    }

    public void setMembresiasProximas(int membresiasProximas) {
        this.membresiasProximas = membresiasProximas;
    }

    public BigDecimal getIngresosEstimados() {
        return ingresosEstimados;
    }

    public void setIngresosEstimados(BigDecimal ingresosEstimados) {
        this.ingresosEstimados = ingresosEstimados;
    }
}