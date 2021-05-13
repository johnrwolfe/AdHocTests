package sysconfig.client.hr.impl;


import io.ciera.runtime.instanceloading.AttributeChangedDelta;
import io.ciera.runtime.instanceloading.InstanceCreatedDelta;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IInstanceIdentifier;
import io.ciera.runtime.summit.classes.InstanceIdentifier;
import io.ciera.runtime.summit.classes.ModelInstance;
import io.ciera.runtime.summit.exceptions.EmptyInstanceException;
import io.ciera.runtime.summit.exceptions.InstancePopulationException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.types.IWhere;
import io.ciera.runtime.summit.types.IXtumlType;
import io.ciera.runtime.summit.types.StringUtil;
import io.ciera.runtime.summit.types.UniqueId;

import sysconfig.Client;
import sysconfig.client.hr.Employee;


public class EmployeeImpl extends ModelInstance<Employee,Client> implements Employee {

    public static final String KEY_LETTERS = "Employee";
    public static final Employee EMPTY_EMPLOYEE = new EmptyEmployee();

    private Client context;

    // constructors
    private EmployeeImpl( Client context ) {
        this.context = context;
        m_Name = "";
        m_Birthdate = "";
        m_Number = 0;
    }

    private EmployeeImpl( Client context, UniqueId instanceId, String m_Name, String m_Birthdate, int m_Number ) {
        super(instanceId);
        this.context = context;
        this.m_Name = m_Name;
        this.m_Birthdate = m_Birthdate;
        this.m_Number = m_Number;
    }
    
    // @Added for 12002
    private EmployeeImpl( Client context, String m_Name, String m_Birthdate, int m_Number ) {
        this.context = context;
        this.m_Name = m_Name;
        this.m_Birthdate = m_Birthdate;
        this.m_Number = m_Number;
    }

    public static Employee create( Client context ) throws XtumlException {
        Employee newEmployee = new EmployeeImpl( context );
        if ( context.addInstance( newEmployee ) ) {
            newEmployee.getRunContext().addChange(new InstanceCreatedDelta(newEmployee, KEY_LETTERS));
            return newEmployee;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Employee create( Client context, UniqueId instanceId, String m_Name, String m_Birthdate, int m_Number ) throws XtumlException {
        Employee newEmployee = new EmployeeImpl( context, instanceId, m_Name, m_Birthdate, m_Number );
        if ( context.addInstance( newEmployee ) ) {
            return newEmployee;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }
    
    // @Added for 12002
    public static Employee create( Client context, String m_Name, String m_Birthdate, int m_Number ) throws XtumlException {
        Employee newEmployee = new EmployeeImpl( context, m_Name, m_Birthdate, m_Number );
        if ( context.addInstance( newEmployee ) ) {
        	newEmployee.getRunContext().addChange(new InstanceCreatedDelta(newEmployee, KEY_LETTERS));
            return newEmployee;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }


    // attributes
    private String m_Name;
    @Override
    public void setName(String m_Name) throws XtumlException {
        checkLiving();
        if (StringUtil.inequality(m_Name, this.m_Name)) {
            final String oldValue = this.m_Name;
            this.m_Name = m_Name;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_Name", oldValue, this.m_Name));
        }
    }
    @Override
    public String getName() throws XtumlException {
        checkLiving();
        return m_Name;
    }
    private String m_Birthdate;
    @Override
    public void setBirthdate(String m_Birthdate) throws XtumlException {
        checkLiving();
        if (StringUtil.inequality(m_Birthdate, this.m_Birthdate)) {
            final String oldValue = this.m_Birthdate;
            this.m_Birthdate = m_Birthdate;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_Birthdate", oldValue, this.m_Birthdate));
        }
    }
    @Override
    public String getBirthdate() throws XtumlException {
        checkLiving();
        return m_Birthdate;
    }
    private int m_Number;
    @Override
    public void setNumber(int m_Number) throws XtumlException {
        checkLiving();
        if (m_Number != this.m_Number) {
            final int oldValue = this.m_Number;
            this.m_Number = m_Number;
            getRunContext().addChange(new AttributeChangedDelta(this, KEY_LETTERS, "m_Number", oldValue, this.m_Number));
        }
    }
    @Override
    public int getNumber() throws XtumlException {
        checkLiving();
        return m_Number;
    }


    // instance identifiers

    // operations
    
    // @Added for 12002
    public String toString() {
    	// @TODO
    	return "";
    }
    
    // static operations
    
    // @Added for 12002
    public static Employee deserialize( Client context, Object o ) {
    	// @TODO
    	return (Employee) null;
    }


    // events


    // selections


    @Override
    public IRunContext getRunContext() {
        return context().getRunContext();
    }

    @Override
    public Client context() {
        return context;
    }

    @Override
    public String getKeyLetters() {
        return KEY_LETTERS;
    }

    @Override
    public Employee self() {
        return this;
    }

    @Override
    public Employee oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_EMPLOYEE;
    }

}

class EmptyEmployee extends ModelInstance<Employee,Client> implements Employee {

    // attributes
    public void setName( String m_Name ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }
    public String getName() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }
    public void setBirthdate( String m_Birthdate ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }
    public String getBirthdate() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }
    public void setNumber( int m_Number ) throws XtumlException {
        throw new EmptyInstanceException( "Cannot set attribute of empty instance." );
    }
    public int getNumber() throws XtumlException {
        throw new EmptyInstanceException( "Cannot get attribute of empty instance." );
    }


    // operations


    // selections


    @Override
    public String getKeyLetters() {
        return EmployeeImpl.KEY_LETTERS;
    }

    @Override
    public Employee self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Employee oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return EmployeeImpl.EMPTY_EMPLOYEE;
    }

}
