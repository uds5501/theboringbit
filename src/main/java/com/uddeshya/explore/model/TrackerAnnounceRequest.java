package com.uddeshya.explore.model;

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

    public TrackerAnnounceRequest(long connectionID, int action, int transactionID,
                                  String infoHash, String peerID, long downloaded, long left,
                                  long uploaded, int event, int IPAddress, int key, int num_want, short port) {
        this.connectionID = connectionID;
        this.action = action;
        this.transactionID = transactionID;
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
}
