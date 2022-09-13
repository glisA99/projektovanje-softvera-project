package controller.clients;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Klijent;
import forms.FrmMain;
import forms.dialogs.CreateClientDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class CreateClientController extends AbstractController<CreateClientDialog> {

    private Klijent client;

    public CreateClientController(FrmMain form) {
        super(form);
    }

    @Override
    protected void prepareView() throws Exception {
        initializeActionListeners();
        disableInputs();
        disableSaveCancelButtons();
    }

    private void initializeActionListeners() {
        this.dialog.getBtnCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    createClient();
                    JOptionPane.showMessageDialog(dialog, "Sistem je kreirao klijenta");
                } catch (Exception ex) {
                    Logger.getLogger(CreateClientController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da kreira klijenta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.dialog.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    saveClient();
                    JOptionPane.showMessageDialog(dialog, "Sistem je zapamtio klijenta");
                } catch (Exception ex) {
                    Logger.getLogger(CreateClientController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da zapamti klijenta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.dialog.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                cancel();
            }
        });
    }

    private void disableInputs() {
        this.dialog.getTxtEmail().setEnabled(false);
        this.dialog.getTxtFirstname().setEnabled(false);
        this.dialog.getTxtLastname().setEnabled(false);
    }

    private void clearInputs() {
        this.dialog.getTxtEmail().setText("");
        this.dialog.getTxtFirstname().setText("");
        this.dialog.getTxtLastname().setText("");
    }

    private void enableInputs() {
        this.dialog.getTxtEmail().setEnabled(true);
        this.dialog.getTxtFirstname().setEnabled(true);
        this.dialog.getTxtLastname().setEnabled(true);
    }

    private void toggleCreateButton(boolean state) {
        this.dialog.getBtnCreate().setEnabled(state);
    }

    private void disableSaveCancelButtons() {
        this.dialog.getBtnSave().setEnabled(false);
        this.dialog.getBtnCancel().setEnabled(false);
    }

    private void enableSaveCancelButtons() {
        this.dialog.getBtnSave().setEnabled(true);
        this.dialog.getBtnCancel().setEnabled(true);
    }

    private void createClient() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.CREATE_CLIENT);

        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            Klijent klijent = (Klijent) response.getResponse();
            this.client = klijent;
            toggleCreateButton(false);
            enableSaveCancelButtons();
            populateClientInformations();
            enableInputs();
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }

    private void saveClient() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.SAVE_CLIENT);

        client.setIme(this.dialog.getTxtFirstname().getText().trim());
        client.setPrezime(this.dialog.getTxtLastname().getText().trim());
        client.setEmail(this.dialog.getTxtEmail().getText().trim());

        validate(client);

        request.setData(client);
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            // uspesno sacuvan klijent
            clearInputs();
            disableInputs();
            disableSaveCancelButtons();
            toggleCreateButton(true);
            this.client = null;
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }

    private void cancel() {
        clearInputs();
        disableInputs();
        disableSaveCancelButtons();
        toggleCreateButton(true);
        this.client = null;
    }

    private void populateClientInformations() {
        this.dialog.getTxtEmail().setText(this.client.getEmail());
        this.dialog.getTxtFirstname().setText(this.client.getIme());
        this.dialog.getTxtLastname().setText(this.client.getPrezime());
    }

    private void validate(Klijent k) throws Exception {
        if (k.getIme().isEmpty()) {
            throw new Exception("Ime ne moze biti prazno!");
        }
        if (k.getPrezime().isEmpty()) {
            throw new Exception("Prezime ne moze biti prazno!");
        }
        if (k.getEmail().isEmpty()) {
            throw new Exception("Email ne moze biti prazan!");
        }
    }

}
