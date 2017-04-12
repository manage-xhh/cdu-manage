package com.cdu.common.Enum;

public enum DriverStatusEnum {
    AUDIT((byte)1 , "待审核") , ACCEPT((byte)2 , "已审核");
    
    private Byte name;
    private String value;
    
    private DriverStatusEnum(byte name , String value){
        this.name = name;
        this.value = value;
    }
    
    public static String getValueByName(Byte name) {
        for (DriverStatusEnum ds : DriverStatusEnum.values()) {
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
