package cn.judian.dao;

import cn.judian.model.CurpeContent;
import cn.judian.model.Project;
import cn.judian.util.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhangruimin on 16/7/13.
 * 处理首页的内容调用问题
 * url规则 /news/yingshi/1.html       -------->      Hash--->yinshi  (key:1     value:content对象)
 */
public class Home {
    static ObjectMapper mapper = new ObjectMapper();
    static Random random = new Random();
    /**
     *
     * @param type          栏目名字
     * @param count         最新数据条数
     * @return              拼接html列表
     */
    public static String getPart(String start, String type, int count) {
        StringBuilder sb = new StringBuilder();
        Jedis jedis = JedisPoolUtils.getJedis();
        String json;
        CurpeContent content;
        long size = jedis.hlen(type);
        if (count > size) {
            count = (int)size;
        }
        for (int i = (int)(size - count); i < size; i++) {
            json = jedis.hget(type, String.valueOf(i));
            try {
                content = mapper.readValue(json, CurpeContent.class);
                sb.append("<li><a href=\"").append(start + i + ".html")
                        .append("\" target=\"_blank\" title=\"").append(content.getTitle())
                        .append("\" >").append(content.getTitle())
                        .append("</a></li>\n");
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        // 关闭连接
        JedisPoolUtils.returnRes(jedis);
        return sb.toString();
    }

    /**
     * 根据栏目名字和文章id去缓存里面取
     * @param part      栏目名字
     * @param id        文章id
     * @return
     */
    public static CurpeContent getContent(String part, int id) {
        CurpeContent content = null;
        Jedis jedis = JedisPoolUtils.getJedis();
        String json = jedis.hget(part, String.valueOf(id));
        if (json != null) {
            try {
                content = mapper.readValue(json, CurpeContent.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        JedisPoolUtils.returnRes(jedis);
        return content;
    }

    /**
     * 返回part目录下随机的一条连接
     * @param part
     * @return
     */
    public static String getCoupeHref(String part) {
        Jedis jedis = JedisPoolUtils.getJedis();
        long size = jedis.hlen(part);
        JedisPoolUtils.returnRes(jedis);
        return "/news/" + part + "/" + random.nextInt((int)size + 1) + ".html";
    }

    /**
     *
     * @param start         连接的开始字符串
     * @param type          类型
     * @param count         数量
     * @return
     */
    public static String getRandomPart(String start, String type, int count) {
        StringBuilder sb = new StringBuilder();
        Jedis jedis = JedisPoolUtils.getJedis();
        String json;
        CurpeContent content;
        long size = jedis.hlen(type);
        if (count > size) {
            count = (int)size;
        }
        for (int i = 0; i < count; i++) {
            int key = random.nextInt((int)size);
            json = jedis.hget(type, String.valueOf(key));
            try {
                content = mapper.readValue(json, CurpeContent.class);
                sb.append("<li><a href=\"").append(start + key + ".html")
                        .append("\" target=\"_blank\" title=\"").append(content.getTitle())
                        .append("\" >").append(content.getTitle())
                        .append("</a></li>\n");
            } catch (IOException e) {
                e.printStackTrace();
                return e.getMessage();
            }
        }
        JedisPoolUtils.returnRes(jedis);
        return sb.toString();
    }
    
    public static String getProjectHrefList() {
        StringBuilder sb = new StringBuilder();
        List<Project> projectList = Common.getProjectList();

        for (Project project : projectList) {
            String name = project.getName();
            sb.append("<li><a title=\"").append(name).append("\" target=\"_blank\" href=\"")
                    .append(RandomUtil.getStringRandom(5) + "/" + RandomUtil.getStringRandom(5) + "/list" + project.getId() + "/" + random.nextInt(999999)).append(".html\"")
                    .append(">").append(name)
                    .append("</a></li>\n");
        }
        projectList.clear();
        return sb.toString();
    }
}
