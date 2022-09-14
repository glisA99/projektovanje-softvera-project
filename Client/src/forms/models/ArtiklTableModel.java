package forms.models;

import domain.Artikl;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ArtiklTableModel extends AbstractTableModel {

    private List<Artikl> artikls;
    private final String[] columnNames = new String[] {"SifraArtikla", "Naziv", "Opis", "ProdajnaCena","ProdajnaVrednost", "Velicina", "Proizvodjac", "KolicinaNaStanju"};

    public ArtiklTableModel(List<Artikl> artikls) {
        this.artikls = artikls;
    }

    @Override
    public int getRowCount() {
        return this.artikls.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        Artikl artikl = this.artikls.get(arg0);
        switch(arg1) {
            case 0: return artikl.getSifraArtikla().toString();
            case 1: return artikl.getNaziv();
            case 2: return artikl.getOpis();
            case 3: return artikl.getProdajnaCena().toString();
            case 4: return artikl.getProdajnaVrednost().toString();
            case 5: return artikl.getVelicina();
            case 6: return artikl.getProizvodjac();
            case 7: return String.valueOf(artikl.getKolicinaNaStanju());
            default: return "N/A";
        }
    }

    public List<Artikl> getArtikls() {
        return artikls;
    }

    public void setArtikls(List<Artikl> artikls) {
        this.artikls = artikls;
    }
    
    public void refresh() {
        fireTableDataChanged();
    }
    
}
