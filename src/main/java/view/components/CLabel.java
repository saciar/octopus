package view.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;

import utils.MyColors;

public class CLabel extends JLabel {

   private AbstractBorder circleBorder = new CircleBorder(MyColors.COLOR_AZUL,2);       
   private int lineBorder=1; 
   private Color lineColor= Color.BLACK;

    /** Constructor */
     public CLabel()
     {
        Dimension d = new Dimension(150,150);
        setSize(d);
        setPreferredSize(d);       
        setOpaque(true);
        setHorizontalAlignment(CENTER);       
        setVisible(true);       
        setBorder(circleBorder); 
     }
     
     public CLabel(Dimension d)
     {
        setSize(d);
        setPreferredSize(d);       
        setOpaque(true);
        setHorizontalAlignment(CENTER);       
        setVisible(true);       
        setBorder(circleBorder); 
     }

    //Color de borde
    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color color) {
        circleBorder = new CircleBorder(color, lineBorder);
        lineColor = color;
        setBorder(circleBorder); 
    }

    //Grosor de borde
    public int getLineBorder() {
        return lineBorder;        
    }

    public void setLineBorder(int lineBorder) {
        circleBorder = new CircleBorder(lineColor, lineBorder);
        this.lineBorder = lineBorder;        
        setBorder(circleBorder); 
    }
}