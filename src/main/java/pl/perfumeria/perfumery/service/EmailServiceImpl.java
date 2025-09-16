package pl.perfumeria.perfumery.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.perfumeria.perfumery.domain.Order;
import pl.perfumeria.perfumery.domain.User;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    @Async
    public void sendOrderConfirmationEmail(User user, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setFrom("no-reply@perfumery.com");
        message.setSubject("Potwierdzenie zamówienia nr " + order.getId());

        String emailBody = String.format(
                "Witaj %s,\n\nDziękujemy za złożenie zamówienia w naszej perfumerii.\n\n" +
                        "Numer Twojego zamówienia: %d\n" +
                        "Całkowita kwota: %.2f zł\n\n" +
                        "Pozdrawiamy,\nZespół Perfumery",
                user.getFirstName(),
                order.getId(),
                order.getTotalPrice()
        );

        message.setText(emailBody);
        javaMailSender.send(message);
    }
}