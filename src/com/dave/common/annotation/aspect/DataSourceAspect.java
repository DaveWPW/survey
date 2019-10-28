package com.dave.common.annotation.aspect;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.dave.common.annotation.DataSource;
import com.dave.common.mybatis.DataSourceContext;

/**
 * AOP动态切换数据源切面类
 * 
 * @author Dave20191025
 *
 */
@Aspect
@Order(0) // <!-- 设置切换数据源的优先级 -->
@Component // <!-- 确认是否配置了该路径下注释配置bean的自动扫描 -->
public class DataSourceAspect {
	private static Logger logger = Logger.getLogger(DataSourceAspect.class);
	
	// @within在类上设置
	// @annotation在方法上进行设置
	// 事务在service层 因此切换数据库的注解只能放在service层或之前
	@Pointcut("@within(com.dave.common.annotation.DataSource)||@annotation(com.dave.common.annotation.DataSource)")
	public void pointcut() {}

	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		DataSource annotationClass = method.getAnnotation(DataSource.class);
		if (annotationClass == null) {
			annotationClass = joinPoint.getTarget().getClass().getAnnotation(DataSource.class);
			if (annotationClass == null)
				return;
		}
		String dataSourceKey = annotationClass.value();
		if (dataSourceKey != null) {
			DataSourceContext.setDataSource(dataSourceKey);
		}
	}

	@After("pointcut()")
	public void after(JoinPoint point) {
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		DataSourceContext.clearDataSource();
		logger.info("切换指定数据源成功！！");
	}
}