package test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dave.service.TestService;


/**
 * Spring单元测试类
 * 
 * @author Dave2019
 *
 */
public class SpringTest extends SpringTestBase{
	
	@Autowired
	private TestService testService;
	
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
	public void findCCHR(){
	    List<Map<String, Object>> findAllData = testService.findAllData();
	    for (Map<String, Object> map : findAllData) {
	    	System.out.println(map.toString());
		}
	    
	}
}