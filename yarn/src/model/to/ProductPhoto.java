package model.to;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ProductPhoto implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_photoid")
	@SequenceGenerator(name="my_photoid",sequenceName="seq_photoid")
	private int photoid;
	
	@Column(length=100)
	private String photoname;
	
	@Column(length=100)
	private String extname;
	
	@Column(length=100)
	private String phototype;
	
	private int photosize;
	
	@ManyToOne
	@JoinColumn(name="productid")
	private ProductInfo product;

	public int getPhotoid() {
		return photoid;
	}

	public void setPhotoid(int photoid) {
		this.photoid = photoid;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getExtname() {
		return extname;
	}

	public void setExtname(String extname) {
		this.extname = extname;
	}

	public String getPhototype() {
		return phototype;
	}

	public void setPhototype(String phototype) {
		this.phototype = phototype;
	}

	public int getPhotosize() {
		return photosize;
	}

	public void setPhotosize(int photosize) {
		this.photosize = photosize;
	}

	public ProductInfo getProduct() {
		return product;
	}

	public void setProduct(ProductInfo product) {
		this.product = product;
	}
}
