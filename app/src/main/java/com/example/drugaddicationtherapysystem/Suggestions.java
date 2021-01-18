package com.example.drugaddicationtherapysystem;

public class Suggestions {
    private String sender;
    private String suggestion;
    private String senderImage;

    public Suggestions(){

    }




    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setSenderImage(String senderImage) {
        this.senderImage = senderImage;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSender() {
        return sender;
    }
    public String getSenderImage() {
        return senderImage;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public Suggestions(String sender, String suggestion,String senderImage) {
        this.sender = sender;
        this.suggestion = suggestion;
        this.senderImage = senderImage;
    }
}
