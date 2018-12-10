package com.viettel.iParkingAdapter.utils;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;

public class TCPChannelManager {

    private static HashMap<String, ChannelHandlerContext> channelHashMap;

    public static HashMap<String, ChannelHandlerContext> getChannels(){
        if(channelHashMap == null){
            channelHashMap = new HashMap<>();

        }

        return channelHashMap;
    }
}
