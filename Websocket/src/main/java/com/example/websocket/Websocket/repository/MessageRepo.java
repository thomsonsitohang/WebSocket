package com.example.websocket.Websocket.repository;

import com.example.websocket.Websocket.entity.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

}
