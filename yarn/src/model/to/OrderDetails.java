package model.to;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class OrderDetails implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_detailid")
	@SequenceGenerator(name="my_detailid",sequenceName="seq_detailid")
	private int detailid;
	
	@ManyToOne
	@JoinColumn(name="productid")
	private ProductInfo product;
	
	private int quantity;
	
	private float price;
	
	@ManyToOne
	@JoinColumn(name="orderid")
	private OrderInfo order;

	public int getDetailid() {
		return detailid;
	}

	public void setDetailid(int detailid) {
		this.detailid = detailid;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}
}
