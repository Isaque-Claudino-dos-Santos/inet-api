package  com.inet.app.middlewares;

import com.framework.server.Middleware;
import com.framework.server.ServerRequest;
import com.framework.server.ServerResponse;

public class AuthMiddleware extends Middleware {

    public Boolean handle(ServerRequest request, ServerResponse response) {
        return next();
    }

}
