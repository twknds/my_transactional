package com.example.demo.domain;

import com.example.demo.transactional.TransactionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class bookRepository{
    public book save(book book) throws SQLException{
        String sql = "insert into book(ID,NAME) values(?,?)";
        PreparedStatement pstmt = null;
        Connection con = DataSourceUtils.getConnection(TransactionManager.getDataSource());
        pstmt = con.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, book.getId());
        pstmt.setString(2, book.getName());
        pstmt.executeUpdate();
        return book;
    }
    public book findByName(String name) throws SQLException {
        String sql = "select * from book where NAME = ?";
        Connection con = DataSourceUtils.getConnection(TransactionManager.getDataSource());
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            book book = new book();
            book.setName(rs.getString("NAME"));
            return book;
        } else {
            throw new NoSuchElementException("Member not founded NAME = " + name);
        }
    }
    public void delete(int bookId) throws SQLException {
        String sql = "delete from book where id = ?";
        Connection con = DataSourceUtils.getConnection(TransactionManager.getDataSource());
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, bookId);
        pstmt.executeUpdate();
    }
}
