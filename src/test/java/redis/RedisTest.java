package redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisTest {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = RedisUtil.getJedis();
        redis_cache(jedis);
        RedisUtil.returnJedis();
    }
    
    public static void redis_String(Jedis jedis) {
        jedis.set("pool", "success");
        jedis.append("pool", " agin");
        System.out.println(jedis.get("pool"));
        jedis.del("pool");
    }
    
    public static void redis_Map(Jedis jedis) {
        Map<String , String> map = new HashMap<String , String>();
        map.put("name", "xiaohaihui");
        map.put("age", "26");
        map.put("sex", "man");
        jedis.hmset("user", map);
        List<String> list = jedis.hmget("user", "name" , "age" , "sex");
        for(String str : list) {
            System.out.println(str + "_");
        }
        jedis.hdel("user", "name");
        Iterator<String> iterator = jedis.hkeys("user").iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            System.out.println(jedis.hmget("user", key));
        }
        jedis.del("user");
        System.out.println(jedis.exists("user"));
    }
    
    public static void redis_List(Jedis jedis) {
        jedis.lpush("info", "llp");
        jedis.lpush("info", "24");
        jedis.lpush("info", "woman");
        System.out.println(jedis.lrange("info", 0, -1));
    }
    
    public static void redis_cache(Jedis jedis) throws InterruptedException {
        jedis.set("cache", "cache");
        jedis.expire("cache", 5);
        System.out.println(jedis.get("cache"));
        Thread.sleep(5000);
        System.out.println(jedis.get("cache"));
    }
}
