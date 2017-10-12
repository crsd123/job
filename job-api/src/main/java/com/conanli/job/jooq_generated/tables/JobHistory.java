/**
 * This class is generated by jOOQ
 */
package com.conanli.job.jooq_generated.tables;


import com.conanli.job.jooq_generated.DefaultSchema;
import com.conanli.job.jooq_generated.tables.records.JobHistoryRecord;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


/**
 * 任务执行记录
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.8"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JobHistory extends TableImpl<JobHistoryRecord> {

    private static final long serialVersionUID = -960958837;

    /**
     * The reference instance of <code>job_history</code>
     */
    public static final JobHistory JOB_HISTORY = new JobHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<JobHistoryRecord> getRecordType() {
        return JobHistoryRecord.class;
    }

    /**
     * The column <code>job_history.JOB_NAME</code>. 任务名
     */
    public final TableField<JobHistoryRecord, String> JOB_NAME = createField("JOB_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(100).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "任务名");

    /**
     * The column <code>job_history.JOB_LOG</code>. 执行日志
     */
    public final TableField<JobHistoryRecord, String> JOB_LOG = createField("JOB_LOG", org.jooq.impl.SQLDataType.CLOB, this, "执行日志");

    /**
     * The column <code>job_history.CREATE_DATE</code>. 创建时间
     */
    public final TableField<JobHistoryRecord, Timestamp> CREATE_DATE = createField("CREATE_DATE", org.jooq.impl.SQLDataType.TIMESTAMP.defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "创建时间");

    /**
     * Create a <code>job_history</code> table reference
     */
    public JobHistory() {
        this("job_history", null);
    }

    /**
     * Create an aliased <code>job_history</code> table reference
     */
    public JobHistory(String alias) {
        this(alias, JOB_HISTORY);
    }

    private JobHistory(String alias, Table<JobHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private JobHistory(String alias, Table<JobHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "任务执行记录");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JobHistory as(String alias) {
        return new JobHistory(alias, this);
    }

    /**
     * Rename this table
     */
    public JobHistory rename(String name) {
        return new JobHistory(name, null);
    }
}
