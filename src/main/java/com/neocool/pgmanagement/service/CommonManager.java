package com.neocool.pgmanagement.service;

import com.neocool.pgmanagement.utils.exception.DDLException;

public interface CommonManager {
    public void createTables() throws DDLException;
}
