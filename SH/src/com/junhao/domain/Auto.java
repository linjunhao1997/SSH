package com.junhao.domain;

import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;  
import javax.persistence.JoinColumn;  
import javax.persistence.ManyToOne;  
@Entity  
public class Auto {  

    /** 
     * one to many 一对多关联 
     */  
    private Integer autoId;  
    private String autotype;  
    private String autonum;  
    private Garage garage;  

    @Id @GeneratedValue  
    public Integer getAutoId() {  
        return autoId;  
    }  
    public void setAutoId(Integer autoId) {  
        this.autoId = autoId;  
    }  
    public String getAutotype() {  
        return autotype;  
    }  
    public void setAutotype(String autotype) {  
        this.autotype = autotype;  
    }  
    public String getAutonum() {  
        return autonum;  
    }  
    public void setAutonum(String autonum) {  
        this.autonum = autonum;  
    }  
    @ManyToOne 
    @JoinColumn(name="garageid")  
    public Garage getGarage() {  
        return garage;  
    }  
    public void setGarage(Garage garage) {  
        this.garage = garage;  
    }  

}  
