package sysconfig.server;


import interfaces.IFoo;

import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.IPort;
import io.ciera.runtime.summit.interfaces.Port;

import sysconfig.Server;
// @Changed client to server for 12002
import sysconfig.server.hr.Employee;


public class ServerClnt extends Port<Server> implements IFoo {

    public ServerClnt( Server context, IPort<?> peer ) {
        super( context, peer );
    }

    // inbound messages
    // @Split into two methods for 12002
    public void c_receive( final Employee p_emp ) throws XtumlException {
        context().LOG().LogInfo( "Server: Employee name, birthdate, number" );
        context().LOG().LogInfo( p_emp.getName() );
        context().LOG().LogInfo( p_emp.getBirthdate() );
        context().LOG().LogInteger( p_emp.getNumber() );
    }
    public void c_send( final sysconfig.client.hr.Employee p_emp ) throws XtumlException {
    	// Leave this empty, uni-directional message
    }



    // outbound messages
    // @Split into two methods for 12002
    public void a_send( final Employee p_emp ) throws XtumlException {
        if ( satisfied() ) send(new IFoo.A(p_emp));
        else {
        }
    }
    public void a_receive( final sysconfig.client.hr.Employee p_emp ) throws XtumlException {
    	// Leave this empty, uni-directional message
    }
    
    public void b( final int p_count ) throws XtumlException {
        if ( satisfied() ) send(new IFoo.B(p_count));
        else {
        }
    }


    @Override
    public void deliver( IMessage message ) throws XtumlException {
        if ( null == message ) throw new BadArgumentException( "Cannot deliver null message." );
        switch ( message.getId() ) {
            case IFoo.SIGNAL_NO_C:
            	// @Added context for 12002
                c_receive(Employee.deserialize(context(), message.get(0)));
                break;
        default:
            throw new BadArgumentException( "Message not implemented by this port." );
        }
    }



    @Override
    public String getName() {
        return "Clnt";
    }

}
