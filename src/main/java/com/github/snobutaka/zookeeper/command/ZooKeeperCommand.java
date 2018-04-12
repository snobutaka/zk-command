package com.github.snobutaka.zookeeper.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class ZooKeeperCommand {

    String host;
    int port;

    public ZooKeeperCommand(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public boolean areYouOk() throws IOException {
        try (Socket socket = new Socket(this.host, this.port);
                OutputStream out = socket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.write("ruok".getBytes());
            List<String> lines = in.lines().collect(Collectors.toList());
            if (lines.size() != 1 || !lines.get(0).equals("imok")) {
                throw new IllegalStateException("ZooKeeper returned unknown message: " + lines);
            }
            return true;
        }
    }
}
