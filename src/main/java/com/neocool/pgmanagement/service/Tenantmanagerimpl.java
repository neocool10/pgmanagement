package com.neocool.pgmanagement.service;

import java.util.List;

import com.neocool.pgmanagement.dao.TenantDao;
import com.neocool.pgmanagement.model.Allo;
// import com.neocool.pgmanagement.model.Allo;
import com.neocool.pgmanagement.model.Allotment;
import com.neocool.pgmanagement.model.Flats;
import com.neocool.pgmanagement.model.Person;
import com.neocool.pgmanagement.model.Status;
// import com.neocool.pgmanagement.model.Status;
import com.neocool.pgmanagement.model.Tenant;
import com.neocool.pgmanagement.utils.exception.DataException;

public class Tenantmanagerimpl implements TenantManager {
	
	 private static Tenantmanagerimpl tenantmanagerimpl = null;

	    private Tenantmanagerimpl(){}

	    public static Tenantmanagerimpl getInstance(){
	        if (tenantmanagerimpl == null) {
	            tenantmanagerimpl = new Tenantmanagerimpl();
	        }
	        return tenantmanagerimpl;
	    }
	public Person registerTenant(Tenant tenant) throws DataException {
       Person person = (Person)tenant;
		return TenantDao.getInstance().saveTenant(person);
   }

	@Override
	public List<Person> display() throws DataException {
		return TenantDao.getInstance().displayTenant();
	}

	@Override
	public List<Person> byId(int tenantID) throws DataException {
		return TenantDao.getInstance().viewTenant(tenantID);
	}

	@Override
	public Boolean deleteTenant(Integer id) {
		return TenantDao.getInstance().deleteTenant(id);
	}

	@Override
	public Boolean updateTenant(Integer ch, String value, Integer i) {
		return TenantDao.getInstance().updateTenant( ch, value, i);
	}

	@Override
	public Allotment tenantAllotment(Allotment allotment) throws DataException {
		   Allo allo = (Allo)allotment;

			return TenantDao.getInstance().saveAllotment(allo);
	}

	@Override
	public List<Allo> displayAllocations() throws DataException {
		return TenantDao.getInstance().displayTenantAllo();
				
	}
	
	@Override
	public List<Allo> rentedflatdisplay() throws DataException {
		return TenantDao.getInstance().viewrentedflats();
	}

	@Override
	public Boolean check(String flat) throws DataException {
		return TenantDao.getInstance().checkflat(flat);
	}

	@Override
	public Status newstatus(Status status) throws DataException {
		Status stat = status;
		return TenantDao.getInstance().saveStatus(stat);
	}

	@Override
	public Boolean deleteStatus(Integer id) {
		return TenantDao.getInstance().deleteStatus(id);

	}

	@Override
	public Boolean updateStatus(Integer ch, String value, Integer i) {
		return TenantDao.getInstance().updateStatus( ch, value, i);

	}

	@Override
	public List<Status> displayStaus() throws DataException {
		return TenantDao.getInstance().displayStatus();
	}

	@Override
	public List<Flats> displayflats() throws DataException {
		return TenantDao.getInstance().viewavailableflats();
	}

	@Override
	public Boolean deleteAllotment(String flatNo) {
		return TenantDao.getInstance().deleteAllotment(flatNo);
	}

	@Override
	public Flats addflat(Flats flats) throws DataException {
		Flats flat = flats;
		return TenantDao.getInstance().addflats(flat);
	}

	@Override
	public Boolean deleteFlats(String flat) {
		return TenantDao.getInstance().deleteFlat(flat);

	}
	

	
	
	
   

}