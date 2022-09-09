package controller.clients;

import communication.Operations;
import communication.Request;
import controller.general.AbstractController;
import domain.Klijent;
import forms.FrmMain;
import forms.models.ClientTableModel;
import forms.panels.client.SearchClientsPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class SearchClientsController extends AbstractController {

    private SearchClientsPanel panel;
    private List<Klijent> clients = new ArrayList<>();

    public SearchClientsController(FrmMain form) {
        super(form);
    }

    @Override
    public void initPanel(JPanel panel) {
        this.panel = (SearchClientsPanel) panel;
        setActionListeners();
        initClientsTable();
        super.initPanel(panel);
    }

    private void setActionListeners() {
        setSearchActionListener();
    }

    private void initClientsTable() {
        ClientTableModel tableModel = new ClientTableModel(clients);
        this.panel.getTblClients().setModel(tableModel);
    }

    private void setSearchActionListener() {
        this.panel.getjButton1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String clientID = panel.getTxtClientID().getText().trim();
                String ime = panel.getTxtFirstname().getText().trim();
                String prezime = panel.getTxtLastname().getText().trim();
                String email = panel.getTxtEmail().getText().trim();
                
                try {
                    sendSearchRequest(clientID, ime, prezime, email);
                    ClientTableModel tableModel = (ClientTableModel) panel.getTblClients().getModel();
                    if (clients.size() == 0) throw new Exception("No clients found");
                    tableModel.setClients(clients);
                    tableModel.refresh();
                    JOptionPane.showMessageDialog(panel, "Pretraga je zavrsena");
                } catch (Exception ex) {
                    Logger.getLogger(SearchClientsController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(panel, "Sistem ne moze da pronadje klijente po zadatim kriterijumima" , "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                clearInputs();
            }
        });
    }
    
    private void clearInputs() {
        panel.getTxtClientID().setText("");
        panel.getTxtFirstname().setText("");
        panel.getTxtLastname().setText("");
        panel.getTxtEmail().setText("");
    }
    
    private void sendSearchRequest(String clientID, String ime, String prezime, String email) throws Exception {
        Klijent client = generateClientForRequest(clientID, ime, prezime, email);
        Request request = new Request();
        request.setOperation(Operations.GET_CLIENTS_CONDITIONAL);
        request.setData(client);
        
        List<Klijent> _clients = (List<Klijent>) this.sendRequest(request);
        this.clients = _clients;
    }
    
    private Klijent generateClientForRequest(String clientID, String ime, String prezime, String email) {
        Klijent client = new Klijent();
        client.setKlijentID(clientID.isEmpty() ? null : Long.parseLong(clientID));
        client.setIme(ime.isEmpty() ? null : ime);
        client.setPrezime(prezime.isEmpty() ? null : prezime);
        client.setEmail(email.isEmpty() ? null : email);
        return client;
    }
    
}
