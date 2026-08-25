package org.example.TP1.factory;

import java.sql.Connection;

public interface ConnectionM {
    Connection getConnection();
    void shutdown();
}
