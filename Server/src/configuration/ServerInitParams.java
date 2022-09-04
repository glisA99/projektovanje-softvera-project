/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package configuration;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class ServerInitParams {

    private int port;

    public ServerInitParams() {
    }

    public ServerInitParams(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
