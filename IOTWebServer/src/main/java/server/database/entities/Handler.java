package server.database.entities;

import server.database.queries.DatabaseQuery;

public final class Handler extends DatabaseQuery {
    private String handlerName;
    private Integer commandId;

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public Integer getCommandId() {
        return commandId;
    }

    public void setCommandId(Integer commandId) {
        this.commandId = commandId;
    }
}
