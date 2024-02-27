package com.moikiitos.dao.model;

import java.util.Date;

public class User {
    private Long userId;

    private Long userNumber;

    private String nickName;

    private String loginPassword;

    private String salt;

    private String realName;

    private String gender;

    private Byte age;

    private String headerUrl;

    private Byte status;

    private String email;

    private Byte emailActive;

    private String phoneNum;

    private Date registerTime;

    private Date lastLoginTime;

    private Date updateTime;

    private Integer loginNums;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl == null ? null : headerUrl.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getEmailActive() {
        return emailActive;
    }

    public void setEmailActive(Byte emailActive) {
        this.emailActive = emailActive;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLoginNums() {
        return loginNums;
    }

    public void setLoginNums(Integer loginNums) {
        this.loginNums = loginNums;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userNumber=" + userNumber +
                ", nickName='" + nickName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", salt='" + salt + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", headerUrl='" + headerUrl + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", emailActive=" + emailActive +
                ", phoneNum='" + phoneNum + '\'' +
                ", registerTime=" + registerTime +
                ", lastLoginTime=" + lastLoginTime +
                ", updateTime=" + updateTime +
                ", loginNums=" + loginNums +
                '}';
    }
}