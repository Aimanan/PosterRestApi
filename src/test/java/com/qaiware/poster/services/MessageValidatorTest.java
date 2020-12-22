package com.qaiware.poster.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.entities.Type;
import com.qaiware.poster.entities.converters.MessageConverter;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import com.qaiware.poster.services.impl.MessageValidatorImpl;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class MessageValidatorTest {

  @Mock
  private MessageConverter messageConverter;

  @Mock
  private MessageRepository messageRepository;

  @Mock
  private Message message;

  @Mock
  private MessageService messageService;

  @Mock
  private MessageModel messageModelValid;

  @Mock
  private MessageModel messageModelText;

  @Mock
  private MessageModel messageModelEmotion;

  @Mock
  private MessageModel messageModelEmotionLen;

  @InjectMocks
  private MessageValidatorImpl messageValidator;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);

    when(message.getId()).thenReturn((long)158);
    when(message.getPayload()).thenReturn("payload");
    when(message.getType()).thenReturn(Type.SEND_TEXT);
    when(message.getCreatedAt()).thenReturn(LocalDateTime.of(1995, 11, 11, 11 ,11));

    when(messageModelValid.getPayload()).thenReturn("Text");
    when(messageModelValid.getType()).thenReturn(Type.SEND_TEXT.toString());

    when(messageRepository.save(Mockito.any(Message.class))).thenReturn(message);
    when(messageConverter.convertToModel(message)).thenReturn(messageModelValid);

    when(messageModelEmotion.getPayload()).thenReturn("af2323");
    when(messageModelEmotion.getType()).thenReturn(Type.SEND_EMOTION.toString());

    when(messageModelEmotionLen.getPayload()).thenReturn("longtext_longtext_longtext_longtext_longtext");
    when(messageModelEmotionLen.getType()).thenReturn(Type.SEND_EMOTION.toString());

    when(messageModelText.getPayload()).thenReturn("");
    when(messageModelText.getType()).thenReturn(Type.SEND_TEXT.toString());

    when(messageService.postMessage(Mockito.any(Type.class), Mockito.any(String.class))).thenReturn(messageModelValid);
  }

  @Test
  public void postMessage() {
    messageValidator
        .manageMessage(Type.SEND_TEXT.toString(), messageModelValid);

    verify(messageService, times(1)).postMessage(Type.SEND_TEXT, messageModelValid.getPayload());
  }

  @Test
  public void postMessageExceptionText() {

    Exception exception = assertThrows(RuntimeException.class, () -> {
      messageValidator
          .manageMessage(Type.SEND_TEXT.toString(), messageModelText);
    });

    String expectedMessage = "Payload length is not between 1 and 160";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void postMessageExceptionEmotionNumber() {

    Exception exception = assertThrows(RuntimeException.class, () -> {
      messageValidator
          .manageMessage(Type.SEND_EMOTION.toString(), messageModelEmotion);
    });

    String expectedMessage = "Payload contains digits";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void postMessageExceptionEmotionLength() {

    Exception exception = assertThrows(RuntimeException.class, () -> {
      messageValidator
          .manageMessage(Type.SEND_EMOTION.toString(), messageModelEmotionLen);
    });

    String expectedMessage = "Payload length is not between 1 and 11";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  public void postMessageExceptionInvalidType() {

    Exception exception = assertThrows(RuntimeException.class, () -> {
      messageValidator
          .manageMessage("invalidType", messageModelText);
    });

    String expectedMessage = "No enum constant";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
