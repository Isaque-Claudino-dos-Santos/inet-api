package com.framework.server.router;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.framework.server.contracts.RouteInterface;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.server.RouteMiddlewares;
import com.framework.server.enums.MethodEnum;
import com.inet.settings.Env;

public class Route implements RouteInterface {
    private String method = null;
    private String endpoint = null;
    private ResponseActionLambda action = null;
    public final RouteMiddlewares middlewares = new RouteMiddlewares();

    public Route(String method, String endpoint, ResponseActionLambda action) {
        this.method = method;
        this.endpoint = endpoint;
        this.action = action;
    }

    public Boolean idMatchWith(String value) {
        String regex = Env.PATTERN_ROUTE_PARAM.matcher(getId()).replaceAll("(\\\\w*)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public String getId() {
        return makeRouteId(endpoint, method);
    }

    public Boolean hasParam() {
        return Env.PATTERN_ROUTE_PARAM.matcher(endpoint).find();
    }

    public String getMethod() {
        return method;
    }

    public Route setMethod(MethodEnum method) {
        this.method = method.value;
        return this;
    }

    public Route setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUri() {
        return endpoint;
    }

    public Route setResponseAction(ResponseActionLambda action) {
        this.action = action;
        return this;
    }

    public ResponseActionLambda getResponseAction() {
        return action;
    }

    public RouteMiddlewares getMiddlewares() {
        return middlewares;
    }

    public static String makeRouteId(String arg0, String arg1) {
        return arg0.toLowerCase().trim() + "-" + arg1.toLowerCase().trim();
    }
}
