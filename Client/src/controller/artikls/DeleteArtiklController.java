package controller.artikls;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Artikl;
import forms.FrmMain;
import forms.dialogs.DeleteArtiklDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class DeleteArtiklController extends AbstractController<DeleteArtiklDialog> {

    private Artikl _artikl;

    public DeleteArtiklController(FrmMain frmMain) {
        super(frmMain);
    }

    @Override
    protected void prepareView() throws Exception {
        disableInputs();
        setActionListeners();
    }

    private void disableInputs() {
        this.dialog.getTxtNazivArtikla().setEditable(false);
        this.dialog.getTxtOpis().setEditable(false);
        this.dialog.getTxtProdajnaCena().setEditable(false);
        this.dialog.getTxtProdajnaVrednost().setEditable(false);
        this.dialog.getTxtSifraArtikla().setEditable(false);
        this.dialog.getTxtStanje().setEditable(false);
        this.dialog.getTxtVelicina().setEditable(false);

        this.dialog.getBtnDelete().setEnabled(false);
    }

    private void setActionListeners() {
        this.dialog.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    search();
                    JOptionPane.showMessageDialog(dialog, "Sistem je nasao artikl po zadatoj vrednosti");
                } catch (Exception ex) {
                    Logger.getLogger(DeleteArtiklController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da pronadje artikl po zadatoj vrednosti", "Error", JOptionPane.ERROR_MESSAGE);
                    searchFailed();
                }
            }
        });

        this.dialog.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    delete();
                    JOptionPane.showMessageDialog(dialog, "Sistem je obrisao artikl");
                } catch (Exception ex) {
                    Logger.getLogger(DeleteArtiklController.class.getName()).log(Level.SEVERE, null, ex);
                    deleteFailed();
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da obrise artikl", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void searchFailed() {
        clearInputs();
        this.dialog.getBtnDelete().setEnabled(false);
    }
    
    public void deleteFailed() {
        clearInputs();
        this.dialog.getBtnDelete().setEnabled(false);
    }

    public void search() throws Exception {
        String sifraArtikla = this.dialog.getTxtSifraSearch().getText().trim();
        Long sifra = Long.parseLong(sifraArtikla);

        Artikl artikl = sendSeachRequest(sifra);
        _artikl = artikl;
        populateArtiklInformations(artikl);

        this.dialog.getBtnDelete().setEnabled(true);
    }

    public void delete() throws Exception {
        String sifraArtikla = this.dialog.getTxtSifraArtikla().getText().trim();
        Long sifra = Long.parseLong(sifraArtikla);
        
        this.sendDeleteRequest(sifra);
        
        this.dialog.getBtnDelete().setEnabled(false);
        clearInputs();
    }

    private Artikl sendSeachRequest(Long sifra) throws Exception {
        Artikl artikl = new Artikl();
        artikl.setSifraArtikla(sifra);

        Request request = new Request();
        request.setOperation(Operations.FIND_ARTIKL);
        request.setData(artikl);

        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (Artikl) response.getResponse();
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }

    private void populateArtiklInformations(Artikl artikl) {
        this.dialog.getTxtNazivArtikla().setText(artikl.getNaziv());
        this.dialog.getTxtOpis().setText(artikl.getOpis());
        this.dialog.getTxtProdajnaCena().setText(artikl.getProdajnaCena().toString());
        this.dialog.getTxtProdajnaVrednost().setText(artikl.getProdajnaVrednost().toString());
        this.dialog.getTxtSifraArtikla().setText(artikl.getSifraArtikla().toString());
        this.dialog.getTxtStanje().setText(String.valueOf(artikl.getKolicinaNaStanju()));
        this.dialog.getTxtVelicina().setText(artikl.getVelicina());
        this.dialog.getLblProizvodjac().setText(artikl.getProizvodjac());
    }

    private void clearInputs() {
        this.dialog.getTxtNazivArtikla().setText("");
        this.dialog.getTxtOpis().setText("");
        this.dialog.getTxtProdajnaCena().setText("");
        this.dialog.getTxtProdajnaVrednost().setText("");
        this.dialog.getTxtSifraArtikla().setText("");
        this.dialog.getTxtStanje().setText("");
        this.dialog.getTxtVelicina().setText("");
        this.dialog.getLblProizvodjac().setText("");
    }

    private void sendDeleteRequest(Long sifra) throws Exception {
        Artikl artikl = new Artikl();
        artikl.setSifraArtikla(sifra);

        Request request = new Request();
        request.setOperation(Operations.DELETE_ARTIKL);
        request.setData(artikl);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return;
        } else {
            throw new Exception(response.getException().getMessage());
        }
    }

}
