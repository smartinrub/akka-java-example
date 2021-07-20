package com.sergiomartinrubio.akkajavaexample;

import akka.actor.typed.ActorSystem;

public class TypedMain {

    public static void main(String[] args) {
        // Create top level actor for the actor system "typed-actor-system"
        ActorSystem<FirstTypedActor.FooMessage> system = ActorSystem.create(FirstTypedActor.create(), "typed-actor-system");
        system.tell(new FirstTypedActor.FooMessage("hello world"));
        system.terminate();
    }
}
