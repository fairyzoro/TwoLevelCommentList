package com.aboo.twolevelcommentlist.app.model;

import java.util.ArrayList;

/**
 * Created by zhongbaojian on 14-8-19.
 * 评论
 */
public class CommentItem {

    private int userHeaderSourceId;
    private String userName;
    private String createTime;
    private String content;
    private ArrayList<SubCommentItem> subCommentItems = new ArrayList<SubCommentItem>();

    public CommentItem(int userHeaderSourceId, String userName, String createTime, String content, ArrayList<SubCommentItem> subCommentItems) {
        setUserHeaderSourceId(userHeaderSourceId);
        setUserName(userName);
        setCreateTime(createTime);
        setContent(content);
        setSubCommentItems(subCommentItems);
    }

    public int getUserHeaderSourceId() {
        return userHeaderSourceId;
    }

    public void setUserHeaderSourceId(int userHeaderSourceId) {
        this.userHeaderSourceId = userHeaderSourceId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<SubCommentItem> getSubCommentItems() {
        return subCommentItems;
    }

    public void setSubCommentItems(ArrayList<SubCommentItem> subCommentItems) {
        if (subCommentItems != null)
            this.subCommentItems = subCommentItems;
    }
}
