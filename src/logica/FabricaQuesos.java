package logica;

import java.util.ArrayList;

public class FabricaQuesos {
	private ArrayList<Queso> quesos;
	private ArrayList<Factura> facturas;
	private ArrayList<Cliente> clientes;
	private int idclie;
	private int idqueso;
	private int idfactura;
	private int cantClie;
	private int cantQ;
	
	public FabricaQuesos() {
		super();
		this.quesos = new ArrayList<Queso>();
		this.facturas = new ArrayList<Factura>();
		this.clientes = new ArrayList<Cliente>();
		this.idclie = 1000;
		this.idqueso = 1000;
		this.idfactura = 1000;
		this.cantClie=0;
		this.cantQ=0;
	}
	public ArrayList<Queso> getQuesos() {
		return quesos;
	}
	public void setQuesos(ArrayList<Queso> quesos) {
		this.quesos = quesos;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	/////////////////////////////////////
	public void insertarQueso(Queso queso) {
		quesos.add(queso);
		cantQ++;
	}
	
	public void insertarCliente(Cliente cliente) {
		clientes.add(cliente);
		cantClie++;
	}
	
	public void insertarFactura(Factura factura) {
		facturas.add(factura);
	}
	
	public int getIdclie() {
		return idclie;
	}
	public void setIdclie(int idclie) {
		this.idclie = idclie;
	}
	
	public int getIdqueso() {
		return idqueso;
	}
	public void setIdqueso(int idqueso) {
		this.idqueso = idqueso;
	}
	public int getIdfactura() {
		return idfactura;
	}
	public void setIdfactura(int idfactura) {
		this.idfactura = idfactura;
	}
	
	public int getCantClie() {
		return cantClie;
	}
	public void setCantClie(int cantClie) {
		this.cantClie = cantClie;
	}
	public int getCantQ() {
		return cantQ;
	}
	public void setCantQ(int cantQ) {
		this.cantQ = cantQ;
	}
	public void cantQuesos () {	
		float cantQCilin=0;
		float cantQEsferico=0;
		float cantQCilinH=0;
		for (Queso aux : quesos) {
			
			if(aux instanceof Cilindrico) {
				cantQCilin++;
				
			}if(aux instanceof Esferico) {
				cantQEsferico++;
				
			}
			if(aux instanceof CilindroHueco) {
				cantQCilinH++;
				
			}
			
		}
		
	}
	public float QuesoEsfericoMasGrande(Factura aux) {
		float volumenMax=0;
		//for (Factura aux : facturas) {
			for (int i = 0; i < aux.getCantQueso().size(); i++) {
				if(aux.getCantQueso().get(i) instanceof Esferico) {
					if(aux.getCantQueso().get(i+1).Volumen()>aux.getCantQueso().get(i+1).Volumen()) {
						volumenMax=aux.getCantQueso().get(i+1).Volumen();
					}else {
						volumenMax=aux.getCantQueso().get(i).Volumen();
					}
				}
			}
		//}
		return volumenMax;
	}
	
	public Queso buscarQues(String id) {
		Queso aux = null;
		
		for(Queso qe : quesos) {
			if(qe.getId().equalsIgnoreCase(id)) {
				aux = qe;
			}
		}
		
		return aux;
	}
	
	public Cliente buscaCliente(String id) {
		Cliente aux = null;
		boolean encontrado = false;
		int i=0;
		while (i<clientes.size()&& !encontrado) {
			if(clientes.get(i).getId().equalsIgnoreCase(id)) {
				aux = clientes.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return aux;
	}
	
	public Factura buscaFactura(String id) {
		Factura aux = null;
		boolean encontrado = false;
		int i=0;
		while (i<facturas.size()&& !encontrado) {
			if(facturas.get(i).getCodigo().equalsIgnoreCase(id)) {
				aux = facturas.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return aux;
	}

	
	public float precioTotalFac(Factura fac) {
		float total = 0;
		for(int i = 0; i<fac.getCantQueso().size(); i++) {
			if(fac.getCantQueso().get(i) !=null) {
				total += fac.getCantQueso().get(i).PrecioTotal();
			}
		}
		return total;
	}
	
	public float precioEsfericoGrade(Factura fac, float volumen) {
		float precio = 0;
		
		for(int i = 0; i<fac.getCantQueso().size();i++ ) {
			if(fac.getCantQueso().get(i).Volumen()==volumen) {
				precio = fac.getCantQueso().get(i).PrecioTotal();
			}
		}
		return precio;
	}
	
	public int buscaClientePunto(String id) {
		int aux = -1;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < clientes.size()) {
			if(clientes.get(i).getId().equalsIgnoreCase(id)){
				encontrado = true;
				aux = i;
			}
			i++;		
		}
		return aux;
	}
	
	public void modificarCliente(Cliente update) {
		int index = buscaClientePunto(update.getId());
		clientes.set(index, update);
		
	}
	
	public void eliminarCliente(String id) {
		Cliente aux = buscaCliente(id);
		for(int i = 0;i<clientes.size();i++) {
			if(aux.getId().equalsIgnoreCase(clientes.get(i).getId())) {
				clientes.remove(i);
			}
		}
		
	}
	
	public void eliminarQueso(String id) {
		Queso aux = buscarQues(id);
		for(int i = 0;i<quesos.size();i++) {
			if(aux.getId().equalsIgnoreCase(quesos.get(i).getId())) {
				quesos.remove(i);
			}
		}

		
	}
	
	
}
