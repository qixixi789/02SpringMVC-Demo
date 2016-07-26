package cn.judian.dao;

import cn.judian.common.CommonData;
import cn.judian.model.Project;
import cn.judian.model.ProjectP;
import cn.judian.util.RandomUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangruimin on 16/7/11.
 */
public class Common {
    static Random random = new Random();

    /**
     * 得到缓存中所有的项目信息
     *
     * @return
     */
    public static List<Project> getProjectList() {
        List<Project> projectList = new ArrayList<Project>();
        Jedis jedis = JedisPoolUtils.getJedis();
        String[] projectArr = jedis.get("project").split("\\|");

        ProjectP projectP = null;

        ObjectMapper objectMapper = new ObjectMapper();
        //解析器支持解析单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //解析器支持解析结束符
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        for (String s : projectArr) {
            try {
                s = s.replaceAll("Project", "project");
                projectP = objectMapper.readValue(s, ProjectP.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (projectP.getProject().getStatus() > 0) {
                projectList.add(projectP.getProject());
            }
        }
        JedisPoolUtils.returnRes(jedis);
        return projectList;
    }

    /**
     * 获取map集合的项目集合
     * key 为项目id
     * value 为项目对象
     *
     * @return
     */
    public static Map<Integer, Project> getProjectMap() {
        Map<Integer, Project> projectMap = new HashMap<Integer, Project>();
        for (Project project : getProjectList()) {
            projectMap.put(project.getId(), project);
        }
        return projectMap;
    }

    /**
     * 随机得到一个项目
     *
     * @return
     */
    public static Project getProject() {
        List<Project> projectList = getProjectList();
        int size = projectList.size();
        return projectList.get(random.nextInt(size));
    }

    /**
     * 根据项目id去获取一个项目的信息
     *
     * @param projectId
     * @return
     */
    public static Project getProject(int projectId) {
        Map<Integer, Project> projectMap = getProjectMap();

        if (projectMap.get(projectId) != null) {
            return projectMap.get(projectId);
        } else {
            return null;
        }
    }

    /**
     * 得到指定数量的新闻标题
     *
     * @param num
     * @return
     */
    public static List<String> getNews(int num) {
        List<String> newsList = new ArrayList<String>();
        // redis缓存
        Jedis jedis = JedisPoolUtils.getJedis();
        int maxNews = Integer.parseInt(jedis.get(CommonData.maxNews));
        int seed = random.nextInt(maxNews - num);
        for (int i = 0; i < num; i++) {
            String s = jedis.get("news_" + seed++);
            newsList.add(s.length() > 24 ? s.substring(0, 20) + "..." : s);
        }
        JedisPoolUtils.returnRes(jedis);
        return newsList;
    }

    /**
     * 得到项目的信息
     *
     * @param num
     * @return
     */
    public static String getProjectData(int num) {
        List<Project> list = getProjectList();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (Project project : list) {
            // 组合
            List<String> titleList = getTitleList(project.getId(), num);
            for (int i = 0; i < titleList.size(); i++) {
                if (i == 0) {
                    sb.append("<div class=\"box-object\"><h2><a href=\"")
                            .append(random.nextInt(999999)).append(".html\">")
                            .append(titleList.get(i)).append("</a></h2>");
                } else {
                    sb.append("<p><a title=\"").append(titleList.get(i)).append("\" target=\"_blank\" href=\"")
                            .append(random.nextInt(999999)).append(".html\"").append(" title=\"")
                            .append(titleList.get(i)).append("\">").append(titleList.get(i))
                            .append("</a></p>");
                }
            }
            sb.append("</div>");
            titleList.clear();
        }
        list.clear();
        return sb.toString();
    }

    /**
     * 获取时间
     *
     * @return
     */
    public static String getDate(String format) {
        return new SimpleDateFormat(format).format(System.currentTimeMillis() - 600000);
    }

    /**
     * 从缓存中获取文章出来
     *
     * @return
     */
    public static String getContent() {
        StringBuilder sb = new StringBuilder();
        Jedis jedis = JedisPoolUtils.getJedis();

        // 获取到最大的文章字段数量
        int maxContent = Integer.parseInt(jedis.get("MAX_content"));
        // 获取到最大的新闻字段数量
        int maxNews = Integer.parseInt(jedis.get("MAX_news"));

        // 拼接网页内容
        // 段落
        for (int i = 0; i < random.nextInt(2) + 3; i++) {
            sb.append("<p style=\"text-indent: 2cm\">");
            // 句子
            for (int j = 0; j < random.nextInt(2) + 3; j++) {
                String s = null;
                int count = 0;
                while (s == null && count < 20) {
                    s = jedis.get("content_" + random.nextInt(maxContent) + 1);
                    count++;
                }
                if (s != null) {
                    sb.append(s + "。");
                }
            }
            sb.append("</p>\n");
        }
        JedisPoolUtils.returnRes(jedis);
        return sb.toString();
    }

    /**
     * 返回一条相对路径的链接
     *
     * @return
     */
    public static String getHref() {
        return random.nextInt(999999999) + ".html";
    }

    /**
     * 返回一条新闻标题
     *
     * @return
     */
    public static String getNew() {
        // redis缓存
        Jedis jedis = JedisPoolUtils.getJedis();
        int maxNews = Integer.parseInt(jedis.get(CommonData.maxNews));
        String s = jedis.get("news_" + random.nextInt(maxNews) + 1);
        JedisPoolUtils.returnRes(jedis);
        return s;
    }

    /**
     * 返回一个随机的title
     *
     * @return
     */
    public static String getTitle() {
        Jedis jedis = JedisPoolUtils.getJedis();
        List<Project> projectList = getProjectList();
        Project p = projectList.get(random.nextInt(projectList.size()));
        int id = p.getId();
        int MAX = Integer.parseInt(jedis.get("MAX_" + id + "_title"));
        String title = jedis.get("title_" + id + "_" + random.nextInt(MAX + 1));
        JedisPoolUtils.returnRes(jedis);
        projectList.clear();
        return title;
    }

    /**
     * 返回一个指定项目id的随机title
     *
     * @param id 指定的项目id
     * @return
     */
    public static String getTitle(int id) {
        StringBuilder sb = new StringBuilder();
        Jedis jedis = JedisPoolUtils.getJedis();
        int MAX = Integer.parseInt(jedis.get("MAX_" + id + "_title"));
        String title = jedis.get("title_" + id + "_" + random.nextInt(MAX + 1));

        JedisPoolUtils.returnRes(jedis);

        if (id == 0) {
            return sb.append("<p><a title=\"").append(title).append("\" target=\"_blank\" href=\"")
                    .append(random.nextInt(999999)).append(".html\"").append(" title=\"")
                    .append(title).append("\">").append(title)
                    .append("</a></p>").toString();
        } else {
            return title;
        }
    }

    /**
     * 从缓存中取关键词
     *
     * @param id  项目的id
     * @param num 项目关键词的数量
     * @return 存放项目id的关键词集合
     */
    public static List<String> getTitleList(int id, int num) {
        List<String> list = new ArrayList<String>();
        Jedis jedis = JedisPoolUtils.getJedis();
        int MAX = Integer.parseInt(jedis.get("MAX_" + id + "_title"));

        String title;
        for (int i = 0; i < num; i++) {
            title = jedis.get("title_" + id + "_" + random.nextInt(MAX + 1));
            list.add(title);
        }
        JedisPoolUtils.returnRes(jedis);
        return list;
    }

    /**
     * 返回一个锚链接版的title
     * 列表页里面的
     *
     * @param id  项目id
     * @param num 链接数量
     * @return String
     */
    public static String getListHrefTitle(int id, int num) {
        StringBuilder sb = new StringBuilder();
        Jedis jedis = JedisPoolUtils.getJedis();
        int MAX = Integer.parseInt(jedis.get("MAX_" + id + "_title"));

        String title;
        for (int i = 0; i < num; i++) {
            title = jedis.get("title_" + id + "_" + random.nextInt(MAX + 1));
            sb.append("<li><a title=\"").append(title).append("\" target=\"_blank\" href=\"")
                    .append(id + "/" + random.nextInt(999999)).append(".html\"")
                    .append(">").append(title)
                    .append("</a></li>\n");
        }
        JedisPoolUtils.returnRes(jedis);
        return sb.toString();
    }

    /**
     * 返回一个锚链接版的title
     * 内容页里面的
     *
     * @param id  项目id
     * @param num 链接数量
     * @return String
     */
    public static String getContentHrefTitle(int id, int num) {
        StringBuilder sb = new StringBuilder();
        Jedis jedis = JedisPoolUtils.getJedis();
        int MAX = Integer.parseInt(jedis.get("MAX_" + id + "_title"));

        String title;
        for (int i = 0; i < num; i++) {
            title = jedis.get("title_" + id + "_" + random.nextInt(MAX + 1));
            sb.append("<li><a title=\"").append(title).append("\" target=\"_blank\" href=\"")
                    .append(RandomUtil.getStringRandom(5)).append(".html\"")
                    .append(">").append(title)
                    .append("</a></li>\n");
        }
        JedisPoolUtils.returnRes(jedis);
        return sb.toString();
    }

    /**
     * 此方法是返回列表页页码的方法
     * @param id  项目id
     * @param num 页码的数量
     * @return
     */
    public static String getPage(int id, int num) {
        int n = random.nextInt(1000) + 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("<a href=\"").append(RandomUtil.getStringRandom(5)).append("/")
                    .append(RandomUtil.getStringRandom(5)).append("/").append("list").append(id)
                    .append("/").append(n).append(".html").append("\">")
                    .append(n).append("</a>\n");
            n ++;
        }
        return sb.toString();
    }
}
