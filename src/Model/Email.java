package Model;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
    private String smtp;
    private String email_from;
    private String email_password;
    private String port;
    private boolean ssl;
    private boolean tls;
    private boolean debug;
    public Email(String smtp, String email_from, String email_password, String port, boolean ssl, boolean tls, boolean debug) {
    this.smtp = smtp;
    this.email_from = email_from;
    this.email_password = email_password;
    this.port = port;
    this.ssl = ssl;
    this.tls = tls;
    this.debug = debug;
}
    public void sendSimpleEmail(String email_to, String subject, String msg) {
        SimpleEmail email = new SimpleEmail();
        try {
            email.setDebug(debug);
            email.setHostName(smtp);
            email.addTo(email_to);
            email.setFrom(email_from);
            email.setAuthentication(email_from, email_password);
            email.setSubject(subject);
            email.setMsg(msg);
            email.setSSL(ssl);
            email.setTLS(tls);
            email.send();
        } catch (EmailException e) {
            System.out.println(e.getMessage());
        }
    }
}


