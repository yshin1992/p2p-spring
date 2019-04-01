package org.vo.sms;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

public class TemporaryStore {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TemporaryStore.class);

	private final Cache<String, String> codeCache = new Cache<String, String>();
	private final Cache<String, VerifyCode> verifyCache = new Cache<String, VerifyCode>();

	private TemporaryStore() {
	}

	private static class CacheManagerHolder {
		private static TemporaryStore manager = new TemporaryStore();
	}

	public static TemporaryStore getInstance() {
		return CacheManagerHolder.manager;
	}

	public String getCacheCode(String codeKey) {
		return codeCache.get(codeKey);
	}

	public void storeCacheCode(String codeKey, long time, TimeUnit unit, String code) {
		logger.debug("存储[{}={}]", codeKey, code);
		codeCache.put(codeKey, code, time, unit);
	}

	public VerifyCode getVerifyCode(String codeKey) {
		VerifyCode code = verifyCache.get(codeKey);
		return code;
	}

	public void storeVerifyCode(String codeKey, long time, TimeUnit unit, VerifyCode code) {
		logger.debug("存储verify[{}={}]", codeKey, code);
		verifyCache.put(codeKey, code, time, unit);
	}
}
