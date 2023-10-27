package tn.esprit.tunisair.Service;

public interface EmailSenderService {

    void sendEmail(String to, String subject, String message);
}

