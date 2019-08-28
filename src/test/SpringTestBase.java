package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Spring测试类基本配置
 * 
 * @author Dave2019
 *
 */
@ContextConfiguration(locations = { "classpath*:spring-mybatis.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestBase {
}