package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JLayeredPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import view.components.CLabel;
import vo.BloqueVo;
import vo.SpeakerVo;

import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import utils.MyColors;
import utils.MyFont;
import utils.SwingFader;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import org.apache.commons.lang3.StringUtils;

import idiomas.Idioma;
import model.Speaker;
import propiedades.PropertiesManager;

import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class SpeakerPreview extends JDialog {
	private CLabel lbLogo;
	private JLabel lblNonmbrEvento;
	private JLabel lblNombreSala;
	private JLabel lblTitulo;
	private JLabel lblNombreDisertante;
	private JTextArea txtrBio;
	private CLabel lblPicture;
	private JLabel lblQr;
	private JLabel lblSponsor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpeakerPreview dialog = new SpeakerPreview(null);
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
	public SpeakerPreview(SpeakerVo speaker) {
		this.setModal(true);
		
		Idioma idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));
		
		getContentPane().setBackground(MyColors.COLOR_FONDO);
		setBounds(0, 0, Integer.parseInt(PropertiesManager.getInstance().getProperty("res_x")), Integer.parseInt(PropertiesManager.getInstance().getProperty("res_y")));
		setUndecorated(true);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		ImageIcon ic = new ImageIcon(BlockView.class.getResource("/octopus.png").getPath());
		Image img= ic.getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		
		JLayeredPane panelSuperior = new JLayeredPane();
		panelSuperior.setOpaque(true);
		panelSuperior.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelSuperior = new GridBagConstraints();
		gbc_panelSuperior.fill = GridBagConstraints.BOTH;
		gbc_panelSuperior.insets = new Insets(0, 0, 5, 0);
		gbc_panelSuperior.gridx = 0;
		gbc_panelSuperior.gridy = 0;
		getContentPane().add(panelSuperior, gbc_panelSuperior);
		GridBagLayout gbl_panelSuperior = new GridBagLayout();
		gbl_panelSuperior.columnWidths = new int[]{50, 150, 854, 0, 50, 0};
		gbl_panelSuperior.rowHeights = new int[]{20, 100, 20, 0};
		gbl_panelSuperior.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSuperior.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSuperior.setLayout(gbl_panelSuperior);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridwidth = 5;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panelSuperior.add(panel_3, gbc_panel_3);
		
		lblNonmbrEvento = new JLabel("NOMBRE DEL CONGRESO");
		lblNonmbrEvento.setForeground(new Color(33, 64, 128));
		lblNonmbrEvento.setFont(new MyFont(MyFont.BOLD).getFont(32f));
		panel_3.add(lblNonmbrEvento);
		
		lbLogo = new CLabel(new Dimension(100, 100));
		lbLogo.setIcon(img2);
		GridBagConstraints gbc_lbLogo = new GridBagConstraints();
		gbc_lbLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lbLogo.gridx = 1;
		gbc_lbLogo.gridy = 1;
		panelSuperior.add(lbLogo, gbc_lbLogo);
		
		JPanel panel_6 = new JPanel();
		panel_6.setOpaque(false);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.gridx = 3;
		gbc_panel_6.gridy = 1;
		panelSuperior.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{97, 0, 0};
		gbl_panel_6.rowHeights = new int[]{29, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		JLabel lblSala = new JLabel(idioma.getProperty("event-sala"));
		lblSala.setForeground(new Color(33, 64, 128));
		lblSala.setFont(new MyFont(MyFont.LIGHT).getFont(20f));
		GridBagConstraints gbc_lblSala = new GridBagConstraints();
		gbc_lblSala.anchor = GridBagConstraints.EAST;
		gbc_lblSala.insets = new Insets(0, 0, 0, 5);
		gbc_lblSala.gridx = 0;
		gbc_lblSala.gridy = 0;
		panel_6.add(lblSala, gbc_lblSala);
		
		lblNombreSala = new JLabel(StringUtils.upperCase("Sala no asignada"));
		lblNombreSala.setForeground(new Color(103, 152, 233));
		lblNombreSala.setFont(new MyFont(MyFont.BOLD).getFont(20f));
		GridBagConstraints gbc_lblNombreSala = new GridBagConstraints();
		gbc_lblNombreSala.anchor = GridBagConstraints.WEST;
		gbc_lblNombreSala.gridx = 1;
		gbc_lblNombreSala.gridy = 0;
		panel_6.add(lblNombreSala, gbc_lblNombreSala);
		
		JPanel panelCentr = new JPanel();
		panelCentr.setBackground(MyColors.COLOR_FONDO);
		GridBagConstraints gbc_panelCentr = new GridBagConstraints();
		gbc_panelCentr.fill = GridBagConstraints.BOTH;
		gbc_panelCentr.gridx = 0;
		gbc_panelCentr.gridy = 1;
		getContentPane().add(panelCentr, gbc_panelCentr);
		GridBagLayout gbl_panelCentr = new GridBagLayout();
		gbl_panelCentr.columnWidths = new int[]{50, 0, 20, 0, 150, 0, 50, 0};
		gbl_panelCentr.rowHeights = new int[]{50, 0, 20, 0, 0, 0};
		gbl_panelCentr.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentr.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentr.setLayout(gbl_panelCentr);
		
		lblPicture = new CLabel(new Dimension(150, 150));
		GridBagConstraints gbc_lblPicture = new GridBagConstraints();
		gbc_lblPicture.insets = new Insets(0, 0, 5, 5);
		gbc_lblPicture.gridx = 1;
		gbc_lblPicture.gridy = 1;
		panelCentr.add(lblPicture, gbc_lblPicture);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setOpaque(false);
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.gridx = 3;
		gbc_panelDatos.gridy = 1;
		panelCentr.add(panelDatos, gbc_panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{20, 0, 5, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		lblTitulo = new JLabel(StringUtils.upperCase("TITULO AQUI"));
		lblTitulo.setBorder(new CompoundBorder(new MatteBorder(0, 5, 0, 0, (Color) MyColors.COLOR_BOTONES), new EmptyBorder(0, 20, 0, 0)));
		lblTitulo.setFont(new MyFont(MyFont.BOLD).getFont(28f));
		lblTitulo.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.WEST;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 1;
		gbc_lblTitulo.gridy = 1;
		panelDatos.add(lblTitulo, gbc_lblTitulo);
		
		lblNombreDisertante = new JLabel("Nombre y apellido");
		lblNombreDisertante.setFont(new MyFont(MyFont.BOLD).getFont(22f));
		lblNombreDisertante.setForeground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_lblNombreDisertante = new GridBagConstraints();
		gbc_lblNombreDisertante.anchor = GridBagConstraints.WEST;
		gbc_lblNombreDisertante.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDisertante.gridx = 1;
		gbc_lblNombreDisertante.gridy = 3;
		panelDatos.add(lblNombreDisertante, gbc_lblNombreDisertante);
		
		JPanel panelRedes = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRedes.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelRedes.setOpaque(false);
		GridBagConstraints gbc_panelRedes = new GridBagConstraints();
		gbc_panelRedes.insets = new Insets(0, 0, 0, 5);
		gbc_panelRedes.fill = GridBagConstraints.BOTH;
		gbc_panelRedes.gridx = 1;
		gbc_panelRedes.gridy = 5;
		panelDatos.add(panelRedes, gbc_panelRedes);
		
		if(speaker.getSpeaker().getFacebookLink()!=null) {
			JLabel lblNewLabel = new JLabel("");
			ImageIcon ic_logo = new ImageIcon(SpeakerPreview.class.getResource("/social_media/facebook.png"));
			Image img_logo= ic_logo.getImage();
			ImageIcon ic_logo_scaled=new ImageIcon(img_logo.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
			lblNewLabel.setIcon(ic_logo_scaled);
			panelRedes.add(lblNewLabel);
		}
		
		if(speaker.getSpeaker().getInstagramLink()!=null) {
			JLabel lblNewLabel = new JLabel("");
			ImageIcon ic_logo = new ImageIcon(SpeakerPreview.class.getResource("/social_media/instagram.png"));
			Image img_logo= ic_logo.getImage();
			ImageIcon ic_logo_scaled=new ImageIcon(img_logo.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
			lblNewLabel.setIcon(ic_logo_scaled);
			panelRedes.add(lblNewLabel);
		}
		
		if(speaker.getSpeaker().getLinkedinLink()!=null) {
			JLabel lblNewLabel = new JLabel("");
			ImageIcon ic_logo = new ImageIcon(SpeakerPreview.class.getResource("/social_media/linkedin.png"));
			Image img_logo= ic_logo.getImage();
			ImageIcon ic_logo_scaled=new ImageIcon(img_logo.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
			lblNewLabel.setIcon(ic_logo_scaled);
			panelRedes.add(lblNewLabel);
		}
		
		if(speaker.getSpeaker().getTwitterLink()!=null) {
			JLabel lblNewLabel = new JLabel("");
			ImageIcon ic_logo = new ImageIcon(SpeakerPreview.class.getResource("/social_media/twitter.png"));
			Image img_logo= ic_logo.getImage();
			ImageIcon ic_logo_scaled=new ImageIcon(img_logo.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
			lblNewLabel.setIcon(ic_logo_scaled);
			panelRedes.add(lblNewLabel);
		}
		
		if(speaker.getSpeaker().getYoutubeLink()!=null) {
			JLabel lblNewLabel = new JLabel("");
			ImageIcon ic_logo = new ImageIcon(SpeakerPreview.class.getResource("/social_media/youtube.png"));
			Image img_logo= ic_logo.getImage();
			ImageIcon ic_logo_scaled=new ImageIcon(img_logo.getScaledInstance(25, 25, Image.SCALE_SMOOTH));
			lblNewLabel.setIcon(ic_logo_scaled);
			panelRedes.add(lblNewLabel);
		}
		
		lblSponsor = new JLabel();
		lblSponsor.setSize(new Dimension(150, 150));
		lblSponsor.setVisible(false);
		GridBagConstraints gbc_lblSponsor = new GridBagConstraints();
		gbc_lblSponsor.insets = new Insets(0, 0, 5, 5);
		gbc_lblSponsor.gridx = 5;
		gbc_lblSponsor.gridy = 1;
		panelCentr.add(lblSponsor, gbc_lblSponsor);
		if(speaker.getSpeaker().getSponsorImage()!=null) {
			
			lblSponsor.setVisible(true);
			
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panelCentr.add(scrollPane, gbc_scrollPane);
		
		txtrBio = new JTextArea();
		txtrBio.setBorder(null);
		txtrBio.setBackground(MyColors.COLOR_FONDO);
		txtrBio.setWrapStyleWord(true);
		txtrBio.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
		txtrBio.setLineWrap(true);
		txtrBio.setForeground(new Color(33, 64, 128));
		txtrBio.setFont(new Font("Nunito Sans", Font.PLAIN, 14));
		scrollPane.setViewportView(txtrBio);
		
		JPanel layeredPane = new JPanel();
		layeredPane.setBackground(MyColors.COLOR_FONDO);
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 5);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 5;
		gbc_layeredPane.gridy = 3;
		panelCentr.add(layeredPane, gbc_layeredPane);
		layeredPane.setLayout(new BorderLayout(0, 0));
		
		lblQr = new JLabel("FOLLOW ME");
		lblQr.setForeground(new Color(33, 64, 128));
		lblQr.setFont(new MyFont(MyFont.BOLD).getFont(20f));
		lblQr.setHorizontalTextPosition(SwingConstants.CENTER);
		lblQr.setVerticalTextPosition(SwingConstants.TOP);
		lblQr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQr.setHorizontalAlignment(SwingConstants.CENTER);
		lblQr.setVerticalAlignment(SwingConstants.TOP);
		lblQr.setSize(new Dimension(150, 150));
		lblQr.setPreferredSize(new Dimension(150, 150));
		lblQr.setBounds(54, 34, 46, 14);
		layeredPane.add(lblQr);
		
		JPanel panelInferior = new JPanel();
		panelInferior.setOpaque(false);
		panelInferior.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelInferior = new GridBagConstraints();
		gbc_panelInferior.insets = new Insets(0, 0, 5, 0);
		gbc_panelInferior.anchor = GridBagConstraints.EAST;
		gbc_panelInferior.fill = GridBagConstraints.VERTICAL;
		gbc_panelInferior.gridx = 0;
		gbc_panelInferior.gridy = 2;
		getContentPane().add(panelInferior, gbc_panelInferior);
		GridBagLayout gbl_panelInferior = new GridBagLayout();
		gbl_panelInferior.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelInferior.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelInferior.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInferior.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInferior.setLayout(gbl_panelInferior);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarcadorEnVivo enVivo = new MarcadorEnVivo(speaker.getSpeaker().getId());
				enVivo.run();
				SwingFader.fadeOut(SpeakerPreview.this);
			}
		});
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/img/48px/back.png")));
		btnSalir.setPressedIcon(new ImageIcon(getClass().getResource("/img/48px/back_pressed.png")));
		btnSalir.setRolloverIcon(new ImageIcon(getClass().getResource("/img/48px/back_hover.png")));
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.anchor = GridBagConstraints.WEST;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 1;
		panelInferior.add(btnSalir, gbc_btnSalir);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				runPresentation(speaker.getSpeaker());
				
			}
		});
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/img/72px/go.png")));
		btnNewButton_1.setPressedIcon(new ImageIcon(getClass().getResource("/img/72px/go_pressed.png")));
		btnNewButton_1.setRolloverIcon(new ImageIcon(getClass().getResource("/img/72px/go_hover.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panelInferior.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(33, 64, 128));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		getContentPane().add(panel_1, gbc_panel_1);
		
		cargarDatos(speaker);		

	}
	
	private void cargarDatos(SpeakerVo speaker) {
		//carga del logo del evento desde propiedades
		ImageIcon ic_logo = new ImageIcon(PropertiesManager.getInstance().getProperty("logo"));
		Image img_logo= ic_logo.getImage();
		ImageIcon ic_logo_scaled=new ImageIcon(img_logo.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		lbLogo.setIcon(ic_logo_scaled);
		
		lblNonmbrEvento.setText(StringUtils.upperCase(PropertiesManager.getInstance().getProperty("eventName")));
		lblNombreSala.setText(StringUtils.upperCase(PropertiesManager.getInstance().getProperty("sala")));
		
		lblNombreDisertante.setText(speaker.getSpeaker().getName());
		lblTitulo.setText(speaker.getSpeaker().getTitle());
		
		txtrBio.setText(speaker.getSpeaker().getBio());

		if(speaker.getSpeaker().getPicture() != null) {
			lblPicture.setIcon(getIconFromData(speaker.getSpeaker().getPicture(), 150));
		}
		
		if(speaker.getSpeaker().getSponsorImage() != null) {
			lblSponsor.setIcon(getIconFromData(speaker.getSpeaker().getSponsorImage(), 150));
		}
		
		if(speaker.getSpeaker().getQr() != null){
			lblQr.setIcon(getIconFromData(speaker.getSpeaker().getQr(), 150));
		}
	}

	/**
	 * devuelve la imagen lista para poner en un jlabel
	 * @param data : ruta de la imagen que viene de los datos del json
	 * @param scaleXY : el tamaño de la imagenes en px
	 * @return : la imagen escalada y cuadrada para que entre en el marco redondo
	 */
	private ImageIcon getIconFromData(String data, int scaleXY) {
		ImageIcon ic = new ImageIcon(data);
		Image img= ic.getImage();
		ImageIcon ic_scaled=new ImageIcon(img.getScaledInstance(scaleXY, scaleXY, Image.SCALE_SMOOTH));
		return ic_scaled;
	}
	
	/**
	 * Ejecuta el programa instalado en el sistema operativo
	 * @param sp : el dato del speaker para armar las rutas de las presentaciones
	 */
	public void runPresentation(Speaker sp) {
		try {
			String sSistemaOperativo = System.getProperty("os.name");
			if(sSistemaOperativo.startsWith("Mac")) {
				if(sp.getNamePresentation() != null) {
					/*
					 * String[] cmdArray = new String[2]; cmdArray[0] =
					 * PropertiesManager.getInstance().getProperty("powerpointRoute"); cmdArray[1] =
					 * "open "+PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/"+
					 * sp.getNamePresentation().substring(0,
					 * sp.getNamePresentation().indexOf("."))+"/"+sp.getNamePresentation(); Runtime
					 * run = Runtime.getRuntime(); run.exec(cmdArray);
					 */
					
					String rutaArchivo =PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/"+sp.getNamePresentation().substring(0,sp.getNamePresentation().indexOf("."))+"/"+sp.getNamePresentation();
					System.out.println("Abriendooo -----------------"+rutaArchivo);
					Runtime.getRuntime().exec(new String[] { "open", rutaArchivo });
					
					MarcadorEnVivo enVivo = new MarcadorEnVivo(sp.getId());
					enVivo.run();
				}
				
			}
			else {
				if (sp.getNamePresentation() != null) {
					String path = PropertiesManager.getInstance().getProperty("data") + "/Octopus/data/"
							+ sp.getNamePresentation().substring(0, sp.getNamePresentation().indexOf(".")) + "/"
							+ sp.getNamePresentation();
					Runtime run = Runtime.getRuntime();
					if (sp.getExtension().equals(".pptx")) {
						run.exec(PropertiesManager.getInstance().getProperty("powerpointRoute") + " /S " + path);
					} else if (sp.getExtension().equals(".mp4")) {
						run.exec(PropertiesManager.getInstance().getProperty("mediaRoute") + " " + path
								+ " /fullscreen /monitor 2 /close");
					} else if (sp.getExtension().equals(".pdf")) {
						run.exec(PropertiesManager.getInstance().getProperty("pdfRoute") + " /A pagemode=FullScreen "
								+ path);
					}
					MarcadorEnVivo enVivo = new MarcadorEnVivo(sp.getId());
					enVivo.run();
				}
			}
									
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private boolean speakerAlive=false;
	
	//https://octopusmanager.space/api/{codigoEvento}/charla/{idCharla}/vivo
	//PropertiesManager.getInstance().getProperty("eventPrefix")
	
	private class MarcadorEnVivo implements Runnable{

		private int speakerId;
		
		
		public MarcadorEnVivo(int speakerId) {
			this.speakerId = speakerId;
		}


		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(1000);
				
					URL url = new URL("https://octopusmanager.space/api/"+PropertiesManager.getInstance().getProperty("eventPrefix")+"/charla/"+this.speakerId+"/vivo");
					Map<String, Integer> params = new LinkedHashMap<>();
					if(!speakerAlive) {
						params.put("vivo", 1);
						speakerAlive =true;
					}
					else {
						params.put("vivo", 0);
						speakerAlive =false;
					}
					StringBuilder postData = new StringBuilder();
					for(Map.Entry<String, Integer> param: params.entrySet()) {
						if(postData.length() != 0) {
							postData.append("&");
						}
						postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
						postData.append("=");
						postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
					}
					
					byte[] postBytes = postData.toString().getBytes("UTF-8");
					
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", String.valueOf(postBytes.length));
					conn.setDoOutput(true);
					conn.getOutputStream().write(postBytes);
					
					
					Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
					for (int i = in.read(); i != -1; i = in.read()) {
						//System.out.println((char) i);
					}
					
					
					 
					
					
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
