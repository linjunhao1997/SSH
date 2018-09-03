package com.junhao.domain;

import java.util.HashSet;  
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;  
import javax.persistence.OneToMany;  
@Entity  
public class Garage {  

    /** 
     * many to one 多对一 
     */  
    private Integer gid;  
    private String garagenum;  
    private Set<Auto> autos = new HashSet<Auto>();  

    @Id @GeneratedValue  
    public Integer getGid() {  
        return gid;  
    }  
    public void setGid(Integer gid) {  
        this.gid = gid;  
    }  
    @Column(length=20)  
    public String getGaragenum() {  
        return garagenum;  
    }  
    public void setGaragenum(String garagenum) {  
        this.garagenum = garagenum;  
    }  
    @OneToMany(mappedBy="garage",cascade= {CascadeType.ALL} )  
    public Set<Auto> getAutos() {  
        return autos;  
    }  
    public void setAutos(Set<Auto> autos) {  
        this.autos = autos;  
    }  
    public void addGarageAuto(Auto auto) {  
        auto.setGarage(this);  
        this.autos.add(auto);  
    }  

}  
