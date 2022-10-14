package com.uddeshya.explore.udp;

import com.uddeshya.explore.model.*;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static com.uddeshya.explore.model.TrackerAnnounceResponse.parseBytesToTrackerAnnounceResponse;
import static com.uddeshya.explore.model.TrackerConnectResponse.parseBytesToTrackerResponse;
import static com.uddeshya.explore.model.TrackerScrapeResponse.parseBytesToTrackerScrapeResponse;

public class UDPTrackerClient extends UDPClient {
    public static final int SCRAPE_RESPONSE_LENGTH = 20;
    public static final int CONNECT_RESPONSE_LENGTH = 16;
    private long connectionID;

    public UDPTrackerClient(String hostName, int port) throws SocketException, UnknownHostException {
        super(hostName, port);
    }

    public void connect() throws IOException {
        TrackerConnectRequest request = new TrackerConnectRequest();
        byte[] buf = parseRequestToBytes(request);
        byte[] responseBuffer = dispatchRequest(buf, CONNECT_RESPONSE_LENGTH);
        TrackerConnectResponse resp = parseBytesToTrackerResponse(responseBuffer);
        this.connectionID = resp.getConnectionID();
    }

    public TrackerScrapeResponse scrape() throws IOException {
        try {
            TrackerScrapeRequest request = new TrackerScrapeRequest(this.connectionID, "20d0b46b041e66d7d0ff149c52eef95e71fca810");
            byte[] buf = request.parseRequestToBytes();
            byte[] responseBuffer = dispatchRequest(buf, SCRAPE_RESPONSE_LENGTH);
            return parseBytesToTrackerScrapeResponse(responseBuffer);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    public TrackerAnnounceResponse announce() throws IOException {
        TrackerAnnounceRequest request = TrackerAnnounceRequest.newBuilder()
                .setConnectionID(this.connectionID)
                .setInfoHash("20d0b46b041e66d7d0ff149c52eef95e71fca810")
                .setPeerID("-CB0001-yyyyyyyyyyya")
                .setDownloaded(0L)
                .setLeft(0)
                .setUploaded(0L)
                .setEvent(0)
                .setIPAddress(0)
                .setKey(100)
                .setNum_want(-1)
                .setPort((short) 80)
                .build();
        byte[] buf = request.convertRequestToBytes();
        byte[] responseBuffer = dispatchRequest(buf, 80);
        return parseBytesToTrackerAnnounceResponse(responseBuffer);
    }

    private byte[] parseRequestToBytes(TrackerConnectRequest req) {
        ByteBuffer buf = ByteBuffer.allocate(CONNECT_RESPONSE_LENGTH);
        buf.putLong(req.getProtocolId());
        buf.putInt(req.getAction());
        buf.putInt(req.getTransactionID());
        return buf.array();
    }

    public static void main(String args[]) throws IOException {
        Map<String, Integer> trackers = new HashMap();
        trackers.put("open.stealth.si", 80);
        for (Map.Entry<String, Integer> entry : trackers.entrySet()) {
            UDPTrackerClient client = new UDPTrackerClient(entry.getKey(), entry.getValue());
            client.connect();
            System.out.println("Client: " + client);
            TrackerAnnounceResponse respAnnounce = client.announce();
            System.out.println("Announce: " + respAnnounce);
            TrackerScrapeResponse resp = client.scrape();
            System.out.println("Tracker Scrape: " + resp);
            client.close();
        }
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
