package com.qaiware.poster.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
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
  @Enumerated(EnumType.STRING)
  private Type type;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public Message(String payload, Type type) {
    this.payload = payload;
    this.type = type;
  }
}