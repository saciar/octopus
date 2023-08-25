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
import javax.swing.SwingConstants;

public class ErrorDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	//private Font fuenteLabel = new MyFont(MyFont.LIGHT).getFont(14f);
	private Font fuenteLabel = new Font("Arial", Font.PLAIN, 14);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog dialog = new ErrorDialog("Error de prueba");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorDialog(String message) {
		Idioma idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));

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
		gbl_contentPanel.rowHeights = new int[]{10, 0, 10, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblMensaje = new JLabel(idioma.getProperty("err-conexion"));
			lblMensaje.setFont(fuenteLabel);
			lblMensaje.setForeground(MyColors.COLOR_BOTONES);
			GridBagConstraints gbc_lblMensaje = new GridBagConstraints();
			gbc_lblMensaje.fill = GridBagConstraints.VERTICAL;
			gbc_lblMensaje.gridwidth = 2;
			gbc_lblMensaje.insets = new Insets(0, 0, 5, 5);
			gbc_lblMensaje.gridx = 1;
			gbc_lblMensaje.gridy = 1;
			lblMensaje.setText(message);
			contentPanel.add(lblMensaje, gbc_lblMensaje);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(MyColors.COLOR_AZUL);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				okButton.setBorderPainted(false);
				okButton.setFocusPainted(false);
				okButton.setContentAreaFilled(false);
				okButton.setIcon(new ImageIcon(getClass().getResource("/img/24px/ok.png")));
				okButton.setPressedIcon(new ImageIcon(getClass().getResource("/img/24px/ok_pressed.png")));
				okButton.setRolloverIcon(new ImageIcon(getClass().getResource("/img/24px/ok_hover.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SwingFader.fadeOut(ErrorDialog.this);
					}
				});
				okButton.setActionCommand("Cancel");
				buttonPane.add(okButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(MyColors.COLOR_AZUL);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblAdmin = new JLabel("Error");
				lblAdmin.setForeground(Color.WHITE);	
				lblAdmin.setFont(new MyFont(MyFont.BOLD).getFont(20f));
				panel.add(lblAdmin);
			}
		}
	}

}
