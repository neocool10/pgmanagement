package com.neocool.pgmanagement.service;
import java.util.List;

import org.apache.log4j.Logger;

import com.neocool.pgmanagement.controller.MController;
import com.neocool.pgmanagement.model.Allo;
// import com.neocool.pgmanagement.model.Allo;
import com.neocool.pgmanagement.model.Allotment;
import com.neocool.pgmanagement.model.Flats;
import com.neocool.pgmanagement.model.Person;
import com.neocool.pgmanagement.model.Status;
// import com.neocool.pgmanagement.model.Status;
import com.neocool.pgmanagement.model.Tenant;
import com.neocool.pgmanagement.utils.exception.DataException;

public interface TenantManager {
	static Logger logger = Logger.getLogger(MController.class);

	default void interfaceName() {
		logger.info("This Is TenantManager.");
	}
   
    public Allotment tenantAllotment(Allotment allotment) throws DataException ;
	List<Allo> displayAllocations() throws DataException;
	public Boolean deleteAllotment(String flatNo);
	
    public Person registerTenant(Tenant tenant) throws DataException ;
	List<Person> display() throws DataException;
	List<Person> byId(int tenantID) throws DataException;
    public Boolean deleteTenant(Integer id);
    public Boolean updateTenant(Integer ch, String value, Integer i);
	
    Boolean check(String flat) throws DataException;
	List<Allo> rentedflatdisplay() throws DataException;
    
	public Flats addflat(Flats flats) throws DataException ;
	List<Flats> displayflats() throws DataException;
    public Boolean deleteFlats(String flat);

    public Status newstatus(Status status) throws DataException ;
    public Boolean deleteStatus(Integer id);
    public Boolean updateStatus(Integer ch, String value, Integer i);
	List<Status> displayStaus() throws DataException;



}