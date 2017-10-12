package com.conanli.job.contact.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class ContactSaveParams {

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", example = "")
    private String name;
    @ApiModelProperty(value = "电话", example = "")
    private String phone;
    @NotNull(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱", example = "")
    private String email;
    @ApiModelProperty(value = "分组", example = "DEFAULT")
    private String grouping;
    @ApiModelProperty(value = "0 禁用 1 启动", example = "0")
    private String isEnable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
}
