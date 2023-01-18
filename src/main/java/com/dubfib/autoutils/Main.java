package com.dubfib.autoutils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;

import com.dubfib.autoutils.config.Config;
import com.dubfib.autoutils.events.ChatEvent;
import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(name = Config.NAME, modid = Config.ID, version = Config.VERSION)
public class Main {
    public static Pattern textFormattingPattern = Pattern.compile("§[0-9a-fk-or]");

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Read the languages file as a stream
        // The languages.json file contains all of the good luck and good game messages
        // in different languages

        InputStream stream;
        try {
            stream = Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("autoutils", "languages.json"))
                    .getInputStream();
        } catch (IOException err) {
            err.printStackTrace();
            return;
        }

        // Parse the JSON using Gson
        Config config = new Gson().fromJson(new InputStreamReader(stream), Config.class);

        // Ensure that the HashMaps are empty, if this method is ever (somehow)
        // called more than once.
        ChatEvent.goodLuckMessages.clear();
        ChatEvent.goodLuckMessages.clear();

        // Add the translations to the HashMap
        ChatEvent.goodLuckMessages.addAll(config.goodLuckTranslations);
        ChatEvent.goodGameMessages.addAll(config.goodGameTranslations);

        LogManager.getLogger(Config.NAME).info("Loaded " + ChatEvent.goodLuckMessages.size()
                + " good luck messages and " + ChatEvent.goodGameMessages.size() + " good game messages");

        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    };

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        ChatEvent.AutoGL(getPlainText(event.message));
        ChatEvent.AutoGG(getPlainText(event.message));
    };

    // Use Regex to remove all of the text formatting codes,
    // leaving only the plaintext.
    private String getPlainText(IChatComponent message) {
        String baseString = message.getUnformattedText();
        Matcher m = textFormattingPattern.matcher(baseString);
        baseString = m.replaceAll("");
        baseString = baseString.replace("  ", "");
        return baseString;
    }
};