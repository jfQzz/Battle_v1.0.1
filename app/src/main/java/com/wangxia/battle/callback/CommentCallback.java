package com.wangxia.battle.callback;

import java.io.Serializable;

public interface CommentCallback extends Serializable{
   String CALLBACK = "callback_comment";
   String HINTS = "hints";
   String SAVE_TXT = "save_txt";
    /**
     * 意外退出界面，当前输入内容的回调
     * @param commentWords
     */
    void saveCommentText(String commentWords);
    void successComment();
    void failComment();
}
