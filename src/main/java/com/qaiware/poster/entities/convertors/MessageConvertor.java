package com.qaiware.poster.entities.convertors;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageConvertor {

  private final MessageRepository messageRepository;

  public MessageConvertor(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public MessageModel convertToModel(final Message message) {
    if (message == null) {
      return null;
    }

    final MessageModel res = new MessageModel();

    res.setPayload(message.getPayload());

    res.setType(message.getType());

    return res;
  }

}
