package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Customer extends Model {

    //Properties
    @Id
    private Long id;

    @Constraints.Required
    private String fName;

    @Constraints.Required
    private String lName;

    @Constraints.Required
    private String address;

 


    // Default Constructor
    public Customer(){
    }

    // Constructor to initialise object
    public Customer( Long id,String fName,String lName,String address ){
        this.id= id;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
         
    }

    // Generic query helper for entity Product with id of type Long
    public static final Finder<Long, Customer> find = new Finder<>(Customer.class);

    // Return an array list of all product objects
    public static final List<Customer> findAll(){
        return Customer.find.all();
    }
    // Accessory methods
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getFName(){
        return fName;
    }
    public void setFName(String fName){
        this.fName = fName;
    }


    public String getLName(){
        return this.lName;
    }
    
    public void setLName(String lName){
        this.lName = lName;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

}