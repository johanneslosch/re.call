package com.johanneslosch.recall.server;

import java.net.InetSocketAddress;
import org.java_websocket.server.WebSocketServer;

public class Server {
  public static int port = 8887;
  public static void main(String[] args) {
    String host = "localhost";

    WebSocketServer server = new WebSockets(new InetSocketAddress(host, port));
    server.run();
  }
}
