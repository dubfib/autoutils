package com.dubfib.autoutils.events;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;

public class ChatEvent {
	// These lists are populated from the languages.json file,
	// which is done on startup.
	public static ArrayList<String> goodLuckMessages = new ArrayList<String>();
	public static ArrayList<String> goodGameMessages = new ArrayList<String>();

	public static void AutoGL(String message) {
		if (goodLuckMessages.contains(message)) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac glhf");
		}
	};

	public static void AutoGG(String message) {
		if (goodGameMessages.contains(message)) {
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/ac gg");
		}
	};
};