
package com.ecommerce.controller;

import com.ecommerce.exception.ProductException;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.request.CreateProductRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@Data
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws ProductException {

        Product createdProduct = productService.CreateProduct(req);

        return new ResponseEntity<Product>(createdProduct, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {

        //  System.out.println("delete product controller....");
        String msg = productService.deleteProduct(productId);

        System.out.println("delete product controller ...msg" + msg);
        ApiResponse response = new ApiResponse(msg, true);
        return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProductHandler(@RequestBody Product product, @PathVariable Long productId) throws
            ProductException {
        Product updatedProduct = productService.updateProduct(productId, product);
        return new ResponseEntity<Product>(updatedProduct, HttpStatus.CREATED);
    }

//    @PostMapping("/creates")
//    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs) throws ProductException{
//
//        for(CreateProductRequest product:reqs) {
//            productService.CreateProduct(product);
//        }
//
//        ApiResponse res=new ApiResponse("products created successfully",true);
//        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
//    }


    @PostMapping("/creates")
    public ResponseEntity<List<Product>> createMultipleProduct(@RequestBody List<CreateProductRequest> requests) throws
            ProductException {
        List<Product> productList = new ArrayList<>();
        for (CreateProductRequest productRequest : requests) {
            Product product = productService.CreateProduct(productRequest);
            productList.add(product);
        }
        ApiResponse response = new ApiResponse("product created successfully", true);
        return new ResponseEntity<List<Product>>(productList, HttpStatus.CREATED);
    }

}

