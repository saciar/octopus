package list.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import utils.MyColors;
import utils.MyFont;
import vo.SpeakerVo;

public class SpeakersListRenderer<E> extends JPanel implements ListCellRenderer<E> {

	JLabel lblNombre = new JLabel();
	JLabel lblNombrePresentacion = new JLabel();
	
	
	
	public SpeakersListRenderer() {
		/*
		 * setLayout(new BorderLayout(5, 5)); JPanel panelText = new JPanel(new
		 * GridLayout(0, 1)); panelText.add(lblNombre);
		 * panelText.add(lblNombrePresentacion); add(panelText, BorderLayout.CENTER);
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 5, 0, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 2;
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		add(lblNombre, gbc_lblNombre);
		
		GridBagConstraints gbc_lblNombrePresentacion = new GridBagConstraints();
		gbc_lblNombrePresentacion.anchor = GridBagConstraints.WEST;
		gbc_lblNombrePresentacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombrePresentacion.gridx = 2;
		gbc_lblNombrePresentacion.gridy = 3;
		add(lblNombrePresentacion, gbc_lblNombrePresentacion);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends E> list, E value, int index, boolean isSelected,
			boolean cellHasFocus) {
		// TODO Auto-generated method stub
		lblNombre.setText(((SpeakerVo)value).getNombre());
		lblNombre.setForeground(MyColors.COLOR_AZUL);
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setFont(new MyFont(MyFont.BOLD).getFont(18f));
		if(((SpeakerVo)value).getNombre_charla()==null)
			lblNombrePresentacion.setText(" ");
		else lblNombrePresentacion.setText(((SpeakerVo)value).getNombre_charla());
		lblNombrePresentacion.setForeground(MyColors.COLOR_BORDE);
		lblNombrePresentacion.setBackground(Color.WHITE);
		lblNombrePresentacion.setFont(new MyFont(MyFont.LIGHT).getFont(16f));
		
		lblNombre.setOpaque(true);
		lblNombrePresentacion.setOpaque(true);
		
		if(isSelected) {
			lblNombre.setForeground(Color.WHITE);
			lblNombre.setBackground(MyColors.COLOR_BOTONES);
			lblNombrePresentacion.setForeground(Color.WHITE);
			lblNombrePresentacion.setBackground(MyColors.COLOR_BOTONES);
			setBackground(MyColors.COLOR_BOTONES);
		}
		else {
			lblNombre.setForeground(MyColors.COLOR_AZUL);
			lblNombre.setBackground(Color.WHITE);
			lblNombrePresentacion.setForeground(MyColors.COLOR_BORDE);
			if(((SpeakerVo)value).getSpeaker().getNamePresentation()==" " || ((SpeakerVo)value).getSpeaker().getNamePresentation()==null) {
				lblNombre.setForeground(Color.LIGHT_GRAY);
				lblNombrePresentacion.setForeground(Color.LIGHT_GRAY);
			}
			
			lblNombrePresentacion.setBackground(Color.WHITE);
			setBackground(Color.WHITE);
		}
		return this;
	}

}
