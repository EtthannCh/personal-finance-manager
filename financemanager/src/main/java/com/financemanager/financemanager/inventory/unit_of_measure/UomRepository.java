package com.financemanager.financemanager.inventory.unit_of_measure;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.financemanager.financemanager.inventory.unit_of_measure.model.UOM;

@Repository
public class UomRepository {

    private final JdbcClient jdbcClient;

    public UomRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Long create(UOM uom) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("""
                insert into unit_of_measure
                (
                    name, code,
                    created_by_id, created_by, created_at,
                    last_updated_by_id, last_updated_by, last_updated_at
                )
                values
                (
                    :name, :code,
                    :createdById, :createdBy, :createdAt,
                    :lastUpdatedById, :lastUpdatedBy, :lastUpdatedAt
                )
                """)
                .param("name", uom.name())
                .param("code", uom.code())
                .param("createdById", uom.created_by_id())
                .param("createdBy", uom.created_by())
                .param("createdAt", uom.created_at())
                .param("lastUpdatedById", uom.last_updated_by_id())
                .param("lastUpdatedBy", uom.last_updated_by())
                .param("lastUpdatedAt", uom.last_updated_at())
                .update(keyHolder, "id");

        return keyHolder.getKey().longValue();
    }
}
