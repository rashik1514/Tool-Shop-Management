package Server;

import Server.Controller.*;
import Server.Model.Database;

import java.io.IOException;

public class BackEnd {


    public static void main(String[] args) throws IOException {
        Server server = new Server(5050);
        server.communicate();
        server.close();
    }


}
