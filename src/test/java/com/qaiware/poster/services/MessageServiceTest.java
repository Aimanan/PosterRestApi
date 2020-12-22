package com.qaiware.poster.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.entities.Type;
import com.qaiware.poster.entities.converters.MessageConverter;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import com.qaiware.poster.services.impl.MessageServiceImpl;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
public class MessageServiceTest {

  @Mock
  private MessageConverter messageConverter;

  @Mock
  private MessageRepository messageRepository;

  @Mock
  private Message message;

  @Mock
  private MessageModel messageModel;

  @InjectMocks
  private MessageServiceImpl messageService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);

    when(message.getId()).thenReturn((long)158);
    when(message.getPayload()).thenReturn("payload");
    when(message.getType()).thenReturn(Type.SEND_TEXT);
    when(message.getCreatedAt()).thenReturn(LocalDateTime.of(1995, 11, 11, 11 ,11));

    when(messageRepository.save(Mockito.any(Message.class))).thenReturn(message);

    when(messageModel.getPayload()).thenReturn("payload");
    when(messageModel.getType()).thenReturn(Type.SEND_TEXT.toString());
    when(messageConverter.convertToModel(message)).thenReturn(messageModel);

  }

  @Test
  public void postMessage() {
    String payload = "payload";
    MessageModel result = messageService
        .postMessage(Type.SEND_TEXT, payload);

    assertNotNull(result);
    assertEquals(result.getPayload(), payload);
    assertEquals(result.getType(), Type.SEND_TEXT.toString());
    assertEquals(result.getType(), Type.SEND_TEXT.toString());
  }
}