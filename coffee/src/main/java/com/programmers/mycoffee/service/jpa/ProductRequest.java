package com.programmers.mycoffee.service.jpa;

import com.programmers.mycoffee.model.Category;
import lombok.Data;

@Data
public class ProductRequest {


    private String productName;

    private Category category;

    private int price;

    private String description;

}
