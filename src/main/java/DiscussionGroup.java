import java.util.HashMap;

public class DiscussionGroup {
    public String discussionID;
    public String groupName;
    public String userEmail;

    public DiscussionGroup(String inDiscussionID, String inGroupName, String inUserEmail){
        this.discussionID = inDiscussionID;
        this.groupName = inGroupName;
        this.userEmail = inUserEmail;
    }

    public HashMap<String, String> getHash() {
        HashMap<String, String> cMap = new HashMap<>();
        cMap.put("discussionID",discussionID);
        cMap.put("groupName",groupName);
        cMap.put("userEmail",userEmail);

        return cMap;
    }

    public String getDiscussionID() {
        return discussionID;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setDiscussionID(String inID) {
        discussionID = inID;
    }

    public void setGroupName(String inName) {
        groupName = inName;
    }

    public void setUserEmail(String inEmail) {
        userEmail = inEmail;
    }


    @Override
    public String toString() {
        return "DiscussionGroup{" +
                "discussionID=" + discussionID +
                ", groupName='" + groupName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}

