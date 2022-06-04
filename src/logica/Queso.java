package logica;

public abstract class Queso implements Comparable<Queso>{
	protected String id;
	protected float precioUnitario;
	protected float precioBase;
	protected int cantidad;
	public Queso(float precioUnitario, float precioBase, String id, int cantidad) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.precioUnitario = precioUnitario;
		this.precioBase = precioBase;
		this.cantidad = cantidad;
	}
	
	
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public float getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}
	
	public abstract float Volumen();
	
	public float PrecioTotal() {
		float total = 0;
		total = precioBase + (precioUnitario*Volumen());
		return total;
	}
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	  
	
	
	  @Override
	    public int compareTo(Queso e){
	        if(e.Volumen()>Volumen()){
	            return -1;
	        }else if(e.Volumen()>Volumen()){
	            return 0;
	        }else{
	            return 1;
	        }
	    }



	  
	
	//Estudiar esto
}
