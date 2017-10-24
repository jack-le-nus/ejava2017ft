package week1.ft.web;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@RequestScoped
@Named
public class AllCustomers {

	private static final String FIND_ALL = "select * from CUSTOMER";

	@Resource(lookup = "ejavaftProject")
	private DataSource ds;

	private List<Customer> customers = new LinkedList<>();

	@PostConstruct
	private void init() {

		try (Connection conn = ds.getConnection()) {

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(FIND_ALL);
			while (rs.next())
				customers.add(Customer.populate(rs));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	
}
