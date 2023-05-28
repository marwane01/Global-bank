package com.example.globalbank.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transaction_H {
    private String id;
    private String sender_rib;
    private String receiver_rib;
    private float amount;
    private String reason;
    private String name_sender;
    private String name_receiver;
    private Date transactionDate;

    public Transaction_H() {
    }

    public Transaction_H(String sender_rib, String name_sender, String receiver_rib, String name_receiver, float amount, String reason, String date) {
        this.id = generateTransactionId();
        this.sender_rib = sender_rib;
        this.name_sender = name_sender;
        this.receiver_rib = receiver_rib;
        this.name_receiver = name_receiver;
        this.amount = amount;
        this.reason = reason;
        this.transactionDate = parseDate(date);
    }

    public String getName_sender() {
        return name_sender;
    }

    public void setName_sender(String name_sender) {
        this.name_sender = name_sender;
    }

    public String getName_receiver() {
        return name_receiver;
    }

    public void setName_receiver(String name_receiver) {
        this.name_receiver = name_receiver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender_rib() {
        return sender_rib;
    }

    public void setSender_rib(String sender_rib) {
        this.sender_rib = sender_rib;
    }

    public String getReceiver_rib() {
        return receiver_rib;
    }

    public void setReceiver_rib(String receiver_rib) {
        this.receiver_rib = receiver_rib;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
