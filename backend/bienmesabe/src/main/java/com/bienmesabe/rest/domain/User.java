package com.bienmesabe.rest.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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

    /**
     * Empty Constructor
     */
    public User() {
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
     * Méthod to recover the name of the user
     * @return a string that represents the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Méthod to asign the name of the user
     * @param name string that represents the name of the user to asign
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Méthod to recover the alias of the user
     * @return a string that represents the alias of the user
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Méthod to asign the alias of the user
     * @param alias string that represents the alias of the user to asign
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Méthod to recover the email of the user
     * @return a string that represents the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Méthod to asign the email of the user
     * @param email string that represents the email of the user to asign
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Méthod to recover the NIF of the user
     * @return a string that represents the NIF of the user
     */
    public String getNIF() {
        return NIF;
    }

    /**
     * Méthod to asign the NIF of the user
     * @param NIF string that represents the NIF of the user to asign
     */
    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    /**
     * Méthod to recover the path for the image of the user
     * @return a string that represents the path for the image of the user
     */
    public String getImage() {
        return image;
    }

    /**
     * Méthod to asign the path for the image of the user
     * @param image a string that represents the path for the image of the user to asign
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Méthod to recover the last name of the user
     * @return string that represents the last name of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Méthod to asign the last name of the user
     * @param surname string that represents the last name of the user to asign
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Méthod to recover the password of the user
     * @return string that represents the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Méthod to asign the password of the user
     * @param password string that represents the password of the user to asign 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Méthod to recover the phone of the user
     * @return string that represents the phone of the user
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Méthod to asign the phone of the user
     * @param phone string that represents the phone of the user to asign
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Méthod to recover the type of the user
     * @return integer that represents the type of the user
     */
    public int getType() {
        return type;
    }

    /**
     * Méthod to asign the type of the user
     * @param type integer that represents the type of the user to asign
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Méthod to recover the id of the user
     * @return a long that represents the phone of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Méthod to asign the id of the user
     * @param id long that represents the phone of the user to asign
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Méthod to recover if the user is active or not
     * @return an integer that represents if the user is active or not
     */
    public int getActive() {
        return active;
    }

    /**
     * Méthod to asign if the user is active or not
     * @param active integer that represents if the user is active or not to asing
     */
    public void setActive(int active) {
        this.active = active;
    }

    /**
     * Méthod to recover the created date of the user
     * @return a date that represents the date of user's creation
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Méthod to asign the created date of the user
     * @param createdAt date that represents the date of user's creation to asign
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Méthod to recover the updated date of the user
     * @return a date that represents the date of user's update
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Méthod to asign the updated date of the user
     * @param updatedAt date that represents the date of user's update to asign
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    

}
