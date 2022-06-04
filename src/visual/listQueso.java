package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import logica.Cilindrico;
import logica.CilindroHueco;
import logica.Esferico;
import logica.FabricaQuesos;
import logica.Queso;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class listQueso extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableQuesos;
	private FabricaQuesos fab1;
	private ArrayList<Queso> queso;
	private JComboBox cbxtiposQuesos;
	private JButton btnOrganizar;
	private JButton btnEliminar;
	private String idQueso = "";
	private JButton btnAumentar;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public listQueso(FabricaQuesos fab) {
		setTitle("Listado de quesos");
		this.fab1 = fab;
		setBounds(100, 100, 811, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"queso (1).png");
		setIconImage(imagen1.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 77, 775, 309);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 775, 309);
		panel.add(scrollPane);
		{
			tableQuesos = new JTable();
			tableQuesos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(tableQuesos.getSelectedRow()>=0){
						int index = tableQuesos.getSelectedRow();
						btnEliminar.setEnabled(true);
						btnAumentar.setEnabled(true); 
						idQueso = (String) tableQuesos.getValueAt(index, 0);
					}
				}
			});
			tableQuesos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableQuesos.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Id", "Titulo", "Materia", "Cant. Ejemplares"
				}
			));
			
			
			scrollPane.setViewportView(tableQuesos);
		}
		
		JLabel lblListadoDequeso = new JLabel("Listado de Quesos:");
		lblListadoDequeso.setBounds(10, 11, 197, 14);
		contentPanel.add(lblListadoDequeso);
		
		JLabel lblTipoDePublicaciones = new JLabel("Tipo de Quesos:");
		lblTipoDePublicaciones.setBounds(20, 36, 94, 14);
		contentPanel.add(lblTipoDePublicaciones);
		mostrarT();
		cbxtiposQuesos = new JComboBox();
		cbxtiposQuesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tablas = cbxtiposQuesos.getSelectedItem().toString();
				if(tablas == "<Todo>") {
					mostrarT();
				}
				if(tablas == "Esferico" ) {
					mostrarEsferico();
				}
				if(tablas == "Cilindro") {
					mostrarCilindrico();
				}
				if(tablas == "Cilindro con hueco") {
					mostrarCH();
				}
			}
		});
		cbxtiposQuesos.setModel(new DefaultComboBoxModel(new String[] {"<Todo>", "Esferico", "Cilindro", "Cilindro con hueco"}));
		cbxtiposQuesos.setBounds(121, 33, 117, 20);
		contentPanel.add(cbxtiposQuesos);
		
		JLabel lblOrganizarPorVolumen = new JLabel("Organizar por volumen:");
		lblOrganizarPorVolumen.setBounds(248, 36, 148, 14);
		contentPanel.add(lblOrganizarPorVolumen);
		
		btnOrganizar = new JButton("Organizar");
		btnOrganizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarOrganizadoPorVolume();
			}
		});
		btnOrganizar.setBounds(391, 32, 104, 23);
		contentPanel.add(btnOrganizar);
		
		JLabel lblAumentarLaCantidad = new JLabel("Aumentar la cantidad:");
		lblAumentarLaCantidad.setBounds(505, 36, 135, 14);
		contentPanel.add(lblAumentarLaCantidad);
		
		btnAumentar = new JButton("Aumentar");
		btnAumentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!idQueso.equalsIgnoreCase("")) {
					Queso q1 = fab.buscarQues(idQueso);
					String respuesta = JOptionPane.showInputDialog("¿Cuantos quesos desea insertar?");
					if(!respuesta.equalsIgnoreCase("") && Integer.parseInt(respuesta)>0) {
						q1.setCantidad(q1.getCantidad()+Integer.parseInt(respuesta));
						JOptionPane.showMessageDialog(null, "Aumento exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
						mostrarT();
					}else {
						JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
					}

				}
			}
		});
		btnAumentar.setEnabled(false);
		btnAumentar.setBounds(650, 32, 104, 23);
		contentPanel.add(btnAumentar);
		
		

	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!idQueso.equalsIgnoreCase("")) {
							int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el queso: "+idQueso, "Información", JOptionPane.WARNING_MESSAGE);
							if(option == 0) {
								fab1.eliminarQueso(idQueso);
								mostrarT();
								JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
					            btnEliminar.setEnabled(false);
							}
						}
					}
				});
				buttonPane.add(btnEliminar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void mostrarOrganizadoPorVolume() {
		Collections.sort(fab1.getQuesos());
		String[][] matriz = new String[fab1.getQuesos().size()][7];
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab1.getQuesos().size(); i++) {
			matriz[i][0] = fab1.getQuesos().get(i).getId();
			matriz[i][1] = df.format(fab1.getQuesos().get(i).getPrecioBase());
			matriz[i][2] = df.format(fab1.getQuesos().get(i).getPrecioUnitario());
			matriz[i][3] = df.format(fab1.getQuesos().get(i).PrecioTotal());
			matriz[i][4] = df.format(fab1.getQuesos().get(i).getCantidad());
			matriz[i][5] = df.format(fab1.getQuesos().get(i).Volumen());
			if(fab1.getQuesos().get(i)  instanceof Esferico) {
				matriz[i][6] = "Esferico";
			}
			if(fab1.getQuesos().get(i)  instanceof Cilindrico) {
				matriz[i][6] = "Cilindro";
			}
			if(fab1.getQuesos().get(i)  instanceof CilindroHueco) {
				matriz[i][6] = "Cilindro hueco";
			}
			}
		tableQuesos.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Precio base", "Precio Unitario","Precio total", "Cantidad", "Volumen", "Tipo"
				}
			));
	}
	
	
	public void mostrarT() {
		String[][] matriz = new String[fab1.getQuesos().size()][7];
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab1.getQuesos().size(); i++) {
			matriz[i][0] = fab1.getQuesos().get(i).getId();
			matriz[i][1] = df.format(fab1.getQuesos().get(i).getPrecioBase());
			matriz[i][2] = df.format(fab1.getQuesos().get(i).getPrecioUnitario());
			matriz[i][3] = df.format(fab1.getQuesos().get(i).PrecioTotal());
			matriz[i][4] = df.format(fab1.getQuesos().get(i).getCantidad());
			matriz[i][5] = df.format(fab1.getQuesos().get(i).Volumen());
			if(fab1.getQuesos().get(i)  instanceof Esferico) {
				matriz[i][6] = "Esferico";
			}
			if(fab1.getQuesos().get(i)  instanceof Cilindrico) {
				matriz[i][6] = "Cilindro";
			}
			if(fab1.getQuesos().get(i)  instanceof CilindroHueco) {
				matriz[i][6] = "Cilindro hueco";
			}
			}
		tableQuesos.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Precio base", "Precio Unitario","Precio total", "Cantidad", "Volumen", "Tipo"
				}
			));
	}
	public void mostrarEsferico() {
		ArrayList<Esferico> e = new ArrayList<Esferico>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab1.getQuesos().size(); i++) {
			if(fab1.getQuesos().get(i) instanceof Esferico ) {
				if(fab1.getQuesos().get(i)!=null) {
						e.add((Esferico)fab1.getQuesos().get(i));
					}
				}
			}
		String[][] matriz = new String[e.size()][8];
		for(int i = 0; i<e.size();i++) {
			matriz[i][0] = e.get(i).getId();
			matriz[i][1] = df.format(e.get(i).getPrecioBase());
			matriz[i][2] = df.format(e.get(i).getPrecioUnitario());
			matriz[i][3] = df.format(e.get(i).PrecioTotal());
			matriz[i][4] = df.format(e.get(i).getCantidad());
			matriz[i][5] = df.format(e.get(i).getRadio());
			matriz[i][6] = df.format(e.get(i).Volumen());
			matriz[i][7] = "Esferico";
			}		
		tableQuesos.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Precio base", "Precio Unitario","Precio total", "Cantidad","Radio", "Volumen", "Tipo"
				}
			));
		
	}
	public void mostrarCilindrico() {
		
		ArrayList<Cilindrico> e = new ArrayList<Cilindrico>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab1.getQuesos().size(); i++) {
			if(fab1.getQuesos().get(i) instanceof Cilindrico ) {
				if(fab1.getQuesos().get(i)!=null) {
						e.add((Cilindrico)fab1.getQuesos().get(i));
					}
				}
			}
		String[][] matriz = new String[e.size()][9];
		for(int i = 0; i<e.size();i++) {
			matriz[i][0] = e.get(i).getId();
			matriz[i][1] = df.format(e.get(i).getPrecioBase());
			matriz[i][2] = df.format(e.get(i).getPrecioUnitario());
			matriz[i][3] = df.format(e.get(i).PrecioTotal());
			matriz[i][4] = df.format(e.get(i).getCantidad());
			matriz[i][5] = df.format(e.get(i).getRadio());
			matriz[i][6] = df.format(e.get(i).getLongitud());
			matriz[i][7] = df.format(e.get(i).Volumen());
			matriz[i][8] = "Cilindro";
			}		
		tableQuesos.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Precio base", "Precio Unitario","Precio Total","Cantidad","Radio","Longitud" , "Volumen", "Tipo"
				}
			));
	}
	public void mostrarCH() {
		ArrayList<CilindroHueco> e = new ArrayList<CilindroHueco>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab1.getQuesos().size(); i++) {
			if(fab1.getQuesos().get(i) instanceof CilindroHueco ) {
				if(fab1.getQuesos().get(i)!=null) {
						e.add((CilindroHueco)fab1.getQuesos().get(i));
					}
				}
			}
		String[][] matriz = new String[e.size()][10];
		for(int i = 0; i<e.size();i++) {
			matriz[i][0] = e.get(i).getId();
			matriz[i][1] = df.format(e.get(i).getPrecioBase());
			matriz[i][2] = df.format(e.get(i).getPrecioUnitario());
			matriz[i][3] = df.format(e.get(i).PrecioTotal());
			matriz[i][4] = df.format(e.get(i).getCantidad());
			matriz[i][5] = df.format(e.get(i).getRadio());
			matriz[i][6] = df.format(e.get(i).getRadioInt());
			matriz[i][7] = df.format(e.get(i).getLongitud());
			matriz[i][8] = df.format(e.get(i).Volumen());
			matriz[i][9] = "Cilindro hueco";
			}		
		tableQuesos.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Precio base", "Precio Unitario","Precio Total","Cantidad","Radio", "Radio Int." ,"Longitud" , "Volumen", "Tipo"
				}
			));
		
	}
}
