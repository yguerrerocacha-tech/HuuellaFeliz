package _02_modelo_entidad;

import java.sql.Date;

public class HistorialClinico {
    private int codHistorial;
    private int codAnimal;
    private Date fechaRevision;
    private String veterinario;
    private String diagnostico;
    private String tratamiento;
    private double costoAtencion; 
    private Date proximaCita;
    
    private String nombreAnimal; 

    public HistorialClinico() {}

    public int getCodHistorial() { return codHistorial; }
    public void setCodHistorial(int codHistorial) { this.codHistorial = codHistorial; }

    public int getCodAnimal() { return codAnimal; }
    public void setCodAnimal(int codAnimal) { this.codAnimal = codAnimal; }

    public Date getFechaRevision() { return fechaRevision; }
    public void setFechaRevision(Date fechaRevision) { this.fechaRevision = fechaRevision; }

    public String getVeterinario() { return veterinario; }
    public void setVeterinario(String veterinario) { this.veterinario = veterinario; }

    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }

    public String getTratamiento() { return tratamiento; }
    public void setTratamiento(String tratamiento) { this.tratamiento = tratamiento; }

    public double getCostoAtencion() { return costoAtencion; }
    public void setCostoAtencion(double costoAtencion) { this.costoAtencion = costoAtencion; }

    public Date getProximaCita() { return proximaCita; }
    public void setProximaCita(Date proximaCita) { this.proximaCita = proximaCita; }

    public String getNombreAnimal() { return nombreAnimal; }
    public void setNombreAnimal(String nombreAnimal) { this.nombreAnimal = nombreAnimal; }
}