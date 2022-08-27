package com.autentica.webservice.entities;

public class HeadsetEntity extends AbstractProductEntity{

    private static final String headsetAttribute = "Type";
    private static final String headsetUnit = "";

    public HeadsetEntity() {
        super(null, headsetAttribute, null, headsetUnit);
    }

    public HeadsetEntity(String name, String attributeValue){
        super(name, headsetAttribute, attributeValue, headsetUnit);
    }

    @Override
    public void setAttributeValue(String size, String weight, String length, String height, String width,
                                  String headphonesType, String headsetType, String microphoneType){
        this.attributeValue = headsetType;
    }
}
