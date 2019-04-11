package Server;

import Server.Controller.*;
import java.io.IOException;

public class BackEnd {

    public static void main(String[] args) throws IOException {
        Server server = new Server(5050);
        server.run();
        server.close();
    }

}
