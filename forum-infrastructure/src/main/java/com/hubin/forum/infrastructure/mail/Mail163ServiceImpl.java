package com.hubin.forum.infrastructure.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.exception.BizException;
import com.hubin.forum.domain.entity.Message;
import com.hubin.forum.domain.service.MailService;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * @author Hubin
 * @create 2021/11/4
 * @desc
 **/
@Data
@ConfigurationProperties(prefix = "custom-config.mail.smtp")
@Component
public class Mail163ServiceImpl implements MailService {

    private String host;

    private String port;

    private String socketFactoryPort;

    private String auth;

    private String account;

    private String password;

    private String fromAddress;

    private String fromName;

    /**
     * 发送html内容
     * @param mailMessage
     */
    @Override
    public void sendHtml(Message mailMessage) {
        sendMail(mailMessage, (message) -> {
            Multipart mainPart = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            try {
                html.setContent(mailMessage.getContent(), "text/html; charset=utf-8");
                mainPart.addBodyPart(html);
                message.setContent(mainPart);
            } catch (MessagingException e) {
                throw new BizException(ErrorCodeEn.MESSAGE_SYSTEM_MAIL_SEND_FAIL);
            }
        });
    }

    /**
     * 发送文本内容
     * @param mailMessage
     */
    @Override
    public void sendText(Message mailMessage) {
        sendMail(mailMessage, (message) -> {
            try {
                message.setText(mailMessage.getContent());
            } catch (MessagingException e) {
                throw new BizException(ErrorCodeEn.MESSAGE_SYSTEM_MAIL_SEND_FAIL);
            }
        });
    }

    private void sendMail(Message emailMessage, Consumer<MimeMessage> consumer) {
        try {
            Session session = Session.getDefaultInstance(getProperties(), new Authenticator() {
                //身份认证
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(account, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress,MimeUtility.encodeText(fromName)));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(emailMessage.getReceiver().getId()));
            message.setSubject(emailMessage.getTitle());

            consumer.accept(message);
            message.saveChanges();
            Transport.send(message);
        } catch (Exception e) {
            System.out.println("看向我");
            e.printStackTrace();
            throw new BizException(ErrorCodeEn.MESSAGE_SYSTEM_MAIL_SEND_FAIL);
        }
    }

    private Properties getProperties() {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        props.setProperty("mail.smtp.socketFactory.port", socketFactoryPort);
        props.setProperty("mail.smtp.auth", auth);
        props.put("mail.smtp.ssl.enable", true);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        return props;
    }
}
