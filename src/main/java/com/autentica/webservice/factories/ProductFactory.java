package com.autentica.webservice.factories;

import com.autentica.webservice.entities.AbstractProductEntity;
import com.autentica.webservice.models.Product;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//Creating factory, so I can avoid a lot of if-else statements to find difference between products
public class ProductFactory {

    private HashMap<String, AbstractProductEntity> productTypes;

    public ProductFactory(){
        productTypes = new HashMap<>();
    }  //dictionary for product types

    public AbstractProductEntity createProduct(String productType, String name, String size, String weight,
                                 String length, String height, String width, String headphonesType,
                                 String headsetType, String microphoneType) {

        var newProduct = productTypes.get(productType);
        newProduct.setName(name);

        //passing all data from request and using polymorphism will handle attribute setting for each product
        newProduct.setAttributeValue(size, weight, length, height, width, headphonesType, headsetType, microphoneType);

        return newProduct;
    }

    public void registerProduct(String productName, AbstractProductEntity productType){
        if (productName.isEmpty() || productType.equals(null)) return;
        productTypes.put(productName, productType);
    }
}
