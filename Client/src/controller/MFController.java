package controller;

import forms.FrmMain;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class MFController {

    private FrmMain mainForm;

    public MFController() {
        this.mainForm = new FrmMain();
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
