package com.dubfib.autoutils.events;

import java.util.HashMap;

import net.minecraft.client.Minecraft;

public class ChatEvent {
	// These hashmaps are populated on startup by the init() method in Main.java
	public static HashMap<String, String> goodLuckMessages = new HashMap<String, String>();
	public static HashMap<String, String> goodGameMessages = new HashMap<String, String>();

	public static void AutoGL(String message) {
		if (goodLuckMessages.containsKey(message.trim())) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + goodLuckMessages.get(message));
		}
	};

	public static void AutoGG(String message) {
		System.out.println("AutoGG called with message: " + message);
		if (goodGameMessages.containsKey(message.trim())) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac " + goodGameMessages.get(message));
		}
	};
};