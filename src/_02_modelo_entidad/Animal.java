package _02_modelo_entidad;

public class Animal {
    // Atributos mapeados fielmente de tu documentación física
    private int codAnimal;
    private String nomAnimal;
    private String especie;
    private String raza;
    private int edad;
    private String sexo;
    private String estado;
    private String descripcion;
    private int codEmpleado;

    // Constructor vacío obligado para frameworks y buenas prácticas
    public Animal() {}

    // Constructor lleno para instanciar rápidamente al registrar
    public Animal(int codAnimal, String nomAnimal, String especie, String raza, int edad, String sexo, String estado, String descripcion, int codEmpleado) {
        this.codAnimal = codAnimal;
        this.nomAnimal = nomAnimal;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.sexo = sexo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.codEmpleado = codEmpleado;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================
    public int getCodAnimal() { return codAnimal; }
    public void setCodAnimal(int codAnimal) { this.codAnimal = codAnimal; }

    public String getNomAnimal() { return nomAnimal; }
    public void setNomAnimal(String nomAnimal) { this.nomAnimal = nomAnimal; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaza() { return raza; }
    public void setRaza(String raza) { this.raza = raza; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getCodEmpleado() { return codEmpleado; }
    public void setCodEmpleado(int codEmpleado) { this.codEmpleado = codEmpleado; }
}