package com.conanli.job.contact;

import com.conanli.job.jooq_generated.tables.records.ContactRecord;
import org.jooq.DSLContext;

import java.util.List;
import java.util.stream.Collectors;

public class ContactStore {

    private DSLContext dsl;

    public ContactStore(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void save(com.conanli.job.contact.Contact contact) {
        com.conanli.job.jooq_generated.tables.Contact CONTACT = com.conanli.job.jooq_generated.tables.Contact.CONTACT;

        ContactRecord contact2 = dsl.newRecord(CONTACT);
        contact2.setName(contact.getName());
        if (isNotBlank(contact.getPhone()))
            contact2.setPhone(contact.getPhone());
        if (isNotBlank(contact.getEmail()))
            contact2.setEmail(contact.getEmail());
        if (isNotBlank(contact.getGrouping()))
            contact2.setGrouping(contact.getGrouping());
        if (isNotBlank(contact.getIsEnable()))
            contact2.setIsEnable(contact.getIsEnable());

        dsl.insertInto(CONTACT)
                .set(contact2)
                .onDuplicateKeyUpdate()
                .set(contact2)
                .execute();
    }

    public void delete(String name) {
        com.conanli.job.jooq_generated.tables.Contact CONTACT = com.conanli.job.jooq_generated.tables.Contact.CONTACT;

        dsl.deleteFrom(CONTACT)
                .where(CONTACT.NAME.eq(name))
                .execute();
    }

    public List<com.conanli.job.contact.Contact> list() {
        com.conanli.job.jooq_generated.tables.Contact CONTACT = com.conanli.job.jooq_generated.tables.Contact.CONTACT;

        List<ContactRecord> contacts = dsl.select()
                .from(CONTACT)
                .fetchInto(CONTACT);

        return contacts.stream().map(contact -> {
            com.conanli.job.contact.Contact contact2 = new com.conanli.job.contact.Contact();
            contact2.setName(contact.getName());
            contact2.setPhone(contact.getPhone());
            contact2.setEmail(contact.getEmail());
            contact2.setGrouping(contact.getGrouping());
            contact2.setIsEnable(contact.getIsEnable());
            return contact2;
        }).collect(Collectors.toList());
    }

    public List<com.conanli.job.contact.Contact> list(String grouping) {
        com.conanli.job.jooq_generated.tables.Contact CONTACT = com.conanli.job.jooq_generated.tables.Contact.CONTACT;

        List<ContactRecord> contacts = dsl.select()
                .from(CONTACT)
                .where(CONTACT.GROUPING.eq(grouping))
                .fetchInto(CONTACT);

        return contacts.stream().map(contact -> {
            com.conanli.job.contact.Contact contact2 = new com.conanli.job.contact.Contact();
            contact2.setName(contact.getName());
            contact2.setPhone(contact.getPhone());
            contact2.setEmail(contact.getEmail());
            contact2.setGrouping(contact.getGrouping());
            contact2.setIsEnable(contact.getIsEnable());
            return contact2;
        }).collect(Collectors.toList());
    }

    private boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }
}
