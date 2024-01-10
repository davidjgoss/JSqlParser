/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.select;

import net.sf.jsqlparser.JSQLParserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static net.sf.jsqlparser.test.TestUtils.assertSqlCanBeParsedAndDeparsed;

@Execution(ExecutionMode.CONCURRENT)
public class TimeTravelTest {
    @Test
    void testAtTimestampCastFromString() throws JSQLParserException {
        String stmt = "SELECT * FROM my_table AT(TIMESTAMP => 'Fri, 01 May 2015 16:20:00 -0700'::timestamp)";

        assertSqlCanBeParsedAndDeparsed(stmt);
    }

    @Test
    void testAtTimestampViaToTimeStampFunction() throws JSQLParserException {
        String stmt = "SELECT * FROM my_table AT(TIMESTAMP => TO_TIMESTAMP(1432669154242, 3))";

        assertSqlCanBeParsedAndDeparsed(stmt);
    }

    @Test
    void testAtOffset() throws JSQLParserException {
        String stmt = "SELECT * FROM my_table AT(TIMESTAMP => TO_TIMESTAMP(1432669154242, 3))";

        assertSqlCanBeParsedAndDeparsed(stmt);
    }

    @Test
    void testBeforeStatement() throws JSQLParserException {
        String stmt = "SELECT * FROM my_table BEFORE(STATEMENT => '8e5d0ca9-005e-44e6-b858-a8f5b37c5726')";

        assertSqlCanBeParsedAndDeparsed(stmt);
    }

    @Test
    void testComplexExample() throws JSQLParserException {
        String stmt = "SELECT oldt.* ,newt.*\n" +
                "  FROM my_table BEFORE(STATEMENT => '8e5d0ca9-005e-44e6-b858-a8f5b37c5726') AS oldt\n" +
                "    FULL OUTER JOIN my_table AT(STATEMENT => '8e5d0ca9-005e-44e6-b858-a8f5b37c5726') AS newt\n" +
                "    ON oldt.id = newt.id\n" +
                "WHERE oldt.id IS NULL OR newt.id IS NULL";

        assertSqlCanBeParsedAndDeparsed(stmt);
    }
}
