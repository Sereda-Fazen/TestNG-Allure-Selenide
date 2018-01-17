package mail;

import com.sun.mail.util.MailSSLSocketFactory;
import config.Values;

import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * Created by mycola on 07.05.2017.
 */
public class PropertiesEmail {
    String host = "imap.gmail.com";
    String user = Values.email + Values.email_domain;
    String password = Values.email_pass;
    int port = 993;

    public Properties setServerProperties() throws GeneralSecurityException {

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);

        Properties properties = new Properties();
        properties.put("mail.imap.host", host);
        properties.put("mail.imap.port", port);
        properties.put("mail.imap.starttls.enable", "true");
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.ssl.trust", "*");
        properties.put("mail.imap.ssl.socketFactory", sf);
        properties.put("mail.imap.ssl.enable", "true");
        return properties;
    }

}
