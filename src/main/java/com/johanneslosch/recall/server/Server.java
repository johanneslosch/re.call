package com.johanneslosch.recall.server;

import com.johanneslosch.recall.util.ConfigReader;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server {//ConfigReader.read("data", "server_config", "WSport")


  public static void main(String[] args) {
    String host = "localhost";
    ConfigReader.read("data", "server_config", "WSurl");

    WebSocketServer server = new WebSockets(new InetSocketAddress("localhost", 8887));
    server.run();
  }
}
