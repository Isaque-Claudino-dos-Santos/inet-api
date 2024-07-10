package com.framework.constants.server.contracts;

import java.util.Map;

public interface ServerRequestParamsInterface extends Map<String, Object> {
    public Integer getInt(String key);

    public String getString(String key);
}
