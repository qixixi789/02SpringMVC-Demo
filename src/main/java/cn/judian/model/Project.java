package cn.judian.model;

/**
 * Created by zhangruimin on 16/7/8.
 */
public class Project {
    private int id;                 // 项目id
    private String ename;           // 英文名字
    private String name;            // 项目名字
    private String keywords;        // 关键词
    private String description;     // 描述
    private String adjs;            // 广告位
    private int status;             // 项目状态
    private int adjs_status;        // 广告位状态
    private String customer;        // 客户名字

    public Project() {
    }

    public Project(int id, String ename, String name, String keywords, String description, String adjs, int status, int adjs_status, String customer) {
        this.id = id;
        this.ename = ename;
        this.name = name;
        this.keywords = keywords;
        this.description = description;
        this.adjs = adjs;
        this.status = status;
        this.adjs_status = adjs_status;
        this.customer = customer;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdjs() {
        return adjs;
    }

    public void setAdjs(String adjs) {
        this.adjs = adjs;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAdjs_status() {
        return adjs_status;
    }

    public void setAdjs_status(int adjs_status) {
        this.adjs_status = adjs_status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("            \"id\":").append("\"" + id + "\"");
        sb.append(",             \"ename\":\"").append(ename).append('\"');
        sb.append(",             \"name\":\"").append(name).append('\"');
        sb.append(",             \"keywords\":\"").append(keywords).append('\"');
        sb.append(",             \"description\":\"").append(description).append('\"');
        sb.append(",             \"adjs\":\"").append(adjs).append('\"');
        sb.append(",             \"status\":").append("\"" + status + "\"");
        sb.append(",             \"adjs_status\":").append("\"" + adjs_status + "\"");
        sb.append(",             \"customer\":\"").append(customer).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
