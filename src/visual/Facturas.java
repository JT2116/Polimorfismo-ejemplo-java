package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

import logica.Cilindrico;
import logica.CilindroHueco;
import logica.Cliente;
import logica.Esferico;
import logica.FabricaQuesos;
import logica.Queso;
import logica.Factura;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class Facturas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FabricaQuesos fab;
	private JTextField txtid;
	private JPanel panelCliente;
	private JPanel panelFactura;
	private JComboBox cbxTipo;
	private JTextField txtTotal;
	private JList listAntes; 
	private JList listVender;
	private JButton btnVender;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtBalace;
	private JTextField txtCredicto;
	private ArrayList<Queso> queso;
	private JButton btnDevolver;
	private JButton btnPagarDeuda;
	private JButton btnBuscar;
	private JLabel label;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Facturas(FabricaQuesos fab,ArrayList<Queso> queso ) {
		setTitle("Factura");
		this.fab = fab;
		this.queso = queso;
		setBounds(100, 100, 729, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"factura.png");
		setIconImage(imagen1.getImage());
		contentPanel.setLayout(null);
		{
			panelCliente = new JPanel();
			panelCliente.setBounds(10, 11, 701, 114);
			panelCliente.setBorder(new TitledBorder(null, "Buscar cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panelCliente);
			panelCliente.setLayout(null);
			{
				JLabel lblNombre = new JLabel("Ingrese la id:");
				lblNombre.setBounds(10, 24, 73, 14);
				panelCliente.add(lblNombre);
			}
			{
				txtid = new JTextField();
				txtid.setBounds(93, 21, 99, 20);
				panelCliente.add(txtid);
				txtid.setColumns(10);
			}
			
			JLabel lblNombre_1 = new JLabel("Nombre:");
			lblNombre_1.setBounds(10, 49, 56, 14);
			panelCliente.add(lblNombre_1);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setBounds(10, 74, 126, 20);
			panelCliente.add(txtNombre);
			txtNombre.setColumns(10);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(148, 52, 62, 14);
			panelCliente.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setBounds(146, 74, 126, 20);
			panelCliente.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			JLabel lblTelefono = new JLabel("Telefono:");
			lblTelefono.setBounds(283, 52, 73, 14);
			panelCliente.add(lblTelefono);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setBounds(282, 74, 126, 20);
			panelCliente.add(txtTelefono);
			txtTelefono.setColumns(10);
			
			JLabel lblCredicto = new JLabel("Cr\u00E9dicto:");
			lblCredicto.setBounds(420, 52, 56, 14);
			panelCliente.add(lblCredicto);
			
			txtBalace = new JTextField();
			txtBalace.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char validar = e.getKeyChar();
					if(Character.isLetter(validar)) {
						getToolkit().beep();
						e.consume();
						JOptionPane.showMessageDialog(rootPane, "Solo numeros");
					}
				}
			});
			txtBalace.setEditable(false);
			txtBalace.setBounds(554, 74, 124, 20);
			panelCliente.add(txtBalace);
			txtBalace.setColumns(10);
			txtBalace.setText("0");
			
			JLabel lblDeuda = new JLabel("Deuda:");
			lblDeuda.setBounds(554, 52, 46, 14);
			panelCliente.add(lblDeuda);
			
			txtCredicto = new JTextField();
			txtCredicto.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char validar = e.getKeyChar();
					if(Character.isLetter(validar)) {
						getToolkit().beep();
						e.consume();
						JOptionPane.showMessageDialog(rootPane, "Solo numeros");
					}
				}
			});
			txtCredicto.setEditable(false);
			txtCredicto.setBounds(418, 74, 126, 20);
			panelCliente.add(txtCredicto);
			txtCredicto.setColumns(10);
			txtCredicto.setText("0");
			
			ImageIcon imagen=new ImageIcon("imagenes"+File.separator+"Buscar.png");
			JButton btnBuscar = new JButton(imagen);
			btnBuscar.setText("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Cliente clie  = fab.buscaCliente(txtid.getText());
					boolean encontrado = false;
					
					
					DecimalFormat df = new DecimalFormat("#.00");
					
					
					for(Cliente aux : fab.getClientes()) {
						if(aux.getId().equalsIgnoreCase(txtid.getText())) {
							txtNombre.setText(aux.getNombre());
							txtCredicto.setText(df.format(aux.getLimiteCredicto()));
							txtBalace.setText(df.format(aux.getBalacen()));
							txtTelefono.setText(aux.getTelefono());
							txtDireccion.setText(aux.getDireccion());
							btnPagarDeuda.setEnabled(true);
							encontrado = true;
							}
						}
					
					if(encontrado==false) {
						JOptionPane.showMessageDialog(null,"Este cliente no existe, registre los datos para un nuevo cliente", "Validación", JOptionPane.WARNING_MESSAGE);
						txtNombre.setEditable(true);
						txtTelefono.setEditable(true);
						txtDireccion.setEditable(true);
						txtCredicto.setEditable(true);
						txtid.setEditable(false);
						txtid.setText("C"+(fab.getIdclie()));
						clean();
						}
						
				
					

				}
			});
			btnBuscar.setBounds(202, 20, 114, 23);
			panelCliente.add(btnBuscar);
			ImageIcon imagen3=new ImageIcon("imagenes"+File.separator+"dinero.png");
			btnPagarDeuda = new JButton(imagen3);
			btnPagarDeuda.setText("Pagar deuda");
			btnPagarDeuda.setEnabled(false);
			btnPagarDeuda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String respuesta = JOptionPane.showInputDialog("¿Cuanto desea abonar a la deuda?");
					DecimalFormat df = new DecimalFormat("#.00");
					if(!respuesta.equalsIgnoreCase("") &&  Float.parseFloat(respuesta)<=Float.parseFloat(txtBalace.getText())) {
						for(Cliente aux : fab.getClientes()) {
							if(aux.getId().equalsIgnoreCase(txtid.getText())) {
								txtBalace.setText(df.format(aux.getBalacen()-Float.parseFloat(respuesta)));
								aux.setBalacen(aux.getBalacen()-Float.parseFloat(respuesta));
								JOptionPane.showMessageDialog(null, "Pago Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
					}
					
				}
			});
			btnPagarDeuda.setBounds(541, 20, 137, 23);
			panelCliente.add(btnPagarDeuda);
		}
		{
			panelFactura = new JPanel();
			panelFactura.setBounds(10, 136, 701, 298);
			panelFactura.setBorder(new TitledBorder(null, "Factura", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelFactura.setLayout(null);
			contentPanel.add(panelFactura);
			
			listAntes = new JList();
			listAntes.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					btnVender.setEnabled(true);
				}
			});
			DefaultListModel list = new DefaultListModel();
			DecimalFormat df = new DecimalFormat("#.00");
			
			for (Queso aux : fab.getQuesos()) {
				list.addElement(aux.getId()+" Volumen: "+df.format(aux.Volumen())+" Precio: "+df.format(aux.PrecioTotal()));
				}
			
			listAntes.setModel(list);
			
			listAntes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listAntes.setName("");
			listAntes.setBounds(20, 50, 280, 209);
			panelFactura.add(listAntes);
			ImageIcon imagen2=new ImageIcon("imagenes"+File.separator+"flecha-derecha.png");
			btnVender = new JButton(imagen2);
			btnVender.setEnabled(false);
			btnVender.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DecimalFormat dv = new DecimalFormat("#.00");
					float tontalq = 0;
					
					String idi = (String)listAntes.getSelectedValue();
					int finalC = 5;
					if(fab.getIdqueso()==10000) {
						finalC++;
					}
					String idin = idi.substring(0,finalC);
					String id = null;
					for(Queso aux : fab.getQuesos()) {
						if(idin.equalsIgnoreCase(aux.getId())) {
							id=aux.getId();
						}
					}
					
					Queso q1 = fab.buscarQues(id);
					
					if(q1.getCantidad()>=1) {
						q1.setCantidad(q1.getCantidad()-1);
						queso.add(q1);
						listVender.setModel(CargarJListVentas(queso));
						for(int i = 0; i<queso.size();i++) {
							tontalq += queso.get(i).PrecioTotal();
							}
						txtTotal.setText(dv.format(tontalq));
					}else {
						JOptionPane.showMessageDialog(null, "Este producto esta agotado", "Validación", JOptionPane.WARNING_MESSAGE);
					}
		
					
					
				}
			});
			btnVender.setBounds(310, 154, 89, 23);
			panelFactura.add(btnVender);
			
			listVender = new JList();
			listVender.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent arg0) {
					btnDevolver.setEnabled(true);
				}
			});
			listVender.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listVender.setName("");
			listVender.setBounds(409, 50, 280, 209);
			panelFactura.add(listVender);
			ImageIcon imagen3=new ImageIcon("imagenes"+File.separator+"flecha-izquierda.png");
			btnDevolver = new JButton(imagen3);
			btnDevolver.setEnabled(false);
			btnDevolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DecimalFormat dv = new DecimalFormat("#.00");
					float tontalq = 0;
					String idi = (String)listVender.getSelectedValue();
					int finalC = 5;
					
					if(fab.getIdqueso()==10000) {
						finalC++;
					}
					String idin = idi.substring(0,finalC);
					String id = null;
					for(Queso aux : fab.getQuesos()) {
						if(idin.equalsIgnoreCase(aux.getId())) {
							id=aux.getId();
						}
					}
					if(JOptionPane.showConfirmDialog(null, "Esta seguro de no incluir este producto", "Información", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
						Queso q1 = fab.buscarQues(id);
						q1.setCantidad(q1.getCantidad()+1);
						queso.remove(q1);
						listVender.setModel(CargarJListVentas(queso));
						for(int i = 0; i<queso.size();i++) {
							tontalq += queso.get(i).PrecioTotal();
						}
						txtTotal.setText(dv.format(tontalq));
					}

					
				}
			});
			btnDevolver.setBounds(310, 188, 89, 23);
			panelFactura.add(btnDevolver);
			{
				JLabel lblTontal = new JLabel("Balance total:");
				lblTontal.setForeground(Color.RED);
				lblTontal.setBounds(486, 270, 89, 14);
				panelFactura.add(lblTontal);
			}
			{
				txtTotal = new JTextField();
				txtTotal.setEditable(false);
				txtTotal.setBounds(585, 267, 80, 20);
				panelFactura.add(txtTotal);
				txtTotal.setColumns(10);
			}
			
			JLabel lblElegirTipoDe = new JLabel("Elegir tipo de queso:");
			lblElegirTipoDe.setBounds(20, 25, 125, 14);
			panelFactura.add(lblElegirTipoDe);
			
			cbxTipo = new JComboBox();
			cbxTipo.setBounds(155, 22, 148, 20);
			panelFactura.add(cbxTipo);
			cbxTipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String tipo = cbxTipo.toString();
					listAntes.setModel(CargarJListQueso(cbxTipo.getSelectedIndex(),fab));
				}
			});
			cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Queso esf\u00E9rico", "Queso cil\u00EDndrico ", "queso cil\u00EDndrico hueco"}));
			
			label = new JLabel("$");
			label.setBounds(668, 270, 21, 14);
			panelFactura.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRealizarComp = new JButton("Completar la compra");
				btnRealizarComp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DecimalFormat df = new DecimalFormat("#.00");

						float tontalq = 0;
						clonarList(queso);
						for(int i = 0; i<clonarList(queso).size();i++) {
							tontalq += clonarList(queso).get(i).PrecioTotal();
						}
						if(!txtCredicto.getText().equalsIgnoreCase("")) {
							String id = txtid.getText();
							String nombre = txtNombre.getText();
							String dire = txtDireccion.getText();
							String tele = txtTelefono.getText();
							float crec = Float.parseFloat(txtCredicto.getText());
							float balance = Float.parseFloat(txtBalace.getText());
							
							boolean encontrado=false;
							for (Cliente aux : fab.getClientes()) {
								if(aux.getId().equalsIgnoreCase(txtid.getText())) {
										encontrado=true;
									}
								}

							
							if(!txtid.getText().equalsIgnoreCase("") && tontalq != 0.00 && !txtTotal.getText().equalsIgnoreCase("") && !txtTotal.getText().equalsIgnoreCase("0.00")) {
								if(encontrado==false) {
									if(!nombre.equalsIgnoreCase("") && !tele.equalsIgnoreCase("") && !dire.equalsIgnoreCase("")) {
										/*Cliente c1 = new Cliente(nombre,dire,tele,crec,id);
										fab.insertarCliente(c1);
										fab.setIdclie(fab.getIdclie()+1);*/
		
										JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
										}else {
											JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
											}
									}
								
								String facid = "";
								fab.setIdfactura(fab.getIdfactura()+1);
								facid = "F"+fab.getIdfactura();
								String[] options = {"Crédito", "Contado"};
								int seleccion = JOptionPane.showOptionDialog(null, "¿Que tipo de compra desea hacer?", "Tipo de compra", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
								
								
								if(seleccion==1) {
									if(encontrado==false) {
										Cliente c1 = new Cliente(nombre,dire,tele,crec,id);
										fab.insertarCliente(c1);
										fab.setIdclie(fab.getIdclie()+1);
										Factura fact = new Factura(facid,clonarList(queso),c1,false);
										fab.insertarFactura(fact);
										queso.clear();
										txtBalace.setText(df.format(c1.getBalacen()));
										listVender.setModel(CargarJListVentas(queso));
										JOptionPane.showMessageDialog(null, "Compra terminada", "Información", JOptionPane.INFORMATION_MESSAGE);
										tontalq=0;
										txtTotal.setText(df.format(tontalq));
										txtid.setEditable(true);
										clean1();
										}else {
											Cliente c = fab.buscaCliente(txtid.getText());
											Factura fact = new Factura(facid,clonarList(queso),c,false);
											fab.insertarFactura(fact);
											queso.clear();
											txtBalace.setText(df.format(c.getBalacen()));
											listVender.setModel(CargarJListVentas(queso));
											JOptionPane.showMessageDialog(null, "Compra terminada", "Información", JOptionPane.INFORMATION_MESSAGE);
											tontalq=0;
											txtTotal.setText(df.format(tontalq));
											txtid.setEditable(true);
											clean1();
										}
		
									}
								
								
								if(crec>tontalq && crec>balance && seleccion == 0) {
									if(encontrado==false) {
										Cliente c1 = new Cliente(nombre,dire,tele,crec,id);
										fab.insertarCliente(c1);
										fab.setIdclie(fab.getIdclie()+1);
										Factura fact = new Factura(facid,clonarList(queso),c1,true);
										fab.insertarFactura(fact);
									
										c1.setBalacen(c1.getBalacen()+tontalq);
										queso.clear();
										txtBalace.setText(df.format(c1.getBalacen()));
										listVender.setModel(CargarJListVentas(queso));
										JOptionPane.showMessageDialog(null, "Compra terminada", "Información", JOptionPane.INFORMATION_MESSAGE);
										tontalq=0;
										txtTotal.setText(df.format(tontalq));
										txtid.setEditable(true);
										clean1();
										}else {
											Cliente c = fab.buscaCliente(txtid.getText());
											Factura fact = new Factura(facid,clonarList(queso),c,true);
											fab.insertarFactura(fact);
											c.setBalacen(c.getBalacen()+tontalq);
											queso.clear();
											txtBalace.setText(df.format(c.getBalacen()));
											listVender.setModel(CargarJListVentas(queso));
											JOptionPane.showMessageDialog(null, "Compra terminada", "Información", JOptionPane.INFORMATION_MESSAGE);
											tontalq=0;
											txtTotal.setText(df.format(tontalq));
											txtid.setEditable(true);
											clean1();
										}

									}
								if(crec<tontalq || crec<Float.parseFloat(txtBalace.getText())) {
									if(JOptionPane.showConfirmDialog(null, "No tiene suficiente credicto, ¿Quiere pagar a contado?", "Información", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
										if(encontrado==false) {
											Cliente c1 = new Cliente(nombre,dire,tele,crec,id);
											fab.insertarCliente(c1);
											fab.setIdclie(fab.getIdclie()+1);
											Factura fact = new Factura(facid,clonarList(queso),c1,false);
											
											fab.insertarFactura(fact);
											queso.clear();
											txtBalace.setText(df.format(c1.getBalacen()));
											listVender.setModel(CargarJListVentas(queso));
											JOptionPane.showMessageDialog(null, "Compra terminada", "Información", JOptionPane.INFORMATION_MESSAGE);
											tontalq=0;
											txtTotal.setText(df.format(tontalq));
											txtid.setEditable(true);
											clean1();
											
										}else {
											Cliente c = fab.buscaCliente(txtid.getText());
											Factura fact = new Factura(facid,clonarList(queso),c,false);
											
											fab.insertarFactura(fact);
											queso.clear();
											txtBalace.setText(df.format(c.getBalacen()));
											listVender.setModel(CargarJListVentas(queso));
											JOptionPane.showMessageDialog(null, "Compra terminada", "Información", JOptionPane.INFORMATION_MESSAGE);
											tontalq=0;
											txtTotal.setText(df.format(tontalq));
											txtid.setEditable(true);
											clean1();
										}
									}
								}
								clean1();
								}else {
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
									}
							
						}else {
							JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
							}
						


						
						
					}
				});
				btnRealizarComp.setActionCommand("OK");
				buttonPane.add(btnRealizarComp);
				getRootPane().setDefaultButton(btnRealizarComp);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void clean() {
		txtid.setText("C"+(fab.getIdclie()));
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		txtCredicto.setText("");
	}
	
	private void clean1() {
		txtid.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		txtCredicto.setText("");
		txtBalace.setText("0");
		txtNombre.setEditable(false);
		txtTelefono.setEditable(false);
		txtDireccion.setEditable(false);
		txtCredicto.setEditable(false);
	}

	public static DefaultListModel CargarJListQueso(int filtro,FabricaQuesos fab) {
		DefaultListModel list = new DefaultListModel();
		DecimalFormat df = new DecimalFormat("#.00");
		
		
		
		if(filtro==0) {
			for (Queso aux : fab.getQuesos()) {
				list.addElement(aux.getId()+" Volumen: "+df.format(aux.Volumen())+" Precio: "+df.format(aux.PrecioTotal()));
				}
			}
		if(filtro==1) {
			for (Queso aux :  fab.getQuesos()) {
				if(aux instanceof Esferico) {
					list.addElement(aux.getId()+" Volumen: "+df.format(aux.Volumen())+" Precio: "+df.format(aux.PrecioTotal()));
					}
				}
			}
		if(filtro==2) {
			for (Queso aux :  fab.getQuesos()) {
				if(aux instanceof Cilindrico) {
					list.addElement(aux.getId()+" Volumen: "+df.format(aux.Volumen())+" Precio: "+df.format(aux.PrecioTotal()));
					}
				}
			}
		if(filtro==3) {
			for (Queso aux :  fab.getQuesos()) {
				if(aux instanceof CilindroHueco) {
					list.addElement(aux.getId()+" Volumen: "+df.format(aux.Volumen())+" Precio: "+df.format(aux.PrecioTotal()));
					}
				}
			}
		
		return list;
	}
	
	public static DefaultListModel CargarJListVentas(ArrayList<Queso> queso) {
		
		//ArrayList<String> publiJ= new ArrayList();
		DefaultListModel vent = new DefaultListModel();
		DecimalFormat df = new DecimalFormat("#.00");
		
		
		for(int i=0; i<queso.size();i++) {
			vent.addElement(queso.get(i).getId()+" Volumen: "+df.format(queso.get(i).Volumen())+" Precio: "+df.format(queso.get(i).PrecioTotal()));
		}
		return vent;
	}
	
	public static ArrayList<Queso> clonarList(ArrayList<Queso> queso){
		ArrayList<Queso> clone = new ArrayList<Queso>(queso.size());
	
		for(int  i =0 ; i<queso.size(); i++) {
			clone.add(queso.get(i));
		}

		return clone;
	}
}
