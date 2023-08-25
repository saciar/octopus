package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.table.JTableHeader;

import org.apache.commons.lang3.StringUtils;

import controller.EventManager;
import controller.EventManager2;
import idiomas.Idioma;
import model.Date;
import model.Room;
import propiedades.PropertiesManager;
import table.model.BlockTableModel;
import table.renderers.CellManager;
import table.renderers.HeaderTableManager;
import utils.MyColors;
import utils.MyConstant;
import utils.MyFont;
import utils.SwingFader;
import view.components.CLabel;
import vo.BloqueVo;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatterBuilder;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JProgressBar;

public class EventView extends JDialog {

	private CLabel lbLogo;
	private JLabel lblNombreEvento;
	private JLabel lblNombreSala;

	private JTabbedPane tabbedPane;
	
	private ArrayList<Room> data;
	
	private EventManager2 manager;
	
	private Timer timer;
	private JProgressBar progressBar;
	
	private boolean isRunning=false;
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}


	/**
	 * Create the dialog.
	 */
	public EventView() {
		
		Idioma idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));
		
		this.setModal(true);

		getContentPane().setBackground(MyColors.COLOR_FONDO);
		setBounds(0, 0, Integer.parseInt(PropertiesManager.getInstance().getProperty("res_x")), Integer.parseInt(PropertiesManager.getInstance().getProperty("res_y")));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{100, 0, 0, 10, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		ImageIcon ic = new ImageIcon(EventView.class.getResource("/octopus.png").getPath());
		Image img= ic.getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		
		JLayeredPane panelSuperior = new JLayeredPane();
		panelSuperior.setOpaque(true);
		panelSuperior.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelSuperior = new GridBagConstraints();
		gbc_panelSuperior.gridwidth = 2;
		gbc_panelSuperior.fill = GridBagConstraints.BOTH;
		gbc_panelSuperior.gridx = 0;
		gbc_panelSuperior.gridy = 0;
		getContentPane().add(panelSuperior, gbc_panelSuperior);
		GridBagLayout gbl_panelSuperior = new GridBagLayout();
		gbl_panelSuperior.columnWidths = new int[]{50, 150, 854, 0, 50, 0};
		gbl_panelSuperior.rowHeights = new int[]{20, 100, 20, 0};
		gbl_panelSuperior.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSuperior.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSuperior.setLayout(gbl_panelSuperior);
		
		lbLogo = new CLabel(new Dimension(100, 100));
		lbLogo.setIcon(img2);
		GridBagConstraints gbc_lbLogo = new GridBagConstraints();
		gbc_lbLogo.insets = new Insets(0, 0, 5, 5);
		gbc_lbLogo.gridx = 1;
		gbc_lbLogo.gridy = 1;
		panelSuperior.add(lbLogo, gbc_lbLogo);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_3.gridwidth = 5;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panelSuperior.add(panel_3, gbc_panel_3);
		
		lblNombreEvento = new JLabel(StringUtils.upperCase("Evento sin nombre"));
		getLblNombreEvento().setForeground(new Color(33, 64, 128));
		getLblNombreEvento().setFont(new MyFont(MyFont.BOLD).getFont(32f));
		panel_3.add(getLblNombreEvento());
		
		JPanel panel_6 = new JPanel();
		panelSuperior.setLayer(panel_6, 1);
		panel_6.setOpaque(false);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.anchor = GridBagConstraints.WEST;
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
		gbc_panelcentral.gridwidth = 2;
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
		
		JLabel lblWelcome = new JLabel(idioma.getProperty("event-bienvenida"));
		lblWelcome.setFont(new MyFont(MyFont.BOLD).getFont(20f));
		lblWelcome.setForeground(MyColors.COLOR_AZUL);
		lblWelcome.setBorder(new CompoundBorder(new MatteBorder(0, 5, 0, 0, (Color) MyColors.COLOR_BOTONES), new EmptyBorder(0, 20, 0, 0)));
		GridBagConstraints gbc_lblWelcome = new GridBagConstraints();
		gbc_lblWelcome.anchor = GridBagConstraints.WEST;
		gbc_lblWelcome.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcome.gridx = 1;
		gbc_lblWelcome.gridy = 1;
		panelcentral.add(lblWelcome, gbc_lblWelcome);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 3;
		panelcentral.add(getTabbedPane(), gbc_tabbedPane);
		
		progressBar = new JProgressBar();
		getProgressBar().setStringPainted(true);
		getProgressBar().setVisible(false);
		getProgressBar().setIndeterminate(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 4;
		panelcentral.add(getProgressBar(), gbc_progressBar);

		JPanel panel = new JPanel();
		panel.setBackground(MyColors.COLOR_FONDO);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.gridwidth = 2;
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
				SwingFader.fadeOut(EventView.this);
				System.exit(0);
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
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getDataFromManager();				
			}
		});
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/img/48px/reload.png")));
		btnNewButton_1.setPressedIcon(new ImageIcon(getClass().getResource("/img/48px/reload_pressed.png")));
		btnNewButton_1.setRolloverIcon(new ImageIcon(getClass().getResource("/img/48px/reload_hover.png")));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 1;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(MyColors.COLOR_AZUL);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		getContentPane().add(panel_1, gbc_panel_1);

		
		
		cargarDatos();
		getDataFromManager();
		
		timer = new Timer(10000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!isRunning) {
					isRunning=true;
					getDataFromManager();
				}
			}
		});
		timer.start();
		
	}

	private void cargarDatos() {
		//carga del logo del evento desde propiedades
		ImageIcon ic = new ImageIcon(PropertiesManager.getInstance().getProperty("logo"));
		Image img= ic.getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		lbLogo.setIcon(img2);
		
		getLblNombreEvento().setText(StringUtils.upperCase(PropertiesManager.getInstance().getProperty("eventName")));
		lblNombreSala.setText(StringUtils.upperCase(PropertiesManager.getInstance().getProperty("sala")));
	}
	
	private synchronized void getDataFromManager() {
		
		EventManager2 manager = new EventManager2(this);
	}
	
	public void rellenarTabla() {
		for (Room room : getData()) {
			for (Date date : room.getDates()) {
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setOpaque(false);
				
				JTable tblBlocks = new JTable();
				tblBlocks.setOpaque(false);				

				BlockTableModel model = new BlockTableModel();
				model.setBloques(consultarListaBloques(date));
				tblBlocks.setModel(model);				
				
				construirTabla(tblBlocks);
				
				scrollPane.setViewportView(tblBlocks);
				
				tblBlocks.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						//capturo fila o columna dependiendo de mi necesidad
						  int fila = tblBlocks.rowAtPoint(e.getPoint());
						  int columna = tblBlocks.columnAtPoint(e.getPoint());
						  
						//uso la columna para valiar si corresponde a la columna de perfil garantizando
						// que solo se produzca algo si selecciono una fila de esa columna
						//
						  if (columna==MyConstant.GO) {
						   //sabiendo que corresponde a la columna de perfil, envio la posicion de la fila seleccionada
						   validarSeleccionMouse(fila);
						  }
						  
					}
					
					private void validarSeleccionMouse(int fila) {

						if(fila>=0) {
							isRunning=true;
							BloqueVo select = model.getBloques().get(fila);							

							BlockView dialog = new BlockView(select);
							dialog.setParent(EventView.this);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							SwingFader.fadeIn(dialog);
						}
					}

				});
				getTabbedPane().addTab(date.getName(), null, scrollPane, null);		
				getTabbedPane().setFont(new MyFont(MyFont.BOLD).getFont(16f));
				getTabbedPane().setForeground(MyColors.COLOR_AZUL);
			}
		}	
	}
	
	
	//ACA TENGO QUE TRAER LOS DATOS DEL JSON
	private ArrayList<BloqueVo> consultarListaBloques(Date date) {
		
		ArrayList<BloqueVo> lista=new ArrayList<BloqueVo>();

		/*lista.add(new BloqueVo("09:00", "10:00", "Apertura"));
		lista.add(new BloqueVo("10:00", "12:00", "Bloque numero 1"));*/

		for(model.Block block: date.getBlocks()) {
			lista.add(new BloqueVo(block.getInitBlock().toString(), block.getEndBlock().toString(), block.getName(),block.getId(),block.getSpeakers()));

		}

		return lista;
	}
	
	private void construirTabla(JTable tblBlocks) { 
		tblBlocks.getColumnModel().getColumn(MyConstant.INICIO).setCellRenderer(new CellManager("texto"));
		tblBlocks.getColumnModel().getColumn(MyConstant.CIERRE).setCellRenderer(new CellManager("texto"));
		tblBlocks.getColumnModel().getColumn(MyConstant.NOMBRE_BLOQUE).setCellRenderer(new CellManager("texto"));
		//tblBlocks.getColumnModel().getColumn(MyConstant.SPEAKERS).setCellRenderer(new CellManager("speakers"));
		tblBlocks.getColumnModel().getColumn(MyConstant.GO).setCellRenderer(new CellManager("icono"));

		tblBlocks.getTableHeader().setReorderingAllowed(false);
		tblBlocks.setRowHeight(25);//tamaño de las celdas
		tblBlocks.setGridColor(new java.awt.Color(0, 0, 0)); //Se define el tamaño de largo para cada columna y su contenido
		tblBlocks.getColumnModel().getColumn(MyConstant.INICIO).setPreferredWidth(50);//inicio
		tblBlocks.getColumnModel().getColumn(MyConstant.CIERRE).setPreferredWidth(50);//cierre
		tblBlocks.getColumnModel().getColumn(MyConstant.NOMBRE_BLOQUE).setPreferredWidth(432);//nombre de bloque
		//tblBlocks.getColumnModel().getColumn(MyConstant.SPEAKERS).setPreferredWidth(10);
		tblBlocks.getColumnModel().getColumn(MyConstant.GO).setPreferredWidth(10);//accion ir

		//personaliza el encabezado 
		JTableHeader jtableHeader =	tblBlocks.getTableHeader(); 
		jtableHeader.setDefaultRenderer(new HeaderTableManager()); 
		tblBlocks.setTableHeader(jtableHeader);
	}
	 
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}


	public ArrayList<Room> getData() {
		return data;
	}

	public void setData(ArrayList<Room> data) {
		this.data = data;
	}


	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}


	public JLabel getLblNombreEvento() {
		return lblNombreEvento;
	}

}
