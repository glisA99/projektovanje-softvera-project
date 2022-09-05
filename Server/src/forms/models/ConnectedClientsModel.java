package forms.models;

import domain.Radnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import utility.ClientStatistic;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ConnectedClientsModel extends AbstractTableModel {
    
    private List<ClientStatistic> ClientStatistic;
    private String[] columnNames = new String[] { "RB", "Date&Time connected", "Employee", "Total requests", "Successfull requests", "Failure requests" };
    
    public ConnectedClientsModel(List<ClientStatistic> ClientStatistic) {
        this.ClientStatistic = ClientStatistic;
    }
    
    @Override
    public int getRowCount() {
        return this.ClientStatistic.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    
    
    @Override
    public Object getValueAt(int arg0, int arg1) {
        ClientStatistic clientStatistic = ClientStatistic.get(arg0);
        switch(arg1) {
            case 0: return arg0 + 1;
            case 1: return clientStatistic.getDateTimeConnected();
            case 2: 
                Radnik radnik = clientStatistic.getLoggedRadnik();
                if (radnik == null) return "NOT LOGGED";
                else return radnik.toString();
            case 3: return clientStatistic.getRequestsSent();
            case 4: return clientStatistic.getSuccessRequests();
            case 5: return clientStatistic.getFailureRequests();
            default: return "N/A";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
    
    

    public List<ClientStatistic> getClientStatistic() {
        return ClientStatistic;
    }

    public void setClientStatistic(List<ClientStatistic> ClientStatistic) {
        this.ClientStatistic = ClientStatistic;
    }
    
}
