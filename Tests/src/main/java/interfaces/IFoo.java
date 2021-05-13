package interfaces;


import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.Message;

// import sysconfig.client.hr.Employee;  @Removed for 12002


public interface IFoo {

    // to provider messages
    public static final int SIGNAL_NO_C = 3;
    public static class C extends Message {
    	// @full-qualification added for 12002
    	// This is used by the sender of the message and so must accept that
    	// component's version of the shared class.
        public C( final sysconfig.client.hr.Employee p_emp ) {  
            super(new Object[]{p_emp});
        }
        @Override
        public int getId() {
            return SIGNAL_NO_C;
        }
 
    }
    // @full-qualification and split into two methods added for 12002
    // For the provider, this is the port activity which executes within the context 
    // of the component sprouting the port and therefore must accept that component's 
    // version of the shared class.
    public void c_receive( final sysconfig.server.hr.Employee p_emp ) throws XtumlException;
    
    // The requirer, this method is invoked to send the outgoing message and
    // therefore must accept that component's version of the shared class.
    public void c_send( final sysconfig.client.hr.Employee p_emp ) throws XtumlException;


    // from provider messages
    public static final int SIGNAL_NO_A = 1;
    public static class A extends Message {
    	// @full-qualification added for 12002
        public A( final sysconfig.server.hr.Employee p_emp ) {
            super(new Object[]{p_emp});
        }
        @Override
        public int getId() {
            return SIGNAL_NO_A;
        }
 
    }
    // @full-qualification added for 12002
    public void a_receive( final sysconfig.client.hr.Employee p_emp ) throws XtumlException;
    public void a_send( final sysconfig.server.hr.Employee p_emp ) throws XtumlException;
    
    public static final int SIGNAL_NO_B = 2;
    public static class B extends Message {
        public B( final int p_count ) {
            super(new Object[]{p_count});
        }
        @Override
        public int getId() {
            return SIGNAL_NO_B;
        }
 
    }
    public void b( final int p_count ) throws XtumlException;


}
