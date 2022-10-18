package com.uddeshya.explore.model;

public class Tracker {
    private final String protocol;
    private final String host;
    private final int port;


    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
    @Override
    public String toString() {
        return "Tracker{" +
                "protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }

    private Tracker(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }

    public static TrackerBuilder newBuilder() {
        return new TrackerBuilder();
    }

    public static class TrackerBuilder {
        private String protocol;
        private String host;
        private int port;

        public TrackerBuilder setProtocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public TrackerBuilder setHost(String host) {
            this.host = host;
            return this;
        }

        public TrackerBuilder setPort(int port) {
            this.port = port;
            return this;
        }

        public Tracker build() {
            return new Tracker(protocol, host, port);
        }
    }
}
