package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import logica.FabricaQuesos;
import logica.Factura;

import javax.swing.JLabel;
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

public class listFactura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableFacturas;
	private JTable tableFactura1;
	private FabricaQuesos fab;
	private JComboBox cbxtiposFactura;
	private String idFactura = "";
	private JButton btnMostrar;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public listFactura(FabricaQuesos fab) {
		setTitle("Lista de Facturas");
		this.fab = fab;
		setBounds(100, 100, 811, 469);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"factura.png");
		setIconImage(imagen1.getImage());
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 77, 775, 309);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 775, 309);
		panel.add(scrollPane);
		{
			tableFacturas = new JTable();
			tableFacturas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(tableFacturas.getSelectedRow()>=0){
						int index = tableFacturas.getSelectedRow();
						btnMostrar.setEnabled(true); 
						idFactura = (String) tableFacturas.getValueAt(index, 0);
					}
				}
			});
			tableFacturas.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Id", "Cliente", "Cant. de queso", "Tipo", "Precio total"
				}
			));
			
			
			scrollPane.setViewportView(tableFacturas);
		}
		
		JLabel lblListadoDeFacturas = new JLabel("Listado de Facturas:");
		lblListadoDeFacturas.setBounds(10, 11, 197, 14);
		contentPanel.add(lblListadoDeFacturas);
		
		JLabel lblTipoDeFactura = new JLabel("Tipos de Factura:");
		lblTipoDeFactura.setBounds(20, 36, 133, 14);
		contentPanel.add(lblTipoDeFactura);
		mostrarT();
		cbxtiposFactura = new JComboBox();
		cbxtiposFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tablas = cbxtiposFactura.getSelectedItem().toString();
				if(tablas == "<Todo>") {
					mostrarT();
				}
				if(tablas == "Credicto" ) {
					mostrarCredicto();
				}
				if(tablas == "Contado") {
					mostrarContado();
				}

			}
		});
		cbxtiposFactura.setModel(new DefaultComboBoxModel(new String[] {"<Todo>", "Credicto", "Contado"}));
		cbxtiposFactura.setBounds(132, 33, 117, 20);
		contentPanel.add(cbxtiposFactura);
		
		JLabel lblOrganizarPorTotal = new JLabel("Organizar por Total:");
		lblOrganizarPorTotal.setBounds(259, 36, 133, 14);
		contentPanel.add(lblOrganizarPorTotal);
		
		JButton btnOrgaizar = new JButton("Orgaizar");
		btnOrgaizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarOrganizarFactura();
			}
		});
		btnOrgaizar.setBounds(380, 32, 89, 23);
		contentPanel.add(btnOrgaizar);
		
		JLabel lblMostrarQuesos = new JLabel("Mostrar quesos:");
		lblMostrarQuesos.setBounds(479, 36, 89, 14);
		contentPanel.add(lblMostrarQuesos);
		
		btnMostrar = new JButton("Mostrar");
		btnMostrar.setEnabled(false);
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueosFactura qf = new QueosFactura(fab.buscaFactura(idFactura));
				qf.setVisible(true);
			}
		});
		btnMostrar.setBounds(578, 32, 89, 23);
		contentPanel.add(btnMostrar);
		
	
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void mostrarT() {
		String[][] matriz = new String[fab.getFacturas().size()][5];
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab.getFacturas().size(); i++) {
			matriz[i][0] = fab.getFacturas().get(i).getCodigo();
			matriz[i][1] = fab.getFacturas().get(i).getClientes().getNombre();
			matriz[i][2] = Integer.toString(fab.getFacturas().get(i).getCantQueso().size());
			if(fab.getFacturas().get(i).getTipoPago() == true) {
				matriz[i][3] = "Credicto"; 
			}
			if(fab.getFacturas().get(i).getTipoPago() == false) {
				matriz[i][3] = "Contado";
			}
			matriz[i][4] = df.format(fab.getFacturas().get(i).precioTotalFactura());
			}
		tableFacturas.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Cliente", "Cant. de queso", "Tipo", "Precio total"
				}
			));
		
	}
	
	public void mostrarOrganizarFactura() {
		Collections.sort(fab.getFacturas());
		String[][] matriz = new String[fab.getFacturas().size()][5];
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab.getFacturas().size(); i++) {
			matriz[i][0] = fab.getFacturas().get(i).getCodigo();
			matriz[i][1] = fab.getFacturas().get(i).getClientes().getNombre();
			matriz[i][2] = Integer.toString(fab.getFacturas().get(i).getCantQueso().size());
			if(fab.getFacturas().get(i).getTipoPago() == true) {
				matriz[i][3] = "Credicto"; 
			}
			if(fab.getFacturas().get(i).getTipoPago() == false) {
				matriz[i][3] = "Contado";
			}
			matriz[i][4] = df.format(fab.getFacturas().get(i).precioTotalFactura());
			}
		tableFacturas.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Cliente", "Cant. de queso", "Tipo", "Precio total"
				}
			));
		
	}
	
	public void mostrarCredicto() {
		ArrayList<Factura> factC = new ArrayList<Factura>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab.getFacturas().size(); i++) {
			if(fab.getFacturas().get(i).getTipoPago() == true) {
				if(fab.getFacturas().get(i)!=null) {
						factC.add(fab.getFacturas().get(i));
					}
				}
			}
		String[][] matriz = new String[factC.size()][5];
		for(int i = 0; i<factC.size();i++) {
			matriz[i][0] = factC.get(i).getCodigo();
			matriz[i][1] = factC.get(i).getClientes().getNombre();
			matriz[i][2] = Integer.toString(factC.get(i).getCantQueso().size());
			matriz[i][3] = "Credicto"; 
			matriz[i][4] = df.format(factC.get(i).precioTotalFactura());
			}		
		tableFacturas.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Cliente", "Cant. de queso", "Tipo", "Precio total"
				}
			));
		
	}
	public void mostrarContado() {
		ArrayList<Factura> factC = new ArrayList<Factura>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fab.getFacturas().size(); i++) {
			if(fab.getFacturas().get(i).getTipoPago() == false) {
				if(fab.getFacturas().get(i)!=null) {
						factC.add(fab.getFacturas().get(i));
					}
				}
			}
		String[][] matriz = new String[factC.size()][5];
		for(int i = 0; i<factC.size();i++) {
			matriz[i][0] = factC.get(i).getCodigo();
			matriz[i][1] = factC.get(i).getClientes().getNombre();
			matriz[i][2] = Integer.toString(factC.get(i).getCantQueso().size());
			matriz[i][3] = "Contado";
			matriz[i][4] = df.format(factC.get(i).precioTotalFactura());
			}		
		tableFacturas.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Cliente", "Cant. de queso", "Tipo", "Precio total"
				}
			));
		
	}
}
