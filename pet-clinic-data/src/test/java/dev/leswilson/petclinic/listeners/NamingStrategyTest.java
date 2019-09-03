package dev.leswilson.petclinic.listeners;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.IdentifierHelper;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NamingStrategyTest {

    private NamingStrategy strategy;

    @Mock
    JdbcEnvironment jdbcEnvironment;

    @BeforeEach
    void setUp() {
        strategy = new NamingStrategy();
    }
    @Test
    void toPhysicalCatalogName() {
        Identifier identifier = Identifier.toIdentifier("Test");
        assertThat(strategy.toPhysicalCatalogName(identifier, null), is(identifier));
    }

    @Test
    void toPhysicalSchemaName() {
        Identifier identifier = Identifier.toIdentifier("Test");
        assertThat(strategy.toPhysicalSchemaName(identifier, null), is(identifier));
    }

    @Test
    void toPhysicalTableName() {
        when(jdbcEnvironment.getIdentifierHelper()).thenReturn(new MockHelper());
        Identifier identifier = Identifier.toIdentifier("TestTable");
        assertThat(strategy.toPhysicalTableName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_table")));
    }

    @Test
    void toPhysicalSequenceName() {
        when(jdbcEnvironment.getIdentifierHelper()).thenReturn(new MockHelper());
        Identifier identifier = Identifier.toIdentifier("Test");
        assertThat(strategy.toPhysicalSequenceName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_seq")));
        identifier = Identifier.toIdentifier("TestSeq");
        assertThat(strategy.toPhysicalSequenceName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_seq")));
        identifier = Identifier.toIdentifier("TestSequence");
        assertThat(strategy.toPhysicalSequenceName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_sequence_seq")));
    }

    @Test
    void toPhysicalColumnName() {
        when(jdbcEnvironment.getIdentifierHelper()).thenReturn(new MockHelper());
        Identifier identifier = Identifier.toIdentifier("TestColumn");
        assertThat(strategy.toPhysicalColumnName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_column")));
        identifier = Identifier.toIdentifier("Test");
        assertThat(strategy.toPhysicalColumnName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test")));
    }

    @Test
    void checkAbbreviations() {
        when(jdbcEnvironment.getIdentifierHelper()).thenReturn(new MockHelper());
        Identifier identifier = Identifier.toIdentifier("TestAccount");
        assertThat(strategy.toPhysicalColumnName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_acct")));
        identifier = Identifier.toIdentifier("Test_Number");
        assertThat(strategy.toPhysicalColumnName(identifier, jdbcEnvironment), is(Identifier.toIdentifier("test_num")));
    }

    class MockHelper implements IdentifierHelper {

        @Override
        public Identifier normalizeQuoting(Identifier identifier) {
            return null;
        }

        @Override
        public Identifier toIdentifier(String s) {
            return Identifier.toIdentifier(s);
        }

        @Override
        public Identifier toIdentifier(String s, boolean b) {
            return Identifier.toIdentifier(s, b);
        }

        @Override
        public Identifier applyGlobalQuoting(String s) {
            return null;
        }

        @Override
        public boolean isReservedWord(String s) {
            return false;
        }

        @Override
        public String toMetaDataCatalogName(Identifier identifier) {
            return null;
        }

        @Override
        public String toMetaDataSchemaName(Identifier identifier) {
            return null;
        }

        @Override
        public String toMetaDataObjectName(Identifier identifier) {
            return null;
        }
    }
}