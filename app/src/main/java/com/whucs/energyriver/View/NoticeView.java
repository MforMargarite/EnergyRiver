package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Notice;
import java.util.List;


public interface NoticeView {
    void setNoticeList(List<Notice> notices, int type);//按类型获取事件通知
    void execError(String msg);

}
