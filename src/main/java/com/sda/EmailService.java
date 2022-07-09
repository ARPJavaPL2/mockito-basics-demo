package com.sda;

public class EmailService {

    private final EmailValidator emailValidator;
    private final ContentValidator contentValidator;

    public EmailService(EmailValidator emailValidator, ContentValidator contentValidator) {
        this.emailValidator = emailValidator;
        this.contentValidator = contentValidator;
    }

    public boolean send(String subject, String receiver, String content) {
        boolean valid = emailValidator.validateEmail(receiver);

        if (!valid) {
            throw new RuntimeException("Email not valid");
        }

        valid = contentValidator.validateContent(content);

        if (!valid) {
            throw new RuntimeException("Content not valid");
        }

        sendEmail(receiver);
        return true;
    }


    public void sendEmail(String receiver) {
        System.out.println("Sending email to receiver " + receiver);
    }

}
