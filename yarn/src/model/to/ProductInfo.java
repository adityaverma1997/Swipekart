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
public class ProductInfo implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_productid")
	@SequenceGenerator(name="my_productid",sequenceName="seq_productid")
	private int productid;
	
	@Column(length=500)
	private String productname;
	
	@ManyToOne
	@JoinColumn(name="subcategoryid")
	private SubcategoryInfo subcategory;
	
	@ManyToOne
	@JoinColumn(name="companyid")
	private CompanyInfo company;
	
	private float price;
	
	@Column(length=3000)
	private String description;

	private float discount;

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public SubcategoryInfo getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubcategoryInfo subcategory) {
		this.subcategory = subcategory;
	}

	public CompanyInfo getCompany() {
		return company;
	}

	public void setCompany(CompanyInfo company) {
		this.company = company;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}
}