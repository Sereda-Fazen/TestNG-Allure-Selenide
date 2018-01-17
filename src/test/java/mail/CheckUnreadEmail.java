package mail;

import config.Values;
import ru.yandex.qatools.allure.annotations.Step;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * Created by mycola on 07.05.2017.
 */
public class CheckUnreadEmail {


    @Step("Проверка получения писем")
    public static String checkUnreadEmail(String text){

        String link = "";
        String body = "";
        int startLink = 0;
        int stopLink = 0;
        try{
            PropertiesEmail propertiesEmail = new PropertiesEmail();
            Properties props = propertiesEmail.setServerProperties();
            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore();
            store.connect(propertiesEmail.host, propertiesEmail.user, propertiesEmail.password);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) +
                    " Unread messages: " + folder.getUnreadMessageCount());
            FlagTerm flag = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message[] unreadMessage = folder.search(flag);
            for (int i = 0, n = unreadMessage.length; i < n; i++){
                Message message = unreadMessage[i];
                Object content = message.getContent();
                message.setFlag(Flags.Flag.DELETED, true);
                if (content instanceof String)
                {
                    body = (String)content;
                }
                else if (content instanceof Multipart)
                {
                    Multipart mp = (Multipart)content;
                    body = mp.getBodyPart(0).getContent().toString();
                }

                if (body.contains(text)) {

                    //                  System.out.println(body.substring(190,275));

                    startLink = body.indexOf(Values.mail_link);
                    if (startLink < 1) continue;
                    stopLink = body.indexOf("Your username,");
                    link = body.substring(startLink, stopLink);
                    if (link.length() > 0) break;
                    System.out.println(link);
                    message.setFlag(Flags.Flag.DELETED, true);

                }

            }
            folder.close(false);
            store.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return link;
    }

    @Step("Cleaning the mailbox")
    public static void cleaningMailbox(){
        try{
            PropertiesEmail propertiesEmail = new PropertiesEmail();
            Properties props = propertiesEmail.setServerProperties();
            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore();
            store.connect(propertiesEmail.host, propertiesEmail.user, propertiesEmail.password);
            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_WRITE);
            Message[] messages = folder.getMessages();
            for (Message message : messages) {
                message.setFlag(Flags.Flag.DELETED, true);
            }
            folder.close(false);
            store.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


