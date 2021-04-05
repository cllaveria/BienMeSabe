/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bienmesabe.rest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author RAUL
 */
@Entity
@Table(name="nutricionists")
@PrimaryKeyJoinColumn(name="ID")
public class Nutricionist extends User{
    @Column(name="COMPANY_NAME")
    private String companyName;
    @Column(name="COMPANY_DIRECTION")
    private String companyDirection;
    @Column(name="COMPANY_POSTAL_CODE")
    private String companyPostalCode;
    @Column(name="COMPANY_CITY")
    private String companyCity;
    @Column(name="COMPANY_TELEPHONE")
    private String companyPhone;

    public Nutricionist() {
    }

    public Nutricionist(String companyName, String companyDirection, String companyPostalCode, String companyCity, String companyPhone) {
        this.companyName = companyName;
        this.companyDirection = companyDirection;
        this.companyPostalCode = companyPostalCode;
        this.companyCity = companyCity;
        this.companyPhone = companyPhone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDirection() {
        return companyDirection;
    }

    public void setCompanyDirection(String companyDirection) {
        this.companyDirection = companyDirection;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

}
