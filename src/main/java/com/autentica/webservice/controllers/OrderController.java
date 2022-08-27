package com.autentica.webservice.controllers;

import com.autentica.webservice.entities.*;
import com.autentica.webservice.factories.ProductFactory;
import com.autentica.webservice.models.Attribute;
import com.autentica.webservice.models.Order;
import com.autentica.webservice.models.Product;
import com.autentica.webservice.repositories.AttributeRepository;
import com.autentica.webservice.repositories.OrderRepository;
import com.autentica.webservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;

@Controller
public class OrderController {

    private static final String newOrderStatus = "Awaiting";

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeRepository attributeRepository;

    private ProductFactory productFactory;

    public OrderController(){
        productFactory = new ProductFactory();
        productFactory.registerProduct("monitor", new MonitorEntity());
        productFactory.registerProduct("keyboard", new KeyboardEntity());
        productFactory.registerProduct("mouse", new MouseEntity());
        productFactory.registerProduct("headphones", new HeadphonesEntity());
        productFactory.registerProduct("headset", new HeadsetEntity());
        productFactory.registerProduct("microphone", new MicrophoneEntity());

    }

    @GetMapping("/orders")
    @PreAuthorize("hasRole('ROLE_HELPDESK')")
    public String orderPage(Model model){
        Iterable<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @PostMapping("/addOrder")
    public String addOrder(@RequestParam Integer tableNum,
                           @RequestParam String workerName,
                           @RequestParam String explanation,
                           @RequestParam String productType,
                           @RequestParam (required=false) String headphonesType,
                           @RequestParam (required=false) String headsetType,
                           @RequestParam (required=false) String microphoneType,
                           @RequestParam String monitorSize,
                           @RequestParam String keyboardHeight,
                           @RequestParam String keyboardWidth,
                           @RequestParam String keyboardLength,
                           @RequestParam String mouseWeight) {

        var newProduct = productFactory.createProduct(productType, productType, monitorSize, mouseWeight, keyboardLength,
                keyboardHeight, keyboardWidth, headphonesType, headsetType, microphoneType);

        /* only now understand that in my case product type == product name,
         because I have only 1 example for each type of product */


        //At first trying to find product with same parameters, so it wouldn't be duplicates in DB tables
        Iterable<Product> products = productRepository.findAll();
        Product product = findOrCreateProduct(products, newProduct);

        Iterable<Attribute> attributes = attributeRepository.findAll();
        Attribute attribute = findOrCreateAttribute(attributes, newProduct);

        Instant datetime = java.time.Clock.systemUTC().instant();
        Order order =  new Order(product, attribute, datetime, newOrderStatus, tableNum, workerName, explanation);
        orderRepository.save(order);

        return "home";
    }

    @GetMapping("/order/{id}")
    @PreAuthorize("hasRole('ROLE_HELPDESK')")
    public String orderDetails(@PathVariable(value = "id") Long orderId, Model model){
        Optional<Order> order =  orderRepository.findById(orderId);
        ArrayList<Order> res = new ArrayList<>();
        order.ifPresent(res::add);    // in model, it is easier to work with ArrayList than with Optional
        model.addAttribute("order", res);
        return "order-details";
    }


    @PostMapping("/order/{id}/deleteOrder")
    @PreAuthorize("hasRole('ROLE_HELPDESK')")
    public String deleteOrder(@PathVariable (value = "id") Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        orderRepository.delete(order);
        return "redirect:/orders";
    }

    @PostMapping("/order/{id}/completeOrder")
    @PreAuthorize("hasRole('ROLE_HELPDESK')")
    public String completeOrder(@PathVariable (value = "id") Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setOrderStatus("Completed");
        orderRepository.save(order);
        return "redirect:/orders";
    }

    public Product findOrCreateProduct(Iterable<Product> products, AbstractProductEntity newProduct){
        Product product = null;
        for (Product prod : products){
            if (prod.getName().equals(newProduct.getName())){
                product = prod;
                break;
            }
        }

        if (product == null){
            product = new Product(newProduct.getName());
            productRepository.save(product);
        }
        return product;
    }

    public Attribute findOrCreateAttribute(Iterable<Attribute> attributes, AbstractProductEntity newProduct){
        Attribute attribute = null;
        for (Attribute attr : attributes){
            if (attr.getAttrType().equals(newProduct.getAttributeType()) &&
                    attr.getAttrUnit().equals(newProduct.getAttributeUnit()) &&
                    attr.getAttrValue().equals(newProduct.getAttributeValue())){

                attribute = attr;
                break;
            }
        }

        if (attribute == null){
            attribute = new Attribute(newProduct.getAttributeType(), newProduct.getAttributeValue(), newProduct.getAttributeUnit());
            attributeRepository.save(attribute);
        }

        return attribute;
    }

}
