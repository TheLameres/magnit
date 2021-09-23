package thelameres.magnit.lib.models.repositories;

import thelameres.magnit.api.models.entities.Entry;
import thelameres.magnit.api.models.repositories.EntryRepository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntryRepositoryImpl implements EntryRepository {

    private final Connection connection;

    public EntryRepositoryImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public void generateTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table if not exists test(field bigint not null, " +
                "primary key(field))");
        statement.close();
    }

    @Override
    public void generateData(Long count) throws SQLException {
        Statement statement = connection.createStatement();
        Random random = new Random();
        random.longs(count)
                .parallel()
                .distinct()
                .forEachOrdered(action -> {
                    try {
                        statement.execute("insert into test(field) values (" + action + ")");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
        statement.close();
    }

    @Override
    public List<Entry> listAll() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from test");
        List<Entry> entries = new ArrayList<>();
        while (resultSet.next()) {
            Entry entry = new Entry(resultSet.getLong("field"));
            entries.add(entry);
        }
        return entries;
    }

    @Override
    public Entry save(Entry entry) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("insert into test(field) values (" + entry.getField() + ")");
        return entry;
    }

    @Override
    public BigDecimal sum() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select sum(field) from test");
        resultSet.next();
        String aLong = resultSet.getString(1);
        resultSet.close();
        statement.close();
        return new BigDecimal(aLong);
    }

    @Override
    public BigDecimal count() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select count(*) from test");
        resultSet.next();
        String aLong = resultSet.getString(1);
        resultSet.next();
        statement.close();
        return new BigDecimal(aLong);
    }

    @Override
    public void clear() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("truncate table test"); //don't use in production code
        statement.close();
    }
}
