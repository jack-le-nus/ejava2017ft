package week1.ft.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class Customer {

	private Integer customerId;
	private String name;
	private String address;
	private String city;
	private String email;
	private String phone;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static Customer populate(ResultSet rs) throws SQLException {
		Customer c = new Customer();
		c.address = rs.getString("ADDRESSLINE1");
		c.city = rs.getString("CITY");
		c.customerId = rs.getInt("CUSTOMER_ID");
		c.email = rs.getString("EMAIL");
		c.name = rs.getString("NAME");
		c.phone = rs.getString("PHONE");

		return (c);
	}

}
