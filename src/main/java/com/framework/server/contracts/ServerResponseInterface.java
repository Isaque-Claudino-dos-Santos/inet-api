package com.framework.server.contracts;

import com.framework.server.enums.StatusEnum;

public interface ServerResponseInterface {
    public <T extends Object> Boolean json(T data);

    public ServerResponseInterface setStatus(StatusEnum status);

    public ServerResponseInterface setStatus(Integer status);

    public ServerResponseInterface setCharSet(String charset);

    public Boolean getWasAnswered();

    public void sendResponseHeaders(Integer status, Integer length);

    public void sendResponseHeaders(StatusEnum status, Integer length);
}
