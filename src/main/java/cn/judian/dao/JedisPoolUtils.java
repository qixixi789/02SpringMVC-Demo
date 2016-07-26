package cn.judian.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class JedisPoolUtils {

    private static JedisPool pool;

    static String host;             //主机
    static String port;             // 端口
    static String passWord;         // 密码
    static String timeout;          // 指定连接服务器的超时时间

    static String maxActive;        // 最大连接数
    static String maxWait;          // 最大阻塞时间，记住是毫秒数milliseconds
    static String maxIdle;          // 空间连接

    public static String flag;          // 是否清空缓存

    static Properties p = new Properties();

    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     */
    private void createJedisPool() {
        String url = this.getClass().getResource("").getPath();
        String path = url.substring(0, url.indexOf("WEB-INF")) + "WEB-INF/property/redis.properties";

        try {
            p.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        host = p.getProperty("host");
        port = p.getProperty("port");
        passWord = p.getProperty("passWord");
        timeout = p.getProperty("timeout");

        maxActive = p.getProperty("maxActive");
        maxWait = p.getProperty("maxWait");
        maxIdle = p.getProperty("maxIdle");

        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();

        // 设置最大连接数
        config.setMaxActive(Integer.parseInt(maxActive));

        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWait(Integer.parseInt(maxWait));

        // 设置空间连接
        config.setMaxIdle(Integer.parseInt(maxIdle));

        // 创建连接池
        if (passWord != null) {
            pool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), passWord);
        } else {
            pool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout));
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            new JedisPoolUtils().createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public static synchronized Jedis getJedis() {
        flag = p.getProperty("flag");

        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    /**
     * 归还一个连接
     *
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
    }

}