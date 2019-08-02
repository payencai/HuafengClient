package com.huafeng.client.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：凌涛 on 2019/5/5 11:53
 * 邮箱：771548229@qq..com
 */
public class UserInfo implements Serializable {


    /**
     * authorities : [0]
     * city : string
     * departmentName : string
     * employeeNumber : string
     * employeeRecordId : 0
     * factoryId : 0
     * gmtCreate : 2019-06-20T07:02:58.755Z
     * gmtModified : 2019-06-20T07:02:58.755Z
     * headPortrait : string
     * headPortraitUrl : string
     * hxPassword : string
     * hxUsername : string
     * id : 0
     * isAdmin : 0
     * isDeleted : 0
     * isFreeze : 0
     * lastPasswordResetDate : 2019-06-20T07:02:58.755Z
     * nickname : string
     * openId : string
     * password : string
     * postName : string
     * province : string
     * sex : 0
     * token : string
     * username : string
     */

    private String city;
    private String departmentName;
    private String employeeNumber;
    private int employeeRecordId;
    private int factoryId;
    private String gmtCreate;
    private String gmtModified;
    private String headPortrait;
    private String headPortraitUrl;
    private String hxPassword;
    private String hxUsername;
    private int id;
    private int isAdmin;
    private int isDeleted;
    private int isFreeze;
    private String lastPasswordResetDate;
    private String nickname;
    private String openId;
    private String password;
    private String postName;
    private String province;
    private int sex;
    private String token;
    private String username;
    private List<Integer> authorities;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getEmployeeRecordId() {
        return employeeRecordId;
    }

    public void setEmployeeRecordId(int employeeRecordId) {
        this.employeeRecordId = employeeRecordId;
    }

    public int getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(int factoryId) {
        this.factoryId = factoryId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getHxPassword() {
        return hxPassword;
    }

    public void setHxPassword(String hxPassword) {
        this.hxPassword = hxPassword;
    }

    public String getHxUsername() {
        return hxUsername;
    }

    public void setHxUsername(String hxUsername) {
        this.hxUsername = hxUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(int isFreeze) {
        this.isFreeze = isFreeze;
    }

    public String getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(String lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Integer> authorities) {
        this.authorities = authorities;
    }
}
