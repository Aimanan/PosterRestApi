package com.qaiware.poster.services;

import com.qaiware.poster.entities.Type;
import com.qaiware.poster.models.MessageModel;

public interface MessageService {

  MessageModel postMessage(Type type, String payload);
}
