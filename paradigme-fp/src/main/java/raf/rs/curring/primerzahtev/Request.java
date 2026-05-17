package raf.rs.curring.primerzahtev;

import java.util.Map;

public class Request {

    private String method;
    private String host;
    private String port;
    private String path;
    private Map<String,String> requestParameters;

    public Request(String method, String host, String port, String path, Map<String, String> requestParameters) {
        this.method = method;
        this.host = host;
        this.port = port;
        this.path = path;
        this.requestParameters = requestParameters;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(Map<String, String> requestParameters) {
        this.requestParameters = requestParameters;
    }
}
