package com.dubfib.autoutils.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class ChatEvent {
	public void AutoGL(String message) {
	    if (message.contains("The game starts in 2 seconds")) {
		   Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac glhf");
	    };
	};
	
	public void AutoGG(String message) {
	    if (message.contains("Reward Summary")) {
		   Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac gg");
	    };
	};
};