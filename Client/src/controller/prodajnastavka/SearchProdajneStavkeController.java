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
import forms.dialogs.SearchProdajneStavkeDialog;
import forms.models.ProdajnaStavkaTM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class SearchProdajneStavkeController extends AbstractControllerProdajneStavke<SearchProdajneStavkeDialog> {
    
    private List<ProdajnaStavka> _stavke = new ArrayList<>();
    private List<Klijent> _clients = new ArrayList<>();
    private List<Artikl> _artikls = new ArrayList<>();
    
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public SearchProdajneStavkeController(FrmMain form) {
        super(form);
    }

    @Override
    protected void prepareView() throws Exception {
        prepareCheckBoxes();
        prepareTableModel();
        setActionsListeners();
    }

    private void prepareCheckBoxes() throws Exception {
        fillCbKlijents();
        fillCbArtikls();
    }

    private void fillCbKlijents() throws Exception {
        List<Klijent> clients = this.loadClients();
        this._clients = clients;
        JComboBox cbClients = this.dialog.getCbKlijent();
        cbClients.removeAllItems();
        for(int i = 0;i < clients.size();i++) {
            cbClients.addItem(clients.get(i));
        }
    }

    private void fillCbArtikls() throws Exception {
        List<Artikl> artikls = this.loadArtikls();
        this._artikls = artikls;
        JComboBox cbArtikls = this.dialog.getCbArtikl();
        cbArtikls.removeAllItems();
        for(int i = 0;i < artikls.size();i++) {
            cbArtikls.addItem(artikls.get(i));
        }
    }
    
    private void prepareTableModel() {
        ProdajnaStavkaTM tm = new ProdajnaStavkaTM(this._stavke,this._artikls,this._clients);
        this.dialog.getTblStavke().setModel(tm);
    }

    private void setActionsListeners() {
        // Search
        this.dialog.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    search();
                    if (_stavke.size() == 0) throw new Exception("Nema stavki");
                    JOptionPane.showMessageDialog(dialog, "Pretraga je zavrsena");
                } catch (Exception ex) {
                    Logger.getLogger(SearchProdajneStavkeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da nadje prodajne stavke po zadatim kriterijumima", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    
    private void search() throws Exception {
        ProdajnaStavka ps = createStavkaFromForm();
        
        List<ProdajnaStavka> pstavke = sendSearchRequest(ps);
        this._stavke = pstavke;
        
        if (this._stavke.size() == 0) clearTable();
        
        refreshTableView();
    }

    private ProdajnaStavka createStavkaFromForm() throws Exception {
        ProdajnaStavka prodajnaStavka = new ProdajnaStavka();
        
        String stavkaID = this.dialog.getTxtStavkaID().getText().trim();
        if (!stavkaID.isEmpty()) prodajnaStavka.setProdajnaStavkaID(Long.parseLong(stavkaID));
        
        String datumProdaje = this.dialog.getTxtDatumProdaje().getText().trim();
        
        // try to parse date if available
        if (datumProdaje != null && !datumProdaje.isEmpty()) prodajnaStavka.setDatumProdaje(sdf.parse(datumProdaje));
        
        // if artikl search param is active
        if (dialog.getCheckArtikl().isSelected()) {
            Artikl selectedArtikl = (Artikl) dialog.getCbArtikl().getSelectedItem();
            prodajnaStavka.setSifraArtikla(selectedArtikl.getSifraArtikla());
        }
        
        // if klijent search param is active
        if (dialog.getCheckKlijent().isSelected()) {
            Klijent selectedKlijent = (Klijent) dialog.getCbKlijent().getSelectedItem();
            prodajnaStavka.setKlijentID(selectedKlijent.getKlijentID());
        }
        
        return prodajnaStavka;
    }

    private List<ProdajnaStavka> sendSearchRequest(ProdajnaStavka ps) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.SEARCH_PRODAJNE_STAVKE);
        request.setData(ps);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<ProdajnaStavka>) response.getResponse();
        } else throw new Exception("Error searching stakve");
    }

    private void refreshTableView() {
        ProdajnaStavkaTM tm = (ProdajnaStavkaTM) dialog.getTblStavke().getModel();
        tm.setStavke(this._stavke);
        tm.refresh();
    }

    private void clearTable() {
        ProdajnaStavkaTM tm = (ProdajnaStavkaTM) dialog.getTblStavke().getModel();
        tm.setStavke(new ArrayList<>());
        tm.refresh();
    }
    
}
