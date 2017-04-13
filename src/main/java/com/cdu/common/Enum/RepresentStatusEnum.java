package com.cdu.common.Enum;

public enum RepresentStatusEnum {
    HOME((byte)1 , "首页") , BACKSTAGE((byte)2 , "后台");
    
    private Byte name;
    private String value;
    
    private RepresentStatusEnum(byte name , String value){
        this.name = name;
        this.value = value;
    }
    
    public static String getValueByName(Byte name) {
        for (RepresentStatusEnum ds : RepresentStatusEnum.values()) {
            if (ds.getName() == name) {
                return ds.getValue();
            }
        }
        return null;
    }

    public Byte getName() {
        return name;
    }

    public void setName(Byte name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }   
}
