package ru.yandex.metrika.clickhouse.copypaste;

import org.junit.Assert;
import org.junit.Test;

public class CHResultBuilderTest {

    @Test
    public void testBuild() throws Exception {
        CHResultSet resultSet = CHResultBuilder.builder(2)
                .names("string", "int")
                .types("String", "UInt32")
                .addRow("ololo", 1000)
                .addRow("o\tlo\nlo", 1000)
                .build();

        Assert.assertEquals("string", resultSet.getColumnNames()[0]);
        Assert.assertEquals("int", resultSet.getColumnNames()[1]);

        Assert.assertEquals("String", resultSet.getTypes()[0]);
        Assert.assertEquals("UInt32", resultSet.getTypes()[1]);

        Assert.assertTrue(resultSet.next());
        Assert.assertEquals("ololo", resultSet.getString(1));
        Assert.assertEquals(1000, resultSet.getInt(2));

        Assert.assertTrue(resultSet.next());
        Assert.assertEquals("o\tlo\nlo", resultSet.getString(1));
        Assert.assertEquals(1000, resultSet.getInt(2));

        Assert.assertFalse(resultSet.next());

    }
}