package sysconfig.server.hr;


import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;

import sysconfig.Server;


public interface Employee extends IModelInstance<Employee,Server> {

    // attributes
    public String getName() throws XtumlException;
    public void setName( String m_Name ) throws XtumlException;
    public String getBirthdate() throws XtumlException;
    public void setBirthdate( String m_Birthdate ) throws XtumlException;
    public int getNumber() throws XtumlException;
    public void setNumber( int m_Number ) throws XtumlException;
 


    // operations
    // @Added for 12002
    public static Employee deserialize( Server context, Object o ) {
    	return (Employee) null;
    };
    public String toString();


    // selections


}
