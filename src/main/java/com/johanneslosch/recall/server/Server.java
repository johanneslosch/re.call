package com.johanneslosch.recall.server;

import com.johanneslosch.recall.util.ConfigReader;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class Server {
  public static String host = ConfigReader.read("data", "server_config", "WSurl");
  public static int port = Integer.parseInt(ConfigReader.read("data", "server_config", "WSport"));

  public static void main(String[] args) {
    WebSocketServer server = new WebSockets(new InetSocketAddress(host, port));
    server.run();
  }
}
