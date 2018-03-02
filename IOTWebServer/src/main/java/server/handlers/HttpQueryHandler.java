package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import server.utils.FileHelper;
import server.utils.HttpHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public abstract class HttpQueryHandler implements HttpHandler {
    public void handle(HttpExchange httpExchange) throws IOException {
        Request request = new Request();

        //get the requested url
        request.url = httpExchange.getRequestURI();

        //get the parameters key value pairs from the raw query
        HttpHelper.parseQuery(request.url.getRawQuery(), request.urlParameters);

        //recover the post body from the request
        request.postData = FileHelper.readFromInputStream(httpExchange.getRequestBody());

        //Handle the request in the derieved class and
        String response = onHandleRequest(request);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }

    protected String onHandleRequest(Request request) {
        return "Response from HttpQueryHandler. The derived class hasn't handled the request!!!";
    }
}
