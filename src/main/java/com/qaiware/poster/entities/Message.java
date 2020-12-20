package com.qaiware.poster.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Message(String payload, String type) {
    this.payload = payload;
    this.type = type;
  }
}