package com.johanneslosch.recall.server;

import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8887;

        WebSocketServer server = new WebSockets(new InetSocketAddress(host, port));
        server.run();
    }
}
