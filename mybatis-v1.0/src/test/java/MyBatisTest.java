import com.example.mybatis.executor.MyDefaultExecutor;
import com.example.mybatis.session.MyConfiguration;
import com.example.mybatis.session.MySqlSession;
import com.example.sys.mapper.UserMapper;

/**
 * @description: mybatis-v1.0测试案例
 * @author: bianqipeng
 * @date: 2021-02-23 11:05:35
 */
public class MyBatisTest {

    public static void main(String[] args) {
        MySqlSession sqlSession = new MySqlSession(new MyConfiguration(), new MyDefaultExecutor());
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(userMapper.selectByPrimaryKey(2L));
    }
}
