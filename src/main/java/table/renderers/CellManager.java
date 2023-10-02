package table.renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Speaker;
import utils.MyColors;
import utils.MyConstant;
import utils.MyFont;

public class CellManager extends DefaultTableCellRenderer {

	private String tipo="text";

	 //se definen por defecto los tipos de datos a usar
	 private Font normal = new MyFont(MyFont.LIGHT).getFont(24f);
	 private Font bold = new MyFont(MyFont.BOLD).getFont(18f);
	 //etiqueta que almacenará el icono a mostrar
	 private JLabel label = new JLabel();
	 //iconos disponibles para ser mostrados en la etiqueta dependiendo de la columna que lo contenga
	 private ImageIcon iconoGo = new ImageIcon(getClass().getResource("/img/24px/init.png"));


	 public CellManager(){
	  
	 }

	//
	//constructor explicito con el tipo de dato que tendrá la celda
	// @param tipo
	//
	 public CellManager(String tipo){
	  this.tipo=tipo;
	 }

	 @Override
	 public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

		 //
		 //Este metodo controla toda la tabla, podemos obtener el valor que contiene
		 // definir que celda está seleccionada, la fila y columna al tener el foco en ella.
		 // 
		 // cada evento sobre la tabla invocará a este metodo
		 //

		 //definimos colores por defecto
		 Color colorFondo = null;
		 Color colorFondoPorDefecto=MyColors.COLOR_BOTONES;
		 Color colorFondoSeleccion=MyColors.COLOR_BOTONES_PRESSED;

		 /*
		  * Si la celda del evento es la seleccionada se asigna el fondo por defecto para la selección
		  */
		 if (selected) {                
			 this.setBackground(colorFondoPorDefecto );   
		 }
		 else
		 {
			 //Para las que no están seleccionadas se pinta el fondo de las celdas de blanco
			 this.setBackground(Color.white);
		 }

		 /*
		  * Se definen los tipos de datos que contendrán las celdas basado en la instancia que
		  * se hace en la ventana de la tabla al momento de construirla
		  */
		 if( tipo.equals("texto"))
		 {
			 //si es tipo texto define el color de fondo del texto y de la celda así como la alineación
				/*
				 * if (focused) { colorFondo=colorFondoSeleccion; }else{ colorFondo=
				 * colorFondoPorDefecto; }
				 */
			 colorFondo=colorFondoPorDefecto;
			 this.setHorizontalAlignment( JLabel.LEFT );
			 this.setText( (String) value );
			 this.setForeground( (selected)? new Color(255,255,255) :MyColors.COLOR_BORDE );   
			 this.setBackground( (selected)? colorFondo :Color.WHITE); 
			 
			 this.setFont(normal);   
			 return this;
		 }

		 //si el tipo es icono entonces valida cual icono asignar a la etiqueta.
		 if( tipo.equals("icono")){
			 if (focused) {
				 colorFondo=colorFondoSeleccion;
			 }else{
				 colorFondo= colorFondoPorDefecto;
			 }
			 
			 label.setOpaque(true);

			 label.setIcon(iconoGo);

			 label.setHorizontalAlignment( JLabel.CENTER );
			 //label.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			 label.setBackground( (selected)? colorFondo :Color.WHITE); 
			 return label;
		 }

		 return this; 
	 }
}
