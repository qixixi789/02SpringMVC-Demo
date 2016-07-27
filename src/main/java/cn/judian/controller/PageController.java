package cn.judian.controller;

import cn.judian.dao.Common;
import cn.judian.dao.Home;
import cn.judian.dao.JedisPoolUtils;
import cn.judian.model.Content;
import cn.judian.model.CurpeContent;
import cn.judian.model.Project;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangruimin on 16/7/7.
 */
@Controller
public class PageController {
    /**
     * 科普文章内容页
     *
     * @param model
     * @param type
     * @param id
     * @return
     */
    @RequestMapping(value = "news/{type:\\w+}/{id:\\d+}.html")
    public String showCoupeContent(Model model, @PathVariable String type,
                                   @PathVariable("id") int id) {
        CurpeContent content;
        if ((content = Home.getContent(type, id)) != null) {
            model.addAttribute("content", content);
            return "/article.jsp";
        } else {
            return "/index.jsp";
        }
    }

    /**
     * 网站地图
     */
    @RequestMapping(value = "/map")
    public String home() {
        return "/WEB-INF/jsp/home/map.jsp";
    }

    /**
     * 指定项目的列表页
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/*/*/list{id:\\d+}/**")
    public String projectList(@PathVariable Integer id, Model model, HttpServletRequest request) {
        // 如果没有这个项目id,返回内容页,有就返回项目列表页。
        if (Common.getProject(id) == null) {
            Project project = Common.getProject();
            Content content = new Content();
            content.setAdjs(project.getAdjs());
            content.setDesc(project.getDescription());
            content.setKeyWords(project.getKeywords());
            content.setId(project.getId());
            content.setTitle(Common.getTitle());

            model.addAttribute("content", content);
            return "/WEB-INF/jsp/common/blank.jsp";
        } else {
            model.addAttribute("id", id);
            return "/WEB-INF/jsp/common/list.jsp";
        }
    }

    /**
     * 指定项目的内容
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/{id:\\d+}/**")
    public String projectContent(@PathVariable Integer id, Model model, HttpServletRequest request) {
        Project project = Common.getProject(id);
        // 如果没有这个项目id,返回内容页,有就返回项目列表页。
        if (project == null) {
            project = Common.getProject();
            Content content = new Content();
            content.setAdjs(project.getAdjs());
            content.setDesc(project.getDescription());
            content.setKeyWords(project.getKeywords());
            content.setId(project.getId());
            content.setTitle(Common.getTitle());

            model.addAttribute("content", content);
            return "/WEB-INF/jsp/common/blank.jsp";
        } else {
            Random random = new Random();
            // ModleAndView对象
            ModelAndView modelAndView = new ModelAndView();
            // 内容页对象
            Content content = new Content();
            // 网页title
            String title = "";
            // 获取reids连接
            Jedis jedis = JedisPoolUtils.getJedis();

            String path = getClass().getResource("").getPath();
            // 访问的url
            String url = request.getRequestURI();
            url = url.replaceAll("\\.html", "");
            url = url.replaceFirst("/", "");
            url = url.replaceAll("(#|=|/|\\?|&)", "_");

            String line = null;
            /** 判断当前访问的页面是否已经被蜘蛛爬过 **/
            if ((line = jedis.get(url)) != null) {    // 蜘蛛爬取过的页面
                String[] lineArr = line.split("_");
                String s = "";
                for (int i = 0; i < lineArr.length; i++) {
                    if (i == 0) {
                        content.setId(Integer.parseInt(lineArr[0]));
                    } else if (i == lineArr.length - 1) {
                        s += lineArr[i];
                    } else {
                        s += lineArr[i] + "_";
                    }
                }
                Pattern pattern = Pattern.compile("-?[1-9]\\d*");
                Matcher matcher = pattern.matcher(s);

                if (matcher.matches()) {            // title全是数字
                    content.setTitle(Common.getTitle(content.getId()));
                } else {                            // title为字
                    content.setTitle(s);
                }
                project = Common.getProject(content.getId());
                content.setKeyWords(project.getKeywords());
                content.setAdjs(project.getAdjs());
                content.setDesc(project.getDescription());
            } else {                                 // 蜘蛛没爬取过的页面

                content.setAdjs(project.getAdjs());
                content.setDesc(project.getDescription());
                content.setId(project.getId());
                content.setKeyWords(project.getKeywords());
                title = Common.getTitle(project.getId());
                content.setTitle(title);
            }

            boolean flag = request.getHeader("User-Agent").toLowerCase().contains("spider");
            /** 判断当前是否蜘蛛来访问 **/
            if (flag) {                     // 蜘蛛来访问的时候
                path = path.substring(0, path.indexOf("WEB-INF")) + "WEB-INF/jsp/content/";
                File[] files = new File(path).listFiles();
                // 从文件夹下面随机获取一个jsp文件
                model.addAttribute("content", content);

                // 将键值对存入缓存当中
                if (jedis.get(url) == null) {
                    jedis.set(url, project.getId() + "_" + title);
                }
                // 把链接返回reids连接池
                JedisPoolUtils.returnRes(jedis);
                return "/WEB-INF/jsp/content/" + files[random.nextInt(files.length)].getName();
            } else {                        // 普通用户访问的时候
                model.addAttribute("content", content);
                return "/WEB-INF/jsp/common/blank.jsp";
            }
        }
    }

    /**
     * 匹配任意链接
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/**")
    public ModelAndView showContent(HttpServletRequest request) {
        Random random = new Random();
        // ModleAndView对象
        ModelAndView modelAndView = new ModelAndView();
        // 项目对象
        Project project;
        // 内容页对象
        Content content = new Content();
        // 网页title
        String title = "";
        // 获取reids连接
        Jedis jedis = JedisPoolUtils.getJedis();

        String path = getClass().getResource("").getPath();
        // 访问的url
        String url = request.getRequestURI();
        url = url.replaceAll("\\.html", "");
        url = url.replaceFirst("/", "");
        url = url.replaceAll("(#|=|/|\\?|&)", "_");

        String line = null;
        /** 判断当前访问的页面是否已经被蜘蛛爬过 **/
        if ((line = jedis.get(url)) != null) {    // 蜘蛛爬取过的页面
            String[] lineArr = line.split("_");
            String s = "";
            for (int i = 0; i < lineArr.length; i++) {
                if (i == 0) {
                    content.setId(Integer.parseInt(lineArr[0]));
                } else if (i == lineArr.length - 1) {
                    s += lineArr[i];
                } else {
                    s += lineArr[i] + "_";
                }
            }
            Pattern pattern = Pattern.compile("-?[1-9]\\d*");
            Matcher matcher = pattern.matcher(s);

            if (matcher.matches()) {            // title全是数字
                content.setTitle(Common.getTitle(content.getId()));
            } else {                            // title为字
                content.setTitle(s);
            }
            project = Common.getProject(content.getId());
            content.setKeyWords(project.getKeywords());
            content.setAdjs(project.getAdjs());
            content.setDesc(project.getDescription());
        } else {                                 // 蜘蛛没爬取过的页面
            // 获取项目对象
            project = Common.getProject();

            content.setAdjs(project.getAdjs());
            content.setDesc(project.getDescription());
            content.setId(project.getId());
            content.setKeyWords(project.getKeywords());
            title = Common.getTitle(project.getId());
            content.setTitle(title);
        }

        boolean flag = request.getHeader("User-Agent").toLowerCase().contains("spider");
        /** 判断当前是否蜘蛛来访问 **/
        if (flag) {                     // 蜘蛛来访问的时候
            path = path.substring(0, path.indexOf("WEB-INF")) + "WEB-INF/jsp/content/";
            File[] files = new File(path).listFiles();
            // 从文件夹下面随机获取一个jsp文件
            modelAndView.setViewName("/WEB-INF/jsp/content/" + files[random.nextInt(files.length)].getName());
            modelAndView.addObject("content", content);

            // 将键值对存入缓存当中
            if (jedis.get(url) == null) {
                jedis.set(url, project.getId() + "_" + title);
            }
        } else {                        // 普通用户访问的时候
            modelAndView.addObject("content", content);
            modelAndView.setViewName("/WEB-INF/jsp/common/blank.jsp");
        }
        // 把链接返回reids连接池
        JedisPoolUtils.returnRes(jedis);
        return modelAndView;
    }
}
