package com.autentica.webservice.entities;

public class HeadphonesEntity extends AbstractProductEntity{

    private static final String headphoneAttribute = "Type";
    private static final String headphoneUnit = "";

    public HeadphonesEntity() {
        super(null, headphoneAttribute, null, headphoneUnit);
    }

    public HeadphonesEntity(String name, String attributeValue){
        super(name, headphoneAttribute, attributeValue, headphoneUnit);
    }

    @Override
    public void setAttributeValue(String size, String weight, String length, String height, String width,
                                  String headphonesType, String headsetType, String microphoneType){
        this.attributeValue = headphonesType;
    }
}
