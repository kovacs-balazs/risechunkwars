package me.koba1.risechunkwars.configs.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public class MessageBuilder {

    private MessageHolder message;

    public MessageBuilder setPlayer(Player player) {
        this.message.replace("%player%", player.getName());
        return this;
    }

    public MessageBuilder setTarget(Player target) {
        this.message.replace("%target%", target.getName());
        return this;
    }

    public MessageBuilder setPrefix() {
        this.message.replace("%prefix%", Message.PREFIX.getMessage());
        return this;
    }

    public void send(Player player) {

    }
}
