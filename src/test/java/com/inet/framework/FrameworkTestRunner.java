package com.inet.framework;


import com.inet.app.App;
import com.inet.framework.servers.Route;
import com.inet.framework.servers.Server;
import com.inet.framework.utils.Reflect;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FrameworkTestRunner extends Runner {

    public final Class<Object> testClass;

    public FrameworkTestRunner(Class<Object> testClass) {
        super();
        this.testClass = testClass;
    }

    @Override
    public Description getDescription() {
        return Description.createTestDescription(testClass, "Run test");
    }

    @Override
    public void run(RunNotifier runNotifier) {
        Server server = new Server("localhost", 3001);

        server.getServerRoutes().add(new Route("GET", "/", (req, res) -> {
            return res.json("Hello World");
        }));

        server.start();

        Object testInstance = Reflect.newInstance(testClass, null);


        for (Method method : testClass.getMethods()) {
            if (!method.isAnnotationPresent(Test.class)) continue;

            runNotifier.fireTestStarted(Description.createTestDescription(testClass, method.getName()));

            Reflect.invoke(method, testInstance);
        }
    }
}
