package controller.izvestaj;

import communication.Operations;
import communication.Request;
import communication.Response;
import communication.ResponseType;
import domain.Izvestaj;
import domain.Radnik;
import domain.StavkaIzvestaja;
import forms.FrmMain;
import forms.dialogs.CreateIzvestajDialog;
import forms.models.StavkaTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class CreateIzvestajController extends AbstractIzvestajController<CreateIzvestajDialog> {
    
    private Izvestaj _izvestaj = null;
    private List<StavkaIzvestaja> stavke = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public CreateIzvestajController(FrmMain form) {
        super(form);
    }

    @Override
    protected void prepareView() throws Exception {
        disableViewInputs();
        setTableModel();
        disableSaveCancelBtns();
        setActionListeners();
    }

    private void disableViewInputs() {
        dialog.getTxtIzvestajID().setEnabled(false);
        dialog.getTxtDatumDo().setEnabled(false);
        dialog.getTxtDatumOd().setEnabled(false);
        dialog.getTxtDatumKreiranja().setEnabled(false);
        dialog.getTxtBrojStavki().setEnabled(false);
        dialog.getTxtUkupanPrihod().setEnabled(false);
        dialog.getTxtFirstname().setEnabled(false);
        dialog.getTxtFirstname().setEnabled(false);
        dialog.getTxtFirstnameIZV().setEnabled(false);
        dialog.getTxtLastnameIZV().setEnabled(false);
    }
    
    private void disableSaveCancelBtns() {
        dialog.getBtnCancel().setEnabled(false);
        dialog.getBtnSave().setEnabled(false);
    }
    
    private void enableSaveCancelBtns() {
        dialog.getBtnCancel().setEnabled(true);
        dialog.getBtnSave().setEnabled(true);
    }

    private void setTableModel() {
        StavkaTableModel tm = new StavkaTableModel(this.stavke);
        this.dialog.getTblStavke().setModel(tm);
    }

    private void setActionListeners() {
        // Generate
        dialog.getBtnGenerate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    create();
                } catch (Exception ex) {
                    Logger.getLogger(CreateIzvestajController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        // Save
        dialog.getBtnSave().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        // Cancel
        dialog.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void create() throws Exception {
        Izvestaj izvestaj = createIzvestajFromForm();
        
        this.validateIzvestaj(izvestaj);
        
        Izvestaj i = sendCreateRequest(izvestaj);
        this._izvestaj = i;
        this.stavke = i.getStavke();
        
        populateInformations(i);
        refreshTable();
        this.dialog.getBtnGenerate().setEnabled(false);
        enableSaveCancelBtns();
    }
    
    public Izvestaj createIzvestajFromForm() throws Exception {
        Izvestaj izvestaj = new Izvestaj();
        
        String datumOd = dialog.getTxtDatumOdUserInput().getText().trim();
        String datumDo = dialog.getTxtDatumDoUserInput().getText().trim();
        Radnik radnik = session.Session.getInstance().getLoggedRadnik();
        
        if (datumOd.isEmpty()) throw new Exception("DatumOd ne moze biti prazno!");
        
        Date datumOdDate = sdf.parse(datumOd);
        Date datumDoDate = null;
        if (!datumDo.isEmpty()) sdf.parse(datumDo);
        String radnikJMBG = radnik.getJmbg();
        
        izvestaj.setDatumDo(datumDoDate);
        izvestaj.setDatumOd(datumOdDate);
        izvestaj.setRadnikJMBG(radnikJMBG);
        
        return izvestaj;
    }

    private Izvestaj sendCreateRequest(Izvestaj izvestaj) throws Exception {
        Request request = new Request();
        request.setOperation(Operations.CREATE_IZVESTAJ);
        request.setData(izvestaj);
        
        Response response = this.sendRequest(request);
        if (response.getResponseType().equals(ResponseType.SUCCESS)) {
            return (Izvestaj) response.getResponse();
        } else throw new Exception("Greska prilikom generisanje izvestaja");
    }

    private void refreshTable() {
        StavkaTableModel tm = (StavkaTableModel) dialog.getTblStavke().getModel();
        tm.setStavke(this.stavke);
        tm.refresh();
    }

    private void populateInformations(Izvestaj izv) {
        dialog.getTxtIzvestajID().setText(izv.getIzvestajID().toString());
        dialog.getTxtDatumOd().setText(sdf.format(izv.getDatumOd()));
        dialog.getTxtDatumDo().setText(sdf.format(izv.getDatumDo()));
        dialog.getTxtFirstnameIZV().setText(dialog.getTxtFirstname().getText());
        dialog.getTxtLastnameIZV().setText(dialog.getTxtLastname().getText());
        dialog.getTxtDatumKreiranja().setText(sdf.format(izv.getDatumKreiranja()));
        dialog.getTxtBrojStavki().setText(String.valueOf(izv.getStavke().size()));
        dialog.getTxtUkupanPrihod().setText(izv.getUkupanPrihod().toString());
    }
    
}
