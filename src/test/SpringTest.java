package test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dave.dao.QuesOptionDao;
import com.dave.dao.QuesDao;
import com.dave.entity.QuesOption;
import com.dave.entity.Ques;

/**
 * 单元测试类
 * 
 * @author Dave2019
 *
 */
public class SpringTest extends SpringTestBase{
	@Autowired
	private QuesDao quesDao;
	@Autowired
    private QuesOptionDao optionDao;
	@Test
	public void addQues(){
		String[] options = {"a.星期一","b.星期三","c.星期五"};
		Ques ques = new Ques();
		ques.setQuesName("今天是星期几呢？");
		ques.setQuesType("01");
		ques.setCreateDate(new Date());
		int row = quesDao.addQues(ques);
		int quesId = quesDao.selectQuesId();
		System.out.println(row);
		if(row == 1) {
			for(int i = 0; i < options.length; i++) {
				QuesOption option = new QuesOption();
				option.setQuesId(quesId);
				option.setOptionContent(options[i]);
				option.setFlag(0);
				int rowOption = optionDao.addOption(option);
				System.out.println(rowOption);
			}
		}
	}
}