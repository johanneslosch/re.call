package com.johanneslosch.recall.client;

import com.johanneslosch.recall.util.ConfigReader;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Recall extends TelegramLongPollingBot {
  @Override
  public void onUpdateReceived(Update update) {
    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      // message content
      update.getMessage().getText();
      Client.WSClient.send(String.format(
              "RECEIVED: message: %s -> from: %s", update.getMessage().getText(),
              update.getMessage().getChat().getFirstName()));
    }
  }

  @Override
  public String getBotUsername() {
    return ConfigReader.read("data", "config", "BotName");
  }

  @Override
  public String getBotToken() {
    return ConfigReader.read("data", "config", "BotToken");
  }
}
