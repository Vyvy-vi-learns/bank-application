package com.csf401.BankingSystem.repository;

import com.csf401.BankingSystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {
    @Autowired
    JdbcTemplate jt;


    public Customer fetch(int id) {
        String sql = "select * from customer where id = ?";
        return jt.query(
                sql,
                (rs, rowNum) -> new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getLong("balance")),
                id
        ).get(0);
    }

    public long changeBalance(int id, long amount) {
        Customer c = fetch(id);
        if (c == null) {
            System.out.println("Customer doesn't exist");
            return -1;
        }
        c.setBalance(c.getBalance() + amount);
        update(c);
        return c.getBalance();
    }

    public List<Customer> fetchAll() {
        String sql = "select * from customer";
        return jt.query(
                sql,
                (rs, rowNum) -> new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getLong("balance"))
        );
    }


//    public List<Customer> fetchAll() {
//        String sql = "select * from customer";
//        return jt.query(
//                sql,
//                new RowMapper<Customer>() {
//                    @Override
//                    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return new Customer(
//                                rs.getInt("id"),
//                                rs.getString("name"),
//                                rs.getLong("balance"));
//                    }
//                }
//        );
//    }


    public int save(Customer c) {
        if (fetch(c.getId()) != null) {
            return 0;
        }
        String sql = "insert into customer(id, name, balance) values (?, ?, ?)";
        return jt.update(sql, c.getId(), c.getName(), c.getBalance());
    }

    public int delete(int id) {
        String sql = "delete from customer where id = ?";
        return jt.update(sql, id);
    }

    public int update(Customer c) {
        String sql = "update customer set name = ?, balance = ? where id = ?";
        return jt.update(sql, c.getName(), c.getBalance(), c.getId());
    }
}
