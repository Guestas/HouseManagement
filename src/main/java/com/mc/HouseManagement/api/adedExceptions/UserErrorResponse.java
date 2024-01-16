package com.mc.HouseManagement.api.adedExceptions;

public class UserErrorResponse {
    private int stat;
    private String message;
    private long timeStamp;

    public UserErrorResponse(){

    }
    public UserErrorResponse(int stat, String message, long timeStamp) {
        this.stat = stat;
        this.message = message;
        this.timeStamp = timeStamp;

    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
