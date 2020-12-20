package com.qaiware.poster.services;

import com.qaiware.poster.entities.Message;
import com.qaiware.poster.models.MessageModel;

public interface MessageValidator {
  void manageMessageType(String type, MessageModel messageModel);
}
