package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nicholas
 */

public class RatesBean {
    private double cost;
    private int premiumDays;
    private int normalDays;
    private String details;
    
    public RatesBean(){
        this.cost = 0;
        this.premiumDays = 0;
        this.normalDays = 0;
        this.details = null;
        
    }
    
    public void setCost(double libCost){
        this.cost = libCost;
    }
    
    public void setPremiumDays(int libPDays){
        this.premiumDays = libPDays;
    }
    
    public void setNormalDays(int libNDays){
        this.normalDays = libNDays;
    }
    
    public double getCost(){
        return cost;
    }
    
    public int getPremiumDays(){
        return premiumDays;
    }
    
    public int getNormalDays(){
        return normalDays;
    }
    
    public void setDetails(String libDetails){
        this.details = libDetails;
    }
    
    public String getDetails(){
        return details;
    }
}
