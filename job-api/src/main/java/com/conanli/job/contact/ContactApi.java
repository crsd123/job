package com.conanli.job.contact;

import com.conanli.job.common.Response;
import com.conanli.job.contact.model.ContactQueryParams;
import com.conanli.job.contact.model.ContactSaveParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "通讯录")
@RestController
@RequestMapping("/contact")
public class ContactApi {

    @Autowired
    ContactStore contactStore;

    @ApiOperation(value = "保存", response = String.class)
    @PostMapping("/save")
    public Response<Boolean> save(@Valid @RequestBody ContactSaveParams params) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(params, contact);
        contactStore.save(contact);
        return Response.success(true);
    }

    @ApiOperation(value = "删除", response = String.class)
    @PostMapping("/delete")
    public Response<Boolean> delete(@Valid @RequestBody ContactQueryParams params) {
        contactStore.delete(params.getName());
        return Response.success(true);
    }

    @ApiOperation(value = "列表", response = List.class)
    @PostMapping("/list")
    public Response<List<Contact>> list() {
        List<Contact> contacts = contactStore.list();
        return Response.success(contacts);
    }
}
