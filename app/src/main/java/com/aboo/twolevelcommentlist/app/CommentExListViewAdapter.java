package com.aboo.twolevelcommentlist.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.aboo.twolevelcommentlist.app.model.CommentItem;
import com.aboo.twolevelcommentlist.app.model.SubCommentItem;

import java.util.ArrayList;

/**
 * Created by zhongbaojian on 14-8-19.
 * 评论列表数据适配器
 */
public class CommentExListViewAdapter extends BaseExpandableListAdapter implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<CommentItem> mCommentItemList;
    private AlertDialog mCommentEditDialog;
    private int mCurrentGroupPosition = 0;

    public CommentExListViewAdapter(Context context, ArrayList<CommentItem> commentItemList) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mCommentItemList = commentItemList;

        createCommentEditDialog();
    }

    private void createCommentEditDialog() {
        View commentInputView = mInflater.inflate(R.layout.dialog_sub_comment, null);
        final EditText commentEdit = (EditText) commentInputView.findViewById(R.id.dialogSubComment_commentContentInput_edt);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请输入内容");
        builder.setView(commentInputView);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String commentContent = commentEdit.getText().toString().trim();
                SubCommentItem subCommentItem = new SubCommentItem(R.drawable.ic_launcher, "评论用户", commentContent);
                if (!commentContent.equals("")) {
                    mCommentItemList.get(mCurrentGroupPosition).getSubCommentItems().add(subCommentItem);

                    notifyDataSetChanged();
                }
            }
        });

        mCommentEditDialog = builder.create();
    }

    @Override
    public int getGroupCount() {
        return mCommentItemList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        CommentItem commentItem = mCommentItemList.get(groupPosition);
        if (commentItem.getSubCommentItems() == null) {
            return 0;
        } else {
            return commentItem.getSubCommentItems().size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mCommentItemList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        CommentItem commentItem = mCommentItemList.get(groupPosition);
        if (commentItem.getSubCommentItems() == null) {
            return null;
        } else {
            return commentItem.getSubCommentItems().get(childPosition);
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        CommentViewHolder commentViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.comment_listview_item, null);
            commentViewHolder = new CommentViewHolder(convertView);
            convertView.setTag(commentViewHolder);
        } else {
            commentViewHolder = (CommentViewHolder) convertView.getTag();
        }

        CommentItem commentItem = mCommentItemList.get(groupPosition);
        commentViewHolder.userHeaderImg.setImageResource(commentItem.getUserHeaderSourceId());
        commentViewHolder.userNameTv.setText(commentItem.getUserName());
        commentViewHolder.createTimeTv.setText(commentItem.getCreateTime());
        commentViewHolder.contentTv.setText(commentItem.getContent());
        commentViewHolder.replyBtn.setTag(groupPosition);
        commentViewHolder.replyBtn.setOnClickListener(this);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        SubCommentViewHolder subCommentViewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.comment_listview_sub_item, null);
            subCommentViewHolder = new SubCommentViewHolder(convertView);
            convertView.setTag(subCommentViewHolder);
        } else {
            subCommentViewHolder = (SubCommentViewHolder) convertView.getTag();
        }

        CommentItem commentItem = mCommentItemList.get(groupPosition);
        if (commentItem.getSubCommentItems() != null) {
            SubCommentItem subCommentItem = commentItem.getSubCommentItems().get(childPosition);

            subCommentViewHolder.userHeaderImg.setImageResource(subCommentItem.getUserHeaderImgId());
            subCommentViewHolder.userNameTv.setText(subCommentItem.getUserName());
            subCommentViewHolder.contentTv.setText(subCommentItem.getContent());

            return convertView;
        } else {
            return null;
        }

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commentListViewItem_reply_btn:
                mCurrentGroupPosition = (Integer) v.getTag();

                mCommentEditDialog.show();
                // 每次评论前，清空Dialog输入框的内容
                ((EditText) mCommentEditDialog.findViewById(R.id.dialogSubComment_commentContentInput_edt)).setText("");
                break;
        }
    }


    class CommentViewHolder {
        ImageView userHeaderImg;
        TextView userNameTv;
        TextView createTimeTv;
        TextView contentTv;
        Button replyBtn;

        CommentViewHolder(View parent) {
            userHeaderImg = (ImageView) parent.findViewById(R.id.commentListViewItem_userHeader_img);
            userNameTv = (TextView) parent.findViewById(R.id.commentListViewItem_userName_tv);
            createTimeTv = (TextView) parent.findViewById(R.id.commentListViewItem_createTime_tv);
            contentTv = (TextView) parent.findViewById(R.id.commentListViewItem_commentContent_tv);
            replyBtn = (Button) parent.findViewById(R.id.commentListViewItem_reply_btn);
        }
    }

    class SubCommentViewHolder {
        ImageView userHeaderImg;
        TextView userNameTv;
        TextView contentTv;

        SubCommentViewHolder(View parent) {
            userHeaderImg = (ImageView) parent.findViewById(R.id.commentListViewChildItem_userHeader_img);
            userNameTv = (TextView) parent.findViewById(R.id.commentListViewChildItem_userName_tv);
            contentTv = (TextView) parent.findViewById(R.id.commentListViewChildItem_content_tv);
        }
    }
}
