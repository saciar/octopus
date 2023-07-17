package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import propiedades.PropertiesManager;
import utils.MyColors;
import utils.MyFont;
import utils.SwingFader;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Portada {

	private JFrame frame;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Portada window = new Portada();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Portada() {
		try {
		    UIManager.setLookAndFeel( new FlatLightLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		initialize();
		System.out.println(PropertiesManager.getInstance().getProperty("eventName"));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/octopus.png"));
		frame.setIconImage(iconoPropio);
		
		JLabel lblNewLabel = new JLabel("Footer");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new MyFont(MyFont.LIGHT).getFont(10f));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(MyColors.COLOR_AZUL);
		frame.getContentPane().add(lblNewLabel, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(MyColors.COLOR_FONDO);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{500, 0};
		gbl_panel.rowHeights = new int[]{10, 0, 251, 150, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnSettings = new JButton("");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Password dialog = new Password();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);								
				SwingFader.fadeIn(dialog);
			}
		});
		btnSettings.setFocusPainted(false);
		btnSettings.setBorderPainted(false);
		btnSettings.setContentAreaFilled(false);
		btnSettings.setIcon(new ImageIcon(getClass().getResource("/img/48px/setting.png")));
		btnSettings.setPressedIcon(new ImageIcon(getClass().getResource("/img/48px/setting_pressed.png")));
		btnSettings.setRolloverIcon(new ImageIcon(getClass().getResource("/img/48px/setting_hover.png")));
		GridBagConstraints gbc_btnSettings = new GridBagConstraints();
		gbc_btnSettings.anchor = GridBagConstraints.EAST;
		gbc_btnSettings.insets = new Insets(0, 0, 0, 10);
		gbc_btnSettings.gridx = 0;
		gbc_btnSettings.gridy = 1;
		panel.add(btnSettings, gbc_btnSettings);
		
		JLabel lbllogo = new JLabel("");
		lbllogo.setHorizontalAlignment(SwingConstants.CENTER);
		lbllogo.setIcon(new ImageIcon(getClass().getResource("/logo_octopus.png")));
		GridBagConstraints gbc_lbllogo = new GridBagConstraints();
		gbc_lbllogo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbllogo.insets = new Insets(0, 0, 5, 0);
		gbc_lbllogo.gridx = 0;
		gbc_lbllogo.gridy = 2;
		panel.add(lbllogo, gbc_lbllogo);
		
		JButton btnStart = new JButton("");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventView dialog = new EventView();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setUndecorated(true);
				dialog.setVisible(true);
				SwingFader.fadeIn(dialog);
				//Portada.this.frame.setVisible(false);
			}
		});
		btnStart.setFocusPainted(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorderPainted(false);
		btnStart.setIcon(new ImageIcon(getClass().getResource("/img/72px/init.png")));
		btnStart.setPressedIcon(new ImageIcon(getClass().getResource("/img/72px/init_pressed.png")));
		btnStart.setRolloverIcon(new ImageIcon(getClass().getResource("/img/72px/init_hover.png")));
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.gridx = 0;
		gbc_btnStart.gridy = 3; 
		panel.add(btnStart, gbc_btnStart);
		frame.setBounds(0, 0, 1280, 768);
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
