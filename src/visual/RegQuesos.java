package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegQuesos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPrecioBase;
	private JTextField txtPrecioUnidad;
	private JTextField txtRadioEsferico;
	private JRadioButton rdbtnCilindrico;
	private JRadioButton rdbtnCilindricoConHueco;
	private JRadioButton rdbtnEsferico;
	private JPanel panelEsferico;
	private JPanel panelC;
	private JPanel panelCH;
	private JTextField textRadioC;
	private JTextField txtLongitud;
	private JTextField txtRadioCH;
	private JTextField txtRadioInt;
	private JTextField txtLongitudCH;
	private JTextField txtId;
	private FabricaQuesos fab;
	private JTextField txtCantidad;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public RegQuesos(FabricaQuesos fab) {
		this.fab = fab;
		setTitle("Registrar queso");
		setBounds(100, 100, 495, 330);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);		
		
		
		ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"queso (1).png");
		setIconImage(imagen1.getImage());
		
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelGeneral = new JPanel();
		panelGeneral.setBounds(10, 11, 463, 89);
		panelGeneral.setBorder(new TitledBorder(null, "Informacion General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelGeneral);
		panelGeneral.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Precio base:");
			lblNewLabel.setBounds(127, 31, 86, 14);
			panelGeneral.add(lblNewLabel);
		}
		
		txtPrecioBase = new JTextField();
		txtPrecioBase.addKeyListener(new KeyAdapter() {
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
		txtPrecioBase.setBounds(137, 45, 86, 20);
		panelGeneral.add(txtPrecioBase);
		txtPrecioBase.setColumns(10);
		
		JLabel lblPrecioPorUnidad = new JLabel("Precio por unidad:");
		lblPrecioPorUnidad.setBounds(237, 31, 106, 14);
		panelGeneral.add(lblPrecioPorUnidad);
		
		txtPrecioUnidad = new JTextField();
		txtPrecioUnidad.addKeyListener(new KeyAdapter() {
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
		txtPrecioUnidad.setBounds(247, 45, 86, 20);
		panelGeneral.add(txtPrecioUnidad);
		txtPrecioUnidad.setColumns(10);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 32, 46, 14);
		panelGeneral.add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(20, 45, 86, 20);
		panelGeneral.add(txtId);
		txtId.setColumns(10);
		txtId.setText("Q"+(fab.getIdqueso()));
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(351, 31, 76, 14);
		panelGeneral.add(lblCantidad);
		
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
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
		txtCantidad.setBounds(361, 45, 86, 20);
		panelGeneral.add(txtCantidad);
		txtCantidad.setColumns(10);
		
		JPanel panelTipo = new JPanel();
		panelTipo.setBounds(10, 104, 463, 84);
		panelTipo.setBorder(new TitledBorder(null, "Tipo de queso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelTipo);
		panelTipo.setLayout(null);
		
		rdbtnEsferico = new JRadioButton("Esferico");
		rdbtnEsferico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnEsferico.setSelected(true);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilindricoConHueco.setSelected(false);
				panelEsferico.setVisible(true);
				panelCH.setVisible(false);
				panelC.setVisible(false);
			}
		});
		rdbtnEsferico.setSelected(true);
		rdbtnEsferico.setBounds(24, 31, 109, 23);
		panelTipo.add(rdbtnEsferico);
		
		rdbtnCilindrico = new JRadioButton("Cilindrico");
		rdbtnCilindrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnEsferico.setSelected(false);
				rdbtnCilindrico.setSelected(true);
				rdbtnCilindricoConHueco.setSelected(false);
				panelEsferico.setVisible(false);
				panelCH.setVisible(false);
				panelC.setVisible(true);
			}
		});
		rdbtnCilindrico.setBounds(135, 31, 109, 23);
		panelTipo.add(rdbtnCilindrico);
		
		rdbtnCilindricoConHueco = new JRadioButton("Cilindrico con hueco");
		rdbtnCilindricoConHueco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEsferico.setSelected(false);
				rdbtnCilindrico.setSelected(false);
				rdbtnCilindricoConHueco.setSelected(true);
				panelEsferico.setVisible(false);
				panelCH.setVisible(true);
				panelC.setVisible(false);
			}
		});
		rdbtnCilindricoConHueco.setBounds(246, 31, 180, 23);
		panelTipo.add(rdbtnCilindricoConHueco);
		
		panelEsferico = new JPanel();
		panelEsferico.setBounds(10, 199, 463, 59);
		panelEsferico.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelEsferico);
		panelEsferico.setLayout(null);
		
		JLabel lblRadio = new JLabel("Radio:");
		lblRadio.setBounds(30, 23, 46, 14);
		panelEsferico.add(lblRadio);
		
		txtRadioEsferico = new JTextField();
		txtRadioEsferico.addKeyListener(new KeyAdapter() {
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
		txtRadioEsferico.setBounds(86, 20, 86, 20);
		panelEsferico.add(txtRadioEsferico);
		txtRadioEsferico.setColumns(10);
		
		panelCH = new JPanel();
		panelCH.setVisible(false);
		panelCH.setBounds(10, 199, 463, 59);
		panelCH.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelCH);
		panelCH.setLayout(null);
	
		JLabel lblRadio_2 = new JLabel("Radio:");
		lblRadio_2.setBounds(10, 29, 46, 14);
		panelCH.add(lblRadio_2);
		
		txtRadioCH = new JTextField();
		txtRadioCH.addKeyListener(new KeyAdapter() {
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
		txtRadioCH.setBounds(48, 26, 86, 20);
		panelCH.add(txtRadioCH);
		txtRadioCH.setColumns(10);
		
		JLabel lblRadioInt = new JLabel("Radio int:");
		lblRadioInt.setBounds(144, 29, 53, 14);
		panelCH.add(lblRadioInt);
		
		txtRadioInt = new JTextField();
		txtRadioInt.addKeyListener(new KeyAdapter() {
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
		txtRadioInt.setBounds(206, 26, 86, 20);
		panelCH.add(txtRadioInt);
		txtRadioInt.setColumns(10);
		
		JLabel label = new JLabel("Longitud:");
		label.setBounds(303, 29, 57, 14);
		panelCH.add(label);
		
		txtLongitudCH = new JTextField();
		txtLongitudCH.addKeyListener(new KeyAdapter() {
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
		txtLongitudCH.setColumns(10);
		txtLongitudCH.setBounds(366, 26, 86, 20);
		panelCH.add(txtLongitudCH);
		
		panelC = new JPanel();
		panelC.setVisible(false);
		panelC.setBounds(10, 199, 463, 59);
		panelC.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panelC);
		panelC.setLayout(null);
		
		JLabel lblRadio_1 = new JLabel("Radio:");
		lblRadio_1.setBounds(32, 22, 46, 14);
		panelC.add(lblRadio_1);
		
		textRadioC = new JTextField();
		textRadioC.addKeyListener(new KeyAdapter() {
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
		textRadioC.setBounds(88, 19, 86, 20);
		panelC.add(textRadioC);
		textRadioC.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Longitud:");
		lblNewLabel_1.setBounds(184, 22, 57, 14);
		panelC.add(lblNewLabel_1);
		
		txtLongitud = new JTextField();
		txtLongitud.addKeyListener(new KeyAdapter() {
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
		txtLongitud.setBounds(251, 19, 86, 20);
		panelC.add(txtLongitud);
		txtLongitud.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Queso aux = null;
						String id = txtId.getText();
						int lim = 0;
						if(!txtPrecioBase.getText().equalsIgnoreCase("") && !txtPrecioUnidad.getText().equalsIgnoreCase("") && !txtCantidad.getText().equalsIgnoreCase("") && lim<Integer.parseInt(txtCantidad.getText()) && lim<Float.parseFloat(txtPrecioUnidad.getText()) && lim<Float.parseFloat(txtPrecioBase.getText()) ) {
							float precioBase = Float.parseFloat(txtPrecioBase.getText());
							float precioUnitario = Float.parseFloat(txtPrecioUnidad.getText());
							int cantidad = Integer.parseInt(txtCantidad.getText());
							
							if(rdbtnEsferico.isSelected()) {
								if(!txtRadioEsferico.getText().equalsIgnoreCase("") && lim< Float.parseFloat(txtRadioEsferico.getText())) {	
									float radioE =  Float.parseFloat(txtRadioEsferico.getText());
									aux = new Esferico(precioUnitario,precioBase,id,cantidad,radioE);
									fab.insertarQueso(aux);
									clean();	
									fab.setIdqueso(fab.getIdqueso()+1);
									JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
								}

							}
							
							if(rdbtnCilindrico.isSelected()) {

								if(!txtLongitud.getText().equalsIgnoreCase("") && !textRadioC.getText().equalsIgnoreCase("") && lim<Float.parseFloat(txtLongitud.getText()) && lim<Float.parseFloat(textRadioC.getText())) {
									float longitudC = Float.parseFloat(txtLongitud.getText());
									float radioC = Float.parseFloat(textRadioC.getText());
									aux = new Cilindrico(precioUnitario,precioBase,id,cantidad,longitudC,radioC);
									fab.insertarQueso(aux);
									clean();	
									fab.setIdqueso(fab.getIdqueso()+1);
									JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
								}
							}
							if(rdbtnCilindricoConHueco.isSelected()) {
								
								if(!txtLongitudCH.getText().equalsIgnoreCase("") && !txtRadioInt.getText().equalsIgnoreCase("") && !txtRadioCH.getText().equalsIgnoreCase("") && lim<Float.parseFloat(txtLongitudCH.getText()) && lim<Float.parseFloat(txtRadioInt.getText()) && lim<Float.parseFloat(txtRadioCH.getText()) && Float.parseFloat(txtRadioCH.getText())>Float.parseFloat(txtRadioInt.getText())) {
									float longitudCH = Float.parseFloat(txtLongitudCH.getText());
									float radioInt = Float.parseFloat(txtRadioInt.getText());
									float radioCH = Float.parseFloat(txtRadioCH.getText());
									aux = new CilindroHueco(precioUnitario,precioBase,id,cantidad,longitudCH,radioCH,radioInt);
									fab.insertarQueso(aux);
									clean();	
									fab.setIdqueso(fab.getIdqueso()+1);
									JOptionPane.showMessageDialog(null, "Registro Exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
								}else if (Float.parseFloat(txtRadioCH.getText())<Float.parseFloat(txtRadioInt.getText())) {
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
								}else {
									JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
								}
							}
							
							
						}else {
							JOptionPane.showMessageDialog(null, "Revise los datos", "Validación", JOptionPane.WARNING_MESSAGE);
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
	private void clean() {
		textRadioC.setText("");
		txtRadioCH.setText("");
		txtRadioInt.setText("");
		txtCantidad.setText("");
		txtLongitudCH.setText("");
		txtRadioEsferico.setText("");
		txtLongitud.setText("");
		txtId.setText("Q"+(fab.getIdqueso()+1));
		txtPrecioUnidad.setText("");
		txtPrecioBase.setText("");
	}
}
