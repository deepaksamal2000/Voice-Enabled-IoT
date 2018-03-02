package server.handlers;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.devices.metadata.DeviceMetadata;
import server.utils.HttpHelper;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DeviceMetadataManagementHandler implements HttpHandler {

    public void handle(HttpExchange he) throws IOException {

        URI requestedUri = he.getRequestURI();
        System.out.println("Query Request : " + requestedUri.toString());

        // parse request
        Map<String, Object> parameters = new HashMap<String, Object>();
        String query = requestedUri.getRawQuery();

        //get the parameters key value pairs from the raw query
        HttpHelper.parseQuery(query, parameters);

        //read list of the devices from the database and build a json to respond back
        String response = DeviceMetadata.getInstance().getMetadataJson();
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.toString().getBytes());
    }
}
