package com.dave.common.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	@Override
    protected Object determineCurrentLookupKey() {
		String dataSource = DataSourceContext.getDataSource();
		System.out.println(dataSource);
        return dataSource;
    }
	
}