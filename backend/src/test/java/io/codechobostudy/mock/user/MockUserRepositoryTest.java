package io.codechobostudy.mock.user;

import io.codechobostudy.Application;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.repository.MockUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class MockUserRepositoryTest {
    @Autowired
    MockUserRepository mockUserRepository;

    @Test
    public void insertMockUser() {
        String jinhyunId = "id_Jinhyun";
        String sukkyuohId = "id_Sukkyuoh";
        String changhwaohId = "id_Changhwaoh";

        MockUser jinhyunUser = new MockUser(jinhyunId, "jinhyun");
        MockUser sukkyuohUser = new MockUser(sukkyuohId, "sukkyu.oh");
        MockUser changhwaohUser = new MockUser(changhwaohId, "changhwaoh");

        mockUserRepository.save(jinhyunUser);
        mockUserRepository.save(sukkyuohUser);
        mockUserRepository.save(changhwaohUser);
        MockUser dbJinhyunUser = mockUserRepository.findOne(1);
        assertThat(jinhyunId, is(dbJinhyunUser.getUserId()));
    }
}
