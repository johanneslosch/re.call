package com.johanneslosch.recall.client;

import org.java_websocket.client.WebSocketClient;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    static WebSocketClient client;
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Recall());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        try {
            client = new SocketClient(new URI("ws://localhost:8887"));
            client.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        while (true){
            if(!client.isOpen()){
                client.reconnect();
            }
        }
    }
}
