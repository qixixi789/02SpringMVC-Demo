package cn.judian.model;

/**
 * Created by zhangruimin on 16/7/14.
 */
public class CurpeContent {
    private String title;           // 关键词
    private String article;         // 内容

    public CurpeContent() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "CurpeContent{" +
                "title='" + title + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}
