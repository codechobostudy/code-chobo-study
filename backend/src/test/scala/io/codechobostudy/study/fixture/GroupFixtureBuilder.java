package io.codechobostudy.study.fixture;

import io.codechobostudy.study.domain.StudyGroup;

public class GroupFixtureBuilder {

    private String groupName;
    private String groupCreateUserId;
    private String groupLeaderUserId;
    private String groupGoals;
    private String groupDesc;
    private String groupStatus;

    public static GroupFixtureBuilder anGroup() {
        return new GroupFixtureBuilder();
    }

    public GroupFixtureBuilder withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public GroupFixtureBuilder withGroupCreateUserId(String groupCreateUserId) {
        this.groupCreateUserId = groupCreateUserId;
        return this;
    }

    public GroupFixtureBuilder withGroupLeaderUserId(String groupLeaderUserId){
        this.groupLeaderUserId = groupLeaderUserId;
        return this;
    }

    public GroupFixtureBuilder withGroupGoals(String groupGoals){
        this.groupGoals = groupGoals;
        return this;
    }

    public GroupFixtureBuilder withGroupDesc(String groupDesc){
        this.groupDesc = groupDesc;
        return this;
    }

    public GroupFixtureBuilder withGroupStatus(String groupStatus){
        this.groupStatus = groupStatus;
        return this;
    }

    public StudyGroup build(){
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setGroupName(this.groupName);
        studyGroup.setGroupCreateUserId(this.groupCreateUserId);
        studyGroup.setGroupLeaderUserId(this.groupLeaderUserId);
        studyGroup.setGroupGoals(this.groupGoals);
        studyGroup.setGroupDesc(this.groupDesc);
        studyGroup.setGroupStatus(this.groupStatus);
        return studyGroup;
    }
}
