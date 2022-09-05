package communication;

import java.io.Serializable;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Response implements Serializable {

    private ResponseType responseType;
    private Object response;
    private Exception exception;

    public Response() {
    }

    public Response(ResponseType responseType, Object response, Exception exception) {
        this.responseType = responseType;
        this.response = response;
        this.exception = exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
    
    
    
}
