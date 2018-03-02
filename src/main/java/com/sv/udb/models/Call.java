/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.models;

import java.util.Date;

/**
 *
 * @author kevin
 */
public class Call {
    int id;
    School school;
    int viable;
    Complaint_type complaint_type;
    User user;
    String description;
    Date call_date;

    public Call() {
    }

    public Call(int id, School school, int viable, Complaint_type complaint_type, User user, String description, Date call_date) {
        this.id = id;
        this.school = school;
        this.viable = viable;
        this.complaint_type = complaint_type;
        this.user = user;
        this.description = description;
        this.call_date = call_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public int getViable() {
        return viable;
    }

    public void setViable(int viable) {
        this.viable = viable;
    }

    public Complaint_type getComplaint_type() {
        return complaint_type;
    }

    public void setComplaint_type(Complaint_type complaint_type) {
        this.complaint_type = complaint_type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCall_date() {
        return call_date;
    }

    public void setCall_date(Date call_date) {
        this.call_date = call_date;
    }

    @Override
    public String toString() {
        return description;
    }
    
    
}
