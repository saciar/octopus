package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.border.MatteBorder;
import java.awt.Dimension;

import org.apache.commons.lang3.StringUtils;

import controller.EventManager;
import controller.EventManager2;
import idiomas.Idioma;
import list.renderer.SpeakersListRenderer;
import model.Speaker;
import propiedades.PropertiesManager;
import utils.MyColors;
import utils.MyFont;
import utils.SwingFader;
import view.components.CLabel;
import vo.BloqueVo;
import vo.SpeakerVo;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class BlockView extends JDialog {
	private JLabel lblNombreSala;
	private JLabel lblNonmbrEvento;
	private CLabel lbLogo;
	private JLabel lblHorarios;
	private JLabel lblNombreBloque;
	private JList<SpeakerVo> list;
	
	private EventView parent;

	public void setParent(EventView parent) {
		this.parent = parent;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlockView dialog = new BlockView(null);
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
	public BlockView(BloqueVo bloque) {
		setUndecorated(true);
		this.setModal(true);
		Idioma idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));
		
		getContentPane().setBackground(MyColors.COLOR_FONDO);
		//setBounds(0, 0,1024,768);
		setBounds(0, 0, Integer.parseInt(PropertiesManager.getInstance().getProperty("res_x")), Integer.parseInt(PropertiesManager.getInstance().getProperty("res_y")));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 10, 0};
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
		
		lblNonmbrEvento = new JLabel(StringUtils.upperCase("NOMBRE DEL CONGRESO"));
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
		
		JPanel panelcentral = new JPanel();
		panelcentral.setBackground(MyColors.COLOR_FONDO);
		GridBagConstraints gbc_panelcentral = new GridBagConstraints();
		gbc_panelcentral.insets = new Insets(0, 0, 5, 0);
		gbc_panelcentral.fill = GridBagConstraints.BOTH;
		gbc_panelcentral.gridx = 0;
		gbc_panelcentral.gridy = 1;
		getContentPane().add(panelcentral, gbc_panelcentral);
		GridBagLayout gbl_panelcentral = new GridBagLayout();
		gbl_panelcentral.columnWidths = new int[]{50, 0, 50, 0};
		gbl_panelcentral.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gbl_panelcentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelcentral.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelcentral.setLayout(gbl_panelcentral);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(MyColors.COLOR_FONDO);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panelcentral.add(panel_2, gbc_panel_2);
		FlowLayout fl_panel_2 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel_2.setLayout(fl_panel_2);
		
		lblHorarios = new JLabel("09:00 - 10:00");
		lblHorarios.setForeground(MyColors.COLOR_AZUL);
		lblHorarios.setFont(new MyFont(MyFont.LIGHT).getFont(20f));
		lblHorarios.setBorder(new CompoundBorder(new MatteBorder(0, 5, 0, 0, (Color) MyColors.COLOR_BOTONES), new EmptyBorder(0, 20, 0, 0)));
		panel_2.add(lblHorarios);
		
		lblNombreBloque = new JLabel(StringUtils.upperCase("Nombre de Bloque"));
		lblNombreBloque.setForeground(MyColors.COLOR_AZUL);
		lblNombreBloque.setFont(new MyFont(MyFont.BOLD).getFont(20f));
		panel_2.add(lblNombreBloque);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panelcentral.add(scrollPane, gbc_scrollPane);
		
		list = new JList<SpeakerVo>();
		list.setFont(new MyFont(MyFont.BOLD).getFont(20f));
		list.setForeground(MyColors.COLOR_BOTONES);
		rellenarLista(bloque.getSpeakers());
		list.setCellRenderer(new SpeakersListRenderer<>());
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<SpeakerVo> list = (JList<SpeakerVo>)evt.getSource();
				if (evt.getClickCount() == 2) { 
					int index = list.locationToIndex(evt.getPoint());
					SpeakerVo s = list.getModel().getElementAt(index);
					SpeakerPreview dialog = new SpeakerPreview(s);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}

			}
		});
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		panel.setBackground(MyColors.COLOR_FONDO);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setRunning(false);
				SwingFader.fadeOut(BlockView.this);
			}
		});
		btnSalir.setBorderPainted(false);
		btnSalir.setFocusPainted(false);
		btnSalir.setContentAreaFilled(false);
		btnSalir.setIcon(new ImageIcon(getClass().getResource("/img/48px/back.png")));
		btnSalir.setPressedIcon(new ImageIcon(getClass().getResource("/img/48px/back_pressed.png")));
		btnSalir.setRolloverIcon(new ImageIcon(getClass().getResource("/img/48px/back_hover.png")));
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 1;
		panel.add(btnSalir, gbc_btnSalir);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/img/48px/reload.png")));
		btnNewButton_1.setPressedIcon(new ImageIcon(getClass().getResource("/img/48px/reload_pressed.png")));
		btnNewButton_1.setRolloverIcon(new ImageIcon(getClass().getResource("/img/48px/reload_hover.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//traer los oradores nuevamente desde el json
				System.out.println("BLOQUE: "+ bloque.getNombre_bloque());
				System.out.println("BLOQUE ID: "+bloque.getId_bloque());
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		getContentPane().add(panel_1, gbc_panel_1);

		cargarDatos(bloque);
		
	}
	

	private void rellenarLista(ArrayList<Speaker> speakers) {
		DefaultListModel<SpeakerVo> modelo=new DefaultListModel<SpeakerVo>();
		for (Speaker speaker : speakers) {
			
			modelo.addElement(new SpeakerVo(speaker.getName(), speaker.getTitle(), speaker));
		}
		list.setModel(modelo);
	}

	private void cargarDatos( BloqueVo bloque) {
		//carga del logo del evento desde propiedades
		ImageIcon ic = new ImageIcon(PropertiesManager.getInstance().getProperty("logo"));
		Image img= ic.getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		lbLogo.setIcon(img2);
		
		lblNonmbrEvento.setText(StringUtils.upperCase(PropertiesManager.getInstance().getProperty("eventName")));
		lblNombreSala.setText(StringUtils.upperCase(PropertiesManager.getInstance().getProperty("sala")));
		
		lblHorarios.setText(bloque.getInicio() + " - " + bloque.getCierre());
		lblNombreBloque.setText(bloque.getNombre_bloque());
	}
	
	
}
