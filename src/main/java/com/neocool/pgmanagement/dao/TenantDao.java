package com.neocool.pgmanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.neocool.pgmanagement.model.Allo;
import com.neocool.pgmanagement.model.Allotment;
import com.neocool.pgmanagement.model.Flats;
import com.neocool.pgmanagement.model.Person;
import com.neocool.pgmanagement.model.Status;
// import com.neocool.pgmanagement.model.Status;
import com.neocool.pgmanagement.utils.AppContext;
import com.neocool.pgmanagement.utils.exception.DataException;

public class TenantDao extends BaseDAO {
	static final Logger logger = Logger.getLogger(TenantDao.class);

	private static TenantDao tenantDao = null;
	private static String err = "There is an Error";

	private TenantDao() {
	}

	public static TenantDao getInstance() {

		if (tenantDao == null) {
			tenantDao = new TenantDao();
		}

		return tenantDao;
	}

	@SuppressWarnings("finally")
	public Person saveTenant(Person person) throws DataException {
		String insert = "insert into registertenant(tname,tphone,tcity,jdate,aadhar,email) values(?,?,?,?,?,?)";
		try (Connection conn = getConnection(); PreparedStatement p = conn.prepareStatement(insert)) {
			p.setString(1, person.getTenantName());
			p.setString(2, person.getTenantPhone());
			p.setString(3, person.getTenantCity());
			p.setString(4, person.getTenantDate());
			p.setString(5, person.getTenantAadhar());
			p.setString(6, person.getTenantEmail());
			int row = p.executeUpdate();
			logger.info(row + " Executed      ");
		} catch (SQLException sqle) {
			logger.info(err, sqle);
			throw new DataException(sqle.toString(), sqle);
		} catch (Exception exp) {
			logger.info(err, exp);
			throw new DataException(exp.toString(), exp);
		} finally {
			return person;
		}

	}

	public Status saveStatus(Status stat) throws DataException {
		String insert = "insert into status(flatno,rent,maintenance,parking) values(?,?,?,?)";
		try (Connection conn = getConnection(); PreparedStatement p = conn.prepareStatement(insert)) {
			p.setString(1, stat.getFlatno());
			p.setString(2, stat.getRent());
			p.setString(3, stat.getMaintenance());
			p.setString(4, stat.getParking());
			int row = p.executeUpdate();
			logger.info(row + " executed  ");
		} catch (SQLException sqle) {
			logger.info(err, sqle);

			throw new DataException(sqle.toString(), sqle);
		} catch (Exception exp) {
			logger.info(err, exp);
			throw new DataException(exp.toString(), exp);
		}
		return stat;
	}

	public void stat(Allo allo) {
		String stat = "UPDATE flats SET stat = 'no' WHERE flatno=?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(stat)) {
			p.setString(1, allo.getFlatno());
			p.executeUpdate();

		} catch (SQLException e) {
			logger.info(err, e);
		}
	}

	public void yesstat(String flatNo) {
		String stat = "UPDATE flats SET stat = 'yes' WHERE flatno=?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(stat)) {
			p.setString(1, flatNo);
			p.executeUpdate();

		} catch (Exception e) {
			logger.info(err, e);

		}
	}

	public void allotmentSave(Allo allo) throws DataException {
		String insertt = "insert into allotment(name,flatno,rent,agreement,startDate,endDate) values(?,?,?,?,?,?)";
		try (Connection conn = getConnection(); PreparedStatement p = conn.prepareStatement(insertt)) {
			p.setString(1, allo.getName());
			p.setString(2, allo.getFlatno());
			p.setString(3, allo.getRent());
			p.setString(4, allo.getAgreement());
			p.setString(5, allo.getStartDate());
			p.setString(6, allo.getEndDate());
			int row = p.executeUpdate();
			logger.info(row + "   Executed");
		} catch (SQLException sqle) {
			logger.info(err, sqle);
			throw new DataException(sqle.toString(), sqle);
		} catch (Exception exp) {
			logger.info(err, exp);
			throw new DataException(exp.toString(), exp);
		}
	}

	public Allotment saveAllotment(Allo allo) throws DataException {
		try {
			allotmentSave(allo);
			stat(allo);
		} catch (Exception exp) {
			logger.info(err, exp);
			throw new DataException(exp.toString(), exp);
		}
		return allo;
	}

	public void deletewithAllotwithstatus(String flatNo) {
		String stat = "DELETE FROM status WHERE flatno=?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(stat)) {
			p.setString(1, flatNo);
			p.executeUpdate();
		} catch (Exception e) {
			logger.info(err, e);
		}
	}

	public Boolean deleteAllotment(String flatNo) {
		boolean f = false;
		String sql = "DELETE FROM allotment WHERE flatno=?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(sql)) {
			p.setString(1, flatNo);
			p.executeUpdate();
			yesstat(flatNo);
			deletewithAllotwithstatus(flatNo);
			f = true;
		} catch (Exception e) {
			logger.info(err, e);
		}
		return f;
	}

	public Boolean deleteStatus(int i) {
		boolean f = false;
		String sql = "DELETE FROM Status WHERE idstatus=?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(sql)) {
			p.setInt(1, i);
			p.executeUpdate();
			f = true;
		} catch (

		Exception e) {
			logger.info(err, e);
		}
		return f;

	}

	public Person setPerson(ResultSet res) throws SQLException {
		Person person = AppContext.getInstance().getBean("Person");
		person.setTenantId(res.getInt(1));
		person.setTenantName(res.getString(2));
		person.setTenantPhone(res.getString(3));
		person.setTenantCity(res.getString(4));
		person.setTenantDate(res.getString(5));
		person.setTenantAadhar(res.getString(6));
		person.setTenantEmail(res.getString(7));
		return person;
	}

	public List<Person> displayTenant() {
		String display = "select * from registertenant";
		List<Person> l = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(display)) {
			ResultSet res = p.executeQuery();
			while (res.next()) {
				l.add(setPerson(res));
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return l;
	}

	public List<Allo> displayTenantAllo() {
		String displayy = "select * from allotment";
		List<Allo> aa = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(displayy)) {
			ResultSet res = p.executeQuery();
			while (res.next()) {
				Allo allo = AppContext.getInstance().getBean("Allo");
				allo.setAllotmentId(res.getInt(1));
				allo.setName(res.getString(2));
				allo.setFlatno(res.getString(3));
				allo.setRent(res.getString(4));
				allo.setAgreement(res.getString(5));
				allo.setStartDate(res.getString(6));
				allo.setEndDate(res.getString(7));
				aa.add(allo);
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return aa;
	}

	public List<Person> viewTenant(int tenantID) {
		String biId = "select * from registertenant where id=?";
		List<Person> a = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(biId)) {
			p.setInt(1, tenantID);
			ResultSet res = p.executeQuery();
			while (res.next()) {
				a.add(setPerson(res));
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return a;
	}

	public Boolean deleteTenant(int tenantID) {
		boolean f = false;
		String sql = "DELETE FROM registertenant WHERE id=?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(sql)) {
			p.setInt(1, tenantID);
			p.executeUpdate();
			f = true;
		} catch (SQLException e) {
			logger.info(err, e);
		}
		return f;

	}

	public boolean updateTenant(int choice, String value, int id) {
		boolean mark = false;
		String q = null;
		switch (choice) {
		case 1:
			q = "UPDATE registertenant SET tname = ? WHERE  id= ?";
			break;
		case 2:
			q = "UPDATE registertenant SET tphone = ? WHERE  id= ?";
			break;
		case 3:
			q = "UPDATE registertenant SET email = ? WHERE  id= ?";
			break;
		default:
		}
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(q)) {
			p.setString(1, value);
			p.setInt(2, id);
			p.executeUpdate();
			mark = true;
		} catch (SQLException e) {
			logger.info(err, e);
		}
		return mark;
	}

	public boolean updateStatus(int ch, String value, int id) {
		boolean f = false;
		String sql = null;
		switch (ch) {
		case 1:
			sql = "UPDATE status SET flatno = ?  WHERE idstatus= ?";
			break;
		case 2:
			sql = "UPDATE status SET rent =  ?   WHERE idstatus= ? ";
			break;
		case 3:
			sql = "UPDATE status SET maintenance = ? WHERE idstatus= ?";
			break;
		case 4:
			sql = "UPDATE status SET parking =  ? WHERE idstatus= ?";
			break;
		default:
		}
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(sql)) {
			p.setString(1, value);
			p.setInt(2, id);
			p.executeUpdate();
			f = true;
		} catch (Exception e) {
			logger.info(err, e);
		}
		return f;
	}

	public List<Allo> viewrentedflats() {
		String rdisplay = "SELECT name,flatno FROM allotment";
		List<Allo> d = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(rdisplay)) {
			ResultSet res = p.executeQuery();
			while (res.next()) {
				Allo allo = AppContext.getInstance().getBean("Allo");
				allo.setName(res.getString(1));
				allo.setFlatno(res.getString(2));
				d.add(allo);
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return d;
	}

	public List<Flats> viewavailableflats() {
		String rdisplay = "SELECT flatno,stat FROM flats WHERE stat='yes'";
		List<Flats> d = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(rdisplay)) {
			ResultSet res = p.executeQuery();
			while (res.next()) {
				d.add(new Flats(res.getString(1)));
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return d;
	}

	public Boolean checkflat(String flat) {
		String f = "SELECT flatno FROM allotment WHERE flatno = ?";
		Boolean status = false;
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(f)) {
			p.setString(1, flat);
			ResultSet res = p.executeQuery();
			if (res.next()) {
				status = true;
			} else {
				status = false;
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return status;
	}

	public List<Status> displayStatus() {
		String display = "select * from status";
		List<Status> l = new ArrayList<>();
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(display)) {
			ResultSet res = p.executeQuery();
			while (res.next()) {
				l.add(new Status(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5)));
			}
		} catch (Exception e) {
			logger.info(err, e);
		}
		return l;
	}

	public Flats addflats(Flats flat) throws DataException {
		String insertt = "insert into flats(flatno,stat) values(?,'yes')";
		try (Connection conn = getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(insertt)) {
			preparedStatement.setString(1, flat.getFlatno());
			int row = preparedStatement.executeUpdate();
			logger.info(row + " Executed :");
		} catch (SQLException sqle) {
			logger.info(err, sqle);
			throw new DataException(sqle.toString(), sqle);
		}
		return flat;
	}

	public Boolean deleteFlat(String flat) {
		boolean f = false;
		String sql = "DELETE FROM flats WHERE flatno= ?";
		try (Connection con = getConnection(); PreparedStatement p = con.prepareStatement(sql)) {
			p.setString(1, flat);
			p.executeUpdate();
			f = true;
		} catch (Exception e) {
			logger.info(err, e);
		}
		return f;
	}
}
