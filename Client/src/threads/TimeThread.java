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
public class TimeThread extends Thread {
    
    private JLabel date;
    private JLabel time;
    private JLabel sessionDuration;
    private Date timeLogged;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
    
    private final long hour = 1000 * 60 * 60;
    private final long min = 1000 * 60;
    private final long sec = 1000;

    public TimeThread(JLabel date, JLabel time, JLabel sessionDuration, Date timeLogged) {
        this.date = date;
        this.time = time;
        this.sessionDuration = sessionDuration;
        this.timeLogged = timeLogged;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                Date datum = new Date();
                date.setText(dateFormat.format(datum));
                time.setText(timeFormat.format(datum));
                sessionDuration.setText(calculateSessionDuration(datum, timeLogged));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
                interrupt();
            }
        }
    }

    private String calculateSessionDuration(Date datum, Date timeLogged) {
        long t = datum.getTime() - timeLogged.getTime();
        int hours = (int) (t / hour);
        int mins = (int) ((t - hours * hour)/ min);
        int secs = (int) ((t - hours * hour - mins * min) / sec);
        return format(hours) + ":" + format(mins) + ":" + format(secs);
    }
    
    private String format(int val) {
        return val < 10 ? "0" + val : String.valueOf(val);
    }
    
}
