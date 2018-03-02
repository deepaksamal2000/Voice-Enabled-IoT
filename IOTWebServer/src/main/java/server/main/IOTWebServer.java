package server.main;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import server.handlers.DeviceMetadataManagementHandler;
import server.handlers.NLUQueryHandler;


public class IOTWebServer {


    public static void main(String[] args) throws Exception {

        //Service PORT number
        int port = 9000;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());

        //register the device management handler for all device related query
        server.createContext("/deviceMetadata", new DeviceMetadataManagementHandler());

        //register the NLU query handler
        server.createContext("/nlu", new NLUQueryHandler());

        server.setExecutor(null); // creates a default executor

        System.out.println("Starting IOT Web Server at port : " + port);
        server.start();

        System.out.println("IOT Web Server Started");
    }

    static class MyHandler implements HttpHandler {

        public void handle(HttpExchange t) throws IOException {
            String response = "This is the IOT Web Server for manging various remote devices";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
