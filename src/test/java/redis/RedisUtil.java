package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private final static String address = "127.0.0.1";
    private final static int port = 6379;
    private final static String pwd = "123456";
    private static JedisPool pool = null;
    
    public static Jedis getJedis() {
        Jedis jedis = setPool().getResource();
        return jedis;
    }
    
    public static JedisPool setPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(512);
        config.setMaxIdle(50);
        config.setMaxWaitMillis(10000);
        pool = new JedisPool(config , address , port , 10000 , pwd);
        return pool;
    }
    
    public static void returnJedis() {
        pool.close();
    }
}
