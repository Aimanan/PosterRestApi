package com.qaiware.poster.rest;

import com.qaiware.poster.errors.BusinessRuleException;
import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.services.MessageValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageValidator messageValidator;

  public MessageController(MessageValidator messageValidator) {
    this.messageValidator = messageValidator;
  }

  @PostMapping("/{type}")
  public ResponseEntity sendMessage(
      @RequestBody final MessageModel message,
      @PathVariable final String type) {
    try {
      messageValidator.manageMessage(type, message);
      return new ResponseEntity(HttpStatus.CREATED);
    } catch (BusinessRuleException ex) {
      return new ResponseEntity(HttpStatus.PRECONDITION_FAILED);
    }
  }
}