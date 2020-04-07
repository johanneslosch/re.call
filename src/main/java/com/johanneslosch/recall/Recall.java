package com.johanneslosch.recall;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Recall  extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            //message content
            update.getMessage().getText();
        }
    }

    @Override
    public String getBotUsername() {
        return "re_call_bot";
    }

    @Override
    public String getBotToken() {
        return RecallSecret.TOKEN;
    }
}