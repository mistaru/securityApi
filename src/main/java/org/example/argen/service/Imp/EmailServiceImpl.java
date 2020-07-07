package org.example.argen.service.Imp;

import org.example.argen.entity.Todo;
import org.example.argen.enums.Status;
import org.example.argen.repository.TodoRepository;
import org.example.argen.service.EmailService;
import org.example.argen.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static org.example.argen.constants.Constants.*;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TodoService todoService;

    private LocalDate date = LocalDate.now();

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        this.mailSender.send(mailMessage);
    }

    @Scheduled(cron = CRON)
    public void sendNotify() {

        for (Todo todo : todoService.ListIsNotDoneTodo(date)) {
            send(todo.getAuthor().getEmail(), SUBJECT_TODO_EXPIRATION,
                    String.format(TODO_EXPIRATION_MESSAGE, todo.getAuthor().getFullName(),
                            todo.getTitle()));
        }
    }
}
