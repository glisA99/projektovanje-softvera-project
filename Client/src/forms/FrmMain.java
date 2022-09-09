/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import javax.accessibility.AccessibleContext;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 *
 * @author ognje
 */
public class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    public FrmMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        pnlMain = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jmiKreirajKlijenta = new javax.swing.JMenuItem();
        jmiPretragaKlijenata = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Butik Application");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        jMenu2.setText("Klijent");

        jmiKreirajKlijenta.setText("Kreiraj klijenta");
        jMenu2.add(jmiKreirajKlijenta);

        jmiPretragaKlijenata.setText("Pretraga klijenata");
        jmiPretragaKlijenata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPretragaKlijenataActionPerformed(evt);
            }
        });
        jMenu2.add(jmiPretragaKlijenata);

        jMenuBar1.add(jMenu2);

        jMenu5.setText("Prodajna Stavka");

        jMenuItem9.setText("Kreiraj prodajnu stavku");
        jMenu5.add(jMenuItem9);

        jMenuItem10.setText("Pretraga prodajnih stavki");
        jMenu5.add(jMenuItem10);

        jMenuItem11.setText("Brisanje prodajne stavke");
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        jMenu3.setText("Artikl");

        jMenuItem4.setText("Pretraga artikala");
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Kreiranje novog artikla");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Promena artikla");
        jMenu3.add(jMenuItem6);

        jMenuItem7.setText("Brisanje artikla");
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Izvestaj");

        jMenuItem8.setText("Kreiranje izvestaja");
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiPretragaKlijenataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPretragaKlijenataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmiPretragaKlijenataActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jmiKreirajKlijenta;
    private javax.swing.JMenuItem jmiPretragaKlijenata;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables

    public JMenu getjMenu2() {
        return jMenu2;
    }

    public void setjMenu2(JMenu jMenu2) {
        this.jMenu2 = jMenu2;
    }

    public JMenu getjMenu3() {
        return jMenu3;
    }

    public void setjMenu3(JMenu jMenu3) {
        this.jMenu3 = jMenu3;
    }

    public JMenu getjMenu4() {
        return jMenu4;
    }

    public void setjMenu4(JMenu jMenu4) {
        this.jMenu4 = jMenu4;
    }

    public JMenuBar getjMenuBar1() {
        return jMenuBar1;
    }

    public void setjMenuBar1(JMenuBar jMenuBar1) {
        this.jMenuBar1 = jMenuBar1;
    }

    public JMenuItem getjMenuItem1() {
        return jMenuItem1;
    }

    public void setjMenuItem1(JMenuItem jMenuItem1) {
        this.jMenuItem1 = jMenuItem1;
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }

    public void setPnlMain(JPanel pnlMain) {
        this.pnlMain = pnlMain;
    }

    public JMenu getjMenu5() {
        return jMenu5;
    }

    public void setjMenu5(JMenu jMenu5) {
        this.jMenu5 = jMenu5;
    }

    public JMenuItem getjMenuItem10() {
        return jMenuItem10;
    }

    public void setjMenuItem10(JMenuItem jMenuItem10) {
        this.jMenuItem10 = jMenuItem10;
    }

    public JMenuItem getjMenuItem11() {
        return jMenuItem11;
    }

    public void setjMenuItem11(JMenuItem jMenuItem11) {
        this.jMenuItem11 = jMenuItem11;
    }

    public JMenuItem getjMenuItem4() {
        return jMenuItem4;
    }

    public void setjMenuItem4(JMenuItem jMenuItem4) {
        this.jMenuItem4 = jMenuItem4;
    }

    public JMenuItem getjMenuItem5() {
        return jMenuItem5;
    }

    public void setjMenuItem5(JMenuItem jMenuItem5) {
        this.jMenuItem5 = jMenuItem5;
    }

    public JMenuItem getjMenuItem6() {
        return jMenuItem6;
    }

    public void setjMenuItem6(JMenuItem jMenuItem6) {
        this.jMenuItem6 = jMenuItem6;
    }

    public JMenuItem getjMenuItem7() {
        return jMenuItem7;
    }

    public void setjMenuItem7(JMenuItem jMenuItem7) {
        this.jMenuItem7 = jMenuItem7;
    }

    public JMenuItem getjMenuItem8() {
        return jMenuItem8;
    }

    public void setjMenuItem8(JMenuItem jMenuItem8) {
        this.jMenuItem8 = jMenuItem8;
    }

    public JMenuItem getjMenuItem9() {
        return jMenuItem9;
    }

    public void setjMenuItem9(JMenuItem jMenuItem9) {
        this.jMenuItem9 = jMenuItem9;
    }

    public JMenuItem getJmiKreirajKlijenta() {
        return jmiKreirajKlijenta;
    }

    public void setJmiKreirajKlijenta(JMenuItem jmiKreirajKlijenta) {
        this.jmiKreirajKlijenta = jmiKreirajKlijenta;
    }

    public JMenuItem getJmiPretragaKlijenata() {
        return jmiPretragaKlijenata;
    }

    public void setJmiPretragaKlijenata(JMenuItem jmiPretragaKlijenata) {
        this.jmiPretragaKlijenata = jmiPretragaKlijenata;
    }

    public JRootPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(JRootPane rootPane) {
        this.rootPane = rootPane;
    }

    public boolean isRootPaneCheckingEnabled() {
        return rootPaneCheckingEnabled;
    }

    public void setRootPaneCheckingEnabled(boolean rootPaneCheckingEnabled) {
        this.rootPaneCheckingEnabled = rootPaneCheckingEnabled;
    }

    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }


    

}
