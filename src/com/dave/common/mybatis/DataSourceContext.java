package com.dave.common.mybatis;

public class DataSourceContext {

	private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

	public static void setDataSource(String dataSource) {
		THREAD_LOCAL.set(dataSource);
	}

	public static String getDataSource() {
		return THREAD_LOCAL.get();
	}

	public static void clearDataSource() {
		THREAD_LOCAL.remove();
	}

}
