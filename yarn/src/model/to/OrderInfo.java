package model.to;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class OrderInfo implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_orderid")
	@SequenceGenerator(name="my_orderid",sequenceName="seq_orderid")
	private int orderid;
	
	@ManyToOne
	@JoinColumn(name="username")
	private LoginInfo login;
	
	@ManyToOne
	@JoinColumn(name="cartid")
	private CartInfo cart;
	
	@Column(nullable=true)
	private Date orderdate;
	
	@Column(nullable=true)
	private Date shippingdate;
	
	@Column(length=1000)
	private String orderstatus;
	
	@Column(length=1000)
	private String shippingaddress;
	
	@Column(length=1000)
	private String paymentdetails;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public LoginInfo getLogin() {
		return login;
	}

	public void setLogin(LoginInfo login) {
		this.login = login;
	}

	public CartInfo getCart() {
		return cart;
	}

	public void setCart(CartInfo cart) {
		this.cart = cart;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	public Date getShippingdate() {
		return shippingdate;
	}

	public void setShippingdate(Date shippingdate) {
		this.shippingdate = shippingdate;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getShippingaddress() {
		return shippingaddress;
	}

	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getPaymentdetails() {
		return paymentdetails;
	}

	public void setPaymentdetails(String paymentdetails) {
		this.paymentdetails = paymentdetails;
	}
}
