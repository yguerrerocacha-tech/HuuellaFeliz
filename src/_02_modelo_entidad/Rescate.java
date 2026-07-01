package _02_modelo_entidad;

import java.util.Date;

public class Rescate {
    private int codRescate;
    private String lugar;
    private Date fechaRescate;
    private int codVoluntario;
    private String condicionInicial;
    private String contactoReporte;
    private String estado;

    public Rescate() {}

    public int getCodRescate() { return codRescate; }
    public void setCodRescate(int codRescate) { this.codRescate = codRescate; }
    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
    public Date getFechaRescate() { return fechaRescate; }
    public void setFechaRescate(Date fechaRescate) { this.fechaRescate = fechaRescate; }
    public int getCodVoluntario() { return codVoluntario; }
    public void setCodVoluntario(int codVoluntario) { this.codVoluntario = codVoluntario; }
    public String getCondicionInicial() { return condicionInicial; }
    public void setCondicionInicial(String condicionInicial) { this.condicionInicial = condicionInicial; }
    public String getContactoReporte() { return contactoReporte; }
    public void setContactoReporte(String contactoReporte) { this.contactoReporte = contactoReporte; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}