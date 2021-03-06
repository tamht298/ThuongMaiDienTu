package com.shopping4th.ecommerce.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Blob;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name = "products")
public class Product{

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id")
	private Long id;
	
	@Column(name="product_code")
	private String productCode;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private String price;
	
	
	@Column(name="stock")
	private int stock;
	
	@Column(name="cpu")
	private String cpu;
	
	@Column(name="description")
	private String description;
	
	@Column(name="ram")
	private String ram;
	
	@Column(name="hard_disk")
	private String hardDisk;
	
	@Column(name="screen")
	private String screen;	
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
    
	
	@UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)

    @OnDelete(action = OnDeleteAction.CASCADE)
  
	//@JsonIgnore
	@JoinColumn(name="category_id", nullable = false)
	
	private Categories category;
	
	@ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Set<Promotion> promotions = new HashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    
    @JoinColumn(name = "product_id")
    private List<Images> images;
	
    
//    @ManyToMany(mappedBy = "products")
//    Set<Vouchers> vouchers;
    
	public Product() {
		super();
	}


	public Product(String productCode, String name, String price, int stock, String cpu, String ram,
			String hardDisk, String screen, Categories category, String description) {
		super();
		this.productCode = productCode;
		this.name = name;
		this.price = price;
	
		this.stock = stock;
		this.cpu = cpu;
		this.ram = ram;
		this.hardDisk = hardDisk;
		this.screen = screen;
		this.category = category;
		this.description=description;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHardDisk() {
		return hardDisk;
	}

	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void add(Images imageList) {
	        if (images == null) {
	            images = new ArrayList<Images>();
	        }
	        images.add(imageList);
	    }

}
