package controller.artikls;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import controller.general.AbstractController;
import domain.Artikl;
import forms.FrmMain;
import forms.dialogs.SearchArtiklsDialog;
import forms.models.ArtiklTableModel;
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
public class SearchArtiklsController extends AbstractController<SearchArtiklsDialog> {
    
    private List<Artikl> _artikls;
    
    public SearchArtiklsController(FrmMain form) {
        super(form);
    }

    @Override
    protected void prepareView() throws Exception {
        initArtiklsTable();
        setActionsListeners();
    }

    private void initArtiklsTable() {
        ArtiklTableModel tmodel = new ArtiklTableModel(new ArrayList<>());
        this.dialog.getTblSearchResults().setModel(tmodel);
    }

    private void setActionsListeners() {
        this.dialog.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    search();
                    JOptionPane.showMessageDialog(dialog, "Pretraga je zavrsena");
                } catch (Exception ex) {
                    Logger.getLogger(SearchArtiklsController.class.getName()).log(Level.SEVERE, null, ex);
                    clearTable();
                    JOptionPane.showMessageDialog(dialog, "Sistem ne moze da pronadje artikle po zadatim kriterijumima", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        this.dialog.getBtnClear().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                clear();
            }
        });
    }
    
    public void search() throws Exception {
        String sifraArtikla = this.dialog.getTxtSifraArtikla().getText().trim();
        String proizvodjac = this.dialog.getTxtProizvodjac().getText().trim();
        String nazivArtikla = this.dialog.getTxtNazivArtikla().getText().trim();
        
        Artikl artikl = new Artikl();
        artikl.setSifraArtikla(sifraArtikla.isEmpty() ? null : Long.parseLong(sifraArtikla));
        artikl.setProizvodjac(proizvodjac);
        artikl.setNaziv(nazivArtikla);
        
        _artikls = sendRequest(artikl);
        if (_artikls.size() == 0) throw new Exception("No results");
        
        ArtiklTableModel tm = (ArtiklTableModel) this.dialog.getTblSearchResults().getModel();
        tm.setArtikls(_artikls);
        tm.refresh();
    }
    
    public List<Artikl> sendRequest(Artikl artikl) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.SEARCH_ARTIKLS);
        request.setData(artikl);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (List<Artikl>) response.getResponse();
        } else throw new Exception(response.getException().getMessage());
    }
    
    public void clearTable() {
        ArtiklTableModel tm = (ArtiklTableModel) this.dialog.getTblSearchResults().getModel();
        tm.setArtikls(new ArrayList<>());
    }
    
    public void clear() {
        this.dialog.getTxtNazivArtikla().setText("");
        this.dialog.getTxtProizvodjac().setText("");
        this.dialog.getTxtSifraArtikla().setText("");
        
    }
    
}
