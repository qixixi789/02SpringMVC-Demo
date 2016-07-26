package cn.judian.model;

/**
 * Created by zhangruimin on 16/7/12.
 * 文章页展现实体类
 */
public class Content {
    private String title;           // 关键词
    private String desc;            // 项目信息
    private String adjs;            // 项目广告位
    private int id;                 // 项目id
    private String keyWords;        // 关键词后缀

    public Content() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAdjs() {
        return adjs;
    }

    public void setAdjs(String adjs) {
        this.adjs = adjs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", adjs='" + adjs + '\'' +
                ", id=" + id +
                ", keyWords='" + keyWords + '\'' +
                '}';
    }
}
