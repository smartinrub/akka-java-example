package com.sergiomartinrubio.akkajavaexample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class EntryPoint {

    public static void main(String[] args) {
        // Create actor system
        ActorSystem system = ActorSystem.create("my-actor-system");
        // Register actor into actor system
        Props actor = FirstActor.props();
        ActorRef actorRef = system.actorOf(actor, "my-actor");

        System.out.println("Start sending messages...");
        actorRef.tell(new FirstActor.BarMessage("param"), ActorRef.noSender());
        actorRef.tell("hello world", ActorRef.noSender());
        actorRef.tell(new FirstActor.FooMessage("another-param"), ActorRef.noSender());
        actorRef.tell("secret-message", ActorRef.noSender());
        System.out.println("Done!");
    }
}
