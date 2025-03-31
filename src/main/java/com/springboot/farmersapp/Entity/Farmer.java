package com.springboot.farmersapp.Entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "farmers")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long farmerId;

    private String farmName;
    private String farmDescription;
    private String farmLocation;
    private String contactInfo;
    private Double rating;

    @ElementCollection
    private List<String> audioVideoContent;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
        name = "farmer_product",
        joinColumns = @JoinColumn(name = "farmer_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnoreProperties("farmers")
    private Set<Product> products;
    
    public Farmer() {}

    public Farmer(String farmName, String farmDescription, String farmLocation, String contactInfo, Double rating, List<String> audioVideoContent, Set<Product> products) {
        this.farmName = farmName;
        this.farmDescription = farmDescription;
        this.farmLocation = farmLocation;
        this.contactInfo = contactInfo;
        this.rating = rating;
        this.audioVideoContent = audioVideoContent;
        this.products = products;
    }
    public void addProduct(Product product) {
        this.products.add(product);
        product.getFarmers().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getFarmers().remove(this);
    }

   
    public Long getFarmerId() { return farmerId; }
    public void setFarmerId(Long farmerId) { this.farmerId = farmerId; }

    public String getFarmName() { return farmName; }
    public void setFarmName(String farmName) { this.farmName = farmName; }

    public String getFarmDescription() { return farmDescription; }
    public void setFarmDescription(String farmDescription) { this.farmDescription = farmDescription; }

    public String getFarmLocation() { return farmLocation; }
    public void setFarmLocation(String farmLocation) { this.farmLocation = farmLocation; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public List<String> getAudioVideoContent() { return audioVideoContent; }
    public void setAudioVideoContent(List<String> audioVideoContent) { this.audioVideoContent = audioVideoContent; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }
}
