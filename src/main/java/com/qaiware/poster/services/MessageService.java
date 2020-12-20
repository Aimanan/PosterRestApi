package com.qaiware.poster.services;

import com.qaiware.poster.models.MessageModel;

public interface MessageService {
  MessageModel postText(String payload, String type);
}
