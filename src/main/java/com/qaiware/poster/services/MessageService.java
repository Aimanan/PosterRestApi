package com.qaiware.poster.services;

import com.qaiware.poster.models.MessageModel;
import com.qaiware.poster.models.Type;

public interface MessageService {
  MessageModel postMessage(String payload, Type type);
}
