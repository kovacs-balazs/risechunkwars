package me.koba1.risechunkwars.configs.messages;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MessageHolder {

    private String message;
    private List<String> messages;

    public MessageHolder(String message) {
        this.message = message;
    }

    public MessageHolder(List<String> messages) {
        this.messages = messages;
    }

    public void replace(String target, String replacement) {
        if(this.message != null) {
            this.message = this.message.replace(target, replacement);
            return;
        }
        this.messages = replaceList(target, replacement);
    }

    private List<String> replaceList(String target, String replacement) {
        List<String> str = new ArrayList<>();
        for (String s : this.messages) {
            str.add(s.replace(target, replacement));
        }
        return str;
    }
}
