package com.viettel.iParkingAdapter.command;

import com.viettel.iParkingAdapter.message.ConfigurationCommand;
import com.viettel.iParkingAdapter.message.OriginalMessage;
import com.viettel.iParkingAdapter.utils.TCPChannelManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.netty.channel.Channel;

@Component
@Path("/")
public class CommandController {
    private Logger logger = LogManager.getLogger(this.getClass());
    public void responseDevice(OriginalMessage responseMsg){
        Channel channel = TCPChannelManager.getChannels().get(responseMsg.getTerminalId());
        if(channel != null){
            logger.info("send response into device id = " + responseMsg.getTerminalId());
            if(channel.isActive() && channel.isWritable()){
                channel.writeAndFlush(responseMsg);
            }else{
                logger.error("channel is not active, can not send to device id = " + responseMsg.getTerminalId());
            }
        }
    }
    @POST
    @Path("/config")
    @Produces("application/json")
    @Consumes("application/json")
    public Response sendConfigCommand(ConfigurationCommand conf){
        logger.info("begin send configuration");
        OriginalMessage orMsg = TransformCommand.transformConf(conf);
        responseDevice(orMsg);
        logger.info("done send configuration");
        return Response.ok().build();
    }
}
