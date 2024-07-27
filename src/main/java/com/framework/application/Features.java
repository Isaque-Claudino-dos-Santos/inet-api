package com.framework.application;

import com.framework.databases.Persist.Persist;
import com.framework.server.Server;

class Features {
    public final Env env = new Env();
    public final Server server = new Server();
    public final Persist database = new Persist();
}
