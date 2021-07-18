package com.sergiomartinrubio.akkajavaexample;


import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class ChildTypedActor extends AbstractBehavior<ChildTypedActor.BarMessage> {


    public static record BarMessage(String param) {

    }

    private ChildTypedActor(ActorContext<BarMessage> context) {
        super(context);
    }

    public static Behavior<BarMessage> create() {
        return Behaviors.setup(ChildTypedActor::new);
    }

    @Override
    public Receive<BarMessage> createReceive() {
        return newReceiveBuilder()
                .onMessage(BarMessage.class, message -> {
                    System.out.println(message);
                    return this;
                })
                .build();
    }


}
