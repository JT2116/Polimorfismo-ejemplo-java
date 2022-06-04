package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logica.Cliente;
import logica.FabricaQuesos;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.ActionEvent;

public class RegCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtId;
	private FabricaQuesos fab;
	private JTextField txtTelefono;
	private JSpinner spnCredicto;
	private String idC;
	/**
	 * Launch the application.
	 */
	/**
	 * Create the dialog.
	 */
	public RegCliente(FabricaQuesos fab, String idC) {
		this.fab = fab;
		this.idC = idC;
		setTitle("Registro Cliente");
		setBounds(100, 100, 320, 279);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPanel.setLayout(null);
		
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"icono.png");
		setIconImage(imagen1.getImage());

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 288, 192);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNmeroDeIdentidad = new JLabel("Identificador:");
		lblNmeroDeIdentidad.setBounds(10, 25, 76, 14);
		panel.add(lblNmeroDeIdentidad);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(96, 22, 76, 20);
		panel.add(txtId);
		txtId.setColumns(10);
		txtId.setText("C"+(fab.getIdclie()));
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 56, 60, 14);
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(96, 53, 168, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Direcci\u00F3n:");
		lblNewLabel.setBounds(10, 87, 60, 14);
		panel.add(lblNewLabel);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(96, 84, 168, 20);
		panel.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono:");
		lblNewLabel_1.setBounds(10, 118, 60, 14);
		panel.add(lblNewLabel_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(96, 115, 168, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblCrdicto = new JLabel("Cr\u00E9dicto:");
		lblCrdicto.setBounds(10, 149, 60, 14);
		panel.add(lblCrdicto);
		
		spnCredicto = new JSpinner();
		spnCredicto.setBounds(96, 146, 76, 20);
		panel.add(spnCredicto);
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = txtId.getText();
						String nombre = txtNombre.getText();
						String dire = txtDireccion.getText();
						String tele = txtTelefono.getText();
						float crec = Float.parseFloat(spnCredicto.getValue().toString());
						
						
						if(!nombre.equalsIgnoreCase("") && !id.equalsIgnoreCase("") && !dire.equalsIgnoreCase("") && idC.equalsIgnoreCase("")) {
							Cliente c1 = new Cliente(nombre,dire,tele,crec,id);
							fab.insertarCliente(c1);
							fab.setIdclie(fab.getIdclie()+1);
							JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
							clean();
							}else if(idC.equalsIgnoreCase("")) {
								JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
								}
						if(!idC.equalsIgnoreCase("")) {
							Cliente update = fab.buscaCliente(idC);
							update.setNombre(txtNombre.getText());
							update.setTelefono(txtTelefono.getText());
							update.setDireccion(txtDireccion.getText());
							update.setLimiteCredicto(Float.parseFloat(spnCredicto.getValue().toString()));
							fab.modificarCliente(update);
							listCliente.mostrarT();
							JOptionPane.showMessageDialog(null, "Operación satisfactoria", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
								}
						}
					}
				);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		
				
		
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		cargaCliente(this.idC);
	}
	private void clean() {
		txtId.setText("C"+(fab.getIdclie()));
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtNombre.setText("");
		spnCredicto.setValue(Integer.parseInt("0"));
	}
	private void cargaCliente(String id) {
		Cliente aux = fab.buscaCliente(id);
		if(aux!=null){
		txtId.setText(aux.getId());
		txtId.setEditable(false);
		txtNombre.setText(aux.getNombre());
		txtDireccion.setText(aux.getDireccion());
		txtTelefono.setText(aux.getTelefono());
		spnCredicto.setValue(new Float (aux.getLimiteCredicto()));
		}
	}
		
	
}
