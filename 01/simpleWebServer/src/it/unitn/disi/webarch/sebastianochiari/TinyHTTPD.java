package it.unitn.disi.webarch.sebastianochiari;

import java.io.IOException;
import java.net.ServerSocket;

public class TinyHTTPD {

    public static void main(String[] argv) throws IOException {
        int port = 8000;
        if(argv.length > 0) {
            port = Integer.parseInt(argv[0]);
        }
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server is ready");
        while(true) {
            new TinyHTTPDConnection(serverSocket.accept());
        }
    }
}
