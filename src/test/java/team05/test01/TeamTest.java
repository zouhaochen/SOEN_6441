package team05.test01;

import org.junit.Test;
import team05.demo01.TeamMember;

/**
 * @Auther: Haochen Zou
 * @Date: 2021/1/26 - 上午8:47
 * @Description: team05.test01
 * @version: 1.0
 */
public class TeamTest {
    @Test
    public void testTeam(){
        TeamMember test = new TeamMember("test","test@gmail.com");
        String name = test.getName();
        String email = test.getEmail();
        System.out.println(name);
        System.out.println(email);
    }
}
