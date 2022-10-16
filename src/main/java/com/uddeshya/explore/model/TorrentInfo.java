package com.uddeshya.explore.model;

import java.net.URL;
import java.util.List;

public class TorrentInfo {
    private final String infoHash;
    private final String displayName;
    private final List<URL> trackerURLs;

    public String getInfoHash() {
        return infoHash;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<URL> getTrackerURLs() {
        return trackerURLs;
    }

    private TorrentInfo(String infoHash, String displayName, List<URL> trackerURLs) {
        this.infoHash = infoHash;
        this.displayName = displayName;
        this.trackerURLs = trackerURLs;
    }

    @Override
    public String toString() {
        return "TorrentInfo{" +
                "infoHash='" + infoHash + '\'' +
                ", displayName='" + displayName + '\'' +
                ", trackerURLs=" + trackerURLs +
                '}';
    }

    public static TorrentInfoBuilder newBuilder() {
        return new TorrentInfoBuilder();
    }

    public static class TorrentInfoBuilder {
        private String infoHash;
        private String displayName;
        private List<URL> trackerURLs;

        public TorrentInfoBuilder setInfoHash(String infoHash) {
            this.infoHash = infoHash;
            return this;
        }

        public TorrentInfoBuilder setDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public TorrentInfoBuilder setTrackerURLs(List<URL> trackerURLs) {
            this.trackerURLs = trackerURLs;
            return this;
        }

        public TorrentInfo build() {
            return new TorrentInfo(infoHash, displayName, trackerURLs);
        }
    }
}
