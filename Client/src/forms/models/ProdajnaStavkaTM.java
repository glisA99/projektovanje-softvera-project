package forms.models;

import domain.Artikl;
import domain.Klijent;
import domain.ProdajnaStavka;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ProdajnaStavkaTM extends AbstractTableModel {
    
    private List<ProdajnaStavka> stavke;
    private HashMap<Long,Klijent> clientMap = new HashMap<>();
    private HashMap<Long,Artikl> artiklMap = new HashMap<>();
    private String[] columnNames = new String[] {"Stavka ID", "Datum prodaje", "Kolicina", "Iznos", "Klijent", "Artikl", "Radnik JMBG"};
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ProdajnaStavkaTM(List<ProdajnaStavka> stavke,List<Artikl> artikls,List<Klijent> clients) {
        this.stavke = stavke;
        System.out.println("Artikls size: " + artikls.size());
        System.out.println("Clients size: " + clients.size());
        artikls.forEach(artikl -> this.artiklMap.put(artikl.getSifraArtikla(), artikl));
        clients.forEach(client -> this.clientMap.put(client.getKlijentID(),client));
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public Object getValueAt(int arg0, int arg1) {
        ProdajnaStavka prodajnaStavka = stavke.get(arg0);
        switch(arg1) {
            case 0: return prodajnaStavka.getProdajnaStavkaID().toString();
            case 1: return sdf.format(prodajnaStavka.getDatumProdaje());
            case 2: return String.valueOf(prodajnaStavka.getKolicina());
            case 3: return prodajnaStavka.getIznos().toString();
            case 4: 
                Long clientID = prodajnaStavka.getKlijentID();
                if (clientID != null) return clientMap.get(prodajnaStavka.getKlijentID()).toString();
                else return "N/A";
            case 5: return artiklMap.get(prodajnaStavka.getSifraArtikla()).toString();
            case 6: return prodajnaStavka.getRadnikJMBG();
            default: return "N/A";
        }
    }

    public List<ProdajnaStavka> getStavke() {
        return stavke;
    }

    public void setStavke(List<ProdajnaStavka> stavke) {
        this.stavke = stavke;
    }

    public void refresh() {
        this.fireTableDataChanged();
    }
    
}
