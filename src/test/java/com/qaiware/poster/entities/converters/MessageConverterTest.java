//package com.qaiware.poster.entities.converters;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import com.qaiware.poster.entities.Message;
//import com.qaiware.poster.entities.Type;
//import com.qaiware.poster.models.MessageModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
////@ExtendWith(SpringExtension.class)
////@SpringBootTest
////@ActiveProfiles("test")
//
////@RunWith(SpringRunner.class)
//@SpringBootTest
////@ActiveProfiles("test")
//public class MessageConverterTest {
//
//  @InjectMocks
//  private MessageConverter messageConverter;
//
//  @Mock
//  private Message messageMock;
//
//  @BeforeEach
//  public void setup() {
//    MockitoAnnotations.initMocks(this);
//
//    when(messageMock.getPayload()).thenReturn("testpayload");
//    when(messageMock.getType()).thenReturn(Type.SEND_TEXT);
//    when(messageMock.getId()).thenReturn((long)165);
//  }
//
//  @Test
//  public void convertToModel() {
////    Message testEntity = new Message("testPayload", Type.SEND_TEXT);
//    messageMock.setPayload("testPayload");
//    messageMock.setType(Type.SEND_TEXT);
//
//    MessageModel result = messageConverter.convertToModel(messageMock);
//
//    assertNotNull(result);
//    assertEquals(result.getPayload(), messageMock.getPayload());
//    assertEquals(result.getType(),  messageMock.getType().toString());
//  }
//}