package com.george.test;

import com.george.jdbc.JdbcUtil;
import com.george.jdbc.databaseDir.DictionaryCreator;
import com.george.jdbc.databaseDir.entity.TableEntity;
import org.junit.Test;

import java.util.List;

/**
 * Created by admin on 2018/5/17.
 */
public class CommonTest {

    @Test
    public void common(){
        DictionaryCreator creator = new DictionaryCreator();
        JdbcUtil jdbcUtil = new JdbcUtil();
        List<TableEntity> mySqlDataDictionary = creator.getMySqlDataDictionary(jdbcUtil.getConnection(),
                "for_study", "rc_district");
        System.out.println();
    }

}
