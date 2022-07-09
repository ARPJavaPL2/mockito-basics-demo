package com.sda;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private EmailValidator emailValidator;

    @Mock
    private ContentValidator contentValidator;

    @Spy
    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void reset() {
        Mockito.reset(emailValidator, contentValidator);
    }


    @Test
    void shouldSendEmailSuccessfully() {
        // given
        String subject = "Subject";
        String receiverEmail = "receiver@gmail.com";
        String content = "Email content";

        Mockito.when(emailValidator.validateEmail(receiverEmail)).thenReturn(true);
        Mockito.when(contentValidator.validateContent(content)).thenReturn(true);

        // when
        boolean send = emailService.send(subject, receiverEmail, content);

        // then
        Assertions.assertTrue(send);

        Mockito.verify(emailValidator).validateEmail(receiverEmail);
        Mockito.verifyNoMoreInteractions(emailValidator);

        Mockito.verify(contentValidator).validateContent(content);
        Mockito.verifyNoMoreInteractions(contentValidator);

        Mockito.verify(emailService).sendEmail(receiverEmail);
    }


    @Test
    void shouldSendEmailSuccessfully2() {
        // given
        String subject = "Subject";
        String receiverEmail = "receiver@gmail.com";
        String content = "Email content";

        Mockito.when(emailValidator.validateEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(contentValidator.validateContent(Mockito.anyString())).thenReturn(true);

        // when
        boolean send = emailService.send(subject, receiverEmail, content);

        // then
        Assertions.assertTrue(send);
        Mockito.verify(emailValidator).validateEmail(receiverEmail);
        Mockito.verifyNoMoreInteractions(emailValidator);

        Mockito.verify(contentValidator).validateContent(content);
        Mockito.verifyNoMoreInteractions(contentValidator);

    }

}