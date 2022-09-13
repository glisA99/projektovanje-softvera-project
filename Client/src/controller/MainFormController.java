package controller;

import controller.clients.SearchClientsController;
import controller.general.AbstractController;
import forms.FrmMain;
import forms.panels.dialogs.SearchClientsDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class MainFormController {

    private FrmMain mainForm;
    private AbstractController controller;

    public MainFormController() {
        this.mainForm = new FrmMain();
        initialize();
        this.mainForm.setVisible(true);
    }

    public void initialize() {
        initializeActionListeners();
    }

    private void initializeActionListeners() {
        initClientActionListeners();
//        initProdajnaStavkaActionListeners();
//        initArtiklActionListeners();
//        initIzvestajActionListeners();
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

    }

    private void initProdajnaStavkaActionListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initArtiklActionListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initIzvestajActionListeners() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
