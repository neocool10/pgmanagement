package com.neocool.pgmanagement.service;

import com.neocool.pgmanagement.dao.TableDDLDao;
import com.neocool.pgmanagement.utils.exception.DDLException;

public class CommonManagerImpl implements CommonManager {
    private static CommonManagerImpl commonManagerImpl = null;

    private CommonManagerImpl(){}

    public static CommonManagerImpl getInstance(){
        if (commonManagerImpl == null) {
            commonManagerImpl = new CommonManagerImpl();
        }
        return commonManagerImpl;
    }

    @Override
    public void createTables() throws DDLException{
        TableDDLDao.getInstance().createTable();
    }
}
