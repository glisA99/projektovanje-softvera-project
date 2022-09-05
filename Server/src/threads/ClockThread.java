package threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ClockThread extends Thread {
    
    private JLabel lblClock;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public ClockThread(JLabel lblClock) {
        this.lblClock = lblClock;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                Date date = new Date();
                lblClock.setText(simpleDateFormat.format(date));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClockThread.class.getName()).log(Level.SEVERE, null, ex);
                this.interrupt();
            }
        }
    }
    
}
