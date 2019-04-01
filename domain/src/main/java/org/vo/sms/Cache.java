package org.vo.sms;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义的缓存类
 * 用于存储手机验证码信息
 * @author yshin1992
 *
 * @param <K>
 * @param <V>
 */
public class Cache<K,V> {
	
	private static Logger logger = LoggerFactory.getLogger(Cache.class);
	private ConcurrentMap<K, V> cacheObjMap = new ConcurrentHashMap<K, V>();

	private DelayQueue<DelayItem<Pair<K, V>>> q = new DelayQueue<DelayItem<Pair<K, V>>>();

	private Thread daemonThread;

	public Cache() {

		Runnable daemonTask = new Runnable() {
			public void run() {
				daemonCheck();
			}
		};

		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}

	private void daemonCheck() {
		logger.info("cache service started.");
		for (;;) {
			try {
				DelayItem<Pair<K, V>> delayItem = q.take();
				if (delayItem != null) {
					Pair<K, V> pair = delayItem.getItem();
					cacheObjMap.remove(pair.first, pair.second); // compare and
																	// remove
				}
			} catch (InterruptedException e) {
				logger.info(e.getMessage(), e);
				break;
			}
		}

		logger.info("cache service stopped.");
	}

	public void put(K key, V value, long time, TimeUnit unit) {
		V oldValue = cacheObjMap.put(key, value);
		if (oldValue != null) {
			q.remove(key);
		}
		long nanoTime = TimeUnit.NANOSECONDS.convert(time, unit);
		q.put(new DelayItem<Pair<K, V>>(new Pair<K, V>(key, value), nanoTime));
	}

	public V get(K key) {
		return cacheObjMap.get(key);
	}

	public V remove(K key) {
		return cacheObjMap.remove(key);
	}

	public boolean contains(K key) {
		return cacheObjMap.containsKey(key);
	}

	public static void main(String[] args) throws Exception {
		Cache<Integer, String> cache = new Cache<Integer, String>();
		cache.put(1, "aaaa", 3, TimeUnit.SECONDS);

		Thread.sleep(1000 * 2);
		{
			String str = cache.get(1);
			System.out.println(str);
		}

		Thread.sleep(1000 * 2);
		{
			String str = cache.get(1);
			System.out.println(str);
		}
	}
	
}
