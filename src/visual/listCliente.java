package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import logica.Cliente;
import logica.FabricaQuesos;
import logica.Factura;


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
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;

public class listCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableCliente;
	private static FabricaQuesos fab1;
	private String idCliente = "";
	private JButton btnModificar;
	private static DefaultTableModel model;
	private String nombreSumi = "";
	private static Object[] fila;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public listCliente(FabricaQuesos fab) {
		this.fab1 = fab;
		setTitle("Listado de Clientes");
		setBounds(100, 100, 699, 405);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"icono.png");
		setIconImage(imagen1.getImage());
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] columns = {"Id", "Nombre", "Direccion", "Telefono", "Crédito", "Balance"};
					tableCliente = new JTable();
					tableCliente.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(tableCliente.getSelectedRow()>=0){
								int index = tableCliente.getSelectedRow();
								btnModificar.setEnabled(true);
								idCliente = (String) tableCliente.getValueAt(index, 0);
							}
						}
					});
					tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columns);
					tableCliente.setModel(model);
					scrollPane.setViewportView(tableCliente);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegCliente mod = new RegCliente(fab,idCliente);
						mod.setModal(true);
						mod.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		mostrarT();
	}
	public static void mostrarT() {
		//String[][] matriz = new String[fab1.getClientes().size()][6];
		DecimalFormat df = new DecimalFormat("#.00");
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for(int i = 0; i<fab1.getClientes().size(); i++) {
			fila[0] = fab1.getClientes().get(i).getId();
			fila[1] = fab1.getClientes().get(i).getNombre();
			fila[2] = fab1.getClientes().get(i).getDireccion();
			fila[3] = fab1.getClientes().get(i).getTelefono();
			fila[4] = df.format(fab1.getClientes().get(i).getLimiteCredicto());
			fila[5] = df.format(fab1.getClientes().get(i).getBalacen());
			model.addRow(fila);
			}		
	}
	/*public void mostrarMeMaBa() {
		//ArrayList<Cliente> clie = new ArrayList<Cliente>();
	
		Collections.sort(fab.getClientes());
		DecimalFormat df = new DecimalFormat("#.00");
		String[][] matriz = new String[fab.getClientes().size()][6];
		for(int i = 0; i<fab.getClientes().size(); i++) {
			matriz[i][0] = fab.getClientes().get(i).getId();
			matriz[i][1] = fab.getClientes().get(i).getNombre();
			matriz[i][2] = fab.getClientes().get(i).getDireccion();
			matriz[i][3] = fab.getClientes().get(i).getTelefono();
			matriz[i][4] = df.format(fab.getClientes().get(i).getLimiteCredicto());
			matriz[i][5] = df.format(fab.getClientes().get(i).getBalacen());
			}
		tableCliente.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Nombre", "Direccion", "Telefono", "Crédito", "Balance"
				}
			));
	}
	public void mostrarMaMeBa() {
		
		Collections.sort(fab.getClientes());
		DecimalFormat df = new DecimalFormat("#.00");
		String[][] matriz = new String[fab.getClientes().size()][6];
		for(int i = 0; i<fab.getClientes().size(); i++) {
			matriz[i][0] = fab.getClientes().get(i).getId();
			matriz[i][1] = fab.getClientes().get(i).getNombre();
			matriz[i][2] = fab.getClientes().get(i).getDireccion();
			matriz[i][3] = fab.getClientes().get(i).getTelefono();
			matriz[i][4] = df.format(fab.getClientes().get(i).getLimiteCredicto());
			matriz[i][5] = df.format(fab.getClientes().get(i).getBalacen());
			}
		tableCliente.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Nombre", "Direccion", "Telefono", "Crédito", "Balance"
						}
			));
		}*/
	
	
	
	
	
}
