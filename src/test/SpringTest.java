package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.List;

import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.dave.dao.QuesOptionDao;
//import com.dave.dao.ResultDao;
//import com.dave.dao.PaperQuesDao;
//import com.dave.dao.QuesDao;
//import com.dave.entity.QuesOption;
//import com.dave.entity.vo.ResultExportInfo;
//import com.sun.javafx.collections.MappingChange.Map;
//import com.dave.entity.PaperQues;
//import com.dave.entity.Ques;

/**
 * 单元测试类
 * 
 * @author Dave2019
 *
 */
public class SpringTest extends SpringTestBase{
//	@Autowired
//	private QuesDao quesDao;
//	@Autowired
//    private QuesOptionDao optionDao;
//	@Autowired
//	private PaperQuesDao paperAllDao;
//	@Autowired
//	private ResultDao resultDao;
	@Test
	public void addQues(){
	}
	@Test
	public void addPaper(){
	}
	@Test
	public void addResult() throws ParseException {
		String time = "20190906111111";
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = simpleDateFormat.parse(time);
        System.out.println(date);
	}
	@Test
	public void findResult() {
	}
}