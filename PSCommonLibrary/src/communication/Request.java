package communication;

import java.io.Serializable;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Request implements Serializable {

    private int operation;
    private Object data;

    public Request() {
    }

    public Request(int operation, Object data) {
        this.operation = operation;
        this.data = data;
    }

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
    
}
