package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Notice;
import java.util.List;


public interface NoticeView {
    void setNoticeList(List<Notice> notices, int type,int total);//按类型获取事件通知
    void execError(String msg);
    void setNoticeListAppend(List<Notice>notices);

}
