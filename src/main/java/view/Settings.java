package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import idiomas.Idioma;
import model.Room;
import propiedades.PropertiesManager;
import utils.MyColors;
import utils.MyFont;
import utils.SwingFader;

import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.lang3.StringUtils;

import controller.SalasManager;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Settings extends JDialog {
	private JTextField txtNombreEvento;
	private JTextField txtServer;
	private JTextField txtServerPrefix;
	
	private Idioma idioma;
	private JRadioButton rdEsp;
	private JRadioButton rdIng;
	
	private Font fuenteTitulo = new MyFont(MyFont.BOLD).getFont(20f);
	private Font fuenteLabel = new MyFont(MyFont.LIGHT).getFont(20f);
	private Font fuenteRadio = new MyFont(MyFont.LIGHT).getFont(14f);
	private Font fuenteBotones = new MyFont(MyFont.BOLD).getFont(14f);
	private Font fuenteRutas = new MyFont(MyFont.LIGHT).getFont(14f);
	private Font fuenteTextfield= new MyFont(MyFont.LIGHT).getFont(14f);
	
	private JComboBox<Room> cmbSalas;
	private JLabel lblLogoPath;
	private JLabel lblPdfPath;
	private JLabel lblVideoPath;
	private JLabel lblPptPath;
	private JLabel lblDataPath;
	private JLabel lblScriptPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings dialog = new Settings();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the dialog.
	 */
	public Settings() {
		setUndecorated(true);
		setBounds(100, 100, 559, 647);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));
		
		JPanel panelSup = new JPanel();
		panelSup.setBackground(MyColors.COLOR_AZUL);
		getContentPane().add(panelSup, BorderLayout.NORTH);
		panelSup.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTitulo = new JLabel(idioma.getProperty("conf-titulo"));
		lblTitulo.setFont(fuenteTitulo);
		lblTitulo.setForeground(Color.WHITE);
		panelSup.add(lblTitulo, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panelSup.add(panel, BorderLayout.EAST);
		
		rdEsp = new JRadioButton("ESP");
		rdEsp.setFont(fuenteRadio);
		rdEsp.setForeground(Color.WHITE);
		rdEsp.setOpaque(false);
		rdEsp.setSelected(true);
		panel.add(rdEsp);
		
		JLabel lblFlagEsp = new JLabel("");
		ImageIcon ic = new ImageIcon(Settings.class.getResource("/img/flags/es.png"));
		Image img= ic.getImage();
		ImageIcon icScaled=new ImageIcon(img.getScaledInstance(25, 20, Image.SCALE_SMOOTH));
		lblFlagEsp.setIcon(icScaled);
		panel.add(lblFlagEsp);
		
		rdIng = new JRadioButton("ING");
		rdIng.setFont(fuenteRadio);
		rdIng.setForeground(Color.WHITE);
		rdIng.setOpaque(false);
		panel.add(rdIng);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdEsp);
		bg.add(rdIng);
		
		JLabel lblFlagIng = new JLabel("");		
		ImageIcon icIng = new ImageIcon(getClass().getResource("/img/flags/gb.png"));
		Image imgIng= icIng.getImage();
		ImageIcon icScaledIng=new ImageIcon(imgIng.getScaledInstance(25, 20, Image.SCALE_SMOOTH));
		lblFlagIng.setIcon(icScaledIng);
		panel.add(lblFlagIng);
		
		JLabel lblNewLabel_9 = new JLabel("     ");
		panelSup.add(lblNewLabel_9, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 0, 0, 10, 0};
		gbl_panel_1.rowHeights = new int[]{30, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNombreEvento = new JLabel(idioma.getProperty("conf-nombre-evento"));
		lblNombreEvento.setFont(fuenteLabel);
		lblNombreEvento.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblNombreEvento = new GridBagConstraints();
		gbc_lblNombreEvento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreEvento.anchor = GridBagConstraints.WEST;
		gbc_lblNombreEvento.gridx = 1;
		gbc_lblNombreEvento.gridy = 1;
		panel_1.add(lblNombreEvento, gbc_lblNombreEvento);
		
		txtNombreEvento = new JTextField();
		txtNombreEvento.setFont(fuenteTextfield);
		GridBagConstraints gbc_txtNombreEvento = new GridBagConstraints();
		gbc_txtNombreEvento.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreEvento.gridx = 2;
		gbc_txtNombreEvento.gridy = 1;
		panel_1.add(txtNombreEvento, gbc_txtNombreEvento);
		txtNombreEvento.setColumns(10);
		
		JLabel lblSalas = new JLabel(idioma.getProperty("conf-sala"));
		lblSalas.setFont(fuenteLabel);
		lblSalas.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblSalas = new GridBagConstraints();
		gbc_lblSalas.anchor = GridBagConstraints.WEST;
		gbc_lblSalas.insets = new Insets(0, 0, 5, 5);
		gbc_lblSalas.gridx = 1;
		gbc_lblSalas.gridy = 3;
		panel_1.add(lblSalas, gbc_lblSalas);
		
		cmbSalas = new JComboBox<Room>();	
		cmbSalas.setFont(fuenteTextfield);
		cmbSalas.addItem(new Room(0, null, idioma.getProperty("conf-sala-pos-0")));
		GridBagConstraints gbc_cmbSalas = new GridBagConstraints();
		gbc_cmbSalas.insets = new Insets(0, 0, 5, 5);
		gbc_cmbSalas.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSalas.gridx = 2;
		gbc_cmbSalas.gridy = 3;
		panel_1.add(cmbSalas, gbc_cmbSalas);		
		
		JLabel lblLogo = new JLabel(idioma.getProperty("conf-logo"));
		lblLogo.setFont(fuenteLabel);
		lblLogo.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblLogo = new GridBagConstraints();
		gbc_lblLogo.anchor = GridBagConstraints.WEST;
		gbc_lblLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogo.gridx = 1;
		gbc_lblLogo.gridy = 5;
		panel_1.add(lblLogo, gbc_lblLogo);
		
		lblLogoPath = new JLabel("");
		lblLogoPath.setFont(fuenteRutas);
		lblLogoPath.setForeground(MyColors.COLOR_BORDE);
		GridBagConstraints gbc_lblLogoPath = new GridBagConstraints();
		gbc_lblLogoPath.anchor = GridBagConstraints.WEST;
		gbc_lblLogoPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogoPath.gridx = 2;
		gbc_lblLogoPath.gridy = 5;
		panel_1.add(lblLogoPath, gbc_lblLogoPath);
		
		JButton btnLogo = new JButton(idioma.getProperty("conf-btn-seleccionar"));
		btnLogo.setFont(fuenteBotones);
		btnLogo.setForeground(MyColors.COLOR_AZUL);
		btnLogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("JPG, PNG & GIF","jpg","png","gif");
				chooser.setFileFilter(filtroImagen);
				int valor = chooser.showOpenDialog(chooser);
				if (valor == JFileChooser.APPROVE_OPTION) {
					String ruta = chooser.getSelectedFile().getAbsolutePath();
					//Image img = new ImageIcon(ruta).getImage();
					
					//lblLogoView.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
					lblLogoPath.setText(ruta);
				}
			}
		});
		GridBagConstraints gbc_btnLogo = new GridBagConstraints();
		gbc_btnLogo.anchor = GridBagConstraints.WEST;
		gbc_btnLogo.insets = new Insets(0, 0, 5, 5);
		gbc_btnLogo.gridx = 3;
		gbc_btnLogo.gridy = 5;
		panel_1.add(btnLogo, gbc_btnLogo);
		
		JLabel lblServidor = new JLabel(idioma.getProperty("conf-servidor"));
		lblServidor.setFont(fuenteLabel);
		lblServidor.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblServidor = new GridBagConstraints();
		gbc_lblServidor.anchor = GridBagConstraints.WEST;
		gbc_lblServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblServidor.gridx = 1;
		gbc_lblServidor.gridy = 7;
		panel_1.add(lblServidor, gbc_lblServidor);
		
		txtServer = new JTextField();
		txtServer.setFont(fuenteTextfield);
		GridBagConstraints gbc_txtServer = new GridBagConstraints();
		gbc_txtServer.insets = new Insets(0, 0, 5, 5);
		gbc_txtServer.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtServer.gridx = 2;
		gbc_txtServer.gridy = 7;
		panel_1.add(txtServer, gbc_txtServer);
		txtServer.setColumns(10);
		
		JLabel lblServerPrefix = new JLabel(idioma.getProperty("conf-codigo-evento"));
		lblServerPrefix.setFont(fuenteLabel);
		lblServerPrefix.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblServerPrefix = new GridBagConstraints();
		gbc_lblServerPrefix.anchor = GridBagConstraints.WEST;
		gbc_lblServerPrefix.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerPrefix.gridx = 1;
		gbc_lblServerPrefix.gridy = 9;
		panel_1.add(lblServerPrefix, gbc_lblServerPrefix);
		
		txtServerPrefix = new JTextField();
		txtServerPrefix.setFont(fuenteTextfield);
		GridBagConstraints gbc_txtServerPrefix = new GridBagConstraints();
		gbc_txtServerPrefix.insets = new Insets(0, 0, 5, 5);
		gbc_txtServerPrefix.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtServerPrefix.gridx = 2;
		gbc_txtServerPrefix.gridy = 9;
		panel_1.add(txtServerPrefix, gbc_txtServerPrefix);
		txtServerPrefix.setColumns(10);
		
		JLabel lblppt = new JLabel(idioma.getProperty("conf-powerpoint"));
		lblppt.setFont(fuenteLabel);
		lblppt.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblppt = new GridBagConstraints();
		gbc_lblppt.anchor = GridBagConstraints.WEST;
		gbc_lblppt.insets = new Insets(0, 0, 5, 5);
		gbc_lblppt.gridx = 1;
		gbc_lblppt.gridy = 11;
		panel_1.add(lblppt, gbc_lblppt);
		
		lblPptPath = new JLabel("");
		lblPptPath.setFont(fuenteRutas);
		lblPptPath.setForeground(MyColors.COLOR_BORDE);
		GridBagConstraints gbc_lblPptPath = new GridBagConstraints();
		gbc_lblPptPath.anchor = GridBagConstraints.WEST;
		gbc_lblPptPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblPptPath.gridx = 2;
		gbc_lblPptPath.gridy = 11;
		panel_1.add(lblPptPath, gbc_lblPptPath);
		
		JButton btnPowerpoint = new JButton(idioma.getProperty("conf-btn-seleccionar"));
		btnPowerpoint.setFont(fuenteBotones);
		btnPowerpoint.setForeground(MyColors.COLOR_AZUL);
		btnPowerpoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(lblPptPath.getText());
				FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("exe, app","exe","app");
				chooser.setFileFilter(filtroImagen);
				int valor = chooser.showOpenDialog(chooser);
				if (valor == JFileChooser.APPROVE_OPTION) {
					String ruta = chooser.getSelectedFile().getAbsolutePath();
					lblPptPath.setText(ruta);
				}
			}
		});
		GridBagConstraints gbc_btnPowerpoint = new GridBagConstraints();
		gbc_btnPowerpoint.anchor = GridBagConstraints.WEST;
		gbc_btnPowerpoint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPowerpoint.gridx = 3;
		gbc_btnPowerpoint.gridy = 11;
		panel_1.add(btnPowerpoint, gbc_btnPowerpoint);
		
		JLabel lblvideo = new JLabel(idioma.getProperty("conf-video"));
		lblvideo.setFont(fuenteLabel);
		lblvideo.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblvideo = new GridBagConstraints();
		gbc_lblvideo.anchor = GridBagConstraints.WEST;
		gbc_lblvideo.insets = new Insets(0, 0, 5, 5);
		gbc_lblvideo.gridx = 1;
		gbc_lblvideo.gridy = 13;
		panel_1.add(lblvideo, gbc_lblvideo);
		
		lblVideoPath = new JLabel("");
		lblVideoPath.setFont(fuenteRutas);
		lblVideoPath.setForeground(MyColors.COLOR_BORDE);
		GridBagConstraints gbc_lblVideoPath = new GridBagConstraints();
		gbc_lblVideoPath.anchor = GridBagConstraints.WEST;
		gbc_lblVideoPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblVideoPath.gridx = 2;
		gbc_lblVideoPath.gridy = 13;
		panel_1.add(lblVideoPath, gbc_lblVideoPath);
		
		JButton btnVideo = new JButton(idioma.getProperty("conf-btn-seleccionar"));
		btnVideo.setFont(fuenteBotones);
		btnVideo.setForeground(MyColors.COLOR_AZUL);
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(lblVideoPath.getText());
				FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("exe, app","exe","app");
				chooser.setFileFilter(filtroImagen);
				int valor = chooser.showOpenDialog(chooser);
				if (valor == JFileChooser.APPROVE_OPTION) {
					String ruta = chooser.getSelectedFile().getAbsolutePath();
					lblVideoPath.setText(ruta);
				}
			}
		});
		GridBagConstraints gbc_btnVideo = new GridBagConstraints();
		gbc_btnVideo.anchor = GridBagConstraints.WEST;
		gbc_btnVideo.insets = new Insets(0, 0, 5, 5);
		gbc_btnVideo.gridx = 3;
		gbc_btnVideo.gridy = 13;
		panel_1.add(btnVideo, gbc_btnVideo);
		
		JLabel lblpdf = new JLabel(idioma.getProperty("conf-pdf"));
		lblpdf.setFont(fuenteLabel);
		lblpdf.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblpdf = new GridBagConstraints();
		gbc_lblpdf.anchor = GridBagConstraints.WEST;
		gbc_lblpdf.insets = new Insets(0, 0, 5, 5);
		gbc_lblpdf.gridx = 1;
		gbc_lblpdf.gridy = 15;
		panel_1.add(lblpdf, gbc_lblpdf);
		
		lblPdfPath = new JLabel("");
		lblPdfPath.setFont(fuenteRutas);
		lblPdfPath.setForeground(MyColors.COLOR_BORDE);
		GridBagConstraints gbc_lblPdfPath = new GridBagConstraints();
		gbc_lblPdfPath.anchor = GridBagConstraints.WEST;
		gbc_lblPdfPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblPdfPath.gridx = 2;
		gbc_lblPdfPath.gridy = 15;
		panel_1.add(lblPdfPath, gbc_lblPdfPath);
		
		JButton btnPdf = new JButton(idioma.getProperty("conf-btn-seleccionar"));
		btnPdf.setFont(fuenteBotones);
		btnPdf.setForeground(MyColors.COLOR_AZUL);
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(lblPdfPath.getText());
				FileNameExtensionFilter filtroImagen=new FileNameExtensionFilter("exe, app","exe","app");
				chooser.setFileFilter(filtroImagen);
				int valor = chooser.showOpenDialog(chooser);
				if (valor == JFileChooser.APPROVE_OPTION) {
					String ruta = chooser.getSelectedFile().getAbsolutePath();
					lblPdfPath.setText(ruta);
				}
			}
		});
		GridBagConstraints gbc_btnPdf = new GridBagConstraints();
		gbc_btnPdf.anchor = GridBagConstraints.WEST;
		gbc_btnPdf.insets = new Insets(0, 0, 5, 5);
		gbc_btnPdf.gridx = 3;
		gbc_btnPdf.gridy = 15;
		panel_1.add(btnPdf, gbc_btnPdf);
		
		JLabel lblData = new JLabel(idioma.getProperty("conf-data"));
		lblData.setFont(fuenteLabel);
		lblData.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.WEST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 1;
		gbc_lblData.gridy = 17;
		panel_1.add(lblData, gbc_lblData);
		
		lblDataPath = new JLabel("");
		lblDataPath.setFont(fuenteRutas);
		lblDataPath.setForeground(MyColors.COLOR_BORDE);
		GridBagConstraints gbc_lblDataPath = new GridBagConstraints();
		gbc_lblDataPath.anchor = GridBagConstraints.WEST;
		gbc_lblDataPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataPath.gridx = 2;
		gbc_lblDataPath.gridy = 17;
		panel_1.add(lblDataPath, gbc_lblDataPath);
		
		JButton btnData = new JButton(idioma.getProperty("conf-btn-data"));
		btnData.setFont(fuenteBotones);
		btnData.setForeground(MyColors.COLOR_AZUL);
		btnData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(lblDataPath.getText());
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int valor = chooser.showOpenDialog(chooser);
				if (valor == JFileChooser.APPROVE_OPTION) {
					String ruta = chooser.getSelectedFile().getAbsolutePath();
					lblDataPath.setText(ruta);
				}
			}
		});
		GridBagConstraints gbc_btnData = new GridBagConstraints();
		gbc_btnData.insets = new Insets(0, 0, 5, 5);
		gbc_btnData.gridx = 3;
		gbc_btnData.gridy = 17;
		panel_1.add(btnData, gbc_btnData);
		
		JLabel lblScript = new JLabel(idioma.getProperty("conf-script"));
		lblScript.setForeground(new Color(33, 64, 128));
		lblScript.setFont(new Font("Nunito Sans Light", Font.PLAIN, 20));
		GridBagConstraints gbc_lblScript = new GridBagConstraints();
		gbc_lblScript.anchor = GridBagConstraints.WEST;
		gbc_lblScript.insets = new Insets(0, 0, 5, 5);
		gbc_lblScript.gridx = 1;
		gbc_lblScript.gridy = 19;
		panel_1.add(lblScript, gbc_lblScript);
		
		lblScriptPath = new JLabel("");
		lblScriptPath.setForeground(new Color(106, 106, 106));
		lblScriptPath.setFont(new Font("Nunito Sans Light", Font.PLAIN, 14));
		GridBagConstraints gbc_lblScriptPath = new GridBagConstraints();
		gbc_lblScriptPath.insets = new Insets(0, 0, 5, 5);
		gbc_lblScriptPath.gridx = 2;
		gbc_lblScriptPath.gridy = 19;
		panel_1.add(lblScriptPath, gbc_lblScriptPath);
		
		JButton btnScript = new JButton(idioma.getProperty("conf-btn-script"));
		btnScript.setForeground(MyColors.COLOR_AZUL);
		btnScript.setFont(fuenteBotones);
		btnScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(lblScriptPath.getText());
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int valor = chooser.showOpenDialog(chooser);
				if (valor == JFileChooser.APPROVE_OPTION) {
					String ruta = chooser.getSelectedFile().getAbsolutePath();
					lblScriptPath.setText(ruta);
				}
			}
		});
		GridBagConstraints gbc_btnScript = new GridBagConstraints();
		gbc_btnScript.insets = new Insets(0, 0, 5, 5);
		gbc_btnScript.gridx = 3;
		gbc_btnScript.gridy = 19;
		panel_1.add(btnScript, gbc_btnScript);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(MyColors.COLOR_AZUL);
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton(idioma.getProperty("conf-btn-guardar"));
		btnGuardar.setFont(fuenteBotones);
		btnGuardar.setForeground(MyColors.COLOR_AZUL);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarOpciones();
				SwingFader.fadeOut(Settings.this);
			}
		});
		panel_2.add(btnGuardar);
		
		JButton btnCancelar = new JButton(idioma.getProperty("conf-btn-cancelar"));
		btnCancelar.setFont(fuenteBotones);
		btnCancelar.setForeground(MyColors.COLOR_AZUL);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingFader.fadeOut(Settings.this);
			}
		});
		panel_2.add(btnCancelar);
		
		cargarSalas();
		cargarOpciones();

	}

	private void cargarSalas() {
		SalasManager api = new SalasManager();
		ArrayList<Room> allRooms = api.getAllRooms();
		
		if(allRooms != null) {
			Iterator<Room> it = allRooms.iterator();
			while(it.hasNext()) {
				Room r = it.next();
				cmbSalas.addItem(r);
				if(!PropertiesManager.getInstance().getProperty("sala").isEmpty()) {
					if(PropertiesManager.getInstance().getProperty("sala").equals(r.getName())) {
						cmbSalas.setSelectedItem(r);
					}
				}
			}
		}
	}

	private void cargarOpciones() {
		// TODO Auto-generated method stub
		if(PropertiesManager.getInstance().getProperty("idioma").equals("Español"))
			rdEsp.setSelected(true);
		else if(PropertiesManager.getInstance().getProperty("idioma").equals("Ingles"))
			rdIng.setSelected(true);		
		txtServer.setText(PropertiesManager.getInstance().getProperty("server"));
		lblPptPath.setText(PropertiesManager.getInstance().getProperty("powerpointRoute"));
		lblVideoPath.setText(PropertiesManager.getInstance().getProperty("mediaRoute"));
		lblPdfPath.setText(PropertiesManager.getInstance().getProperty("pdfRoute"));
		txtServerPrefix.setText(PropertiesManager.getInstance().getProperty("eventPrefix"));
		txtNombreEvento.setText(PropertiesManager.getInstance().getProperty("eventName"));
		lblDataPath.setText(PropertiesManager.getInstance().getProperty("data"));
		lblScriptPath.setText(PropertiesManager.getInstance().getProperty("script"));
		
		if(!StringUtils.isBlank(PropertiesManager.getInstance().getProperty("logo"))) {
			lblLogoPath.setText(PropertiesManager.getInstance().getProperty("logo"));
			//Image img = new ImageIcon(PropertiesManager.getInstance().getProperty("logo")).getImage();		
			//lblLogoView.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		}
	}
	
	private void guardarOpciones() {
		
		HashMap<String, String> ourHashmap = new HashMap<>();

		if(cmbSalas.getSelectedIndex()>=1) {
			ourHashmap.put("sala", ((Room)cmbSalas.getSelectedItem()).getName());
			int id =((Room)cmbSalas.getSelectedItem()).getId();
			ourHashmap.put("salaId",String.valueOf(id));
		}
		
		if(rdEsp.isSelected()) {
			ourHashmap.put("idioma", "Espanol");
		}
		else {
			ourHashmap.put("idioma", "Ingles");
		}
		
		ourHashmap.put("powerpointRoute",lblPptPath.getText());
		ourHashmap.put("server", txtServer.getText());
		ourHashmap.put("pdfRoute", lblPdfPath.getText());
		ourHashmap.put("mediaRoute", lblVideoPath.getText());
		ourHashmap.put("logo", lblLogoPath.getText());
		ourHashmap.put("eventPrefix", txtServerPrefix.getText());
		ourHashmap.put("eventName", txtNombreEvento.getText());
		ourHashmap.put("data", lblDataPath.getText());
		ourHashmap.put("script", lblScriptPath.getText());
		
		PropertiesManager.getInstance().setProperties(ourHashmap);
	}

}
