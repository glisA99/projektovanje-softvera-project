package threads;

import forms.models.ConnectedClientsModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import utility.ClientStatistic;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class RefreshThread extends Thread {

    private JTable table;
    private ServerThread serverThread;
    private JLabel lblRefreshed;

    public RefreshThread(JTable table, ServerThread ServerThread, JLabel refreshed) {
        this.table = table;
        this.serverThread = ServerThread;
        this.lblRefreshed = refreshed;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted() && serverThread.isAlive()) {
                List<ClientThread> clients = this.serverThread.getClients();
                List<ClientStatistic> statistics = new ArrayList<>(clients.size());
                for (int i = 0; i < clients.size(); i++) {
                    statistics.add(clients.get(i).clientStatistic);
                }

                ConnectedClientsModel tblModel = new ConnectedClientsModel(statistics);
                this.table.setModel(tblModel);
                lblRefreshed.setText("Table refreshed...");
                Thread.sleep(1000);
                lblRefreshed.setText("");
                Thread.sleep(4000);
            }
        } catch (Exception ex) {
            System.out.println("Error occurred inside Refresh Thread...");
        }
    }

}
