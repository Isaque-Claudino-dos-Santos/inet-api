package com.framework.application;

import com.framework.server.Server;
import com.framework.server.ServerRequest;
import com.framework.server.annotations.Middleware;
import com.framework.server.annotations.Route;
import com.framework.server.contracts.lambdas.ResponseActionLambda;
import com.framework.server.response.ServerResponse;
import com.framework.server.response.modules.Response;
import com.framework.server.router.Router;
import com.framework.utils.ExceptionHandler;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Objects;

class Automatic {
    public void handleAutoInvokeController(Server server) {
        String rootDir = System.getProperty("user.dir");
        String dirPath = STR."\{rootDir}/src/main/java/com/inet/app/controllers";
        String controllersPackage = "com.inet.app.controllers";
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        com.framework.server.router.Route route = null;

        if (files == null) {
            System.err.println("Error on find directory");
            System.err.println(dirPath);
            return;
        }

        for (File file : files) {
            String fileName = file.getName().split("\\.")[0];
            String fileExtension = file.getName().split("\\.")[1];

            if (!fileExtension.equals("java")) {
                continue;
            }

            try {
                Class<?> objectClass = Class.forName(STR."\{controllersPackage}.\{fileName}");
                Router router = new Router(server);

                if (objectClass.isAnnotationPresent(Middleware.class)) {
                    Middleware annotations = objectClass.getAnnotation(Middleware.class);
                    router.middleware(annotations.value());
                }

                for (Method method : objectClass.getMethods()) {
                    int modifiers = method.getModifiers();
                    Parameter[] parameters = method.getParameters();

                    if (!(Modifier.isStatic(modifiers) && parameters.length == 2 && parameters[0].getType().equals(ServerRequest.class) && parameters[1].getType().equals(ServerResponse.class))) {
                        continue;
                    }

                    //----------------------------------------------
                    // @ANNOTATION - Route - IMPLEMENTATION
                    //----------------------------------------------

                    if (method.isAnnotationPresent(Route.class)) {
                        Route annotation = method.getAnnotation(Route.class);
                        ResponseActionLambda lambda = new ResponseActionLambda() {
                            public Response execute(ServerRequest request, ServerResponse response) throws Exception {
                                Object returned = method.invoke(objectClass, request, response);
                                return (Response) returned;
                            }
                        };

                        route = new com.framework.server.router.Route(annotation.method(), annotation.uri(), lambda);
                    }

                    //----------------------------------------------
                    // @ANNOTATION - Middleware - IMPLEMENTATION
                    //----------------------------------------------

                    if (route != null && method.isAnnotationPresent(Middleware.class)) {
                        Middleware annotation = method.getAnnotation(Middleware.class);

                        for (Class<? extends com.framework.server.Middleware> middleware : annotation.value()) {
                            route.addMiddleware(middleware);
                        }
                    }

                    if (route != null) {
                        server.getServerRoutes().add(route);
                    }
                }
            } catch (ClassNotFoundException exception) {
                ExceptionHandler.print(exception);
            }
        }
    }
}
