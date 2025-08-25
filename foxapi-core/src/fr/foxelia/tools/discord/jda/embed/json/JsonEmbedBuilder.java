package fr.foxelia.tools.discord.jda.embed.json;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.sql.Timestamp;

/**
 * This class is used to create an discord embed from a JSON file
 * <br>It use the GSON library to parse the JSON file and give a formatted embed for the JavaDiscordAPI
 * <br>License : CC BY-SA 4.0
 * <br>Author : Zarinoow, Foxelia
 * @version 1.0
 */
public class JsonEmbedBuilder {

    private final JsonObject jsonEmbed;

    /**
     * This constructor is used to create an embed from a JSON formatted {@link java.lang.String}
     * @param jsonContent The JSON content of the embed
     * @throws JsonSyntaxException If the JSON content is not valid
     */
    public JsonEmbedBuilder(String jsonContent) throws JsonSyntaxException {
        this(JsonParser.parseString(jsonContent).getAsJsonObject());
    }

    /**
     * This constructor is used to create an embed from a {@link com.google.gson.JsonObject}
     * @param jsonEmbed The JSON object of the embed
     */
    public JsonEmbedBuilder(JsonObject jsonEmbed) {
        this.jsonEmbed = jsonEmbed;
    }

    /**
     * Call this method to build the embed
     * @return The built embed
     * @see MessageEmbed
     */
    public MessageEmbed build() {
        /*
         * Constants needed for to build the embed
         */
        EmbedBuilder embedBuilder = new EmbedBuilder();
        // Set the author
        if(jsonEmbed.has("author")) {
            JsonObject author = jsonEmbed.get("author").getAsJsonObject();
            embedBuilder.setAuthor(editString(author.get("name").getAsString()),
                    editURL(author.get("url").getAsString()),
                    editURL(author.get("icon_url").getAsString()));
        }

        // Set the title
        if(jsonEmbed.has("title")) {
            embedBuilder.setTitle(editString(jsonEmbed.get("title").getAsString()));
        }

        // Set the description
        if(jsonEmbed.has("description")) {
            embedBuilder.setDescription(editString(jsonEmbed.get("description").getAsString()));
        }

        // Set the url
        if(jsonEmbed.has("url")) {
            embedBuilder.setUrl(editURL(jsonEmbed.get("url").getAsString()));
        }

        // Set the image
        if(jsonEmbed.has("image")) {
            JsonObject image = jsonEmbed.get("image").getAsJsonObject();
            embedBuilder.setImage(editURL(image.get("url").getAsString()));
        }

        // Set the thumbnail
        if(jsonEmbed.has("thumbnail")) {
            JsonObject thumbnail = jsonEmbed.get("thumbnail").getAsJsonObject();
            embedBuilder.setThumbnail(editURL(thumbnail.get("url").getAsString()));
        }

        // Set the color
        if(jsonEmbed.has("color")) {
            embedBuilder.setColor(Color.decode(jsonEmbed.get("color").getAsString()));
        }

        // Set the footer
        if(jsonEmbed.has("footer")) {
            JsonObject footer = jsonEmbed.get("footer").getAsJsonObject();
            String text = (footer.has("text") && !footer.get("text").isJsonNull()) ? editString(footer.get("text").getAsString()) : null;
            String icon = (footer.has("icon_url") && !footer.get("icon_url").isJsonNull()) ? editURL(footer.get("icon_url").getAsString()) : null;
            embedBuilder.setFooter(text, icon);
        }

        // Set the timestamp
        if(jsonEmbed.has("timestamp")) {
            embedBuilder.setTimestamp(new Timestamp(jsonEmbed.get("timestamp").getAsLong()).toInstant());
        }

        // Set the fields
        if(jsonEmbed.has("fields")) {
            for(int i = 0; i < jsonEmbed.get("fields").getAsJsonArray().size(); i++) {
                JsonObject field = jsonEmbed.get("fields").getAsJsonArray().get(i).getAsJsonObject();
                boolean inline = false;
                if(field.has("inline")) inline = field.get("inline").getAsBoolean();
                embedBuilder.addField(editString(field.get("name").getAsString()),
                        editString(field.get("value").getAsString()),
                        inline);
            }
        }

        return embedBuilder.build();
    }

    /**
     * This method is called for each string field in the JSON file
     * You can edit the String before the line is added to the embed
     * @apiNote To edit the method, you can override it in a subclass
     * @param string The String to edit
     * @return The edited String
     */
    public String editString(String string) {
        return string;
    }

    /**
     * This method is called for each URL field in the JSON file
     * You can edit the URL before the line is added to the embed
     * @apiNote To edit the method, you can override it in a subclass
     * @param url The URL to edit
     * @return The edited URL
     */
    public String editURL(String url) {
        return url;
    }

}
