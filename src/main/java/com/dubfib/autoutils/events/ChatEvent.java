package com.dubfib.autoutils.events;

import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public class ChatEvent {
	public void AutoGL(String message) {
		HashMap<String, String> messages = new HashMap<String, String>();
		messages.put("The game starts in 2 seconds", "glhf");
		messages.put("Th' voyage begins in 2 seconds", "glhf");
		
		for (String i: messages.keySet()) {
			if (message.contains(i)) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + messages.get(i));
			};
		};
	};
	
	public void AutoGG(String message) {
		HashMap<String, String> messages = new HashMap<String, String>();
		messages.put("Reward Summary", "gg");
		messages.put("Treasure Summary", "gg");
		
		for (String i: messages.keySet()) {
			if (message.contains(i)) {
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + messages.get(i));
			};
		};
	};
};