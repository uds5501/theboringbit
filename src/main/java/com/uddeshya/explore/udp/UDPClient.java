package com.uddeshya.explore.udp;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public abstract class UDPClient {
    protected DatagramSocket socket;
    protected InetAddress address;
    protected String hostName;
    protected int port;

    public UDPClient(String hostName, int port) throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        this.hostName = hostName;
        address = InetAddress.getByName(hostName);
        this.port = port;
    }

    protected byte[] dispatchRequest(byte[] buf, int responseLength) throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, buf.length, this.address, port);
        socket.send(packet);

        ByteBuffer responseBuffer = ByteBuffer.allocate(responseLength);
        packet = new DatagramPacket(responseBuffer.array(), responseLength);
        socket.receive(packet);
        return responseBuffer.array();
    }

    public void close() {
        socket.close();
    }
}
