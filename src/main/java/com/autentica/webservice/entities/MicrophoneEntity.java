package com.autentica.webservice.entities;

public class MicrophoneEntity extends AbstractProductEntity {

    private static final String microphoneAttribute = "Type";
    private static final String microphoneUnit = "";

    public MicrophoneEntity() {
        super(null, microphoneAttribute, null, microphoneUnit);

    }

    public MicrophoneEntity(String name, String attributeValue){
        super(name, microphoneAttribute, attributeValue, microphoneUnit);
    }

    @Override
    public void setAttributeValue(String size, String weight, String length, String height, String width,
                                  String headphonesType, String headsetType, String microphoneType){
        this.attributeValue = microphoneType;
    }
}
