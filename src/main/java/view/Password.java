package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import idiomas.Idioma;
import propiedades.PropertiesManager;
import utils.MyColors;
import utils.MyFont;
import utils.SwingFader;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Password extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JLabel lblError;

	//private Font fuenteLabel = new MyFont(MyFont.LIGHT).getFont(14f);
	private Font fuenteLabel = new Font("Arial", Font.PLAIN, 14);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Password dialog = new Password();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Password() {
		Idioma idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));
		System.out.println("Idima: "+PropertiesManager.getInstance().getProperty("idioma"));
		setUndecorated(true);
		getContentPane().setBackground(MyColors.COLOR_FONDO);
		setBounds(100, 100, 350, 200);
		setLocationRelativeTo(null);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());		
		contentPanel.setBackground(Color.WHITE);
		//contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{10, 0, 0, 10, 0};
		gbl_contentPanel.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblNewLabel = new JLabel(idioma.getProperty("pass-ingrese"));
			lblNewLabel.setFont(fuenteLabel);
			lblNewLabel.setForeground(MyColors.COLOR_BOTONES);
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			passwordField = new JPasswordField();
			GridBagConstraints gbc_passwordField = new GridBagConstraints();
			gbc_passwordField.insets = new Insets(0, 0, 5, 5);
			gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordField.gridx = 2;
			gbc_passwordField.gridy = 1;
			contentPanel.add(passwordField, gbc_passwordField);
		}
		{
			lblError = new JLabel(idioma.getProperty("pass-error"));
			lblError.setFont(fuenteLabel);
			lblError.setVisible(false);
			lblError.setForeground(Color.RED);
			GridBagConstraints gbc_lblError = new GridBagConstraints();
			gbc_lblError.anchor = GridBagConstraints.WEST;
			gbc_lblError.gridwidth = 2;
			gbc_lblError.insets = new Insets(0, 0, 5, 5);
			gbc_lblError.gridx = 1;
			gbc_lblError.gridy = 3;
			contentPanel.add(lblError, gbc_lblError);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(MyColors.COLOR_AZUL);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//verificar que la contraseña sea la misma que la guardada
						if(PropertiesManager.getInstance().getProperty("adminPass").equals(String.valueOf(passwordField.getPassword()))){
							
							Settings dialog = new Settings();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							Password.this.dispose();
							SwingFader.fadeIn(dialog);							
						}
						else {							
							lblError.setVisible(true);
						}
					}
				});
				okButton.setBorderPainted(false);
				okButton.setFocusPainted(false);
				okButton.setContentAreaFilled(false);
				okButton.setActionCommand("OK");
				okButton.setIcon(new ImageIcon(getClass().getResource("/img/24px/ok.png")));
				okButton.setPressedIcon(new ImageIcon(getClass().getResource("/img/24px/ok_pressed.png")));
				okButton.setRolloverIcon(new ImageIcon(getClass().getResource("/img/24px/ok_hover.png")));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("");
				cancelButton.setBorderPainted(false);
				cancelButton.setFocusPainted(false);
				cancelButton.setContentAreaFilled(false);
				cancelButton.setIcon(new ImageIcon(getClass().getResource("/img/24px/cross.png")));
				cancelButton.setPressedIcon(new ImageIcon(getClass().getResource("/img/24px/cross_pressed.png")));
				cancelButton.setRolloverIcon(new ImageIcon(getClass().getResource("/img/24px/cross_hover.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SwingFader.fadeOut(Password.this);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(MyColors.COLOR_AZUL);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblAdmin = new JLabel(idioma.getProperty("pass-titulo"));
				lblAdmin.setForeground(Color.WHITE);	
				lblAdmin.setFont(new MyFont(MyFont.BOLD).getFont(20f));
				panel.add(lblAdmin);
			}
		}
	}

}
