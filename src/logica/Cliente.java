package logica;

public class Cliente implements Comparable<Cliente> {
	private String nombre;
	private String direccion;
	private String telefono;
	private float limiteCredicto; 
	private String id;
	private float balacen;
	public Cliente(String nombre, String direccion, String telefono, float limiteCredicto, String id) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.limiteCredicto=limiteCredicto;
		this.id=id;
		this.balacen = 0;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public float getLimiteCredicto() {
		return limiteCredicto;
	}
	public void setLimiteCredicto(float limiteCredicto) {
		this.limiteCredicto = limiteCredicto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getBalacen() {
		return balacen;
	}
	public void setBalacen(float balacen) {
		this.balacen = balacen;
	}
	
	  @Override
	    public int compareTo(Cliente e){
	        if(e.getLimiteCredicto()>limiteCredicto){
	            return -1;
	        }else if(e.getLimiteCredicto()>limiteCredicto){
	            return 0;
	        }else{
	            return 1;
	        }
	    }
  
	
}
