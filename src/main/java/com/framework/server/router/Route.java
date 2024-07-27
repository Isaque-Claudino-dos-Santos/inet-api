package com.framework.server.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.framework.server.Middleware;
import com.framework.server.contracts.RouteInterface;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.server.RouteMiddlewares;
import com.framework.server.enums.MethodEnum;
import com.framework.utils.ExceptionHandler;

public class Route implements RouteInterface {
    private String method = null;
    private String endpoint = null;
    private ResponseActionLambda action = null;
    public final RouteMiddlewares middlewares = new RouteMiddlewares();
    private final Pattern PATTERN_ROUTE_PARAM = Pattern.compile("\\{\\w*}", Pattern.MULTILINE);

    public Route(String method, String endpoint, ResponseActionLambda action) {
        this.method = method;
        this.endpoint = endpoint;
        this.action = action;
    }

    public Boolean idMatchWith(String value) {
        String regex = PATTERN_ROUTE_PARAM.matcher(getId()).replaceAll("(\\\\w*)");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    @Override
    public void addMiddleware(Class<? extends Middleware> middlewareClass) {
        try {
            Constructor<? extends Middleware> middlewareConstructor = middlewareClass.getDeclaredConstructor();
            middlewares.add(middlewareConstructor.newInstance());
        } catch (NoSuchMethodException |
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException exception) {
            ExceptionHandler.print(exception);
        }
    }

    @Override
    public void addMiddleware(Middleware middlewareClass) {
        middlewares.add(middlewareClass);
    }

    public String getId() {
        return makeRouteId(endpoint, method);
    }

    public Boolean hasParam() {
        return PATTERN_ROUTE_PARAM.matcher(endpoint).find();
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
