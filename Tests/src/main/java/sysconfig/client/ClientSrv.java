package sysconfig.client;


import interfaces.IFoo;

import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.IPort;
import io.ciera.runtime.summit.interfaces.Port;
import io.ciera.runtime.summit.types.IntegerUtil;

import sysconfig.Client;

import sysconfig.client.hr.Employee;


public class ClientSrv extends Port<Client> implements IFoo {

    public ClientSrv( Client context, IPort<?> peer ) {
        super( context, peer );
    }

    // inbound messages
    public void b( final int p_count ) throws XtumlException {
    }
    
    // @Split into two methods for 12002
    public void a_receive( final Employee p_emp ) throws XtumlException {
        context().LOG().LogInfo( "Client: Employee name, birthdate, number" );
        context().LOG().LogInfo( p_emp.getName() );
        context().LOG().LogInfo( p_emp.getBirthdate() );
        context().LOG().LogInteger( p_emp.getNumber() );
    }
    public void a_send( final sysconfig.server.hr.Employee p_emp ) throws XtumlException {
    	// Leave this empty, uni-directional message
    }

    // outbound messages
    // @Split into two methods for 12002
    public void c_send( final Employee p_emp ) throws XtumlException {
        if ( satisfied() ) send(new IFoo.C(p_emp));
        else {
        }
    }
    public void c_receive( final sysconfig.server.hr.Employee p_emp ) throws XtumlException {
    	// Leave this empty, uni-directional message
    }

    @Override
    public void deliver( IMessage message ) throws XtumlException {
        if ( null == message ) throw new BadArgumentException( "Cannot deliver null message." );
        switch ( message.getId() ) {
            case IFoo.SIGNAL_NO_B:
                b(IntegerUtil.deserialize(message.get(0)));
                break;
            case IFoo.SIGNAL_NO_A:
            	// @Added context for 12002
                a_receive(Employee.deserialize(context(), message.get(0)));
                break;
        default:
            throw new BadArgumentException( "Message not implemented by this port." );
        }
    }



    @Override
    public String getName() {
        return "Srv";
    }

}
