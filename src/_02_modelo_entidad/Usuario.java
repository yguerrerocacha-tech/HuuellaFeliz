package _02_modelo_entidad;

public class Usuario {
    private int codUsuario;
    private String username;
    private String password;
    private String nombreCompleto;
    private String cargo;

    public Usuario() {}

    // Getters y Setters
    public int getCodUsuario() { return codUsuario; }
    public void setCodUsuario(int codUsuario) { this.codUsuario = codUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

	public void setDni(String string) {
		// TODO Auto-generated method stub
		
	}
}