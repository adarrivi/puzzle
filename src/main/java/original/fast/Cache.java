package original.fast;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
  private static final int DEFAULT_SIZE = 50;

  private final Map<CacheKey, Object> holder = new ConcurrentHashMap<>();
  private int maxSize;

  public Cache() {
    this(DEFAULT_SIZE);
  }

  public Cache(int maxSize) {
    this.maxSize = maxSize;
  }

  public void put(Object key, Object value) {
    while (holder.size() > maxSize) {
      holder.remove(holder.keySet().iterator().next());
    }
    holder.put(new CacheKey(key), value);
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
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      CacheKey cacheKey = (CacheKey) o;

      return key.equals(cacheKey.key);
    }

    @Override
    public int hashCode() {
      return key.hashCode();
    }
  }
}
