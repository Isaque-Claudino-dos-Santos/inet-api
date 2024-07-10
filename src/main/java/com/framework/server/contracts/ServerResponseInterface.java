package com.framework.server.contracts;

import com.framework.server.response.modules.CsvResponse;
import com.framework.server.response.modules.JsonResponse;

public interface ServerResponseInterface {
    public CsvResponse csv(String data, Integer status);

    public JsonResponse json(Object data, Integer status);
}
