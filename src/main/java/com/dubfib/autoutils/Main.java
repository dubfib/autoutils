package com.dubfib.autoutils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

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
        Gson gson = new Gson();
        Config config = gson.fromJson(new InputStreamReader(stream), Config.class);

        // Ensure that the HashMaps are empty, if this method is ever (somehow)
        // called more than once.
        ChatEvent.goodLuckMessages.clear();
        ChatEvent.goodLuckMessages.clear();

        // Add the translations to the HashMap
        for (HashMap<String, String> translation : config.translations) {
            Object[] keyCollection = translation.keySet().toArray();
            ChatEvent.goodLuckMessages.put((String) keyCollection[0], translation.get(keyCollection[0]));
            System.out.println("Added " + keyCollection[0] + " to the good luck messages");

            ChatEvent.goodLuckMessages.put((String) keyCollection[1], translation.get(keyCollection[1]));
            System.out.println("Added " + keyCollection[1] + " to the good game messages");
        }

        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    };

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        ChatEvent.AutoGL(getPlainText(event.message));
        ChatEvent.AutoGG(getPlainText(event.message));
    };

    private String getPlainText(IChatComponent component) {
        String baseString = component.getUnformattedTextForChat();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = baseString.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // If this is not the last character in the string
            if (i < chars.length - 1) {
                // If this is a formatting code
                // Or a double space
                if ((c == '§' && "0123456789abcdefklmnor".indexOf(chars[i + 1]) > -1)
                        || c == ' ' && chars[i + 1] == ' ') {
                    // This skips the current character and the next character (the formatting code)
                    i++;
                    continue;
                }
            } else if (c == ' ') {
                // If the last character is a space, skip it
                continue;
            }

            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
};