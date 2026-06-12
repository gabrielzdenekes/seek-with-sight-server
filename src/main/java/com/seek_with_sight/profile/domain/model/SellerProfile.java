package com.seek_with_sight.profile.domain.model;

import com.seek_with_sight.domain.model.BaseDomainModel;
import com.seek_with_sight.user.domain.model.User;

public class SellerProfile extends BaseDomainModel {
    private String businessName;
    private String businessAddress;
    private String taxId;
    private User user;
    private SellerStatus status;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SellerStatus getStatus() {
        return status;
    }

    public void setStatus(SellerStatus status) {
        this.status = status;
    }
}
