package com.whucs.energyriver.Bean;


public class User {
    private String telephone;	//手机号码
    private Integer branchTypeID;	//机构类型
    private long branchID;		//所属机构
    private Integer userLevelID;	//用户等级编号
    private	double userBalance;		//用户余额
    private String address;		//用户地址
    private String sex;			//性别
    private String createMan;	//创建人
    private String birthday;		//出生年月
    private String idCode;		//证件号码
    private String email;		//
    private Integer isAdmin;	//是否管理
    private Integer isSysAdmin;	//是否系统管理员
    private String createTime;	//创建时间
    private String modifyTime;	//修改时间
    private Integer userPoint;	//用户积分
    private Integer isBill;		//是否账单
    private String headImg;	    //头像文件的路径
    private LoginInfo data;
    private int code;           //执行结果

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LoginInfo getData() {
        return data;
    }

    public void setData(LoginInfo data) {
        this.data = data;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getBranchTypeID() {
        return branchTypeID;
    }

    public void setBranchTypeID(Integer branchTypeID) {
        this.branchTypeID = branchTypeID;
    }

    public long getBranchID() {
        return branchID;
    }

    public void setBranchID(long branchID) {
        this.branchID = branchID;
    }

    public Integer getUserLevelID() {
        return userLevelID;
    }

    public void setUserLevelID(Integer userLevelID) {
        this.userLevelID = userLevelID;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getIsSysAdmin() {
        return isSysAdmin;
    }

    public void setIsSysAdmin(Integer isSysAdmin) {
        this.isSysAdmin = isSysAdmin;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getUserPoint() {
        if(userPoint!=null)
            return userPoint;
        return 0;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Integer getIsBill() {
        return isBill;
    }

    public void setIsBill(Integer isBill) {
        this.isBill = isBill;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "User{" +
                "telephone='" + telephone + '\'' +
                ", branchTypeID=" + branchTypeID +
                ", branchID=" + branchID +
                ", userLevelID=" + userLevelID +
                ", userBalance=" + userBalance +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", createMan='" + createMan + '\'' +
                ", birthday='" + birthday + '\'' +
                ", idCode='" + idCode + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", isSysAdmin=" + isSysAdmin +
                ", createTime='" + createTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", userPoint=" + userPoint +
                ", isBill=" + isBill +
                ", headImg='" + headImg + '\'' +
                ", data=" + data +
                ", code=" + code +
                '}';
    }
}
