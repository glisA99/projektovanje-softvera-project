package controller.artikls;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Artikl;
import exception.ValidationException;
import forms.FrmMain;
import forms.dialogs.ChangeArtiklDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ChangeArtiklController extends AbstractController<ChangeArtiklDialog> {
    
    private Artikl _artikl;
    
    public ChangeArtiklController(FrmMain form) {
        super(form);
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
        this.dialog.getTxtProizvodjac().setEditable(false);

        this.dialog.getBtnSave().setEnabled(false);
    }
    
    private void enableInputs() {
        this.dialog.getTxtNazivArtikla().setEditable(true);
        this.dialog.getTxtOpis().setEditable(true);
        this.dialog.getTxtProdajnaCena().setEditable(true);
        this.dialog.getTxtProdajnaVrednost().setEditable(true);
        this.dialog.getTxtStanje().setEditable(true);
        this.dialog.getTxtVelicina().setEditable(true);
        this.dialog.getTxtProizvodjac().setEditable(true);
    }

    private void setActionListeners() {
        // Search
        this.dialog.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    search();
                    JOptionPane.showMessageDialog(dialog, "Sistem je nasao artikl po zadatoj vrednosti");
                } catch (Exception ex) {
                    Logger.getLogger(DeleteArtiklController.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex instanceof ValidationException) JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Validation error", JOptionPane.ERROR_MESSAGE);
                    else JOptionPane.showMessageDialog(dialog, "Sistem ne moze da pronadje artikl po zadatoj vrednosti", "Error", JOptionPane.ERROR_MESSAGE);
                    searchFailed();
                }
            }
        });
        // Save
        this.dialog.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    save();
                    JOptionPane.showMessageDialog(dialog, "Sistem je zapamtio artikl");
                } catch (Exception ex) {
                    Logger.getLogger(DeleteArtiklController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da zapamti artikl", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    public void search() throws Exception {
        String sifraArtikla = this.dialog.getTxtSifraSearch().getText().trim();
        Long sifra = Long.parseLong(sifraArtikla);

        Artikl artikl = sendSeachRequest(sifra);
        _artikl = artikl;
        populateArtiklInformations(artikl);

        this.dialog.getBtnSave().setEnabled(true);
        enableInputs();
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
    
    public void save() throws Exception {
        Artikl artikl = new Artikl();
        artikl.setNaziv(this.dialog.getTxtNazivArtikla().getText().trim());
        artikl.setKolicinaNaStanju(Integer.parseInt(this.dialog.getTxtStanje().getText().trim()));
        artikl.setOpis(this.dialog.getTxtOpis().getText().trim());
        artikl.setProdajnaCena(BigDecimal.valueOf(Long.valueOf(this.dialog.getTxtProdajnaCena().getText().trim())));
        artikl.setProdajnaVrednost(BigDecimal.valueOf(Long.valueOf(this.dialog.getTxtProdajnaVrednost().getText().trim())));
        artikl.setProizvodjac(this.dialog.getTxtProizvodjac().getText().trim());
        artikl.setSifraArtikla(Long.parseLong(this.dialog.getTxtSifraArtikla().getText()));
        artikl.setVelicina(this.dialog.getTxtProizvodjac().getText().trim());
        
        validate(artikl);
        
        sendSaveRequest(artikl);
        
        clearInputs();
        disableInputs();
    }
    
    public void searchFailed() {
        clearInputs();
        disableInputs();
    }
    
    private void clearInputs() {
        this.dialog.getTxtNazivArtikla().setText("");
        this.dialog.getTxtOpis().setText("");
        this.dialog.getTxtProdajnaCena().setText("");
        this.dialog.getTxtProdajnaVrednost().setText("");
        this.dialog.getTxtSifraArtikla().setText("");
        this.dialog.getTxtStanje().setText("");
        this.dialog.getTxtVelicina().setText("");
        this.dialog.getTxtProizvodjac().setText("");
    }

    private void populateArtiklInformations(Artikl artikl) {
        this.dialog.getTxtNazivArtikla().setText(artikl.getNaziv());
        this.dialog.getTxtOpis().setText(artikl.getOpis());
        this.dialog.getTxtProdajnaCena().setText(artikl.getProdajnaCena().toString());
        this.dialog.getTxtProdajnaVrednost().setText(artikl.getProdajnaVrednost().toString());
        this.dialog.getTxtSifraArtikla().setText(artikl.getSifraArtikla().toString());
        this.dialog.getTxtStanje().setText(String.valueOf(artikl.getKolicinaNaStanju()));
        this.dialog.getTxtVelicina().setText(artikl.getVelicina());
        this.dialog.getTxtProizvodjac().setText(artikl.getProizvodjac());
    }

    private void validate(Artikl artikl) throws ValidationException {
        if (artikl.getSifraArtikla() == null) throw new ValidationException("Sifra artikla ne moze biti null!");
        if (artikl.getNaziv() == null || artikl.getNaziv().isEmpty()) throw new ValidationException("Naziv artikla ne moze biti prazan!");
        if (artikl.getKolicinaNaStanju() <= 0) throw new ValidationException("Kolicina na stanju ne moze biti 0 ili manja od 0!");
        if (artikl.getOpis() == null) throw new ValidationException("Opis ne moze biti null!");
        if (artikl.getProdajnaCena() == null || artikl.getProdajnaCena().compareTo(BigDecimal.ZERO) != 1) {
            throw new ValidationException("Prodajna cena mora biti veca od nule!");
        }
        if (artikl.getProdajnaVrednost() == null || artikl.getProdajnaVrednost().compareTo(BigDecimal.ZERO) != 1) {
            throw new ValidationException("Prodajna vrednost mora biti veca od nule!");
        }
        if (artikl.getProizvodjac() == null || artikl.getProizvodjac().isEmpty()) {
            throw new ValidationException("Proizvodjac ne mozee biti prazan!");
        }
        if (artikl.getVelicina() == null || artikl.getVelicina().isEmpty()) {
            throw new ValidationException("Velicina ne moze biti prazna!");
        }
     }

    private void sendSaveRequest(Artikl artikl) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.SAVE_ARTIKL);
        request.setData(artikl);
        
        Response response = this.sendRequest(request);
        
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return;
        } else throw new Exception("Neuspesno cuvanje artikla");
    }
    
}
