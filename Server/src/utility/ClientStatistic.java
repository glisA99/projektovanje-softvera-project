package utility;

import communication.ResponseType;
import domain.Radnik;
import java.util.Date;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ClientStatistic {

    private Radnik loggedRadnik;
    private Date dateTimeConnected;
    private int requestsSent;
    private int successRequests;
    private int failureRequests;

    public ClientStatistic() {
        this.dateTimeConnected = new Date();
    }

    public ClientStatistic(Radnik loggedRadnik, Date dateTimeConnected, int requestsSent, int successRequests, int failureRequests) {
        this.loggedRadnik = loggedRadnik;
        this.dateTimeConnected = dateTimeConnected;
        this.requestsSent = requestsSent;
        this.successRequests = successRequests;
        this.failureRequests = failureRequests;
    }
    
    public void addRequest(ResponseType responseType) {
        requestsSent++;
        if (responseType.equals(ResponseType.SUCCESS)) successRequests++;
        else failureRequests++;
    }

    public Radnik getLoggedRadnik() {
        return loggedRadnik;
    }

    public void setLoggedRadnik(Radnik loggedRadnik) {
        this.loggedRadnik = loggedRadnik;
    }

    public Date getDateTimeConnected() {
        return dateTimeConnected;
    }

    public void setDateTimeConnected(Date dateTimeConnected) {
        this.dateTimeConnected = dateTimeConnected;
    }

    public int getRequestsSent() {
        return requestsSent;
    }

    public void setRequestsSent(int requestsSent) {
        this.requestsSent = requestsSent;
    }

    public int getSuccessRequests() {
        return successRequests;
    }

    public void setSuccessRequests(int successRequests) {
        this.successRequests = successRequests;
    }

    public int getFailureRequests() {
        return failureRequests;
    }

    public void setFailureRequests(int failureRequests) {
        this.failureRequests = failureRequests;
    }
    
    
    
}
