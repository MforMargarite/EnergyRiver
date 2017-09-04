package com.whucs.energyriver.View;

import com.whucs.energyriver.Bean.Bill;
import java.util.List;


public interface BillView {
    void getBillSuccess(List<Bill> bills);
    void execError(String msg);
}
