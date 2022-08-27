package com.autentica.webservice.models;

import javax.persistence.*;

@Entity
@Table(name = "attributes")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Attr_id", nullable = false)
    private Long id;

    @Column(name = "Attr_type")
    private String attrType;

    @Column(name = "Attr_value")
    private String attrValue;

    @Column(name = "Attr_unit")
    private String attrUnit;

    public Attribute() {
    }

    public Attribute(String attrType, String attrValue, String attrUnit) {
        this.attrType = attrType;
        this.attrValue = attrValue;
        this.attrUnit = attrUnit;
    }

    public String getAttrUnit() {
        return attrUnit;
    }

    public void setAttrUnit(String attrUnit) {
        this.attrUnit = attrUnit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

}