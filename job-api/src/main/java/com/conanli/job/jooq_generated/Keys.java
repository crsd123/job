/**
 * This class is generated by jOOQ
 */
package com.conanli.job.jooq_generated;


import com.conanli.job.jooq_generated.tables.Contact;
import com.conanli.job.jooq_generated.tables.records.ContactRecord;
import com.conanli.job.jooq_generated.tables.Job;
import com.conanli.job.jooq_generated.tables.records.JobRecord;

import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code></code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ContactRecord> KEY_CONTACT_UNIQUE_NAME_GROUPING = UniqueKeys0.KEY_CONTACT_UNIQUE_NAME_GROUPING;
    public static final UniqueKey<JobRecord> KEY_JOB_PRIMARY = UniqueKeys0.KEY_JOB_PRIMARY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<ContactRecord> KEY_CONTACT_UNIQUE_NAME_GROUPING = createUniqueKey(Contact.CONTACT, "KEY_contact_UNIQUE_NAME_GROUPING", Contact.CONTACT.NAME, Contact.CONTACT.GROUPING);
        public static final UniqueKey<JobRecord> KEY_JOB_PRIMARY = createUniqueKey(Job.JOB, "KEY_job_PRIMARY", Job.JOB.JOB_NAME);
    }
}