package org.example.argen.service;

public interface EmailService {

    void send(String emailTo, String subject, String message);

    void sendNotify();
}
