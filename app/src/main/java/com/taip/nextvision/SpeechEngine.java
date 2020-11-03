package com.taip.nextvision;

public interface SpeechEngine {
    public String speechToText();

    public void textToSpeech(String text);
}
