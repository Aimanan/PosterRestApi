package com.qaiware.poster.services.impl;

import static com.qaiware.poster.errors.BusinessRuleException.isValid;

import com.qaiware.poster.errors.BusinessRuleException;
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
  public void manageMessageType(final String type, final MessageModel messageModel) {
    String payload = messageModel.getPayload();

    if (type == "send_text") {
      validateTextMessage(payload);
      messageService.postText(payload, type);

    } else if (type == "send_emotion") {
      validateEmotionMessage(payload);
      messageService.postText(payload, type);
      //TODO emotion
    } else
      throw new BusinessRuleException("Unsupported message type", type);
  }

  private void validateTextMessage(final String payload) {
    isValid(payload.length() > 0 && payload.length()<161,
        "Payload length is not between 1 and 160",
        payload);
  }

  private void validateEmotionMessage(final String payload) {
    isValid(payload.length() > 1 && payload.length()<11 && !payload.matches(".*\\d.*"),
        "Payload length is not between 1 and 160",
        payload);
  }
}