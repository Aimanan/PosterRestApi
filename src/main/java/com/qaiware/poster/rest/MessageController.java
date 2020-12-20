package com.qaiware.poster.rest;

import com.qaiware.poster.errors.BusinessRuleException;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.repository.MessageRepository;
import com.qaiware.poster.services.MessageService;
import com.qaiware.poster.services.MessageValidator;
import com.qaiware.poster.services.impl.MessageValidatorImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageService messageService;
  private final MessageValidator messageValidator;

  public MessageController(MessageService messageService,
      MessageValidator messageValidator) {
    this.messageService = messageService;
    this.messageValidator = messageValidator;
  }

  @PostMapping("/{type}")
  public void sendText(
      @RequestBody final MessageModel message,
      @PathVariable final String type) {
    try {
      MessageModel result = messageService.postText(message.getPayload(), type);
      messageValidator.manageMessageType(type, message);
//      return ResponseEntity.ok(result);
    } catch (BusinessRuleException ex) {
      throw new ResponseStatusException(
          HttpStatus.PRECONDITION_FAILED, "Message validation failed:", ex);
    }
  }
}