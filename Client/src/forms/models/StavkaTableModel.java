package forms.models;

import domain.StavkaIzvestaja;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class StavkaTableModel extends AbstractTableModel {
    
    private List<StavkaIzvestaja> stavke;
    private String[] columnNames = new String[] {"RB", "Prihod stavke", "Prodajna stavka ID"};

    public StavkaTableModel(List<StavkaIzvestaja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return this.stavke.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        StavkaIzvestaja stavka = stavke.get(arg0);
        switch(arg1) {
            case 0: return String.valueOf(stavka.getRB());
            case 1: return stavka.getPrihodStavke().toString();
            case 2: return stavka.getProdajnaStavkaID().toString();
            default: return "N/A";
        }
    }
    
    public void refresh() {
        this.fireTableDataChanged();
    }

    public List<StavkaIzvestaja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIzvestaja> stavke) {
        this.stavke = stavke;
    }
    
    
    
}
