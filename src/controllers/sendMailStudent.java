package controllers;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class sendMailStudent {


    public static void sendMail(String recepient) throws Exception {
        final String username = "pcpsbulletinboard@gmail.com";
        final String password = "Row$h@n5678";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pcpsbulletinboard@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(StudentRecordsController.mail));
            message.setSubject("Hello, there use the provided User id and password to login. Thank You");
            message.setText("Your user ID: "+StudentRecordsController.uid+" \n Your password is: "+StudentRecordsController.pass);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    }
