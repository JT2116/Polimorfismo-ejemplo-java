package logica;

import java.util.ArrayList;

public class Factura implements Comparable<Factura>{
	private String codigo;
	private ArrayList<Queso> cantQueso;
	private Cliente clientes;
	private boolean tipoPago;
	public Factura(String codigo, ArrayList<Queso> cantQueso, Cliente clientes, boolean tipoPago) {
		super();
		this.codigo = codigo;
		this.cantQueso = cantQueso;
		this.clientes = clientes;
		this.tipoPago = tipoPago;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public ArrayList<Queso> getCantQueso() {
		return cantQueso;
	}
	public void setCantQueso(ArrayList<Queso> cantQueso) {
		this.cantQueso = cantQueso;
	}
	public Cliente getClientes() {
		return clientes;
	}
	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}
	
	public boolean getTipoPago() {
		return tipoPago;
	}
	
	public void setTipoPago(boolean tipoPago) {
		this.tipoPago = tipoPago;
	}
	
	public float precioTotalFactura() {
		float total = 0;
		for(int i = 0; i<cantQueso.size(); i++) {
			if(cantQueso.get(i) !=null) {
				total += cantQueso.get(i).PrecioTotal();
			}
		}
		return total;
	}

	
	  @Override
	    public int compareTo(Factura e){
	        if(e.precioTotalFactura()<precioTotalFactura()){
	            return -1;
	        }else if(e.precioTotalFactura()<precioTotalFactura()){
	            return 0;
	        }else{
	            return 1;
	        }
	    }

}
