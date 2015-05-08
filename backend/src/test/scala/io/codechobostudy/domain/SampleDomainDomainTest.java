package io.codechobostudy.domain;

import io.codechobostudy.domain.SampleDomain;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleDomainDomainTest {

    @Test
    public void test_get_set_domain(){

        SampleDomain sampleDomain = new SampleDomain();
        sampleDomain.setId(1L);
        sampleDomain.setCategoryName("TEST");

        assertThat(sampleDomain.getId(), is(1L));
        assertThat(sampleDomain.getCategoryName(), is("TEST"));
    }
}
