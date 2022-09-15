package com.wordprocessor.worker;

import net.minidev.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.UUID;

@RabbitListener(queues = "q")
public class Receiver {

    @Autowired
    private EntryDao entryDao;

    private String uuidAsString;

    @RabbitHandler
    public void receiveAndSaveToDB(String input) {
        uuidAsString = input.substring(0, 36);
        input = input.replace(input.substring(0, 36), "");
        EntryDTO entryDTO = new EntryDTO(UUID.fromString(uuidAsString), generateWordCountJson(input));
        entryDao.saveEntry(entryDTO);
    }

    private String generateWordCountJson(String input) {
        HashMap<String, Integer> count = new HashMap<>();
        String[] splitInput = input.toLowerCase().replaceAll("[^a-zA-Z0-9-\\s+]", "").split("\\s+");
        for (String word: splitInput) {
            if (count.containsKey(word)) {
                count.replace(word, count.get(word) + 1);
            }
            else {
                count.put(word, 1);
            }
        }
        String countAsJson = new JSONObject(count).toString();
        return countAsJson;

    }

}
