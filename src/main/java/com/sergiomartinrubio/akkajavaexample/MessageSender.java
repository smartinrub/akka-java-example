package com.sergiomartinrubio.akkajavaexample;

import akka.actor.ActorRef;

import static akka.actor.Nobody.tell;

/**
 * Messages can be sent to an actor with:
 * - tell: Sends a message asynchronously and returns immediately. This does not wait for a message.
 * - ask: Sends a messages asynchronously and returns a future.
 */
public class MessageSender {

    public void sendMessage() {

    }
}
