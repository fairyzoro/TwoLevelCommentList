package com.aboo.twolevelcommentlist.app.model;

/**
 * Created by zhongbaojian on 14-8-19.
 * 子评论
 */
public class SubCommentItem {

    private int userHeaderImgId;
    private String userName;
    private String content;

    public SubCommentItem(int userHeaderImgId, String userName, String content) {
        setUserHeaderImgId(userHeaderImgId);
        setUserName(userName);
        setContent(content);
    }

    public int getUserHeaderImgId() {
        return userHeaderImgId;
    }

    public void setUserHeaderImgId(int userHeaderImgId) {
        this.userHeaderImgId = userHeaderImgId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
