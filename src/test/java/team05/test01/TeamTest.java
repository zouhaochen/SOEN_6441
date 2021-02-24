package team05.test01;

import org.junit.Test;
import team05.demo01.TeamMember;

/**
 * @author Haochen Zou
 *
 * @version 1.0
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
