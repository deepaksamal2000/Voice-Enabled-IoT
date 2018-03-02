package server.handlers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class Request {
    //url of the request
    public URI  url;

    //URL parameters are stored into the map
    public Map<String, Object> urlParameters = new HashMap<String, Object>();

    //Data
    public String postData;
}
