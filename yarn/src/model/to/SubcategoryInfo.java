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
public class SubcategoryInfo implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="my_subcategoryid")
	@SequenceGenerator(name="my_subcategoryid",sequenceName="seq_subcategoryid")
	private int subcategoryid;
	
	@Column(length=100)
	private String subcategoryname;
	
	@Column(length=3000)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private CategoryInfo category;

	public int getSubcategoryid() {
		return subcategoryid;
	}

	public void setSubcategoryid(int subcategoryid) {
		this.subcategoryid = subcategoryid;
	}

	public String getSubcategoryname() {
		return subcategoryname;
	}

	public void setSubcategoryname(String subcategoryname) {
		this.subcategoryname = subcategoryname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CategoryInfo getCategory() {
		return category;
	}

	public void setCategory(CategoryInfo category) {
		this.category = category;
	}
}
