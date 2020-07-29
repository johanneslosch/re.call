package com.johanneslosch.recall.client;

import org.java_websocket.client.WebSocketClient;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tech.jslol.javautillities.data.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class Client {

    static WebSocketClient WSClient;
    public static void main(String[] args) {
        System.getProperties().put("proxySet", "true");

        System.getProperties().put("socksProxyHost", "212.62.95.45");
        //requires a Socks4 proxy server
        System.getProperties().put("socksProxyPort", "1080");
        try {
            WSClient = new SocketClient(new URI("ws://localhost:8887"));
            WSClient.connect();
            if (WSClient.isClosed()) {
                Logger.error("Web Socket server not running!");
                System.err.println("Web Socket server not running!");
                System.exit(1);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Recall());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        int counter = 0;
        while (true) {
            if (!WSClient.isOpen()) {
                WSClient.reconnect();
                counter++;
                if (counter == 10) {
                    WSClient.close();
                    Logger.error("Web Socket server not running!");
                    System.err.println("Web Socket server not running!");
                    System.exit(1);
                }
            }
        }
    }
}
