package com.huafeng.client.net;

/**
 * 作者：凌涛 on 2019/5/5 11:17
 * 邮箱：771548229@qq..com
 */
public class Api {
    public static final String baseUrl = "http://47.106.65.208:8080";
    //public static final String baseUrl = "http://192.168.3.120:8080";
    //public static final String baseUrl = "http://172.20.10.8:8080";

    //    http://47.106.65.208:8080/swagger-ui.html#/
    public static class EmployeeUser {
        public static final String bindTelephone = baseUrl + "/client/_user/bindTelephone";// "微信绑定手机号码";
        public static final String isTelephoneBindWechat = baseUrl + "/client/_user/isTelephoneBindWechat";// "判断手机号是否绑定了微信号 0不存在此手机号 1未绑定微信 2已绑定微信";
        public static final String login = baseUrl + "/client/_user/login";// "登录";
        public static final String register = baseUrl + "/client/_user/register";// "注册";
        public static final String loginByWechat = baseUrl + "/client/_user/loginByWechat";// "通过微信登录";
        public static final String getInformation=baseUrl+"/client/_user/getInformation";//获取个人信息
        public static final String resetPassword = baseUrl + "/client/_user/resetPassword";// "重置密码";
        public static final String getVerificationCode = baseUrl + "/employee_user/getVerificationCode";// "发送验证码";
        public static final String addReback=baseUrl+"/user/feedback/add" ;//添加反馈
        public static final String edit=baseUrl+"/client/_user/edit" ;//修改个人资料
        public static final String exchangeTelephone=baseUrl+ "/client/_user/exchangeTelephone";
    }
    public static class Guide {
        public static final String get = baseUrl + "/guide/page/client/getForApp";//
    }
    public static class Notice {
        public static final String get = baseUrl + "/notice/get";//
    }
    public static class Push {
        public static final String getUnReadNum = baseUrl + "/push/getUnReadNum";//
        public static final String read = baseUrl + "/push/read";//
        public static final String getList = baseUrl + "/push/getList";//
    }
    public static class User {
        public static final String add = baseUrl + "/user/feedback/add";//意见反馈
    }

    public static class Authority {
        public static final String get = baseUrl + "/authority/get";//
        public static final String getMyAuthority = baseUrl + "/authority/getMyAuthority";//
    }

    public static class Raw {
        public static final String add = baseUrl + "/inventory/material/add";//
        public static final String edit = baseUrl + "/inventory/material/edit";//
        public static final String getById = baseUrl + "/raw/matrerial/getById";//
    }



    public static class Purchase {
        public static final String materialCount = baseUrl + "/purchase/requirement/materialCount";//
        public static final String agree = baseUrl + "/purchase/requisition/agree";//
        public static final String refuse = baseUrl + "/purchase/requisition/refuse";//
        public static final String submitBuyOrder = baseUrl + "/purchase/note/fill";//
        public static final String getBuyDetail = baseUrl + "/purchase/note/get";//
        public static final String getAskDetail = baseUrl + "/purchase/requisition/get";//
        public static final String add = baseUrl + "/purchase/requisition/add";//
        public static final String fill = baseUrl + "/purchase/requisition/fill";//
        public static final String get = baseUrl + "/purchase/requisition/get";//
        public static final String getWaitList = baseUrl + "/purchase/requirement/getGroupList";//待购单列表
        public static final String getDetailList = baseUrl + "/purchase/requirement/getList";//待购单列表
        public static final String getGroupList = baseUrl + "/purchase/note/getGroupList";//采购单列表
        public static final String getRequestList = baseUrl + "/purchase/requisition/getGroupList";//请购单列表
    }

    public static class Banner {
        public static final String getBanner = baseUrl + "/banner/client/getBanners";//首页轮播图
    }

    public static class Production {
        public static final String getAllocationForLeader = baseUrl + "/production/order/getAllocationForLeader";
        public static final String leaderAllocate = baseUrl + "/production/order/leaderAllocate";
        public static final String entryInventorySample = baseUrl + "/production/order/entryInventorySample";
        public static final String getAllocation = baseUrl + "/production/order/getAllocation";//获取分配工作
        public static final String preAllocate = baseUrl + "/production/order/preAllocate";//提交分配工作
        public static final String commmitAllocate = baseUrl + "/production/order/commmitAllocate";//提交分配工作
        public static final String setDeliveryNote = baseUrl + "/production/order/setDeliveryNote";//设置送货单
        public static final String setPickupNote = baseUrl + "/production/order/setPickupNote";//设置取货单
        public static final String create = baseUrl + "/production/order/create";//创建
        public static final String materialTake = baseUrl + "/production/order/materialTake";//领用
        public static final String nextFlow = baseUrl + "/production/order/nextFlow";//下一步
        public static final String getDeliveryNote = baseUrl + "/production/order/getDeliveryNote";//获取送货单
        public static final String getMterialConsumeAccount = baseUrl + "/production/order/getMterialConsumeAccount";//用料统计
        public static final String getPickupNote = baseUrl + "/production/order/getPickupNote";//获取取货单
        public static final String getList = baseUrl + "/production/order/getList";//生产订单
        public static final String getProductionOrder = baseUrl + "/production/order/getProductionOrder";//获取生产单详情
        public static final String getProcessForAllocate = baseUrl + "/production/order/getProcessForAllocate";//为工作分配获取工序
        public static final String getProductionOrderClothMaterial = baseUrl + "/production/order/getProductionOrderClothMaterial";//获取布料

    }

    public static class Supplier {
        public static final String getListForSelect = baseUrl + "/supplier/getListForSelect";//为筛选获取供应商列表（不分页）
    }

    public static class Product {
        public static final String getFirstCategorys = baseUrl + "/product/category/getFirstCategorys";//样式一级分类
        public static final String getSecondCategorysByFirst = baseUrl + "/product/category/getSecondCategorysByFirst";//二级分类
    }

    public static class Pattern {
        public static final String designCreate = baseUrl + "/pattern/making/designCreate";
        public static final String addNoPrefixe = baseUrl + "/pattern/making/addNoPrefix";
        public static final String prepareToSample = baseUrl + "/pattern/making/prepareToSample";
        public static final String create = baseUrl + "/pattern/making/create";//创建任务
        public static final String addDesign = baseUrl + "/pattern/making/design";//提交样板卡
        public static final String pattern = baseUrl + "/pattern/making/pattern";//纸样
        public static final String sewing = baseUrl + "/pattern/making/sewing";//车间
        public static final String washing = baseUrl + "/pattern/making/washing";//洗水
        public static final String afterfinish = baseUrl + "/pattern/making/afterfinish";//后整
        public static final String stop = baseUrl + "/pattern/making/stop";//终止任务
        public static final String getFlow = baseUrl + "/pattern/making/getFlow";//详情
        public static final String getList = baseUrl + "/pattern/making/getList";//设计制版列表数据
        public static final String getFlowStatus = baseUrl + "/pattern/making/getFlowStatus";//任务进度
        public static final String getNoPrefixs = baseUrl + "/pattern/making/getNoPrefixs";//获取制版号标头列表
        public static final String copyCard = baseUrl + "/pattern/making/copyCard";//复制
        public static final String saveCard = baseUrl + "/pattern/making/saveCard";//保存
    }

    public static final class Image {
        public static final String uploadImage = baseUrl + "/image/uploadImage";//上传照片
    }

    public static class Size {
        public static final String getHierarchy = baseUrl + "/size/getHierarchy";//获取尺码
        public static final String getSizeType = baseUrl + "/size/information/getList";//获取尺寸类型
    }

    public static class Matrerial {
        public static final String getList = baseUrl + "/raw/matrerial/getList";//获取原料资料
        public static final String getRawMaterials = baseUrl + "/raw/matrerial/getRawMaterials";//获取二级分类下材质类型
        public static final String getFirstCategorys = baseUrl + "/raw/matrerial/category/getFirstCategorys";//获取一级分类
        public static final String getSecondCategorysByFirst = baseUrl + "/raw/matrerial/category/getSecondCategorysByFirst";//获取二级分类
    }

    public static final class Stage {
        public static final String getStageHierarchy = baseUrl + "/stage/getStageHierarchy";
        public static final String getWashingProcess = baseUrl + "/stage/getWashingProcess";//获取洗水流程
    }

    public static final class Client {
        public static final String getMyFactory = baseUrl + "/client/record/getMyFactory";//我的供应商
        public static final String getApplicationsAndInvitations = baseUrl + "/client/invation/getApplicationsAndInvitations";//我的供应商
        public static final String apply = baseUrl + "/client/application/apply";//获取客户不分页
        public static final String getFactoryList = baseUrl + "/client/application/getFactoryList";//获取客户不分页
        public static final String refuse = baseUrl + "/client/invation/refuse";//获取客户不分页
        public static final String agree = baseUrl + "/client/invation/agree";//获取客户不分页

        public static final String getClients = baseUrl + "/client/record/getClients";//获取客户不分页
        public static final String getList = baseUrl + "/client/record/getList";//获取客户分页
        public static final String addClient = baseUrl + "/client/record/add";//添加客户
        public static final String getDetail = baseUrl + "/client/record/get";//添加客户
        public static final String edit = baseUrl + "/client/record/edit";//添加客户
        public static final String getOrderList = baseUrl + "/client/record/getOrderList";
    }

    public static final class Employee {
        public static final String getEmployeesForSewing = baseUrl + "/employee/record/getEmployeesForSewing";//获取设计师不分页
        public static final String getEmployeesForWashing = baseUrl + "/employee/record/getEmployeesForWashing";//获取审批人不分页
        public static final String getEmployeesForAfterfinish = baseUrl + "/employee/record/getEmployeesForAfterfinish";

        public static final String getDesigners = baseUrl + "/employee/record/getDesigners";//获取设计师不分页
        public static final String getApprovers = baseUrl + "/employee/record/getApprovers";//获取审批人不分页
        public static final String getEmployeeForAllocate = baseUrl + "/employee/record/getEmployeeForAllocate";
    }

    public static final class Employ {
        public static final String applyJoinFactory = baseUrl + "/employ/applyJoinFactory";//申请加入工厂
    }

    public static final class Order {

        public static final String getListForCustomer = baseUrl + "/order/getListForCustomer";//完成

        public static final String getButtonOptions = baseUrl + "/order/getButtonOptions";//获取销售订单
        public static final String getOrderList = baseUrl + "/order/getList";//获取销售订单
        public static final String getDetail = baseUrl + "/order/get";//获取订单详情
        public static final String create = baseUrl + "/order/employee/create";//创建
        public static final String finish = baseUrl + "/order/finish";//完成
        public static final String confirm = baseUrl + "/order/employee/confirm";//确认
        public static final String cancel = baseUrl + "/order/cancel";//取消
        public static final String stop = baseUrl + "/order/stop";//终止
        public static final String getProductionOrderList = baseUrl + "/order/getProductionOrderList";//  获取生产单列表
        public static final String getProductionOrderStatistics = baseUrl + "/order/getProductionOrderStatistics";//  获取生产单数量统计
    }

    public static final class Sample {
        public static final String create = baseUrl + "/order/client/create";
        public static final String getForCustomer = baseUrl + "/sample/getForCustomer";
        public static final String getListForCustmerSelect = baseUrl + "/sample/getListForCustmerSelect";
        public static final String getListForCustomer = baseUrl + "/sample/getListForCustomer";

        public static final String editProcessModule = baseUrl + "/sample/module/editProcessModule";
        public static final String editSizeModule = baseUrl + "/sample/module/editSizeModule";
        public static final String editMaterialModule = baseUrl + "/sample/module/editMaterialModule";
        public static final String getMaterialModule = baseUrl + "/sample/module/getMaterialModule";
        public static final String getProcessModule = baseUrl + "/sample/module/getProcessModule";
        public static final String getModels = baseUrl + "/sample/module/getList";
        public static final String getSizeModule = baseUrl + "/sample/module/getSizeModule";
        public static final String add = baseUrl + "/sample/add";
        public static final String getMaterials = baseUrl + "/sample/getMaterials";
        public static final String getList = baseUrl + "/sample/getList";//获取样板列表
        public static final String getDetail = baseUrl + "/sample/get";//获取样板详情
        public static final String getListByClientId = baseUrl + "/sample/getListByClientId";//根据客户id获取列表（不分页，作选择用）
    }

    public static final class Factory {
        public static final String getList = baseUrl + "/factory/getListForApply";//获取工厂列表
    }

    public static final class Approval {
        public static final String getDetail = baseUrl + "/approval/getApproval";//获取详情
        public static final String approve = baseUrl + "/approval/approve";//审批
        public static final String addApproval = baseUrl + "/approval/add";//添加申请
        public static final String getMyApprovals = baseUrl + "/approval/getMyApprovals";//获取我的申请列表
        public static final String getApprovalsForApprove = baseUrl + "/approval/getApprovalsForApprove";//获取工厂申请列表
    }

    public static final class Huanxin {
        public static final String dismissCrowdByCrowdId = baseUrl + "/huanxin/dismissCrowdByCrowdId";//
        public static final String getMyFriendList = baseUrl + "/huanxin/getMyFriendList";//获取我得通讯录
        public static final String getFriendApplyList = baseUrl + "/huanxin/getFriendApplyList";//获取我的好友申请列表
        public static final String getHxUserListForAdd = baseUrl + "/huanxin/getHxUserListForAdd";//查询要添加的用户
        public static final String addFriendApply = baseUrl + "/huanxin/addFriendApply";//添加好友申请
        public static final String updateFriendApply = baseUrl + "/huanxin/updateFriendApply";//审核好友申请
        public static final String deleteMyFriend = baseUrl + "/huanxin/deleteMyFriend";//删除好友
        public static final String getCrowdsList = baseUrl + "/huanxin/getCrowdsList";//获取我的群列表
        public static final String getCrowdApplyList = baseUrl + "/huanxin/getCrowdApplyList";//获取我的群申请列表数据
        public static final String getCrowdDetailsByCrowdId = baseUrl + "/huanxin/getCrowdDetailsByCrowdId";//根据群id获取群相关的所有数据
        public static final String quitCrowdByCrowdId = baseUrl + "/huanxin/quitCrowdByCrowdId";//退出群
        public static final String addCrowd = baseUrl + "/huanxin/addCrowd";//创建群
        public static final String joinCrowdByUserIds = baseUrl + "/huanxin/joinCrowdByUserIds";//群主批量邀请好友进群
        public static final String getHxUserDetails = baseUrl + "/huanxin/getHxUserDetails";//获取好友详情
        public static final String deleteCrowdByUserIds = baseUrl + "/huanxin/deleteCrowdByUserIds";//批量删除群成员
        public static final String updateFriendsById = baseUrl + "/huanxin/updateFriendsById";//批量删除群成员
        public static final String updateCrowds = baseUrl + "/huanxin/updateCrowds";//批量删除群成员
    }

    /**
     * 打卡
     */
    public static final class Attendance {
        public static final String getLocation = baseUrl + "/attendance/location/get";//获取考勤位置
        public static final String clockIn = baseUrl + "/attendance/record/clock";//打卡
        public static final String getMyAttendanceRecordToday=baseUrl+"/attendance/record/getMyAttendanceRecordToday";//获取今日打卡详情
    }

    /**
     * 仓储
     */
    public static final class Inventory {
        public static final String getListForCustomer = baseUrl + "/sample/getListForCustomer";


        public static final String getQuantityAndAmount = baseUrl + "/inventory/sample/getQuantityAndAmount";
        public static final String getMaterialPriceBatch = baseUrl + "/inventory/material/getMaterialPriceBatch";
        public static final String addReturnBill = baseUrl + "/inventory/sample/addReturnBill";//
        public static final String addDispatchBill = baseUrl + "/inventory/sample/addDispatchBill";//
        public static final String finishcheck = baseUrl + "/inventory/sample/check";//
        public static final String finishentry = baseUrl + "/inventory/sample/entry";//
        public static final String getReturnBill = baseUrl + "/inventory/sample/getReturnBill";//
        public static final String getDispatchBill = baseUrl + "/inventory/sample/getDispatchBill";//
        public static final String check = baseUrl + "/inventory/material/check";//
        public static final String entry = baseUrl + "/inventory/material/entry";//
        public static final String scrap = baseUrl + "/inventory/material/scrap";//
        public static final String getFinishDetail = baseUrl + "/inventory/sample/getInventory";//
        public static final String getOriginStorageDetail = baseUrl + "/inventory/material/get";//
        public static final String getRaw = baseUrl + "/inventory/material/getInventory";//
        public static final String getSampleList = baseUrl + "/inventory/sample/getList";//获取成品列表
        public static final String getMaterialList = baseUrl + "/inventory/material/getList";//获取原料列表
        public static final String halfEntry = baseUrl + "/inventory/simi/entry";//
        public static final String getSampleDispatched = baseUrl + "/inventory/simi/getSampleDispatched";//
        public static final String getHalfList = baseUrl + "/inventory/simi/getList";//
        public static final String halfOut = baseUrl + "/inventory/simi/out";//获取成品列表
        public static final String halfCheck = baseUrl + "/inventory/simi/check";//获取原料列表
        public static final String getHalfDetail = baseUrl + "/inventory/simi/getInventory";//获取原料列表
        public static final String getSizeList = baseUrl + "/inventory/sample/getSizeList";//获取原料列表
    }

}
