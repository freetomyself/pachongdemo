package com.whd.bean;

/**
 * Auto-generated: 2020-10-21 22:39:8
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */

/**
 * json数据中的数组部分
 */
public class OccInfos {

    private String id;
    private String xlid;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setXlid(String xlid) {
        this.xlid = xlid;
    }

    public String getXlid() {
        return xlid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "OccInfos{" +
                "id='" + id + '\'' +
                ", xlid='" + xlid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
