package com.qaiware.poster.services.impl;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.entities.convertors.MessageConvertor;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import com.qaiware.poster.services.MessageService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;

  private final MessageConvertor messageConvertor;

  public MessageServiceImpl(MessageRepository messageRepository,
      MessageConvertor messageConvertor) {
    this.messageRepository = messageRepository;
    this.messageConvertor = messageConvertor;
  }

  @Override
  public MessageModel postText(final String payload, String type) {

    Message messageEntity = new Message(
        payload,
        type
    );

    messageEntity.setCreatedAt(LocalDateTime.now());

    Message message = messageRepository.save(messageEntity);

    final MessageModel messageModel = messageConvertor.convertToModel(message);

    return messageModel;
  }
}
