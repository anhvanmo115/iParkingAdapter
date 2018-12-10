package com.viettel.iParkingAdapter.command;

import com.viettel.iParkingAdapter.message.*;
import com.viettel.iParkingAdapter.utils.TCPChannelManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    @Path("config")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendConfigCommand(ConfigurationCommand conf){
        logger.info("begin send configuration command");
        OriginalMessage orMsg = TransformCommand.transformConf(conf);
        responseDevice(orMsg);
        logger.info("done send configuration command");
        return Response.ok().build();
    }

    @POST
    @Path("reset")
//    @Produces("application/json")
//    @Consumes("application/json")
    public Response sendResetCommand(ResetCommand rs){
        logger.info("begin send reset command");
        OriginalMessage orMsg = TransformCommand.transformReset(rs);
        responseDevice(orMsg);
        logger.info("done send reset command");
        return Response.ok().build();
    }

    @POST
    @Path("guard")
//    @Produces("application/json")
//    @Consumes("application/json")
    public Response sendGuardCommand(GuardCommand gd){
        logger.info("begin send guard command");
        OriginalMessage orMsg = TransformCommand.transformGuard(gd);
        responseDevice(orMsg);
        logger.info("done send guard command");
        return Response.ok().build();
    }

    @POST
    @Path("read-bootMsg")
//    @Produces("application/json")
//    @Consumes("application/json")
    public Response sendReadBootMsgCommand(ReadBootMsgCommand rbmc){
        logger.info("begin send read boot msg command");
        OriginalMessage orMsg = TransformCommand.transformReadBootMsg(rbmc);
        responseDevice(orMsg);
        logger.info("done send read boot msg command");
        return Response.ok().build();
    }

    @POST
    @Path("restore-factory-default")
//    @Produces("application/json")
//    @Consumes("application/json")
    public Response sendRestoreFactoryDefaultCommand(RestoreFactoryDefaultCommand rfdc){
        logger.info("begin send restore factory default command");
        OriginalMessage orMsg = TransformCommand.transformRestoreFactory(rfdc);
        responseDevice(orMsg);
        logger.info("done send restore factory default command");
        return Response.ok().build();
    }

    @POST
    @Path("sleepMode")
//    @Produces("application/json")
//    @Consumes("application/json")
    public Response sendEnterSleepModeCommand(EnterSleepModeCommand esmc){
        logger.info("begin send enter sleep mode command");
        OriginalMessage orMsg = TransformCommand.transformSleepMode(esmc);
        responseDevice(orMsg);
        logger.info("done send enter sleep mode command");
        return Response.ok().build();
    }

    @POST
    @Path("BGMagneticField")
//    @Produces("application/json")
//    @Consumes("application/json")
    public Response sendBGMagneticFieldCommand(BGMagneticField bgmf){
        logger.info("begin send BG magnetic field command");
        OriginalMessage orMsg = TransformCommand.transformBGMagneticField(bgmf);
        responseDevice(orMsg);
        logger.info("done send BG magnetic field command");
        return Response.ok().build();
    }
}
