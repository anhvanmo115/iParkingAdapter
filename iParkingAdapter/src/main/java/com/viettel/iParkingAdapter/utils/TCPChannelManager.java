package com.viettel.iParkingAdapter.utils;


import io.netty.channel.Channel;

import java.util.HashMap;

public class TCPChannelManager {

    private static HashMap<String, Channel> channelHashMap;

    public static HashMap<String, Channel> getChannels(){
        if(channelHashMap == null){
            channelHashMap = new HashMap<>();

        }

        return channelHashMap;
    }
}
