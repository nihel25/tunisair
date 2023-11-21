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
public class EmailSenderServiceImplTests {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailSenderServiceImpl emailSenderService;

    @Test
    void testSendEmail() {
        // Paramètres du test
        String to = "destinataire@example.com";
        String subject = "Sujet du message";
        String messageText = "Contenu du message";

        // Appel de la méthode sendEmail du service
        emailSenderService.sendEmail(to, subject, messageText);

        // Vérifier si la méthode send du mailSender a été appelée
        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));

        // Vous pouvez également vérifier le contenu du message si nécessaire
        ArgumentCaptor<SimpleMailMessage> argumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(argumentCaptor.capture());
        SimpleMailMessage sentMessage = argumentCaptor.getValue();

        assertEquals("nihel.riahi@esprit.tn", sentMessage.getFrom());
        assertEquals(to, sentMessage.getTo()[0]); // Assuming only one recipient
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(messageText, sentMessage.getText());
    }
}
