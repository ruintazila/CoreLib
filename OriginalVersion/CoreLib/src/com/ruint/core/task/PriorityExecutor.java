package com.ruint.core.task;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * com.ruint.core.task
 * 
 * @author ruint <br/>
 *         create at 2014-11-25 上午11:13:43
 */
public class PriorityExecutor implements Executor {

  private static final int CORE_POOL_SIZE = 5;
  private static final int MAXIMUM_POOL_SIZE = 256;
  private static final int KEEP_ALIVE = 1;

  private static final ThreadFactory sThreadFactory = new ThreadFactory() {
    private final AtomicInteger mCount = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
      return new Thread(r, "PriorityExecutor #" + mCount.getAndIncrement());
    }
  };

  private final BlockingQueue<Runnable> mPoolWorkQueue = new PriorityObjectBlockingQueue<Runnable>();
  private final ThreadPoolExecutor mThreadPoolExecutor;

  public PriorityExecutor() {
    this(CORE_POOL_SIZE);
  }

  public PriorityExecutor(int poolSize) {
    mThreadPoolExecutor = new ThreadPoolExecutor(poolSize, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS,
        mPoolWorkQueue, sThreadFactory);
  }

  public int getPoolSize() {
    return mThreadPoolExecutor.getCorePoolSize();
  }

  public void setPoolSize(int poolSize) {
    if (poolSize > 0) {
      mThreadPoolExecutor.setCorePoolSize(poolSize);
    }
  }

  public boolean isBusy() {
    return mThreadPoolExecutor.getActiveCount() >= mThreadPoolExecutor.getCorePoolSize();
  }

  @Override
  public void execute(final Runnable r) {
    mThreadPoolExecutor.execute(r);
  }
}
