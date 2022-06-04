package logica;

public class CilindroHueco extends Cilindrico {
	private float radioInt;

	public CilindroHueco(float precioUnitario, float precioBase, String id,int cantidad, float longitud, float radio,
			float radioInt) {
		super(precioUnitario, precioBase, id,cantidad, longitud, radio);
		this.radioInt = radioInt;
	}

	public float getRadioInt() {
		return radioInt;
	}

	public void setRadioInt(float radioInt) {
		this.radioInt = radioInt;
	}
	
	public float AreaBase() {
		float area = 0;
		area = (float)((Math.PI)*(longitud)*(Math.pow(radio,2)-Math.pow(radioInt,2)));
		return area;
	}
	

}
