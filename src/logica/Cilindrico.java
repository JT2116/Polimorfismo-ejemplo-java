package logica;

public class Cilindrico extends Queso {
	protected float longitud;
	protected float radio;
	
	public Cilindrico(float precioUnitario, float precioBase, String id,int cantidad, float longitud, float radio) {
		super(precioUnitario, precioBase, id, cantidad);
		this.longitud = longitud;
		this.radio = radio;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getRadio() {
		return radio;
	}

	public void setRadio(float radio) {
		this.radio = radio;
	}

	@Override
	public float Volumen() {
		float volumen = 0; 
		volumen = longitud * AreaBase();
		return volumen;
	}
	
	public float AreaBase() {
		float Area = 0;
		Area = (float)(Math.PI*Math.pow(radio,2));
		return Area; 
	}


	

}
