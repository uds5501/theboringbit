package com.uddeshya.explore.udp;

import com.uddeshya.explore.model.TrackerConnectRequest;
import com.uddeshya.explore.model.TrackerConnectResponse;
import com.uddeshya.explore.model.TrackerScrapeRequest;
import com.uddeshya.explore.model.TrackerScrapeResponse;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import static com.uddeshya.explore.constants.TrackerConstants.ACTION_ERROR;

public class UDPTrackerClient extends UDPClient {
    public static final int SCRAPE_RESPONSE_LENGTH = 20;
    public static final int CONNECT_RESPONSE_LENGTH = 16;
    private long connectionID;

    public UDPTrackerClient(String hostName, int port) throws SocketException, UnknownHostException {
        super(hostName, port);
    }

    public TrackerConnectResponse connect() throws IOException {
        TrackerConnectRequest request = new TrackerConnectRequest();
        byte[] buf = parseRequestToBytes(request);
        byte[] responseBuffer = dispatchRequest(buf, CONNECT_RESPONSE_LENGTH);
        TrackerConnectResponse resp = parseBytesToTrackerResponse(responseBuffer);
        this.connectionID = resp.getConnectionID();
        return resp;
    }

    public TrackerScrapeResponse scrape() throws IOException {
        try {
            TrackerScrapeRequest request = new TrackerScrapeRequest(this.connectionID, "A4128E0CFFDC3DBE4067CD1A7366AB9B01051774");
            byte[] buf = request.parseRequestToBytes();
            byte[] responseBuffer = dispatchRequest(buf, SCRAPE_RESPONSE_LENGTH);
            TrackerScrapeResponse resp = parseBytesToTrackerScrapeResponse(responseBuffer);
            return resp;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    private TrackerScrapeResponse parseBytesToTrackerScrapeResponse(byte[] responseBuffer) {
        ByteBuffer fetchedBytes = ByteBuffer.wrap(responseBuffer);
        int action = fetchedBytes.getInt();
        int transactionId = fetchedBytes.getInt();
        TrackerScrapeResponse.TrackerScrapeResponseBuilder builder = TrackerScrapeResponse.newBuilder()
                .setAction(action)
                .setTransactionId(transactionId);
        if (action == ACTION_ERROR) {
            builder = builder.setError(new String(fetchedBytes.array()));
        } else {
            builder = builder.setSeeders(fetchedBytes.getInt())
                    .setCompleted(fetchedBytes.getInt())
                    .setLeechers(fetchedBytes.getInt());
        }
        return builder.build();
    }

    private byte[] parseRequestToBytes(TrackerConnectRequest req) {
        ByteBuffer buf = ByteBuffer.allocate(CONNECT_RESPONSE_LENGTH);
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

    public static void main(String args[]) throws IOException {
        UDPTrackerClient client = new UDPTrackerClient("explodie.org", 6969);
        client.connect();
        System.out.println("Client: " + client);
        TrackerScrapeResponse resp = client.scrape();
        System.out.println("Tracker Scrape: " + resp);
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
