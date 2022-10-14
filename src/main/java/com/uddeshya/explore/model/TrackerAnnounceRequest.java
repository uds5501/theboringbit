package com.uddeshya.explore.model;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadLocalRandom;

import static com.uddeshya.explore.constants.TrackerConstants.ACTION_ANNOUNCE;

public class TrackerAnnounceRequest {
    private long connectionID;
    private int action;
    private int transactionID;
    private String infoHash;
    private String peerID;
    private long downloaded;
    private long left;
    private long uploaded;
    private int event;
    private int IPAddress;
    private int key;
    private int num_want;

    public TrackerAnnounceRequest(long connectionID,
                                  String infoHash, String peerID, long downloaded, long left,
                                  long uploaded, int event, int IPAddress, int key, int num_want, short port) {
        this.connectionID = connectionID;
        this.action = ACTION_ANNOUNCE;
        this.transactionID = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);;
        this.infoHash = infoHash;
        this.peerID = peerID;
        this.downloaded = downloaded;
        this.left = left;
        this.uploaded = uploaded;
        this.event = event;
        this.IPAddress = IPAddress;
        this.key = key;
        this.num_want = num_want;
        this.port = port;
    }

    private short port;

    public long getConnectionID() {
        return connectionID;
    }

    public int getAction() {
        return action;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getInfoHash() {
        return infoHash;
    }

    public String getPeerID() {
        return peerID;
    }

    public long getDownloaded() {
        return downloaded;
    }

    public long getLeft() {
        return left;
    }

    public long getUploaded() {
        return uploaded;
    }

    public int getEvent() {
        return event;
    }

    public int getIPAddress() {
        return IPAddress;
    }

    public int getKey() {
        return key;
    }

    public int getNum_want() {
        return num_want;
    }

    public short getPort() {
        return port;
    }

    public static TrackerAnnounceRequestBuilder newBuilder() {
        return new TrackerAnnounceRequestBuilder();
    }

    public byte[] convertRequestToBytes() {
        ByteBuffer buf = ByteBuffer.allocate(118);
        buf.putLong(this.connectionID);
        buf.putInt(this.action);
        buf.putInt(this.transactionID);
        buf.put(this.infoHash.getBytes());
        buf.put(this.peerID.getBytes());
        buf.putLong(this.downloaded);
        buf.putLong(this.left);
        buf.putLong(this.uploaded);
        buf.putInt(this.event);
        buf.putInt(this.IPAddress);
        buf.putInt(this.key);
        buf.putInt(this.num_want);
        buf.putShort(this.port);
        return buf.array();
    }
}
