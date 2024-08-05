package me.koba1.risechunkwars.adventure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

@Getter
@AllArgsConstructor
public class TextCommand {

    private String pureText;
    private Component text;
    private String command;

    public Component getComponent() {
        this.text.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND, this.command));
        return this.text;
    }

    public static TextCommand createCommand(String input) {
        String[] args = input.split("=");
        if(args.length == 2) {
            return new TextCommand("[" +input + "]", Component.text(args[0]), args[1]);
        }
        return null;
    }
 }
