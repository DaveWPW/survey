package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dave.common.annotation.DataSource;
import com.dave.dao.TestDao;


/**
 * Spring单元测试类
 * 
 * @author Dave2019
 *
 */
public class SpringTest extends SpringTestBase{
	
	@Autowired
	private TestDao testDao;
	
	@Test
	public void addQues(){
	}
	@Test
	public void addPaper(){
	}
	@Test
	public void addResult(){
	}
	@Test
	public void findResult() {
	}
	
	@Test
	@DataSource("cchrDataSource")
	public void findCCHR(){
	    List<Map<String, Object>> findAllData = testDao.findAllData();
	    for (Map<String, Object> map : findAllData) {
	    	System.out.println(map.toString());
		}
	    
	}
}