package com.uddeshya.explore.utils;

import com.uddeshya.explore.model.TorrentInfo;
import com.uddeshya.explore.model.Tracker;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MagnetLinkParser {

    public static final String UTF_ENCODING = "UTF-8";
    public static final String MAGNET_LINK = "magnet:?xt=urn:btih:A4128E0CFFDC3DBE4067CD1A7366AB9B01051774&dn=The+Witcher+2019+Season+2+Complete+720p+NF+WEBRip+x264+%5Bi_c%5D&tr=udp%3A%2F%2Fopentor.org%3A2710%2Fannounce&tr=udp%3A%2F%2Ftracker.torrent.eu.org%3A451%2Fannounce&tr=udp%3A%2F%2Fexodus.desync.com%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.moeking.me%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.opentrackr.org%3A1337%2Fannounce&tr=udp%3A%2F%2Fopen.stealth.si%3A80%2Fannounce&tr=udp%3A%2F%2Ftracker.theoks.net%3A6969%2Fannounce&tr=udp%3A%2F%2Fretracker.lanta-net.ru%3A2710%2Fannounce&tr=udp%3A%2F%2Fmovies.zsw.ca%3A6969%2Fannounce&tr=udp%3A%2F%2Fexplodie.org%3A6969%2Fannounce&tr=udp%3A%2F%2Ftracker.zer0day.to%3A1337%2Fannounce&tr=udp%3A%2F%2Ftracker.leechers-paradise.org%3A6969%2Fannounce&tr=udp%3A%2F%2Fcoppersurfer.tk%3A6969%2Fannounce";

    public static TorrentInfo parseFromMagnetLink(String magnetLink) throws UnsupportedEncodingException {
        String decodedLink = URLDecoder.decode(magnetLink, UTF_ENCODING);
        String[] lists = decodedLink.split("&");
        TorrentInfo.TorrentInfoBuilder infoBuilder = TorrentInfo.newBuilder();
        List<URL> trackerList = new ArrayList<>();
        for (String listEntry : lists) {
            if (listEntry.contains("xt=")) {
                String[] splitMagnet = listEntry.split(":");
                infoBuilder = infoBuilder.setInfoHash(splitMagnet[splitMagnet.length - 1]);
            } else if (listEntry.contains("dn=")) {
                infoBuilder = infoBuilder.setDisplayName(listEntry.substring(3));
            } else if (listEntry.contains("tr=")) {
                try {
                    trackerList.add(new URL(listEntry.substring(3)));
                } catch (MalformedURLException e) {
                    System.out.println("malformed URL");
                }
            }
        }
        infoBuilder.setTrackerURLs(trackerList);
        return infoBuilder.build();
    }

    public static void main(String[] args) {
        try {
            TorrentInfo info = MagnetLinkParser.parseFromMagnetLink(MAGNET_LINK);
            System.out.println(info);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
