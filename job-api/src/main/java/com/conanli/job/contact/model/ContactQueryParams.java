package com.conanli.job.contact.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel
public class ContactQueryParams {

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名", example = "")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
