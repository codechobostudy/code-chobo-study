package io.codechobostudy.sample.fixture;

import io.codechobostudy.sample.domain.SampleDomain;

public class SampleBuilder {

    private String categoryName;
    private String categoryDesc;

    public static SampleBuilder anCategory(){
        return new SampleBuilder();
    }

    public SampleBuilder withCategoryName(String categoryName){
        this.categoryName = categoryName;
        return this;
    }

    public SampleBuilder withCategoryDesc(String categoryDesc){
        this.categoryDesc = categoryDesc;
        this.categoryDesc = categoryDesc;
        return this;
    }

    public SampleDomain build(){
        SampleDomain sampleDomain = new SampleDomain();
        sampleDomain.setCategoryName(this.categoryName);
        sampleDomain.setCategoryDesc(this.categoryDesc);
        return sampleDomain;
    }
}
