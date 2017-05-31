package ru.spbau.mit.chat;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * ServerSide that manages startup/shutdown of a {@code Greeter} server.
 */
public class ServerSide {
    private static final Logger logger = Logger.getLogger(ServerSide.class.getName());

    private Server server;
    private String name;
    private int port;

    public ServerSide(String name, int port) {
        this.name = name;
        this.port = port;
    }

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl(name))
                .build()
                .start();
        logger.info("ServerSide started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ServerSide.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
      /* Access a service running on the local machine on port 50051 */
        int port = 1234;
        String name = "server";
        switch (args.length) {
            case 1: {
                name = args[0];
                break;
            }
            case 2: {
                name = args[0];
                port = Integer.parseInt(args[1]);
                break;
            }
        }
        final ServerSide server = new ServerSide(name, port);
        server.start();
        server.blockUntilShutdown();
    }

    private static class GreeterImpl extends ChatGrpc.ChatImplBase {

        private String friendName;
        private String name;

        public GreeterImpl(String name) {
            this.name = name;
        }

        @Override
        public void greeting(Name request, StreamObserver<Name> responseObserver) {
            friendName = request.getName();
            logger.info("start chat with " + friendName);
            Name reply = Name.newBuilder().setName(name).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

        public void talk(Message request, StreamObserver<ru.spbau.mit.chat.Message> responseObserver) {
            System.out.println(friendName + ": " + request.getMessage()
                                + " (" + request.getDate() + ")");
            Scanner scanner = new Scanner(System.in);
            Message reply = Message.newBuilder()
                                .setMessage(scanner.nextLine())
                                .setDate(Utils.getDate())
                                .build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }

    }
}
