package com.framework.constants.server;

import java.util.HashMap;

import com.framework.constants.server.contracts.ServerRequestParamsInterface;

public class ServerRequestParams extends HashMap<String, Object> implements ServerRequestParamsInterface {
    public Integer getInt(String key) {
        Object data = get(key);

        if (!(data instanceof String)) {
            return null;
        }

        try {
            return Integer.parseInt((String) data);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String getString(String key) {
        Object data = get(key);

        if (!(data instanceof String)) {
            return null;
        }

        try {
            return String.valueOf(data);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
