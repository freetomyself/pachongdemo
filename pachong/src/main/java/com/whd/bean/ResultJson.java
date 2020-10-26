package com.whd.bean;


import java.util.List;

/**
 * json数据格式接收
 */
public class ResultJson {

        private String ktid;
        private String ktname;
        private List<OccInfos> occInfos;
        public void setKtid(String ktid) {
            this.ktid = ktid;
        }
        public String getKtid() {
            return ktid;
        }

        public void setKtname(String ktname) {
            this.ktname = ktname;
        }
        public String getKtname() {
            return ktname;
        }

        public void setOccInfos(List<OccInfos> occInfos) {
            this.occInfos = occInfos;
        }
        public List<OccInfos> getOccInfos() {
            return occInfos;
        }

    @Override
    public String toString() {
        return "ResultJson{" +
                "ktid='" + ktid + '\'' +
                ", ktname='" + ktname + '\'' +
                ", occInfos=" + occInfos +
                '}';
    }
}
