package com.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Column(name = "discounted_price")
    private int discountedPrice;

    @Column(name = "discount_present")
    private int discountPresent;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "brand")
    private String brand;

    @Column(name = "color")
    private String color;

    @Embedded
    @ElementCollection
    @Column(name = "sizes")
    private Set<Size> sizes = new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "num_ratings")
    private int numRatings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt;

    @Override
    public int hashCode() {
        return Objects.hash(brand, category, color, description, discountPresent, discountedPrice, id, imageUrl,
                numRatings, price, quantity, ratings, reviews, sizes, title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        return Objects.equals(brand, other.brand) && Objects.equals(category, other.category)
                && Objects.equals(color, other.color) && Objects.equals(description, other.description)
                && discountPresent == other.discountPresent && discountedPrice == other.discountedPrice
                && Objects.equals(id, other.id) && Objects.equals(imageUrl, other.imageUrl)
                && numRatings == other.numRatings && price == other.price && quantity == other.quantity
                && Objects.equals(ratings, other.ratings) && Objects.equals(reviews, other.reviews)
                && Objects.equals(sizes, other.sizes) && Objects.equals(title, other.title);
    }

}
