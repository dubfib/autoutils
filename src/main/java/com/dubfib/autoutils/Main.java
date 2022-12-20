package com.dubfib.autoutils;

import com.dubfib.autoutils.config.Config;
import com.dubfib.autoutils.events.ChatEvent;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
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
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    };
    
	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
            new ChatEvent().AutoGL(event.message.getUnformattedText());
	    new ChatEvent().AutoGG(event.message.getUnformattedText());
	};
};
