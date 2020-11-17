package com.taip.nextvision;

public interface SpeechEngine {
    public void startListening();

    public void stopListening();


    public void textToSpeech(String text);
}
