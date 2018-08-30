package model.to;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class CartInfo implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_cartid")
	@SequenceGenerator(name="my_cartid",sequenceName="seq_cartid")
	private int cartid;
	
	@Column(length=100)
	private String username;
	
	@Column(nullable=true)
	private Date cartdate;
	
	@Column(length=10)
	private String isorder;

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCartdate() {
		return cartdate;
	}

	public void setCartdate(Date cartdate) {
		this.cartdate = cartdate;
	}

	public String getIsorder() {
		return isorder;
	}

	public void setIsorder(String isorder) {
		this.isorder = isorder;
	}
}
