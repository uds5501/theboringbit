package com.uddeshya.explore.model;
public class TrackerAnnounceRequestBuilder {
    private long connectionID;
    private String infoHash;
    private String peerID;
    private long downloaded;
    private long left;
    private long uploaded;
    private int event;
    private int IPAddress;
    private int key;
    private int num_want;

    public TrackerAnnounceRequestBuilder setConnectionID(long connectionID) {
        this.connectionID = connectionID;
        return this;
    }


    public TrackerAnnounceRequestBuilder setInfoHash(String infoHash) {
        this.infoHash = infoHash;
        return this;
    }

    public TrackerAnnounceRequestBuilder setPeerID(String peerID) {
        this.peerID = peerID;
        return this;
    }

    public TrackerAnnounceRequestBuilder setDownloaded(long downloaded) {
        this.downloaded = downloaded;
        return this;
    }

    public TrackerAnnounceRequestBuilder setLeft(long left) {
        this.left = left;
        return this;
    }

    public TrackerAnnounceRequestBuilder setUploaded(long uploaded) {
        this.uploaded = uploaded;
        return this;
    }

    public TrackerAnnounceRequestBuilder setEvent(int event) {
        this.event = event;
        return this;
    }

    public TrackerAnnounceRequestBuilder setIPAddress(int IPAddress) {
        this.IPAddress = IPAddress;
        return this;
    }

    public TrackerAnnounceRequestBuilder setKey(int key) {
        this.key = key;
        return this;
    }

    public TrackerAnnounceRequestBuilder setNum_want(int num_want) {
        this.num_want = num_want;
        return this;
    }

    public TrackerAnnounceRequestBuilder setPort(short port) {
        this.port = port;
        return this;
    }

    private short port;

    public TrackerAnnounceRequestBuilder() {}

    public TrackerAnnounceRequest build() {
        return new TrackerAnnounceRequest(this.connectionID,
                this.infoHash, this.peerID, this.downloaded, this.left, this.uploaded, this.event,
                this.IPAddress, this.key, this.num_want, this.port);
    }
}