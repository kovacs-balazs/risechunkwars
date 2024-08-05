package me.koba1.risechunkwars.adventure;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentBuilder;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class TextBuilder {

    private static final LegacyComponentSerializer legacySerializer = LegacyComponentSerializer.legacyAmpersand();
    private static final PlainTextComponentSerializer plainSerializer = PlainTextComponentSerializer.plainText();

    private String message;
    private Component component;
    public TextBuilder(String message) {
        this.message = message;
        this.component = legacySerializer.deserialize(this.message);

        for (TextCommand command : findCommands(this.message)) {
            replace(command.getPureText(), command.getComponent());
        }
    }

    public TextBuilder replace(String target, Component replacement) {
        List<Component> parts = new ArrayList<>();
        splitAndReplace(component, target, replacement, parts);
        this.component = Component.empty().children(parts);
        return this;
    }

    private void splitAndReplace(Component current, String target, Component replacement, List<Component> parts) {
        if (current instanceof TextComponent textComp) {
            String content = ((TextComponent) current).content();
            int index = content.indexOf(target);
            if (index == -1) {
                parts.add(current);
            } else {
                Component before = textComp.content(content.substring(0, index));
                parts.add(preserveEvents(before, current));
                parts.add(replacement);
                Component remaining = textComp.content(content.substring(index + target.length()));
                splitAndReplace(preserveEvents(remaining, current), target, replacement, parts);
            }
        } else {
            parts.add(current);
        }
    }

    private Component preserveEvents(Component component, Component original) {
        ClickEvent clickEvent = original.clickEvent();
        HoverEvent<?> hoverEvent = original.hoverEvent();

        if (clickEvent != null) {
            component = component.clickEvent(clickEvent);
        }
        if (hoverEvent != null) {
            component = component.hoverEvent(hoverEvent);
        }

        return component;
    }


    public List<TextCommand> findCommands(String input) {
        String regex = "\\[((?:[^\\[\\]]|\\[(?:[^\\[\\]]|\\[[^\\[\\]]*\\])*\\])*?)\\]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        List<TextCommand> commands = new ArrayList<>();
        while (matcher.find()) {
            commands.add(TextCommand.createCommand(matcher.group(1)));
        }
        return commands;
    }
}
