package com.uddeshya.explore.udp;

import com.uddeshya.explore.model.TrackerConnectRequest;
import com.uddeshya.explore.model.TrackerConnectResponse;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class UDPTrackerClient extends UDPClient {
    private long connectionID;

    public UDPTrackerClient(String hostName, int port) throws SocketException, UnknownHostException {
        super(hostName, port);
    }

    public TrackerConnectResponse connect() throws IOException {
        TrackerConnectRequest request = new TrackerConnectRequest();
        byte[] buf = parseRequestToBytes(request);
        byte[] responseBuffer = dispatchRequest(buf, 16);
        TrackerConnectResponse resp = parseBytesToTrackerResponse(responseBuffer);
        this.connectionID = resp.getConnectionID();
        close();
        return resp;
    }

    private byte[] parseRequestToBytes(TrackerConnectRequest req) {
        ByteBuffer buf = ByteBuffer.allocate(16);
        buf.putLong(req.getProtocolId());
        buf.putInt(req.getAction());
        buf.putInt(req.getTransactionID());
        return buf.array();
    }

    private TrackerConnectResponse parseBytesToTrackerResponse(byte[] bytes) {
        ByteBuffer fetchedBytes = ByteBuffer.wrap(bytes);
        return TrackerConnectResponse.newBuilder()
                .setAction(fetchedBytes.getInt())
                .setTransactionID(fetchedBytes.getInt())
                .setConnectionID(fetchedBytes.getLong())
                .build();
    }

    public static void main (String args[]) throws IOException {
        UDPTrackerClient client = new UDPTrackerClient("tracker.moeking.me", 6969);
        client.connect();
        System.out.println(client);
    }

    @Override
    public String toString() {
        return "UDPTrackerClient{" +
                "connectionID=" + connectionID +
                ", hostName='" + hostName + '\'' +
                ", port=" + port +
                '}';
    }
}
