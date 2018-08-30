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
public class CartItems implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_itemid")
	@SequenceGenerator(name="my_itemid",sequenceName="seq_itemid")
	private int itemid;
	
	@ManyToOne
	@JoinColumn(name="productid")
	private ProductInfo product;
	
	private int quantity;
	
	private float price;
	
	@ManyToOne
	@JoinColumn(name="cartid")
	private CartInfo cart;

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
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

	public CartInfo getCart() {
		return cart;
	}

	public void setCart(CartInfo cart) {
		this.cart = cart;
	}
}
