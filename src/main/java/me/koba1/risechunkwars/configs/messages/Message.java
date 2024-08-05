package me.koba1.risechunkwars.configs.messages;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Message {

    private static final Map<String, Message> messages = new HashMap<String, Message>();

    public static final Message PREFIX = new Message("prefix", "&5RiseDuels > ");
    public static final Message NO_PERMISSION = new Message("no_permission", "&cEhhez nincs jogod");

    private final String key;
    private final String message;

    public Message(String key, String message) {
        this.key = key;
        this.message = message;

        messages.put(key, this);
    }
}
