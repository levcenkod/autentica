package com.autentica.webservice.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Attr_id")
    private Attribute attr;

    @Column(name = "Creation_date")
    private Instant creationDate;

    @Column(name = "Order_status", length = 50)
    private String orderStatus;

    @Column(name = "Table_number")
    private Integer tableNumber;

    @Column(name = "Worker_name", length = 45)
    private String workerName;

    @Column(name = "Explanation")
    private String explanation;


    public Order() {
    }

    public Order(Product product, Attribute attr, Instant creationDate, String orderStatus, Integer tableNumber,
                 String workerName, String explanation) {
        this.product = product;
        this.attr = attr;
        this.creationDate = creationDate;
        this.orderStatus = orderStatus;
        this.tableNumber = tableNumber;
        this.workerName = workerName;
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Attribute getAttr() {
        return attr;
    }

    public void setAttr(Attribute attr) {
        this.attr = attr;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}