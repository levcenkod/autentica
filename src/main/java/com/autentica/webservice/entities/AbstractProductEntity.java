package com.autentica.webservice.entities;

abstract public class AbstractProductEntity {

    protected String name;
    protected String attributeType;
    protected String attributeValue;
    protected String attributeUnit;

    public AbstractProductEntity() {
    }

    public AbstractProductEntity(String name, String attributeType, String attributeValue, String attributeUnit) {
        this.name = name;
        this.attributeType = attributeType;
        this.attributeValue = attributeValue;
        this.attributeUnit = attributeUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    //different products have different attributes, so will override for them setting implementation
    abstract public void setAttributeValue(String size, String weight, String length, String height, String width,
                                           String headphonesType, String headsetType, String microphoneType);

    public String getAttributeUnit() {
        return attributeUnit;
    }

    public void setAttributeUnit(String attributeUnit) {
        this.attributeUnit = attributeUnit;
    }
}
