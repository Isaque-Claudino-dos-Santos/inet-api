import com.framework.application.Application;
import com.inet.app.routes.PublicRouter;

import java.util.List;

Application app = new Application();

void main() {
//    app.settings.setRouter(List.of(PublicRouter.class));

    app.boot();
}
