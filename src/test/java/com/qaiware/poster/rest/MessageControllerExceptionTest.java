package com.qaiware.poster.rest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.qaiware.poster.errors.BusinessRuleException;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.services.MessageValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class MessageControllerExceptionTest {

  @Mock
  private MessageValidator messageValidator;

  @Mock
  private MessageModel messageModel;

  @InjectMocks
  private MessageController messageController;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);

    doThrow(BusinessRuleException.class)
        .when(messageValidator)
        .manageMessage(Mockito.any(String.class), Mockito.any(MessageModel.class));
  }

  @Test
  public void sendMessage() {
    messageController.sendMessage(messageModel, "send_text");

    verify(messageValidator, times(1)).manageMessage("send_text", messageModel);
  }

  @Test
  public void sendMessageExceptionText() {
    ResponseEntity responseEntity = messageController.sendMessage(messageModel, "send_text");

    Object body = responseEntity.getBody();
    assertNotNull(responseEntity);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.PRECONDITION_FAILED);
  }

  @Test
  public void sendMessageExceptionEmotion() {
    ResponseEntity responseEntity = messageController.sendMessage(messageModel, "send_emotion");

    Object body = responseEntity.getBody();
    assertNotNull(responseEntity);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.PRECONDITION_FAILED);
  }
}