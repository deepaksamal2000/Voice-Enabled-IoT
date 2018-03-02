package server.handlers;


import java.io.IOException;

public class NLUQueryHandler extends HttpQueryHandler {

    @Override
    protected String onHandleRequest(Request request) {
        return "Response from NLU Module!!";
    }
}
