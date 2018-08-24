package mailtools;

import filetools.FilesCollector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

public class MailTools {
    private final String user = "corgialwaysincode";
    private final String password = "rjhubhekzn";

    private Properties properties;

    private void propInit() {
        properties = new Properties();
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.user", user);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", true);
    }

    public void sendMsg(String pathWithFiles) {
        propInit();

        Session session = Session.getInstance(properties, null);
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user + "@gmail.com"));
            message.setSubject("Report");

            Multipart multipart = new MimeMultipart("alternative");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Hello");
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String textMessage = "Hello!";
            messageBodyPart.setContent(textMessage, "text/html");

            multipart.addBodyPart(messageBodyPart);

            ArrayList<String> files = FilesCollector.getAllFiles(pathWithFiles);

            for(String path:files) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(path);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(new File(path).getName());
                multipart.addBodyPart(messageBodyPart);
            }

            message.setContent(multipart);

            System.out.println("Sending");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", user, password);
            System.out.println("Transport: " + transport.toString());
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("Done");

        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
