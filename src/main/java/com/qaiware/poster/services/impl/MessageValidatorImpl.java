package com.qaiware.poster.services.impl;

import static com.qaiware.poster.errors.BusinessRuleException.isValid;

import com.qaiware.poster.entities.Type;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.services.MessageService;
import com.qaiware.poster.services.MessageValidator;
import org.springframework.stereotype.Service;

@Service
public class MessageValidatorImpl implements MessageValidator {

  private final MessageService messageService;

  public MessageValidatorImpl(MessageService messageService) {
    this.messageService = messageService;
  }

  @Override
  public void manageMessage(final String type, final MessageModel messageModel) {
    String payload = messageModel.getPayload();

    Type messageType = Type.valueOf(type.toUpperCase());
    messageModel.setType(type);

    if (messageType.equals(Type.SEND_TEXT)) {
      validateTextMessage(payload);
      messageService.postMessage(messageType, payload);
    }

    if (messageType.equals(Type.SEND_EMOTION)) {
      validateEmotionMessage(payload);
      messageService.postMessage(messageType, payload);
    }
  }

  private void validateTextMessage(final String payload) {
    isValid(payload != null && payload.length() > 0 && payload.length() < 161,
        "Payload length is not between 1 and 160",
        payload);
  }

  private void validateEmotionMessage(final String payload) {
    isValid(payload != null && payload.length() > 1 && payload.length() < 11,
        "Payload length is not between 1 and 11",
        payload);

    isValid(!payload.matches(".*\\d.*"),
        "Payload contains digits",
        payload);
  }
}
