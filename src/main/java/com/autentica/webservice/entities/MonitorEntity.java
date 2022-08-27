package com.autentica.webservice.entities;

public class MonitorEntity extends AbstractProductEntity {

    private static final String monitorAttribute = "Size";
    private static final String monitorUnit = "Inches";

    public MonitorEntity() {
        super(null, monitorAttribute, null, monitorUnit);

    }

    public MonitorEntity(String name, String attributeValue){
        super(name, monitorAttribute, attributeValue, monitorUnit);
    }

    @Override
    public void setAttributeValue(String size, String weight, String length, String height, String width,
                                  String headphonesType, String headsetType, String microphoneType){
        this.attributeValue = size;
    }
}
