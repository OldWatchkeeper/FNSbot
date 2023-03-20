package org.example.bot;

import org.example.Main;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class TelegramBot extends TelegramLongPollingBot {

    public TelegramBot() throws IOException {
    }

    @Override
    public String getBotUsername() {
        return "Your bot username";
    }

    @Override
    public String getBotToken() {
        return "YOUR Token";
    }
    private static boolean isNumeric (String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getChatId().toString();
        String textForMessage;
        long id_Utente = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();
        String firstName = update.getMessage().getFrom().getFirstName();
        String lastName = update.getMessage().getFrom().getLastName();
        long time = update.getMessage().getDate();
        long time2 = time*1000;
        Date l = new Date(time2);
        boolean isNumber = isNumeric (update.getMessage().getText());
        System.out.println("isNumber = " + isNumber);
        boolean InnExist = true;
        String newPath2 = Main.getMapFromFile(text);
        if  (newPath2 == null) {
            InnExist = false;
        }

        if (InnExist && isNumber && (update.getMessage().hasText() && update.getMessage().getText().toString().length() == 10)) {

            GameFrame frame;

            String lineSeparator = System.getProperty("line.separator");
            try { FileWriter writer = new FileWriter("D:\\logBotEGRUL.txt", true);
                  writer.write(lineSeparator + "userName: " + userName + ";" + "  firstName: " + firstName + ";" + "  lastName: " + lastName + ";" + "  id_user: " +  Long.toString(id_Utente) +  ";" + "  date: " + l + ";"  + "        message: " + update.getMessage().getText().toString() + lineSeparator);
                  writer.append("\n");
                  writer.flush();
                  System.out.println( "log записан");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                frame = new GameFrame(update.getMessage().getText().toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            try {
                MySAXParser.setVsegozapisey(0);
                textForMessage = String.valueOf(frame.vivodTekstaIzGameFrame);

                sendMessage.setChatId(chatId);
                sendMessage.setText(textForMessage);

                this.execute(sendMessage);

            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        }
        else {
            sendMessage.setChatId(chatId);
            sendMessage.setText("Неверный ИНН.");
            try {
                this.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
