package controller.prodajnastavka;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import domain.Artikl;
import domain.Klijent;
import domain.ProdajnaStavka;
import domain.Radnik;
import forms.FrmMain;
import forms.dialogs.CreateProdajnaStavkaDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class CreateProdajnaStavkaController extends AbstractControllerProdajneStavke<CreateProdajnaStavkaDialog> {
    
    private ProdajnaStavka _prodajnaStavka;

    public CreateProdajnaStavkaController(FrmMain form) {
        super(form);
    }
    
    @Override
    protected void prepareView() throws Exception {
        prepareCheckBoxes();
        disableIn();
        prefillData();
        setActionsListeners();
    }

    private void prepareCheckBoxes() throws Exception {
        fillCbKlijents();
        fillCbArtikls();
    }

    private void fillCbKlijents() throws Exception {
        List<Klijent> clients = this.loadClients();
        System.out.println("Clients size: " + clients.size());
        JComboBox cbClients = this.dialog.getCbKlijent();
        cbClients.removeAllItems();
        for(int i = 0;i < clients.size();i++) {
            cbClients.addItem(clients.get(i));
        }
    }

    private void fillCbArtikls() throws Exception {
        List<Artikl> artikls = this.loadArtikls();
        System.out.println("Artikls size: " + artikls.size());
        JComboBox cbArtikls = this.dialog.getCbArtikl();
        cbArtikls.removeAllItems();
        for(int i = 0;i < artikls.size();i++) {
            cbArtikls.addItem(artikls.get(i));
        }
    }

    private void disableIn() {
        this.dialog.getTxtDatumProdaje().setEditable(false);
        this.dialog.getTxtPrezime().setEditable(false);
        this.dialog.getTxtCena().setEditable(false);
        this.dialog.getTxtIme().setEditable(false);
        this.dialog.getTxtKolicina().setEditable(false);
        this.dialog.getTxtIznos().setEditable(false);
        this.dialog.getTxtNazivArtikla().setEditable(false);
        
        this.dialog.getCbArtikl().setEnabled(false);
        this.dialog.getCbKlijent().setEnabled(false);
        
        this.dialog.getBtnSave().setEnabled(false);
    }
    
    private void enableInputs() {
        this.dialog.getTxtDatumProdaje().setEditable(true);
        this.dialog.getTxtPrezime().setEditable(false);
        this.dialog.getTxtCena().setEditable(false);
        this.dialog.getTxtIznos().setEditable(false);
        this.dialog.getTxtIme().setEditable(false);
        this.dialog.getTxtKolicina().setEditable(true);
        this.dialog.getTxtNazivArtikla().setEditable(false);
        
        this.dialog.getCbArtikl().setEnabled(true);
        this.dialog.getCbKlijent().setEnabled(true);
        
        this.dialog.getBtnSave().setEnabled(true);
    }

    private void prefillData() throws IOException {
        Radnik radnik = session.Session.getInstance().getLoggedRadnik();
        this.dialog.getTxtIme().setText(radnik.getIme());
        this.dialog.getTxtPrezime().setText(radnik.getPrezime());
        
        this.dialog.getCbArtikl().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                JComboBox cbArtikl = (JComboBox) arg0.getSource();
                Artikl selected = (Artikl) cbArtikl.getSelectedItem();
                dialog.getTxtNazivArtikla().setText(selected.getNaziv());
                dialog.getTxtCena().setText(selected.getProdajnaCena().toString());
            }
        });
        
        this.dialog.getBtnCalculate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String value = dialog.getTxtKolicina().getText().trim();
                try {
                    int valueInt = Integer.parseInt(value);
                    Artikl artikl = (Artikl) dialog.getCbArtikl().getSelectedItem();
                    BigDecimal cena = artikl.getProdajnaCena();
                    BigDecimal vrednost = cena.multiply(BigDecimal.valueOf(new Long(valueInt)));
                    dialog.getTxtIznos().setText(vrednost.toString());
                } catch (Exception ex) {}
            }
        });
        
        
    }

    private void setActionsListeners() {
        // Create
        this.dialog.getBtnCreate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    create();
                    JOptionPane.showMessageDialog(dialog, "Sistem je kreirao prodajnu stavku");
                } catch (Exception ex) {
                    Logger.getLogger(CreateProdajnaStavkaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da kreira prodajnu stavku", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        // Save
        this.dialog.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    save();
                    JOptionPane.showMessageDialog(dialog, "Sistem je zapamtio prodajnu stavku");
                } catch (Exception ex) {
                    Logger.getLogger(CreateProdajnaStavkaController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da zapamti prodajnu stavku", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void create() throws Exception {
        ProdajnaStavka ps = new ProdajnaStavka();
        
        this._prodajnaStavka = sendCreateRequest(ps);
        
        enableInputs();
    }
    
    private ProdajnaStavka sendCreateRequest(ProdajnaStavka ps) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.CREATE_PRODAJNA_STAVKA);
        request.setData(ps);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (ProdajnaStavka) response.getResponse();
        } else throw new Exception("Error creating stavka");
    }
    
    private void save() throws Exception {
        ProdajnaStavka prodajnaStavka = new ProdajnaStavka();
        Klijent klijent = (Klijent) this.dialog.getCbKlijent().getSelectedItem();
        prodajnaStavka.setKlijentID(klijent.getKlijentID());
        Artikl artikl = (Artikl) this.dialog.getCbArtikl().getSelectedItem();
        prodajnaStavka.setSifraArtikla(artikl.getSifraArtikla());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        prodajnaStavka.setDatumProdaje(sdf.parse(this.dialog.getTxtDatumProdaje().getText().trim()));
        int kolicina = Integer.parseInt(this.dialog.getTxtKolicina().getText().trim());
        prodajnaStavka.setKolicina(kolicina);
        Artikl artikl1 = (Artikl) dialog.getCbArtikl().getSelectedItem();
        prodajnaStavka.setIznos(artikl1.getProdajnaCena().multiply(new BigDecimal(kolicina)));
        Radnik radnik = session.Session.getInstance().getLoggedRadnik();
        prodajnaStavka.setRadnikJMBG(radnik.getJmbg());
        
        this.validateProdajnaStavka(prodajnaStavka);
        
        sendSaveRequest(prodajnaStavka);
        
        disableIn();
        clearInputs();
    }
    
    private void sendSaveRequest(ProdajnaStavka ps) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.SAVE_PRODAJNA_STAVKA);
        request.setData(ps);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return;
        } else throw new Exception("Error saving stavka");
    }

    private void clearInputs() {
        this.dialog.getTxtDatumProdaje().setText("");
        this.dialog.getTxtCena().setText("");
        this.dialog.getTxtKolicina().setText("");
        this.dialog.getTxtNazivArtikla().setText("");
        this.dialog.getTxtIznos().setText("");
    }
    
}
