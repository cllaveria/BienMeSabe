package com.bienmesabe.rest.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * This class defines the objects of User type
 * @author RAUL RAMOS CENDRERO
 * @version 11/04/2021
 */
@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", updatable = false, nullable = false)
    private Long id;
    @Column(name="USER_NIF")
    private String NIF;
    @Column(name="USER_IMAGE")
    private String image;
    @Column(name="USER_NAME")
    private String name;
    @Column(name="USER_SURNAME")
    private String surname;
    @Column(name="USER_PASSWORD")
    private String password;
    @Column(name="USER_ALIAS")
    private String alias;
    @Column(name="USER_EMAIL")
    private String email;
    @Column(name="USER_PHONE")
    private String phone;
    @Column(name="USER_TYPE")
    private int type;
    @Column(name="USER_CREATEDAT")
    @CreationTimestamp
    private Date createdAt;
    @Column(name="USER_UPDATEDAT")
    @UpdateTimestamp
    private Date updatedAt;
    @Column(name="USER_ACTIVE")
    private int active;

    private String token;
    
    private String tokenEndValidityDate;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<AdminContact> adminContacts;
    
    /**
     * Empty Constructor
     */
    public User() {
    }

    /**
     * User Constructor with the required parameters
     * @param name string that represents the name of the user
     * @param password string that represents the password of the user
     * @param email string that represents the email of the user
     */
    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    /**
     * User Constructor with the required parameters
     * @param name string that represents the name of the user
     * @param password string that represents the password of the user
     * @param email string that represents the email of the user
     * @param alias string that represents the alias of the user
     */
    public User(String name, String password, String email,String alias) {
        this.name = name;
        this.password = password;
        this.alias = alias;
        this.email = email;
    }

    /**
     * User Constructor with the required parameters
     * @param id long that represents the id of the user
     * @param image string that represents the image path of the user 
     * @param name string that represents the name of the user
     * @param surname string that represents the surname of the user
     * @param alias string that represents the alias of the user
     * @param email string that represents the email of the user
     * @param type int that represents the type of the user
     */
    public User(Long id, String image, String name, String surname, String alias, String email, int type) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.surname = surname;
        this.alias = alias;
        this.email = email;
        this.type = type;
    }

    /**
     * User Constructor with the required parameters
     * @param id long that represents the id of the user
     * @param name string that represents the name of the user
     * @param password string that represents the password of the user
     * @param email string that represents the email of the user
     * @param alias string that represents the alias of the user
     */
    public User(Long id, String name, String password, String email, String alias) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.alias = alias;
        this.email = email;
    }
    
    /**
     * User Constructor with the required parameters
     * @param id long that represents the id of the user
     * @param image string that represents the image path of the user 
     * @param name string that represents the name of the user
     * @param surname string that represents the surname of the user
     * @param alias string that represents the alias of the user
     * @param email string that represents the email of the user
     * @param phone string that represents the phone of the user
     * @param type int that represents the type of the user
     */
    public User(Long id, String image, String name, String surname, String alias, String email, String phone, int type) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.surname = surname;
        this.alias = alias;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

    /**
     * User's Constructor with all required parameters
     * @param name string that represents the name of the user
     * @param password string that represents the password of the user
     * @param email string that represents the email of the user
     * @param type int that represents the type of the user
     */
    public User(String name, String password, String email, int type) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }
    
    /**
     * M??thod to recover the name of the user
     * @return a string that represents the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * M??thod to asign the name of the user
     * @param name string that represents the name of the user to asign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * M??thod to recover the alias of the user
     * @return a string that represents the alias of the user
     */
    public String getAlias() {
        return alias;
    }

    /**
     * M??thod to asign the alias of the user
     * @param alias string that represents the alias of the user to asign
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * M??thod to recover the email of the user
     * @return a string that represents the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * M??thod to asign the email of the user
     * @param email string that represents the email of the user to asign
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * M??thod to recover the NIF of the user
     * @return a string that represents the NIF of the user
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * M??thod to asign the NIF of the user
     * @param NIF string that represents the NIF of the user to asign
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     * M??thod to recover the path for the image of the user
     * @return a string that represents the path for the image of the user
     */
    public String getImage() {
        return image;
    }

    /**
     * M??thod to asign the path for the image of the user
     * @param image a string that represents the path for the image of the user to asign
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * M??thod to recover the last name of the user
     * @return string that represents the last name of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * M??thod to asign the last name of the user
     * @param surname string that represents the last name of the user to asign
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * M??thod to recover the password of the user
     * @return string that represents the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * M??thod to asign the password of the user
     * @param password string that represents the password of the user to asign 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * M??thod to recover the phone of the user
     * @return string that represents the phone of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * M??thod to asign the phone of the user
     * @param phone string that represents the phone of the user to asign
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * M??thod to recover the type of the user
     * @return integer that represents the type of the user
     */
    public int getType() {
        return type;
    }

    /**
     * M??thod to asign the type of the user
     * @param type integer that represents the type of the user to asign
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * M??thod to recover the id of the user
     * @return a long that represents the phone of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * M??thod to asign the id of the user
     * @param id long that represents the phone of the user to asign
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * M??thod to recover if the user is active or not
     * @return an integer that represents if the user is active or not
     */
    public int getActive() {
        return active;
    }

    /**
     * M??thod to asign if the user is active or not
     * @param active integer that represents if the user is active or not to asing
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * M??thod to recover the created date of the user
     * @return a date that represents the date of user's creation
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * M??thod to asign the created date of the user
     * @param createdAt date that represents the date of user's creation to asign
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * M??thod to recover the updated date of the user
     * @return a date that represents the date of user's update
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * M??thod to asign the updated date of the user
     * @param updatedAt date that represents the date of user's update to asign
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * M??thod to recover the token of the user
     * @return a string that represents the user token
     */
    public String getToken() {
        return token;
    }

    /**
     * M??thod to asign the token of the user
     * @param token string that represents the user token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * M??thod to recover the validity of the token
     * @return a string that represents the end date validity of the token
     */
    public String getTokenEndValidityDate() {
        return tokenEndValidityDate;
    }

    /**
     * M??thod to asign the validity of the token
     * @param tokenEndValidityDate string that represents the end date validity of the token
     */
    public void setTokenEndValidityDate(String tokenEndValidityDate) {
        this.tokenEndValidityDate = tokenEndValidityDate;
    }

    /**
     * M??thod to recover the admin contacts
     * @return a list with all the admin contacts
     */
    public List<AdminContact> getAdminContacts() {
        return adminContacts;
    }

    /**
     * M??thod to asign the admin contacts
     * @param adminContacts list with all the admin contacts
     */
    public void setAdminContacts(List<AdminContact> adminContacts) {
        this.adminContacts = adminContacts;
    }

    

}
