package com.neocool.pgmanagement.controller;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import org.apache.log4j.Logger;

import com.neocool.pgmanagement.model.Allo;
import com.neocool.pgmanagement.model.Flats;
import com.neocool.pgmanagement.model.Person;
import com.neocool.pgmanagement.model.Status;
import com.neocool.pgmanagement.service.CommonManagerImpl;
import com.neocool.pgmanagement.service.Tenantmanagerimpl;
import com.neocool.pgmanagement.utils.AppContext;
import com.neocool.pgmanagement.utils.exception.DDLException;
import com.neocool.pgmanagement.utils.exception.DataException;

public class MController extends Controller {
	static Logger logger = Logger.getLogger(MController.class);

	static Scanner s = new Scanner(System.in);

	@Override
	public void main() throws DataException, DDLException {
		String loggedin = "";
		Boolean canRun = Boolean.TRUE;

		while (Boolean.TRUE.equals(canRun)) {
			if (loggedin.equals("")) {
				loggedin = login();
			} else if (loggedin.equals("admin")) {
				admin();
				canRun = false;
			} else {
				user();
				canRun = false;
			}
		}
	}

	private void user() throws DataException,DDLException {
		Boolean canRun = Boolean.TRUE;
		while (Boolean.TRUE.equals(canRun)) {
			String mainIp = printMenuForUser();
			switch (mainIp) {
			case "1":
				registerNewTenant();
				break;
			case "2":
				viewavailableflats();
				break;
			case "3":
				allocatedFlatStatusUpdate();
				break;
			case "4":
				allocatedFlatStatusDisplay();
				break;
			case "10":
				createTables();
				break;
			case "0":
				canRun = false;
				break;
			default:
				logger.info("INVALID RESPONSE! try again");
			}
		}
	}

	private void admin() throws DataException {
		Boolean canRun = Boolean.TRUE;
		while (Boolean.TRUE.equals(canRun)) {
			String mainIp = printMenu();
			switch (mainIp) {
			case "1":
				newflat();
				break;
			case "2":
				viewavailableflats();
				break;
			case "3":
				tenantsrentedflats();
				break;
			case "4":
				removeExitingFlats();
				break;
			case "5":
				registerNewTenant();
				break;
			case "6":
				registeredTenantUpdate();
				break;
			case "7":
				registeredTenantDisplay();
				break;
			case "8":
				registeredTenantDisplayById();
				break;
			case "9":
				tenantDeleteById();
				break;
			case "10":
				newTenantAllotment();
				break;
			case "11":
				tenantAllocations();
				break;
			case "12":
				deleteTenantAllotment();
				break;
			case "13":
				allocatedflatstatus();
				break;
			case "14":
				allocatedFlatStatusUpdate();
				break;
			case "15":
				allocatedFlatStatusDisplay();
				break;
			case "16":
				allocatedFlatStatusDelete();
				break;
			case "0":
				canRun = false;
				break;
			default:
				logger.info("INVALID RESPONSE! try again");
			}
		}
	}

	public void newflat() throws DataException {
		Flats flats = AppContext.getInstance().getBean("flats");
		logger.info("Enter Flat No                : ");
		String flat = s.nextLine();
		if (flat == null || flat.trim().isEmpty()) {
			logger.info("Invalid input Flat No is required.");
			return;
		}
		flats.setFlatno(flat);
		Tenantmanagerimpl.getInstance().addflat(flats);

	}

	public void viewavailableflats() throws DataException {

		try {
			List<Flats> l = Tenantmanagerimpl.getInstance().displayflats();
			logger.info("All this flats are available:");
			l.stream().forEach(e -> logger.info("Flat No :" + e.getFlatno()));

		} catch (Exception e) {
			logger.info("There is an error in viewavailableflats", e);
		}
	}

	private void tenantsrentedflats() {
		logger.info("These Flats Are Rented to these tenants:");
		logger.info("+--------------+-------------+");
		logger.info("| name         | flatno      |");
		logger.info("+--------------+-------------+");

		try {
			List<Allo> res = Tenantmanagerimpl.getInstance().rentedflatdisplay();

			res.stream().forEach(e -> logger.info(e.getName() + "\t\t" + e.getFlatno() + "\t"));
		} catch (Exception e) {
			logger.info("There is an error in tenantrentedflats", e);

		}
	}

	private void removeExitingFlats() {
		logger.info("Enter flat No : ");
		String flat = s.nextLine();
		if (flat == null || flat.trim().isEmpty()) {
			logger.info("Invalid input Flat required.");
			return;
		}
		boolean d = Tenantmanagerimpl.getInstance().deleteFlats(flat);
		if (d) {
			logger.info("  Successfully Deleted : ");

		} else {
			logger.info("SOMTHING WENT WRONG:");
		}
	}

	public void registerNewTenant() throws DataException {

		Person per = AppContext.getInstance().getBean("Person");

		logger.info("Enter Tenant Name      : ");
		String name = s.nextLine();
		if (name == null || name.trim().isEmpty()) {
			logger.info("Invalid input. Tenant Name is required.");
			return;
		}
		logger.info("Enter Tenant Phone: ");
		String phone = s.nextLine();
		if (phone == null || phone.trim().isEmpty()) {
			logger.info("Invalid input. Tenant Phone is required.");
			return;
		}
		String phoneRegex = "^[6-9]\\d{9}$";
		Predicate<String> phoneNoFormatValidator = p -> p.matches(phoneRegex);

		if (!phoneNoFormatValidator.test(phone)) {
			logger.info("invalid phoneno format");
			return;
		}
		logger.info("Enter Tenant City: ");
		String city = s.nextLine();
		if (city == null || city.trim().isEmpty()) {
			logger.info("Invalid input. Tenant City is required.");
			return;
		}
		logger.info("Enter Tenant Date: ");
		String tdate = s.nextLine();
		if (tdate == null || tdate.trim().isEmpty()) {
			logger.info("Invalid input. Tenant Date is required.");
			return;
		}
//			else {
//            try {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
//                LocalDate tenantDate = LocalDate.parse(tdate, formatter);
//                logger.info("Parsed Tenant Date: " + tenantDate);
//            } catch (DateTimeParseException e) {
//                logger.info("Invalid input. Tenant Date format should be YYYY-MM-DD.");
//                return;
//            }
//        }
		logger.info("Enter Tenant aadhar ID : ");
		String aadhar = s.nextLine();
		if (aadhar == null || aadhar.trim().isEmpty()) {
			logger.info("Invalid input. Tenant aadhar is required.");
			return;
		}
		String aadharRegex = "^\\d{4}\\s\\d{4}\\s\\d{4}$";
		Predicate<String> panFormatValidator = p -> p.matches(aadharRegex);

		if (!panFormatValidator.test(aadhar)) {
			logger.info("invalid aadhar format");
			return;
		}
		logger.info("Enter Tenant Email: ");
		String temail = s.nextLine();
		if (temail == null || temail.trim().isEmpty()) {
			logger.info("Invalid input. Tenant Email is required.");
			return;
		}

		String emailRegex = "^[\\w.+\\-]+@gmail\\.com$";
		Predicate<String> emailFormatValidator = p -> p.matches(emailRegex);

		if (!emailFormatValidator.test(temail)) {
			logger.info("invalid aadhar format");
			return;
		}
		per.setTenantName(name);
		per.setTenantPhone(phone);
		per.setTenantCity(city);
		per.setTenantDate(tdate);
		per.setTenantAadhar(aadhar);
		per.setTenantEmail(temail);
		Tenantmanagerimpl.getInstance().registerTenant(per);

	}

	private void registeredTenantUpdate() {
		logger.info("Enter Id : ");
		int i = s.nextInt();
		logger.info("==========================");
		logger.info("Select 1  to Update Name :");
		logger.info("Select 2  to Update Phone:");
		logger.info("Select 3  to Update Email:");
		logger.info("==========================");
		logger.info("Enter choice to Update : ");
		int ch = s.nextInt();
		s.nextLine();

		logger.info("Enter Data value       : ");
		String value = s.nextLine();
		if (value == null || value.trim().isEmpty()) {
			logger.info("Invalid Flat is required.");
			return;
		}
		Boolean ffff = Tenantmanagerimpl.getInstance().updateTenant(ch, value, i);

		if (Boolean.TRUE.equals(ffff)) {
			logger.info("Successfully Updated Tenant Data : ");
		} else {
			logger.info("SOMTHING WENT WRONG  :");
		}
	}

	public void registeredTenantDisplay() throws DataException {

		try {
			List<Person> l = Tenantmanagerimpl.getInstance().display();

			l.stream()
					.forEach(e -> logger.info(e.getTenantId() + "\t" + e.getTenantName() + "\t" + e.getTenantPhone()
							+ "\t" + e.getTenantCity() + "\t" + e.getTenantDate() + "\t" + e.getTenantAadhar() + "\t"
							+ e.getTenantEmail()));

		} catch (Exception e) {
			logger.info("There is an error in registeredTenantDisplay", e);
		}
	}

	public void registeredTenantDisplayById() throws DataException {
		logger.info("Enter Tenant ID : ");
		int tenantid = s.nextInt();

		try {
			List<Person> a = Tenantmanagerimpl.getInstance().byId(tenantid);

			a.stream()
					.forEach(e -> logger.info(e.getTenantId() + "\t" + e.getTenantName() + "\t" + e.getTenantPhone()
							+ "\t" + e.getTenantCity() + "\t" + e.getTenantDate() + "\t" + e.getTenantAadhar() + "\t"
							+ e.getTenantEmail()));
		} catch (Exception e) {
			logger.info("There is an error in registeredTenantDisplayById", e);
		}
	}

	private void tenantDeleteById() {
		logger.info("Enter tenant id to Delete : ");
		int tenantID = s.nextInt();
		boolean fff = Tenantmanagerimpl.getInstance().deleteTenant(tenantID);
		if (fff) {
			logger.info("Successfully Deleted  : ");

		} else {
			logger.info("SOMTHING  WENT WRONG  :");
		}
	}

	public void newTenantAllotment() throws DataException {

		Allo allo = AppContext.getInstance().getBean("Allo");
		logger.info("Enter Tenant Name       : ");
		String name = s.nextLine();
		if (name == null || name.trim().isEmpty()) {
			logger.info("Invalid input Name is required.");
			return;
		}
		logger.info("Enter Tenant flatno     : ");
		String flatno = s.nextLine();
		if (flatno == null || flatno.trim().isEmpty()) {
			logger.info("Invalid input  flatno is  required.");
			return;
		}
		logger.info("Enter Tenant rent       : ");
		String rent = s.nextLine();
		if (rent == null || rent.trim().isEmpty()) {
			logger.info(" Invalid input Rent is required.");
			return;
		}
		logger.info("Enter Tenant Agreement  : ");
		String agreement = s.nextLine();
		if (agreement == null || agreement.trim().isEmpty()) {
			logger.info("Invalid input Agreement is required.");
			return;
		}
		logger.info("Enter Tenant StartDate  : ");
		String startDate = s.nextLine();
		if (startDate == null || startDate.trim().isEmpty()) {
			logger.info("Invalid input StartDate is required.");
			return;
		}
		logger.info("Enter Tenant EndDate    : ");
		String endDate = s.nextLine();
		if (endDate == null || endDate.trim().isEmpty()) {
			logger.info("Invalid input EndDate is required.");
			return;
		}
		allo.setName(name);
		allo.setFlatno(flatno);
		allo.setRent(rent);
		allo.setAgreement(agreement);
		allo.setStartDate(startDate);
		allo.setEndDate(endDate);
		Tenantmanagerimpl.getInstance().tenantAllotment(allo);
	}

	private void tenantAllocations() {
		try {
			List<Allo> aa = Tenantmanagerimpl.getInstance().displayAllocations();

			logger.info("+--------------+-------------+-------------+---------------+-------------+-------------+");
			logger.info("| name         | flatno      | rent        |  agreement    | startDate   |  endDate    |");
			logger.info("+--------------+-------------+-------------+---------------+-------------+-------------+");
			aa.stream().forEach(e -> logger.info("\t" + e.getName() + "\t\t" + e.getFlatno() + "\t" + e.getRent()
					+ "\t\t" + e.getAgreement() + "\t" + e.getStartDate() + "\t" + e.getEndDate()));
			logger.info("+-----------+-------------+-------------+---------------+-------------+-------------+");

		} catch (Exception e) {
			logger.info("There is an error in tenantAllocations", e);

		}
	}

	private void deleteTenantAllotment() {
		logger.info("Enter FlatNo : ");
		String flatNo = s.nextLine();
		if (flatNo == null || flatNo.trim().isEmpty()) {
			logger.info("Invalid input FlatNo is required.");
			return;
		}
		boolean fff = Tenantmanagerimpl.getInstance().deleteAllotment(flatNo);
		if (fff) {
			logger.info(" Successfully Deleted : ");

		} else {
			logger.info("SOMTHING WENT WRONG  : ");
		}
	}

	public void allocatedflatstatus() throws DataException {
		Status stat = AppContext.getInstance().getBean("Status");
		logger.info("Enter Flat No: ");
		String flat = s.nextLine();
		if (flat == null || flat.trim().isEmpty()) {
			logger.info("Invalid Input Flat is required.");
			return;
		}
		logger.info("Enter Rent Status: ");
		String rent = s.nextLine();
		if (rent == null || rent.trim().isEmpty()) {
			logger.info("Invalid input Rent is required.");
			return;
		}
		logger.info("Enter maintenence Status: ");
		String maintenence = s.nextLine();
		if (maintenence == null || maintenence.trim().isEmpty()) {
			logger.info("Invalid input Maintenence is required.");
			return;
		}
		logger.info("Enter parking Status: ");
		String parking = s.nextLine();
		if (parking == null || parking.trim().isEmpty()) {
			logger.info("Invalid input Parking is required.");
			return;
		}
		stat.setFlatno(flat);
		stat.setRent(rent);
		stat.setMaintenance(maintenence);
		stat.setParking(parking);
		Tenantmanagerimpl.getInstance().newstatus(stat);

	}

	private void allocatedFlatStatusUpdate() {
		logger.info("Enter Id: ");
		int id = s.nextInt();
		logger.info("==============================");
		logger.info("Enter 1 to Update flatno     :");
		logger.info("Enter 2 to Update rent       :");
		logger.info("Enter 3 to Update maintenance:");
		logger.info("Enter 4 to Update parking    :");
		logger.info("==============================");
		logger.info("Enter choice to Update : ");
		int choice = s.nextInt();
		s.nextLine();
		logger.info("Enter Data value: ");
		String value = s.nextLine();
		if (value == null || value.trim().isEmpty()) {
			logger.info("Invalid input. Value is required.");
			return;
		}
		Boolean updateStatus = Tenantmanagerimpl.getInstance().updateStatus(choice, value, id);
		if (Boolean.TRUE.equals(updateStatus)) {
			logger.info("Successfully Updated Status Data : ");
		} else {
			logger.info("SOMTHING WENT WRONG :");
		}
	}

	public void allocatedFlatStatusDisplay() throws DataException {

		try {
			List<Status> l = Tenantmanagerimpl.getInstance().displayStaus();
			logger.info("+-----+-----------+-------------+-------------+--------------+");
			logger.info("|id   |flatno     |rent         |maintenance  |parking      |");
			logger.info("+-----+-----------+-------------+-------------+-------------+");
			l.stream().forEach(e -> logger.info(e.getId() + "\t" + e.getFlatno() + "\t" + e.getRent() + "\t\t"
					+ e.getMaintenance() + "\t" + e.getParking()));
			logger.info("+-----+-----------+------------+-------------+------------+");

		} catch (Exception e) {
			logger.info("There is an error in AllocatedFlatStatusDisplay", e);

		}
	}

	private void allocatedFlatStatusDelete() {
		logger.info("Enter Status id to Delete : ");
		int i = 0;
		if (s.hasNextInt()) {
			i = s.nextInt();
		} else {
			logger.info("Invalid input. Must Be Integer.");
		}
		boolean fo = Tenantmanagerimpl.getInstance().deleteStatus(i);
		if (fo) {
			logger.info(" Successfully Deleted :");

		} else {
			logger.info("SOMTHING WENT WRONG :");
		}
	}

	public void checkflat() throws DataException {
		logger.info("Rented Flats from 100 to  110");
		logger.info("Enter Flat No : ");
		String flat = s.nextLine();
		if (flat == null || flat.trim().isEmpty()) {
			logger.info("Invalid input Flat is required.");
			return;
		}

		try {
			Boolean res = Tenantmanagerimpl.getInstance().check(flat);
			
			if (Boolean.TRUE.equals(res)) {
				logger.info("Flat no :  " + flat + ":  Rented  :");
			} else
				logger.info("Flat no :  " + flat + ":  Available :");
		} catch (Exception e) {
			logger.info("There is an error in checkFlat", e);
		}
	}

	private void createTables() throws DDLException{
		CommonManagerImpl.getInstance().createTables();
	}

}
