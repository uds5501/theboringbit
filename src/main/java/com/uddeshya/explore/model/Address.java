package com.uddeshya.explore.model;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Logger;

public class Address {
    private final int ipInt;
    private final short port;
    private final InetAddress address;

    public int getIpInt() {
        return ipInt;
    }

    public short getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    private Address(int host, short port, InetAddress address) {
        this.ipInt = host;
        this.port = port;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "ipInt=" + ipInt +
                ", port=" + port +
                ", address=" + address +
                '}';
    }

    public static AddressBuilder newBuilder() {
        return new AddressBuilder();
    }

    public static class AddressBuilder {
        private int host;
        private short port;
        private InetAddress inetAddress;

        public AddressBuilder setHost(int host) {
            this.host = host;
            byte[] bytes = BigInteger.valueOf(host).toByteArray();
            InetAddress address = null;
            try {
                address = InetAddress.getByAddress(bytes);
            } catch (UnknownHostException e) {
                System.out.println(e.getMessage());
            }
            this.inetAddress = address;
            return this;
        }

        public AddressBuilder setPort(short port) {
            this.port = port;
            return this;
        }

        public Address build() {
            return new Address(this.host, this.port, this.inetAddress);
        }
    }
}

