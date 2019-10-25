package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dave.common.annotation.DataSource;
/**
 * Spring测试类基本配置
 * 
 * @author Dave2019
 *
 */
@ContextConfiguration(locations = { "classpath*:spring-mybatis.xml" })
@DataSource("cchrDataSource")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestBase {
}