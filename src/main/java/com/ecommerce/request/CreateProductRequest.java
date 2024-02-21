package com.ecommerce.request;

import com.ecommerce.model.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
public class CreateProductRequest {

    private String title;
    private String description;
    private int price;
    private int numRatings;
    private int discountedPrice;
    private int discountPresent;
    private int quantity;
    private String brand;
    private String color;
    private Set<Size> sizes = new HashSet<>();
    private String imageUrl;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
//  private String parentCategory;


}
