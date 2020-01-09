package controllers;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class JavaMailUtilFaculty {

    //method to append the message, set the email credentials and create connection with smtp gmail
    public static void sendMail(String recepient) throws Exception{
        System.out.println("preparing to send email");
        Properties properties = new Properties();
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        String UID = FacultyRecordsController.uid;
        String pass = FacultyRecordsController.passwrd;
        //Your gmail address
        String myAccountEmail = "pcpsbulletinboard@gmail.com";
        //Your gmail password
        String password = "Row$h@n5678";
        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient);
        Transport.send(message);
        System.out.println("Message sent successfully");

    }

    //prepare mail content including the user id and password which is accessed from faculty controller class
    private static Message prepareMessage(Session session,String myAccountEmail, String recepient){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Hello, there use the provided User id and password to login. Thank You");
            String pass = FacultyRecordsController.passwrd;
            String UID = FacultyRecordsController.uid;
            message.setText("Your user ID: "+UID+" \n Your password is: "+pass);
            return message;
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
