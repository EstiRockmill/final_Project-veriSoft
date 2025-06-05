import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.File;
import java.util.Properties;

public class EmailSender {


    private final String email;
    private final String password;

    public EmailSender(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public static void sendEmailWithAttachment(String to, String subject, String body, String attachmentPath) {
        final String username = "EstiRockmill@gmail.com";
        final String password = "xbne vwul tmtt buhi"; // אם ג'ימייל – צריך סיסמת אפליקציה

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // גוף ההודעה
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // מצרף
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File(attachmentPath));

            // הכל יחד
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Mail sent!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
