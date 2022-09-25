package com.uddeshya.explore.model;
public class TrackerAnnounceRequestBuilder {
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

    public void setConnectionID(long connectionID) {
        this.connectionID = connectionID;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setInfoHash(String infoHash) {
        this.infoHash = infoHash;
    }

    public void setPeerID(String peerID) {
        this.peerID = peerID;
    }

    public void setDownloaded(long downloaded) {
        this.downloaded = downloaded;
    }

    public void setLeft(long left) {
        this.left = left;
    }

    public void setUploaded(long uploaded) {
        this.uploaded = uploaded;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setIPAddress(int IPAddress) {
        this.IPAddress = IPAddress;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setNum_want(int num_want) {
        this.num_want = num_want;
    }

    public void setPort(short port) {
        this.port = port;
    }

    private short port;

    public TrackerAnnounceRequestBuilder() {}

    public TrackerAnnounceRequest build() {
        return new TrackerAnnounceRequest(this.connectionID, this.action, this.transactionID,
                this.infoHash, this.peerID, this.downloaded, this.left, this.uploaded, this.event,
                this.IPAddress, this.key, this.num_want, this.port);
    }
}