package controller;

import controller.artikls.ChangeArtiklController;
import controller.artikls.CreateArtiklController;
import controller.artikls.DeleteArtiklController;
import controller.artikls.SearchArtiklsController;
import controller.clients.CreateClientController;
import controller.clients.SearchClientsController;
import controller.general.AbstractController;
import controller.izvestaj.CreateIzvestajController;
import controller.prodajnastavka.CreateProdajnaStavkaController;
import controller.prodajnastavka.DeleteProdajnaStavkaController;
import controller.prodajnastavka.SearchProdajneStavkeController;
import domain.Radnik;
import forms.FrmMain;
import forms.dialogs.ChangeArtiklDialog;
import forms.dialogs.CreateArtiklDialog;
import forms.dialogs.CreateClientDialog;
import forms.dialogs.CreateIzvestajDialog;
import forms.dialogs.CreateProdajnaStavkaDialog;
import forms.dialogs.DeleteArtiklDialog;
import forms.dialogs.DeleteProdajnaStavkaDialog;
import forms.dialogs.SearchArtiklsDialog;
import forms.dialogs.SearchClientsDialog;
import forms.dialogs.SearchProdajneStavkeDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class MainFormController {

    private FrmMain mainForm;
    private AbstractController controller;
    private Radnik loggedRadnik;

    public MainFormController(Radnik radnik) {
        this.loggedRadnik = radnik;
        this.mainForm = new FrmMain(radnik);
        initialize();
        this.mainForm.setVisible(true);
    }

    public void initialize() {
        initializeActionListeners();
    }

    private void initializeActionListeners() {
        initClientActionListeners();
        initProdajnaStavkaActionListeners();
        initArtiklActionListeners();
        initIzvestajActionListeners();
    }

    private void initClientActionListeners() {
        // Search clients panel + controller
        this.mainForm.getJmiPretragaKlijenata().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new SearchClientsController(mainForm);
                controller.initDialog(new SearchClientsDialog(mainForm));
            }
        });
        // Create client panel + controller
        this.mainForm.getJmiKreirajKlijenta().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new CreateClientController(mainForm);
                controller.initDialog(new CreateClientDialog(mainForm, true));
            }
        });
    }

    private void initProdajnaStavkaActionListeners() {
        // Create prodajna stavka
        this.mainForm.getJmiKreirajProdajnuStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new CreateProdajnaStavkaController(mainForm);
                controller.initDialog(new CreateProdajnaStavkaDialog(mainForm,true));
            }
        });
        // Search prodaje stavke
        this.mainForm.getJmiPretragaProdajnihStavki().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new SearchProdajneStavkeController(mainForm);
                controller.initDialog(new SearchProdajneStavkeDialog(mainForm,true));
            }
        });
        // Delete prodajna stavka
        this.mainForm.getJmiBrisanjeProdajneStavke().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new DeleteProdajnaStavkaController(mainForm);
                controller.initDialog(new DeleteProdajnaStavkaDialog(mainForm,true));
            }
        });
    }

    private void initArtiklActionListeners() {
        // Search artikls
        this.mainForm.getJmiPretragaArtikala().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new SearchArtiklsController(mainForm);
                controller.initDialog(new SearchArtiklsDialog(mainForm, true));
            }
        });
        // Delete artikl
        this.mainForm.getJmiBrisanjeArtikla().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new DeleteArtiklController(mainForm);
                controller.initDialog(new DeleteArtiklDialog(mainForm,true));
            }
        });
        // Change artikl
        this.mainForm.getJmiPromenaArtikla().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new ChangeArtiklController(mainForm);
                controller.initDialog(new ChangeArtiklDialog(mainForm,true));
            }
        });
        // Create artikl
        this.mainForm.getJmiKreiranjeArtikla().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new CreateArtiklController(mainForm);
                controller.initDialog(new CreateArtiklDialog(mainForm,true));
            }
        });
    }

    private void initIzvestajActionListeners() {
        // Create (generate)
        this.mainForm.getJmiKreiranjeIzvestaja().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                controller = new CreateIzvestajController(mainForm);
                controller.initDialog(new CreateIzvestajDialog(mainForm,true));
            }
        });
    }

    public Radnik getLoggedRadnik() {
        return loggedRadnik;
    }

}
