package table.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import idiomas.Idioma;
import model.Speaker;
import propiedades.PropertiesManager;
import utils.MyConstant;
import vo.BloqueVo;

public class BlockTableModel extends AbstractTableModel {
	
	private final Class[] tipoColumnas = new Class[] {String.class, String.class, String.class,Object.class};
	private String[] titulosColumnas = new String[] {"Inicio", "Cierre", "Bloque", "Accion"};
	private List<BloqueVo> bloques;	

	public BlockTableModel() {
		Idioma idioma = new Idioma(PropertiesManager.getInstance().getProperty("idioma"));
		
		titulosColumnas=new String[] {idioma.getProperty("event-inicio-tabla"), idioma.getProperty("event-cierre-tabla"), idioma.getProperty("event-bloque-tabla"), idioma.getProperty("event-inicio-tabla")};
		
		bloques = new ArrayList<BloqueVo>();
	}

	@Override
	public String getColumnName(int column) {
		return titulosColumnas[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return tipoColumnas[columnIndex];
	}
	
	@Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex==MyConstant.SPEAKERS)
			return true;
		else return false;
    }

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return bloques.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titulosColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
        case 0:
            return bloques.get(rowIndex).getInicio();
        case 1:
            return bloques.get(rowIndex).getCierre();
        case 2:
            return bloques.get(rowIndex).getNombre_bloque();
        case 3: 
            return bloques.get(rowIndex).getSpeakers();
        default:
            return null;
		}
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
            	bloques.get(rowIndex).setInicio(aValue.toString());
                break;
            case 1:
            	bloques.get(rowIndex).setCierre(aValue.toString());
                break;
            case 2:
            	bloques.get(rowIndex).setNombre_bloque(aValue.toString());
                break;
            case 3:
            	bloques.get(rowIndex).setSpeakers((ArrayList<Speaker>)aValue);
            default: ;
        }
        this.fireTableCellUpdated(rowIndex, columnIndex);
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }

	public List<BloqueVo> getBloques() {
		return bloques;
	}

	public void setBloques(List<BloqueVo> bloques) {
		this.bloques = bloques;
	}

}
