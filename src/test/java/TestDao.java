import com.yq.ApplicationStart;
import com.yq.dao.CategoryMapper;
import com.yq.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Date;

/**
 * created by wb-yq264139 on 2017/11/6
 */
@SpringBootTest(classes = ApplicationStart.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "home")
public class TestDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testDatasource() {
        System.out.println(dataSource);
    }

    @Test
    public void testCategoryInsert() {
        Category category = new Category();
        Date currentTime = new Date();
        category.setCreateTime(currentTime);
        category.setUpdateTime(currentTime);
        category.setName("咨询");
        categoryMapper.insert(category);
    }
}
