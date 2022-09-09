package forms.models;

import domain.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ClientTableModel extends AbstractTableModel {
    
    private List<Klijent> clients;
    private final String[] columnNames = new String[] {"ClientID", "Firstname", "Lastname", "Email"};

    public ClientTableModel(List<Klijent> clients) {
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        return this.clients.size();
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
        Klijent client = this.clients.get(arg0);
        switch(arg1) {
            case 0: return client.getKlijentID().toString();
            case 1: return client.getIme();
            case 2: return client.getPrezime();
            case 3: return client.getEmail();
            default: return "N/A";
        }
    }
    
    public void refresh() {
        this.fireTableDataChanged();
    }

    public List<Klijent> getClients() {
        return clients;
    }

    public void setClients(List<Klijent> clients) {
        this.clients = clients;
    }
    
}
