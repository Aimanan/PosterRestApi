package com.qaiware.poster.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "payload")
  private String payload;

  @Column(name = "type")
  private String type;

  public Message(String payload, String type) {
    this.payload = payload;
    this.type = type;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPayload() {
    return payload;
  }

  public void setPayload(String payload) {
    this.payload = payload;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}