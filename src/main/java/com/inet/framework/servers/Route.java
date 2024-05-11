package com.inet.framework.servers;

import java.util.function.Consumer;
import com.inet.framework.servers.enums.MethodEnum;
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

    public static String makeRouteKey(String arg0, String arg1) {
        return arg0.toLowerCase().trim() + "-" + arg1.toLowerCase().trim();
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

    public String getEndPoint() {
        return endPoint;
    }

    public Consumer<ServerRequest> exec(Consumer<ServerRequest> action) {
        return action;
    }

    public String getRouteKey() {
        return makeRouteKey(endPoint, method);
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
}
