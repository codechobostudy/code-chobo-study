package io.codechobostudy.notifications;

import io.codechobostudy.notifications.dto.NotiDTO;
import io.codechobostudy.notifications.fixture.NotiDTOBuilderData_BoardFirst;
import io.codechobostudy.notifications.fixture.NotiDTOBuilderData;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class NotiBuilderTest {
    @Test
    public void testNotiDTOBuilderData() {
        NotiDTOBuilderData boardFirst_NotiDTOBuilderData = new NotiDTOBuilderData_BoardFirst();
        NotiDTO notiDTO = boardFirst_NotiDTOBuilderData.buildData();

        assertThat(notiDTO.getContents(), is(notNullValue()));
    }

    @Test
    public void testNotiDTOBuilder() {
        NotiDTO notiDTO = new NotiDTO.NotiDTOBuilder()
                .withContents("contents")
                .withModule("board")
                .withUrl("url")
                .withUserDTO(null)
                .build();
        assertThat("contents", is(notiDTO.getContents()));
        assertThat(notiDTO, is(notNullValue()));
    }
}
