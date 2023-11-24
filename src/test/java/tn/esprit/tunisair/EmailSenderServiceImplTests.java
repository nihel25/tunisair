package tn.esprit.tunisair;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import tn.esprit.tunisair.service.EmailSenderServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class EmailSenderServiceImplTests {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;

    @Test
    void testSendEmail() {

        String to = "destinataire@example.com";
        String subject = "Sujet du message";
        String messageText = "Contenu du message";

        emailSenderService.sendEmail(to, subject, messageText);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

        ArgumentCaptor<SimpleMailMessage> argumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(argumentCaptor.capture());
        SimpleMailMessage sentMessage = argumentCaptor.getValue();

        assertEquals("nihel.riahi@esprit.tn", sentMessage.getFrom());
        assertEquals(to, sentMessage.getTo()[0]); // Assuming only one recipient
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(messageText, sentMessage.getText());
    }
}
