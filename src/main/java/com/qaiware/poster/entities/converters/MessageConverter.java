package com.qaiware.poster.entities.converters;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageConverter {

  private final MessageRepository messageRepository;

  public MessageConverter(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public MessageModel convertToModel(final Message message) {
    if (message == null) {
      return null;
    }

    final MessageModel res = new MessageModel();

    res.setPayload(message.getPayload());

    res.setType(message.getType().toString());

    return res;
  }

}
