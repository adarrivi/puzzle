package solution;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LimitedCache {
	private static final int DEFAULT_SIZE = 80;

	private final Map<CacheKey, Object> holder = new ConcurrentHashMap<>();
	private int maxSize;

	public LimitedCache() {
		this(DEFAULT_SIZE);
	}

	public LimitedCache(int maxSize) {
		this.maxSize = maxSize;
	}

	public void put(Object key, Object value) {
		synchronized (holder) {
			while (holder.size() > maxSize) {
				holder.remove(holder.keySet().iterator().next());
			}
			holder.put(new CacheKey(key), value);
		}
	}

	public Object get(Object key) {
		return holder.get(new CacheKey(key));
	}

	public static class CacheKey {
		public Object key;

		public CacheKey(Object key) {
			this.key = key;
		}

		@Override
		public int hashCode() {
			return Objects.hash(key);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof CacheKey)) {
				return false;
			}
			CacheKey other = (CacheKey) obj;
			return Objects.equals(key, other.key);
		}
	}
}
