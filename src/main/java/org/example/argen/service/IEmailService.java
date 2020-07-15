package org.example.argen.service;

public interface IEmailService {

    void send(String emailTo, String subject, String message);

    void sendNotify();
}
