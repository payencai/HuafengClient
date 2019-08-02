package com.huafeng.client.ui.home.model;

public class Record {

    /**
     * attendanceRecord : {"addressFirst":"string","addressLast":"string","attendanceShiftBakId":0,"attendanceShiftId":0,"attendanceShiftScheduleBakId":0,"attendanceShiftScheduleId":0,"day":"2019-07-22T06:39:41.590Z","employeeRecordId":0,"factoryId":0,"gmtFirstTime":"2019-07-22T06:39:41.590Z","gmtSecondTime":"2019-07-22T06:39:41.590Z","id":0,"latFirst":"string","latLast":"string","lngFirst":"string","lngLast":"string","status":0}
     * attendanceShiftBak : {"attendanceShiftId":0,"factoryId":0,"gmtCreate":"2019-07-22T06:39:41.590Z","gmtModified":"2019-07-22T06:39:41.590Z","id":0,"isDeleted":0,"name":"string","remarks":"string"}
     * attendanceShiftScheduleBak : {"attendacneShiftScheduleId":0,"attendanceShiftBakId":0,"attendanceShiftId":0,"endTime":"2019-07-22T06:39:41.590Z","factoryId":0,"gmtCreate":"2019-07-22T06:39:41.590Z","gmtEffect":"2019-07-22T06:39:41.590Z","gmtModified":"2019-07-22T06:39:41.590Z","id":0,"isDeleted":0,"remarks":"string","startTime":"2019-07-22T06:39:41.590Z","week":"string"}
     */

    private AttendanceRecordBean attendanceRecord;
    private AttendanceShiftBakBean attendanceShiftBak;
    private AttendanceShiftScheduleBakBean attendanceShiftScheduleBak;

    public AttendanceRecordBean getAttendanceRecord() {
        return attendanceRecord;
    }

    public void setAttendanceRecord(AttendanceRecordBean attendanceRecord) {
        this.attendanceRecord = attendanceRecord;
    }

    public AttendanceShiftBakBean getAttendanceShiftBak() {
        return attendanceShiftBak;
    }

    public void setAttendanceShiftBak(AttendanceShiftBakBean attendanceShiftBak) {
        this.attendanceShiftBak = attendanceShiftBak;
    }

    public AttendanceShiftScheduleBakBean getAttendanceShiftScheduleBak() {
        return attendanceShiftScheduleBak;
    }

    public void setAttendanceShiftScheduleBak(AttendanceShiftScheduleBakBean attendanceShiftScheduleBak) {
        this.attendanceShiftScheduleBak = attendanceShiftScheduleBak;
    }

    public static class AttendanceRecordBean {
        /**
         * addressFirst : string
         * addressLast : string
         * attendanceShiftBakId : 0
         * attendanceShiftId : 0
         * attendanceShiftScheduleBakId : 0
         * attendanceShiftScheduleId : 0
         * day : 2019-07-22T06:39:41.590Z
         * employeeRecordId : 0
         * factoryId : 0
         * gmtFirstTime : 2019-07-22T06:39:41.590Z
         * gmtSecondTime : 2019-07-22T06:39:41.590Z
         * id : 0
         * latFirst : string
         * latLast : string
         * lngFirst : string
         * lngLast : string
         * status : 0
         */

        private String addressFirst;
        private String addressLast;
        private int attendanceShiftBakId;
        private int attendanceShiftId;
        private int attendanceShiftScheduleBakId;
        private int attendanceShiftScheduleId;
        private String day;
        private int employeeRecordId;
        private int factoryId;
        private String gmtFirstTime;
        private String gmtSecondTime;
        private int id;
        private String latFirst;
        private String latLast;
        private String lngFirst;
        private String lngLast;
        private int status;

        public String getAddressFirst() {
            return addressFirst;
        }

        public void setAddressFirst(String addressFirst) {
            this.addressFirst = addressFirst;
        }

        public String getAddressLast() {
            return addressLast;
        }

        public void setAddressLast(String addressLast) {
            this.addressLast = addressLast;
        }

        public int getAttendanceShiftBakId() {
            return attendanceShiftBakId;
        }

        public void setAttendanceShiftBakId(int attendanceShiftBakId) {
            this.attendanceShiftBakId = attendanceShiftBakId;
        }

        public int getAttendanceShiftId() {
            return attendanceShiftId;
        }

        public void setAttendanceShiftId(int attendanceShiftId) {
            this.attendanceShiftId = attendanceShiftId;
        }

        public int getAttendanceShiftScheduleBakId() {
            return attendanceShiftScheduleBakId;
        }

        public void setAttendanceShiftScheduleBakId(int attendanceShiftScheduleBakId) {
            this.attendanceShiftScheduleBakId = attendanceShiftScheduleBakId;
        }

        public int getAttendanceShiftScheduleId() {
            return attendanceShiftScheduleId;
        }

        public void setAttendanceShiftScheduleId(int attendanceShiftScheduleId) {
            this.attendanceShiftScheduleId = attendanceShiftScheduleId;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getEmployeeRecordId() {
            return employeeRecordId;
        }

        public void setEmployeeRecordId(int employeeRecordId) {
            this.employeeRecordId = employeeRecordId;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getGmtFirstTime() {
            return gmtFirstTime;
        }

        public void setGmtFirstTime(String gmtFirstTime) {
            this.gmtFirstTime = gmtFirstTime;
        }

        public String getGmtSecondTime() {
            return gmtSecondTime;
        }

        public void setGmtSecondTime(String gmtSecondTime) {
            this.gmtSecondTime = gmtSecondTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLatFirst() {
            return latFirst;
        }

        public void setLatFirst(String latFirst) {
            this.latFirst = latFirst;
        }

        public String getLatLast() {
            return latLast;
        }

        public void setLatLast(String latLast) {
            this.latLast = latLast;
        }

        public String getLngFirst() {
            return lngFirst;
        }

        public void setLngFirst(String lngFirst) {
            this.lngFirst = lngFirst;
        }

        public String getLngLast() {
            return lngLast;
        }

        public void setLngLast(String lngLast) {
            this.lngLast = lngLast;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class AttendanceShiftBakBean {
        /**
         * attendanceShiftId : 0
         * factoryId : 0
         * gmtCreate : 2019-07-22T06:39:41.590Z
         * gmtModified : 2019-07-22T06:39:41.590Z
         * id : 0
         * isDeleted : 0
         * name : string
         * remarks : string
         */

        private int attendanceShiftId;
        private int factoryId;
        private String gmtCreate;
        private String gmtModified;
        private int id;
        private int isDeleted;
        private String name;
        private String remarks;

        public int getAttendanceShiftId() {
            return attendanceShiftId;
        }

        public void setAttendanceShiftId(int attendanceShiftId) {
            this.attendanceShiftId = attendanceShiftId;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }

    public static class AttendanceShiftScheduleBakBean {
        /**
         * attendacneShiftScheduleId : 0
         * attendanceShiftBakId : 0
         * attendanceShiftId : 0
         * endTime : 2019-07-22T06:39:41.590Z
         * factoryId : 0
         * gmtCreate : 2019-07-22T06:39:41.590Z
         * gmtEffect : 2019-07-22T06:39:41.590Z
         * gmtModified : 2019-07-22T06:39:41.590Z
         * id : 0
         * isDeleted : 0
         * remarks : string
         * startTime : 2019-07-22T06:39:41.590Z
         * week : string
         */

        private int attendacneShiftScheduleId;
        private int attendanceShiftBakId;
        private int attendanceShiftId;
        private String endTime;
        private int factoryId;
        private String gmtCreate;
        private String gmtEffect;
        private String gmtModified;
        private int id;
        private int isDeleted;
        private String remarks;
        private String startTime;
        private String week;

        public int getAttendacneShiftScheduleId() {
            return attendacneShiftScheduleId;
        }

        public void setAttendacneShiftScheduleId(int attendacneShiftScheduleId) {
            this.attendacneShiftScheduleId = attendacneShiftScheduleId;
        }

        public int getAttendanceShiftBakId() {
            return attendanceShiftBakId;
        }

        public void setAttendanceShiftBakId(int attendanceShiftBakId) {
            this.attendanceShiftBakId = attendanceShiftBakId;
        }

        public int getAttendanceShiftId() {
            return attendanceShiftId;
        }

        public void setAttendanceShiftId(int attendanceShiftId) {
            this.attendanceShiftId = attendanceShiftId;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getFactoryId() {
            return factoryId;
        }

        public void setFactoryId(int factoryId) {
            this.factoryId = factoryId;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public String getGmtEffect() {
            return gmtEffect;
        }

        public void setGmtEffect(String gmtEffect) {
            this.gmtEffect = gmtEffect;
        }

        public String getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(String gmtModified) {
            this.gmtModified = gmtModified;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsDeleted() {
            return isDeleted;
        }

        public void setIsDeleted(int isDeleted) {
            this.isDeleted = isDeleted;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }
    }
}
