package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Panel;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.FabricaQuesos;
import logica.Queso;
import javax.swing.*;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Container;

public class Pri extends JFrame {

	private JPanel contentPane;
	private FabricaQuesos fab;
	private ArrayList<Queso> queso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Queso> queso = new ArrayList<Queso>();
					FabricaQuesos fab = new FabricaQuesos();
					Pri frame = new Pri(fab, queso);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pri(FabricaQuesos fab,ArrayList<Queso> queso) {
		this.fab = fab;
		this.queso = queso;
		
		setTitle("Tienda de quesos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1350, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
        /*contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);*/
         
        ImageIcon imagen1=new ImageIcon("imagenes"+File.separator+"queso.png");
        ImageIcon imagen2=new ImageIcon("imagenes"+File.separator+"fondo.png");
        setIconImage(imagen1.getImage());
       
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		ImageIcon imagen5 = new ImageIcon("imagenes"+File.separator+"queso (1).png");
		JMenu mnQueso = new JMenu("Queso");
		menuBar.add(mnQueso);
		mnQueso.setIcon(imagen5);
		
		JMenuItem mntmRegistrarQueso = new JMenuItem("Registrar queso");
		mntmRegistrarQueso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegQuesos rq = new RegQuesos(fab);
				rq.setVisible(true);
			}
		});
		mnQueso.add(mntmRegistrarQueso);
		
		JMenuItem mntmListaDeQuesos = new JMenuItem("Lista de quesos");
		mntmListaDeQuesos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listQueso listq = new listQueso(fab);
				listq.setVisible(true);
			}
		});
		mnQueso.add(mntmListaDeQuesos);
		ImageIcon imagen3=new ImageIcon("imagenes"+File.separator+"icono.png");
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		mnCliente.setIcon(imagen3);
		
		JMenuItem mntmListaDeClientes = new JMenuItem("Lista de clientes");
		mntmListaDeClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCliente listC = new listCliente(fab);
				listC.setVisible(true);
			}
		});
		mnCliente.add(mntmListaDeClientes);
		ImageIcon imagen4=new ImageIcon("imagenes"+File.separator+"factura.png");
		JMenu mnFactura = new JMenu("Factura");
		menuBar.add(mnFactura);
		mnFactura.setIcon(imagen4);
		JMenuItem mntmRegistrarFactura = new JMenuItem("Registrar factura");
		mntmRegistrarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Facturas fact = new Facturas(fab,queso);
				fact.setVisible(true);
			}
		});
		mnFactura.add(mntmRegistrarFactura);
		
		JMenuItem mntmListaDeFacturas = new JMenuItem("Lista de facturas");
		mntmListaDeFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listFactura listF = new listFactura(fab);
				listF.setVisible(true);
			}
		});
		mnFactura.add(mntmListaDeFacturas);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(imagen2);
		lblNewLabel.setBounds(853, 236, 558, 533);
		contentPane.add(lblNewLabel);
		
		
	
	}
}
