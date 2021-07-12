package com.sergiomartinrubio.akkajavaexample;

import akka.actor.AbstractActor;
import akka.actor.Props;

import java.util.Optional;

// Defining a "Classic" actor
public class FirstActor extends AbstractActor {

    private FirstActor() {
    }

    // It's good practice to define messages as inner classes
    public static record FooMessage(String param) {

    }

    public static record BarMessage(String param) {
    }

    static Props props() {
        return Props.create(FirstActor.class, FirstActor::new);
    }

    // Used to set the initial behaviour of the actor
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(FooMessage.class, message -> System.out.println("Received Foo message: " + message.param))
                .match(BarMessage.class, message -> System.out.println("Received Bar message: " + message.param))
                .matchEquals("secret-message", message -> System.out.println("This is a secret message: " + message))
                // if there is no match for a message, unhandled is called. We can use matchAny instead
                .matchAny(message -> System.out.println("Received unknown message: " + message)) // Default case
                .build();
    }

    @Override
    public void unhandled(Object message) {
        System.out.println("Unknown message: " + message.toString());
        super.unhandled(message);
    }


    // Invoked right after the actor starts. Executed only once when the actor is first created if postRestart
    // is not overridden
    @Override
    public void preStart() throws Exception {
        super.preStart();
    }

    /**
     * When a restart happens the following methods are called as part of the actor lifecycle.
     * - The message that caused a restart is lost
     * - Messages sent to the actor while it is restarting are enqueued
     * - Messages already enqueued to be consumed by the actor are not lost
     */

    // Invoked when an exception happens while processing the message. Good for clean up.
    // postStop is called afterward
    @Override
    public void preRestart(Throwable reason, Optional<Object> message) throws Exception {
        super.preRestart(reason, message);
    }

    // The new actor invokes this method after the restart. This method calls preStart of the new actor
    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
    }

    // This is called after stopping an actor. Messages sent to a stopped actor are sent to dead letters.
    // You can use it for cleaning up resources
    @Override
    public void postStop() throws Exception {
        super.postStop();
    }
}
