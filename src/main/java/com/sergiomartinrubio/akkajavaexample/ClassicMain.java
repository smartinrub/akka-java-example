package com.sergiomartinrubio.akkajavaexample;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ClassicMain {

    public static void main(String[] args) {
        // Create actor system
        ActorSystem system = ActorSystem.create("my-actor-system");
        // Register actor into actor system
        // We can have multiple top level actors defined for a single system
        Props actor = FirstClassicActor.props();
        ActorRef actorRef = system.actorOf(actor, "my-actor");

        System.out.println("Start sending messages...");
        actorRef.tell(new FirstClassicActor.BarMessage("param"), ActorRef.noSender());
        actorRef.tell("hello world", ActorRef.noSender());
        actorRef.tell(new FirstClassicActor.FooMessage("another-param"), ActorRef.noSender());
        actorRef.tell("secret-message", ActorRef.noSender());
        System.out.println("Done!");
        // Kills an actor and no more messages will be processed by the actor
        system.stop(actorRef);
    }
}
