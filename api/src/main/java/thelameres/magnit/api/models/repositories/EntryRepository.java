package thelameres.magnit.api.models.repositories;

import thelameres.magnit.api.models.entities.Entry;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface EntryRepository extends IRepository<Entry> {
    void generateTable() throws SQLException;

    void generateData(Long count) throws SQLException;

    List<Entry> listAll() throws SQLException;

    Entry save(Entry iEntry) throws SQLException;

    BigDecimal sum() throws SQLException;

    BigDecimal count() throws SQLException;

    void clear() throws SQLException;

}
