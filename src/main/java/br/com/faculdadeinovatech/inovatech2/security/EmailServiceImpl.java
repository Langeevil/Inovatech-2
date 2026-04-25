package br.com.faculdadeinovatech.inovatech2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech2.repository.EmailRepository;

@Service
public class EmailServiceImpl implements EmailRepository {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        String resetLink = "http://localhost:8082/trocar-senha?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Redefinição de Senha - Inovatech");
        message.setText("Clique no link abaixo para redefinir sua senha:\n" + resetLink);
        
        mailSender.send(message);
    }
}