package com.sergiomartinrubio.akkajavaexample;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.PostStop;
import akka.actor.typed.PreRestart;
import akka.actor.typed.SpawnProtocol;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

/**
 * Akka Typed is an improved API of Akka Classic. We use "AbstractBehavior" instead of "AbstractActor".
 * It's called Behavior because it better represent what happens with each message
 */
public class FirstTypedActor extends AbstractBehavior<FirstTypedActor.FooMessage> {

    private final ActorRef<ChildTypedActor.BarMessage> childTypedActor;

    public static record FooMessage(String message) implements SpawnProtocol.Command {

    }

    // Default constructor is required
    private FirstTypedActor(ActorContext<FooMessage> context) {
        super(context);

        // Create child actor on startup
        childTypedActor = context.spawn(ChildTypedActor.create(), "child-typed-actor");
    }

    public static Behavior<FooMessage> create() {
        return Behaviors.setup(FirstTypedActor::new);
    }

    @Override
    public Receive<FooMessage> createReceive() {
        return newReceiveBuilder()
                .onMessage(FooMessage.class, this::onFooMessage)
                .onSignal(PreRestart.class, this::onPreRestart)
                .onSignal(Terminated.class, this::onTerminated)
                .onSignal(PostStop.class, this::onPostStop)
                .build();
    }

    private FirstTypedActor onFooMessage(FooMessage message) {
        System.out.println("Received Foo message: " + message.message);
        childTypedActor.tell(new ChildTypedActor.BarMessage("hello from top level actor"));
        return this; // a "Behavior" type is expected to be returned
    }

    private FirstTypedActor onPreRestart(PreRestart preRestart) {
        System.out.println("Job is about to restart.");
        return this;
    }

    private FirstTypedActor onTerminated(Terminated terminated) {
        System.out.println("Job" + terminated.getRef().path().name() + "stopped.");
        return this;
    }

    private FirstTypedActor onPostStop(PostStop postStop) {
        System.out.println("Job is stopped.");
        return this;
    }

}
