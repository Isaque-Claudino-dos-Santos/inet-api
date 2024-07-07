package  com.inet.app.middlewares;

import com.framework.servers.Middleware;
import com.framework.servers.ServerRequest;
import com.framework.servers.ServerResponse;

public class AuthMiddleware extends Middleware {

    public Boolean handle(ServerRequest request, ServerResponse response) {
        return next();
    }

}
