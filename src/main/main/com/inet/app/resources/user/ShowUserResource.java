package  com.inet.app.resources.user;

import  com.inet.app.models.data.UserData;

public class ShowUserResource {
    class Data {
        public String name;
        public String email;
    }

    public Boolean success = true;
    public Data data = new Data();

    public ShowUserResource(UserData user) {
        data.name = user.name;
        data.email = user.email;
    }
}
