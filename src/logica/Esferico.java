package logica;

public class Esferico extends Queso {
	private float radio;
	
	public Esferico(float precioUnitario, float precioBase, String id,int cantidad, float radio) {
		super(precioUnitario, precioBase, id,cantidad);
		this.radio = radio;
	}

	@Override
	public float Volumen() {
		float volumen=0;
		volumen=(float) ((Math.PI*Math.pow(radio, 3)*4)/3);
		return volumen;
	}

	public float getRadio() {
		return radio;
	}

	public void setRadio(float radio) {
		this.radio = radio;
	}
}
