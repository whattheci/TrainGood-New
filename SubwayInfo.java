package swapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubwayInfo {

    @SerializedName("errorMessage")
    @Expose
    private ErrorMessage errorMessage;
    @SerializedName("realtimeArrivalList")
    @Expose
    private List<RealtimeArrivalList> realtimeArrivalList = null;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<RealtimeArrivalList> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(List<RealtimeArrivalList> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }
    public class ErrorMessage {

        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("developerMessage")
        @Expose
        private String developerMessage;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("total")
        @Expose
        private Integer total;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDeveloperMessage() {
            return developerMessage;
        }

        public void setDeveloperMessage(String developerMessage) {
            this.developerMessage = developerMessage;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

    }

    public class RealtimeArrivalList {

        @SerializedName("arvlCd")
        @Expose
        private String arvlCd;
        @SerializedName("arvlMsg2")
        @Expose
        private String arvlMsg2;
        @SerializedName("arvlMsg3")
        @Expose
        private String arvlMsg3;
        @SerializedName("barvlDt")
        @Expose
        private String barvlDt;
        @SerializedName("beginRow")
        @Expose
        private Object beginRow;
        @SerializedName("bstatnId")
        @Expose
        private String bstatnId;
        @SerializedName("bstatnNm")
        @Expose
        private String bstatnNm;
        @SerializedName("btrainNo")
        @Expose
        private String btrainNo;
        @SerializedName("btrainSttus")
        @Expose
        private Object btrainSttus;
        @SerializedName("curPage")
        @Expose
        private Object curPage;
        @SerializedName("endRow")
        @Expose
        private Object endRow;
        @SerializedName("ordkey")
        @Expose
        private String ordkey;
        @SerializedName("pageRow")
        @Expose
        private Object pageRow;
        @SerializedName("recptnDt")
        @Expose
        private String recptnDt;
        @SerializedName("rowNum")
        @Expose
        private Integer rowNum;
        @SerializedName("selectedCount")
        @Expose
        private Integer selectedCount;
        @SerializedName("statnFid")
        @Expose
        private String statnFid;
        @SerializedName("statnId")
        @Expose
        private String statnId;
        @SerializedName("statnList")
        @Expose
        private String statnList;
        @SerializedName("statnNm")
        @Expose
        private String statnNm;
        @SerializedName("statnTid")
        @Expose
        private String statnTid;
        @SerializedName("subwayHeading")
        @Expose
        private String subwayHeading;
        @SerializedName("subwayId")
        @Expose
        private String subwayId;
        @SerializedName("subwayList")
        @Expose
        private String subwayList;
        @SerializedName("subwayNm")
        @Expose
        private Object subwayNm;
        @SerializedName("totalCount")
        @Expose
        private Integer totalCount;
        @SerializedName("trainCo")
        @Expose
        private Object trainCo;
        @SerializedName("trainLineNm")
        @Expose
        private String trainLineNm;
        @SerializedName("updnLine")
        @Expose
        private String updnLine;

        public String getArvlCd() {
            return arvlCd;
        }

        public void setArvlCd(String arvlCd) {
            this.arvlCd = arvlCd;
        }

        public String getArvlMsg2() {
            return arvlMsg2;
        }

        public void setArvlMsg2(String arvlMsg2) {
            this.arvlMsg2 = arvlMsg2;
        }

        public String getArvlMsg3() {
            return arvlMsg3;
        }

        public void setArvlMsg3(String arvlMsg3) {
            this.arvlMsg3 = arvlMsg3;
        }

        public String getBarvlDt() {
            return barvlDt;
        }

        public void setBarvlDt(String barvlDt) {
            this.barvlDt = barvlDt;
        }

        public Object getBeginRow() {
            return beginRow;
        }

        public void setBeginRow(Object beginRow) {
            this.beginRow = beginRow;
        }

        public String getBstatnId() {
            return bstatnId;
        }

        public void setBstatnId(String bstatnId) {
            this.bstatnId = bstatnId;
        }

        public String getBstatnNm() {
            return bstatnNm;
        }

        public void setBstatnNm(String bstatnNm) {
            this.bstatnNm = bstatnNm;
        }

        public String getBtrainNo() {
            return btrainNo;
        }

        public void setBtrainNo(String btrainNo) {
            this.btrainNo = btrainNo;
        }

        public Object getBtrainSttus() {
            return btrainSttus;
        }

        public void setBtrainSttus(Object btrainSttus) {
            this.btrainSttus = btrainSttus;
        }

        public Object getCurPage() {
            return curPage;
        }

        public void setCurPage(Object curPage) {
            this.curPage = curPage;
        }

        public Object getEndRow() {
            return endRow;
        }

        public void setEndRow(Object endRow) {
            this.endRow = endRow;
        }

        public String getOrdkey() {
            return ordkey;
        }

        public void setOrdkey(String ordkey) {
            this.ordkey = ordkey;
        }

        public Object getPageRow() {
            return pageRow;
        }

        public void setPageRow(Object pageRow) {
            this.pageRow = pageRow;
        }

        public String getRecptnDt() {
            return recptnDt;
        }

        public void setRecptnDt(String recptnDt) {
            this.recptnDt = recptnDt;
        }

        public Integer getRowNum() {
            return rowNum;
        }

        public void setRowNum(Integer rowNum) {
            this.rowNum = rowNum;
        }

        public Integer getSelectedCount() {
            return selectedCount;
        }

        public void setSelectedCount(Integer selectedCount) {
            this.selectedCount = selectedCount;
        }

        public String getStatnFid() {
            return statnFid;
        }

        public void setStatnFid(String statnFid) {
            this.statnFid = statnFid;
        }

        public String getStatnId() {
            return statnId;
        }

        public void setStatnId(String statnId) {
            this.statnId = statnId;
        }

        public String getStatnList() {
            return statnList;
        }

        public void setStatnList(String statnList) {
            this.statnList = statnList;
        }

        public String getStatnNm() {
            return statnNm;
        }

        public void setStatnNm(String statnNm) {
            this.statnNm = statnNm;
        }

        public String getStatnTid() {
            return statnTid;
        }

        public void setStatnTid(String statnTid) {
            this.statnTid = statnTid;
        }

        public String getSubwayHeading() {
            return subwayHeading;
        }

        public void setSubwayHeading(String subwayHeading) {
            this.subwayHeading = subwayHeading;
        }

        public String getSubwayId() {
            return subwayId;
        }

        public void setSubwayId(String subwayId) {
            this.subwayId = subwayId;
        }

        public String getSubwayList() {
            return subwayList;
        }

        public void setSubwayList(String subwayList) {
            this.subwayList = subwayList;
        }

        public Object getSubwayNm() {
            return subwayNm;
        }

        public void setSubwayNm(Object subwayNm) {
            this.subwayNm = subwayNm;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Object getTrainCo() {
            return trainCo;
        }

        public void setTrainCo(Object trainCo) {
            this.trainCo = trainCo;
        }

        public String getTrainLineNm() {
            return trainLineNm;
        }

        public void setTrainLineNm(String trainLineNm) {
            this.trainLineNm = trainLineNm;
        }

        public String getUpdnLine() {
            return updnLine;
        }

        public void setUpdnLine(String updnLine) {
            this.updnLine = updnLine;
        }

    }
}