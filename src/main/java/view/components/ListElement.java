package view.components;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ListElement extends JPanel {

	/**
	 * Create the panel.
	 */
	public ListElement() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 5, 0, 5, 0};
		gridBagLayout.rowHeights = new int[]{5, 0, 0, 0, 5, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNombre = new JLabel("New label");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 2;
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 1;
		add(lblNombre, gbc_lblNombre);
		
		JLabel lblNombrePresentacion = new JLabel("New label");
		GridBagConstraints gbc_lblNombrePresentacion = new GridBagConstraints();
		gbc_lblNombrePresentacion.anchor = GridBagConstraints.WEST;
		gbc_lblNombrePresentacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombrePresentacion.gridx = 2;
		gbc_lblNombrePresentacion.gridy = 3;
		add(lblNombrePresentacion, gbc_lblNombrePresentacion);

	}

}
