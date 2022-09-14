package controller.clients;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Klijent;
import forms.FrmMain;
import forms.models.ClientTableModel;
import forms.dialogs.SearchClientsDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class SearchClientsController extends AbstractController<SearchClientsDialog> {

    private List<Klijent> clients = new ArrayList<>();

    public SearchClientsController(FrmMain form) {
        super(form);
    }

    @Override
    protected void prepareView() throws Exception {
        initClientsTable();
        setActionListeners();
    }

    private void setActionListeners() {
        setSearchActionListener();
        setClearActionListener();
    }

    private void initClientsTable() {
        ClientTableModel tableModel = new ClientTableModel(clients);
        this.dialog.getTblClients().setModel(tableModel);
    }

    private void setSearchActionListener() {
        this.dialog.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String clientID = dialog.getTxtClientID().getText().trim();
                String ime = dialog.getTxtFirstname().getText().trim();
                String prezime = dialog.getTxtLastname().getText().trim();
                String email = dialog.getTxtEmail().getText().trim();

                try {
                    sendSearchRequest(clientID, ime, prezime, email);
                    ClientTableModel tableModel = (ClientTableModel) dialog.getTblClients().getModel();
                    if (clients.size() == 0) {
                        throw new Exception("No clients found");
                    }
                    tableModel.setClients(clients);
                    tableModel.refresh();
                    JOptionPane.showMessageDialog(dialog, "Pretraga je zavrsena.");
                } catch (Exception ex) {
                    Logger.getLogger(SearchClientsController.class.getName()).log(Level.SEVERE, null, ex);
                    clearTable();
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da pronadje klijente po zadatim kriterijumima!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void clearTable() {
        ClientTableModel tmodel = (ClientTableModel) this.dialog.getTblClients().getModel();
        tmodel.setClients(new ArrayList<>());
        tmodel.refresh();
    }

    private void clearInputs() {
        dialog.getTxtClientID().setText("");
        dialog.getTxtFirstname().setText("");
        dialog.getTxtLastname().setText("");
        dialog.getTxtEmail().setText("");
    }

    private void sendSearchRequest(String clientID, String ime, String prezime, String email) throws Exception {
        Klijent client = generateClientForRequest(clientID, ime, prezime, email);
        Request request = new Request();
        request.setOperation(Operations.GET_CLIENTS_CONDITIONAL);
        request.setData(client);

        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            List<Klijent> _clients = (List<Klijent>) response.getResponse();
            this.clients = _clients;
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }

    private Klijent generateClientForRequest(String clientID, String ime, String prezime, String email) {
        Klijent client = new Klijent();
        client.setKlijentID(clientID.isEmpty() ? null : Long.parseLong(clientID));
        client.setIme(ime.isEmpty() ? null : ime);
        client.setPrezime(prezime.isEmpty() ? null : prezime);
        client.setEmail(email.isEmpty() ? null : email);
        return client;
    }

    private void setClearActionListener() {
       this.dialog.getBtnClear().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent arg0) {
               clearInputs();
           }
       });
    }

}
