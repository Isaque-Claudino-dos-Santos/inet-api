package com.inet.framework.servers;

import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inet.framework.servers.enums.MethodEnum;
import com.inet.settings.Env;
import com.inet.framework.servers.contracts.RouteInterface;
import com.inet.framework.servers.contracts.lambdas.ResponseActionLambda;

public class Route implements RouteInterface {
    private String method = null;
    private String endPoint = null;
    private ResponseActionLambda action = null;
    public final RouteMiddlewares middlewares = new RouteMiddlewares();

    public Route(String endPoint) {
        this.endPoint = endPoint;
    }

    public Route(String method, String endPoint, ResponseActionLambda action) {
        this.method = method;
        this.endPoint = endPoint;
        this.action = action;
    }

    public Route(MethodEnum method, String endPoint, ResponseActionLambda action) {
        this.method = method.value;
        this.endPoint = endPoint;
        this.action = action;
    }

    public Route(String method, String endPoint) {
        this.method = method;
        this.endPoint = endPoint;
    }

    public Route(MethodEnum method, String endPoint) {
        this.method = method.value;
        this.endPoint = endPoint;
    }

    public Boolean idMatchWith(String value) {
        String regex = Env.PATTERN_ROUTE_PARAM.matcher(getId()).replaceAll("(\\\\w*)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public String getId() {
        return makeRouteId(endPoint, method);
    }

    public Boolean hasParam() {
        return Env.PATTERN_ROUTE_PARAM.matcher(endPoint).find();
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
        return endPoint;
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
