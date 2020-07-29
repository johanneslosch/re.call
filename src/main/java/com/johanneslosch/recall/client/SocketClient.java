package com.johanneslosch.recall.client;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;
import tech.jslol.javautillities.data.Logger;

import java.net.URI;
import java.nio.ByteBuffer;

public class SocketClient extends WebSocketClient {

    public SocketClient(URI serverUri, Draft draft) {
        super(serverUri, draft);
    }

    public SocketClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        send(String.format("Connect from %s", handshakedata.getHttpStatus()));
        System.out.println("new connection opened");
        Logger.msg("new connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.printf("closed with exit code %d additional info: %s%n", code, reason);
        Logger.msg(String.format("closed with exit code %d additional info: %s", code, reason));
    }

    @Override
    public void onMessage(String message) {
        System.out.printf("received message: %s%n", message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        System.out.println("received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        System.err.printf("an error occurred:%s%n", ex);
        Logger.error(String.format("an error occurred:%s", ex));
    }
}