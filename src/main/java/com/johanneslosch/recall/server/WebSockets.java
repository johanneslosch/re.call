package com.johanneslosch.recall.server;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class WebSockets extends WebSocketServer {

  public WebSockets(InetSocketAddress address) { super(address); }

  @Override
  public void onOpen(WebSocket conn, ClientHandshake handshake) {
    conn.send("Welcome to the server!"); // This method sends a message to the
                                         // new client
    broadcast(String.format(
        "new connection: %s",
        handshake.getResourceDescriptor())); // This method sends a message to
                                             // all clients connected
    // System.out.printf("new connection to %s%n",
    // conn.getRemoteSocketAddress());
  }

  @Override
  public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    System.out.printf("closed %s with exit code %d additional info: %s%n",
                      conn.getRemoteSocketAddress(), code, reason);
  }

  @Override
  public void onMessage(WebSocket conn, String message) {
    HandleMessages.message(message);
    System.out.printf("received message from %s: %s%n",
                      conn.getRemoteSocketAddress(), message);
  }

  @Override
  public void onMessage(WebSocket conn, ByteBuffer message) {
    HandleMessages.message(new String(message.array(), StandardCharsets.US_ASCII));
    System.out.printf("received ByteBuffer from %s%n",
            conn.getRemoteSocketAddress());
  }

  @Override
  public void onError(WebSocket conn, Exception ex) {
    System.err.printf("an error occurred on connection %s:%s%n",
                      conn.getRemoteSocketAddress(), ex);
  }

  @Override
  public void onStart() {
    System.out.println("server started successfully");
  }
}
