package ru.spbau.mit.chat;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link ServerSide}.
 */
public class ClientSide {
    private static final Logger logger = Logger.getLogger(ClientSide.class.getName());

    private final ManagedChannel channel;
    private final ChatGrpc.ChatBlockingStub blockingStub;
    private String name;
    private String friendName;

    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public ClientSide(String host, int port, String name) {
        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true));
        this.name = name;
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    ClientSide(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = ChatGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public void greeting() {
        Name request = Name.newBuilder().setName(name).build();
        Name response;
        try {
            response = blockingStub.greeting(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        friendName = response.getName();
        logger.info("start chat with " + friendName);
    }

    public void talk() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            Message request = Message.newBuilder()
                                    .setMessage(message)
                                    .setDate(Utils.getDate())
                                    .build();
            Message response;
            try {
                response = blockingStub.talk(request);
            } catch (StatusRuntimeException e) {
                logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
                return;
            }
            System.out.println(friendName + ": " + response.getMessage()
                                + " (" + response.getDate() + ")");
            if (message.equals("goodbye")) {
                break;
            }
        }
        logger.info("chat end");
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 1234;
        String name = "client";
        switch (args.length) {
            case 1: {
                name = args[0];
                break;
            }
            case 3: {
                name = args[0];
                host = args[1];
                port = Integer.parseInt(args[2]);
                break;
            }
        }
        ClientSide clientSide = new ClientSide(host, port, name);
        try {
            clientSide.greeting();
            clientSide.talk();
        } finally {
            clientSide.shutdown();
        }
    }
}

