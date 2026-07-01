package _02_modelo_entidad;

import java.sql.Date;

public class Adopcion {
    private int codAdopcion;
    private int codAdoptante;
    private int codAnimal;
    private Date fechaAdopcion;
    private String estadoTramite;
    private String seguimientoPostAdopcion; // Variable añadida y corregida
    private String observaciones;
    
    // Variables adicionales para mostrar nombres reales en el JTable (INNER JOIN)
    private String nomAdoptante;
    private String nomAnimal;

    public Adopcion() {}

    public int getCodAdopcion() { return codAdopcion; }
    public void setCodAdopcion(int codAdopcion) { this.codAdopcion = codAdopcion; }
    
    public int getCodAdoptante() { return codAdoptante; }
    public void setCodAdoptante(int codAdoptante) { this.codAdoptante = codAdoptante; }
    
    public int getCodAnimal() { return codAnimal; }
    public void setCodAnimal(int codAnimal) { this.codAnimal = codAnimal; }
    
    public Date getFechaAdopcion() { return fechaAdopcion; }
    public void setFechaAdopcion(Date fechaAdopcion) { this.fechaAdopcion = fechaAdopcion; }
    
    public String getEstadoTramite() { return estadoTramite; }
    public void setEstadoTramite(String estadoTramite) { this.estadoTramite = estadoTramite; }
    
    public String getSeguimientoPostAdopcion() { return seguimientoPostAdopcion; }
    public void setSeguimientoPostAdopcion(String seguimientoPostAdopcion) { this.seguimientoPostAdopcion = seguimientoPostAdopcion; }
    
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    
    public String getNomAdoptante() { return nomAdoptante; }
    public void setNomAdoptante(String nomAdoptante) { this.nomAdoptante = nomAdoptante; }
    
    public String getNomAnimal() { return nomAnimal; }
    public void setNomAnimal(String nomAnimal) { this.nomAnimal = nomAnimal; }
}