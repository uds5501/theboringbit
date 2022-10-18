package com.uddeshya.explore.model;

import java.util.List;

public class TorrentInfo {
    private final String infoHash;
    private final String displayName;
    private final List<Tracker> trackerList;

    public String getInfoHash() {
        return infoHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Tracker> getTrackerList() {
        return trackerList;
    }

    private TorrentInfo(String infoHash, String displayName, List<Tracker> trackerList) {
        this.infoHash = infoHash;
        this.displayName = displayName;
        this.trackerList = trackerList;
    }

    @Override
    public String toString() {
        return "TorrentInfo{" +
                "infoHash='" + infoHash + '\'' +
                ", displayName='" + displayName + '\'' +
                ", trackerList=" + trackerList +
                '}';
    }

    public static TorrentInfoBuilder newBuilder() {
        return new TorrentInfoBuilder();
    }

    public static class TorrentInfoBuilder {
        private String infoHash;
        private String displayName;
        private List<Tracker> trackerURLs;

        public TorrentInfoBuilder setInfoHash(String infoHash) {
            this.infoHash = infoHash;
            return this;
        }

        public TorrentInfoBuilder setDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public TorrentInfoBuilder setTrackerURLs(List<Tracker> trackerURLs) {
            this.trackerURLs = trackerURLs;
            return this;
        }

        public TorrentInfo build() {
            return new TorrentInfo(infoHash, displayName, trackerURLs);
        }
    }
}
