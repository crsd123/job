package com.conanli.job;

import com.conanli.job.contact.Contact;
import com.conanli.job.contact.ContactStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobApiApplication.class)
public class ContactTest {

    @Autowired
    private ContactStore contactStore;

    @Test
    public void test() {
        Contact contact = new Contact();
        contact.setName("conanli");
        contact.setPhone("13800138000");
        contact.setEmail("conanli@163.com");
        contact.setGrouping("DEFAULT");
        contact.setIsEnable("1");
        contactStore.save(contact);
    }
}
