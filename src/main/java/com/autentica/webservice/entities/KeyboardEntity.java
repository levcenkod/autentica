package com.autentica.webservice.entities;

public class KeyboardEntity extends AbstractProductEntity {

    private static final String keyboardAttribute = "Dimension";
    private static final String keyboardUnit = "";

    public KeyboardEntity() {
        super(null, keyboardAttribute, null, keyboardUnit);

    }

    public KeyboardEntity(String name, String attributeValue){
        super(name, keyboardAttribute, attributeValue, keyboardUnit);
    }

    @Override
    public void setAttributeValue(String size, String weight, String length, String height, String width,
                                  String headphonesType, String headsetType, String microphoneType){
        this.attributeValue = height.concat("x").concat(width).concat("x").concat(length);
    }
}
