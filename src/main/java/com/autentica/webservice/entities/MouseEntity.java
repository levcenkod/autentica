package com.autentica.webservice.entities;

public class MouseEntity extends AbstractProductEntity {

    private static final String mouseAttribute = "Weight";
    private static final String mouseUnit = "KG";

    public MouseEntity() {
        super(null, mouseAttribute, null, mouseUnit);

    }

    public MouseEntity(String name, String attributeValue){
        super(name, mouseAttribute, attributeValue, mouseUnit);
    }

    @Override
    public void setAttributeValue(String size, String weight, String length, String height, String width,
                                  String headphonesType, String headsetType, String microphoneType){
        this.attributeValue = weight;
    }
}
