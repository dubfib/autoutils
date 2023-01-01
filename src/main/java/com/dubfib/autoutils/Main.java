package com.dubfib.autoutils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.dubfib.autoutils.config.Config;
import com.dubfib.autoutils.events.ChatEvent;
import com.google.gson.Gson;

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
        InputStream stream = getClass().getResourceAsStream("languages.json");

        // Parse the JSON using Gson
        Gson gson = new Gson();
        Config config = gson.fromJson(new InputStreamReader(stream), Config.class);

        // Ensure that the HashMaps are empty, if this method is ever (somehow)
        // called more than once.
        ChatEvent.goodLuckMessages.clear();
        ChatEvent.goodLuckMessages.clear();

        // Add the translations to the HashMap
        for (HashMap<String, String> translation : config.translations) {
            var keyCollection = translation.keySet().toArray();
            ChatEvent.goodLuckMessages.put((String) keyCollection[0], translation.get(keyCollection[0]));
            ChatEvent.goodLuckMessages.put((String) keyCollection[1], translation.get(keyCollection[1]));
        }

        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    };

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        ChatEvent.AutoGL(event.message.getUnformattedText());
        ChatEvent.AutoGG(event.message.getUnformattedText());
    };
};