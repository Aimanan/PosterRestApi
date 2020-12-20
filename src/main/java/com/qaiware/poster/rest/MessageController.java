package com.qaiware.poster.rest;

import com.qaiware.poster.errors.BusinessRuleException;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.services.MessageService;
import com.qaiware.poster.services.MessageValidator;
import org.springframework.http.HttpStatus;
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
  public void sendMessage(
      @RequestBody final MessageModel message,
      @PathVariable final String type) {
    try {
      messageValidator.manageMessage(type, message);
    } catch (BusinessRuleException ex) {
      throw new ResponseStatusException(
          HttpStatus.PRECONDITION_FAILED, "Message validation failed:", ex);
    }
  }
}