package controller.general;

import forms.FrmMain;
import javax.swing.JPanel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public abstract class AbstractController {

    protected FrmMain form;

    public AbstractController(FrmMain form) {
        this.form = form;
    }
    
    public void initPanel(JPanel panel) {
        
    }
    
    public void setPanel(JPanel panel) {
        
    }
    
}
