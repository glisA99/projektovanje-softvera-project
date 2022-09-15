package controller.artikls;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Artikl;
import exception.ValidationException;
import forms.FrmMain;
import forms.dialogs.CreateArtiklDialog;
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
public class CreateArtiklController extends AbstractController<CreateArtiklDialog> {
    
    private Artikl _artikl;
    
    public CreateArtiklController(FrmMain form) {
        super(form);
    }

    @Override
    protected void prepareView() throws Exception {
        setActionListeners();
        disableIn();
    }

    private void setActionListeners() {
        // Create
        this.dialog.getBtnCreateNew().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    create();
                    JOptionPane.showMessageDialog(dialog, "Sistem je kreirao artikl");
                } catch (Exception ex) {
                    Logger.getLogger(CreateArtiklController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da kreira artikl", "Error", JOptionPane.ERROR_MESSAGE);
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
                    Logger.getLogger(CreateArtiklController.class.getName()).log(Level.SEVERE, null, ex);
                    if (ex instanceof ValidationException) {
                        JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Validation error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Sistem ne moze da zapamti artikl", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        // Clear
        this.dialog.getBtnClearForm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clearInputs();
            }
        });
    }
    
    
    public void create() throws Exception {
        Artikl artikl = sendCreateRequest();
        this._artikl = artikl;
        
        enableIn();
        this.dialog.getBtnCreateNew().setEnabled(false);
    }
    
    public Artikl sendCreateRequest() throws Exception {
        Request request = new Request();
        request.setOperation(Operations.CREATE_ARTIKL);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (Artikl) response.getResponse();
        } else throw new Exception("Greska");
    }
    
    public void save() throws Exception {
        Artikl artikl = new Artikl();
        artikl.setNaziv(this.dialog.getTxtNazivArtikla().getText().trim());
        artikl.setOpis(this.dialog.getTxtOpis().getText().trim());
        artikl.setKolicinaNaStanju(Integer.parseInt(this.dialog.getTxtStanje().getText().trim()));
        artikl.setProizvodjac(this.dialog.getTxtProizvodjac().getText().trim());
        artikl.setVelicina(this.dialog.getTxtVelicina().getText().trim());
        artikl.setProdajnaCena(BigDecimal.valueOf(Long.parseLong(this.dialog.getTxtProdajnaCena().getText().trim())));
        artikl.setProdajnaVrednost(BigDecimal.valueOf(Long.parseLong(this.dialog.getTxtProdajnaVrednost().getText().trim())));
        
        validate(artikl);
        
        sendSaveRequest(artikl);
        
        clearInputs();
        disableIn();
        this.dialog.getBtnCreateNew().setEnabled(true);
    }
    
    private void sendSaveRequest(Artikl artikl) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.SAVE_ARTIKL);
        request.setData(artikl);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return;
        } else throw new Exception("Greska");
    }

    private void disableIn() {
        this.dialog.getTxtNazivArtikla().setEditable(false);
        this.dialog.getTxtOpis().setEditable(false);
        this.dialog.getTxtProdajnaCena().setEditable(false);
        this.dialog.getTxtProdajnaVrednost().setEditable(false);
        this.dialog.getTxtSifraArtikla().setEditable(false);
        this.dialog.getTxtStanje().setEditable(false);
        this.dialog.getTxtVelicina().setEditable(false);
        this.dialog.getTxtProizvodjac().setEditable(false);

        this.dialog.getBtnSave().setEnabled(false);
        this.dialog.getBtnClearForm().setEnabled(false);
    }
    
    private void enableIn() {
        this.dialog.getTxtNazivArtikla().setEditable(true);
        this.dialog.getTxtOpis().setEditable(true);
        this.dialog.getTxtProdajnaCena().setEditable(true);
        this.dialog.getTxtProdajnaVrednost().setEditable(true);
        this.dialog.getTxtSifraArtikla().setEditable(false);
        this.dialog.getTxtStanje().setEditable(true);
        this.dialog.getTxtVelicina().setEditable(true);
        this.dialog.getTxtProizvodjac().setEditable(true);

        this.dialog.getBtnSave().setEnabled(true);
        this.dialog.getBtnClearForm().setEnabled(true);
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

    private void validate(Artikl artikl) throws ValidationException {
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

    
    
}
