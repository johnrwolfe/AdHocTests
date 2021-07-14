package deployment.server;


import deployment.Server;

import interfaces.IReporting;

import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.IPort;
import io.ciera.runtime.summit.interfaces.Port;
import io.ciera.runtime.summit.types.Date;
import io.ciera.runtime.summit.types.IntegerUtil;
import io.ciera.runtime.summit.types.StringUtil;


public class ServerPort1 extends Port<Server> implements IReporting {

    public ServerPort1( Server context, IPort<?> peer ) {
        super( context, peer );
    }

    // inbound messages
    public void ProjectUpdate( final Date p_ReportDate,  final String p_Name,  final int p_Value ) throws XtumlException {
        context().LOG().LogInfo( "Report received" );
        context().LOG().LogInfo( "date received (should match):" );
        context().LOG().LogInteger( context().TIM().get_day( p_ReportDate ) );
        context().LOG().LogInteger( context().TIM().get_month( p_ReportDate ) );
        context().LOG().LogInteger( context().TIM().get_year( p_ReportDate ) );
    }



    // outbound messages


    @Override
    public void deliver( IMessage message ) throws XtumlException {
        if ( null == message ) throw new BadArgumentException( "Cannot deliver null message." );
        switch ( message.getId() ) {
            case IReporting.SIGNAL_NO_PROJECTUPDATE:
                Date thedate = (Date)message.get(0);
                String strdate = thedate.serialize();
                ProjectUpdate(Date.deserialize(strdate), StringUtil.deserialize(message.get(1)), IntegerUtil.deserialize(message.get(2)));
                break;
        default:
            throw new BadArgumentException( "Message not implemented by this port." );
        }
    }



    @Override
    public String getName() {
        return "Port1";
    }

}
