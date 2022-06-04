package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Cilindrico;
import logica.CilindroHueco;
import logica.Esferico;
import logica.Factura;

public class QueosFactura extends JFrame {

	private JPanel contentPane;
	private Factura fac;
	private final JPanel contentPanel = new JPanel();
	private JTable tableCliente;
	private static DefaultTableModel model;
	private static Object[] fila;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public QueosFactura(Factura fac) {
		this.fac = fac;
		setTitle("Listado de Quesos");
		setBounds(100, 100, 699, 405);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"factura.png");
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
	
	public void mostrarT() {
		String[][] matriz = new String[fac.getCantQueso().size()][7];
		DecimalFormat df = new DecimalFormat("#.00");
		for(int i = 0; i<fac.getCantQueso().size(); i++) {
			matriz[i][0] = fac.getCantQueso().get(i).getId();
			matriz[i][1] = df.format(fac.getCantQueso().get(i).getPrecioBase());
			matriz[i][2] = df.format(fac.getCantQueso().get(i).getPrecioUnitario());
			matriz[i][3] = df.format(fac.getCantQueso().get(i).PrecioTotal());
			matriz[i][4] = df.format(fac.getCantQueso().get(i).getCantidad());
			matriz[i][5] = df.format(fac.getCantQueso().get(i).Volumen());
			if(fac.getCantQueso().get(i)  instanceof Esferico) {
				matriz[i][6] = "Esferico";
			}
			if(fac.getCantQueso().get(i)  instanceof Cilindrico) {
				matriz[i][6] = "Cilindro";
			}
			if(fac.getCantQueso().get(i)  instanceof CilindroHueco) {
				matriz[i][6] = "Cilindro hueco";
			}
			}
		tableCliente.setModel(new DefaultTableModel(
				matriz,
				new String[] {
						"Id", "Precio base", "Precio Unitario","Precio total", "Cantidad", "Volumen", "Tipo"
				}
			));
	}

}
