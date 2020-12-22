package com.qaiware.poster.services.impl;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.entities.Type;
import com.qaiware.poster.entities.converters.MessageConverter;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import com.qaiware.poster.services.MessageService;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;

  private final MessageConverter messageConverter;

  public MessageServiceImpl(MessageRepository messageRepository,
      MessageConverter messageConverter) {
    this.messageRepository = messageRepository;
    this.messageConverter = messageConverter;
  }

  @Override
  public MessageModel postMessage(Type type, final String payload) {

    Message messageEntity = new Message(payload, type);

    messageEntity.setCreatedAt(LocalDateTime.now());
    Message message = messageRepository.save(messageEntity);

    return messageConverter.convertToModel(message);
  }
}
