package com.ai.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by Levent8421
 * Date: 2024/3/11 16:30
 * ClassName: QuarkSnowflake
 * Description:
 * 雪花算法生成
 *
 * @author levent8421
 */
public class QuarkSnowflake {
    /**
     * Without: I L O U
     */
    private static final char[] CHARS = "0123456789ABCDEFGHJKMNPQRSTVWXYZ".toCharArray();
    public final AtomicInteger serialGenerator = new AtomicInteger(0);
    private final int dataCenter;
    private final int worker;
    private final boolean reverse;

    public QuarkSnowflake(int dataCenter, int worker, boolean reverse) {
        this.dataCenter = dataCenter;
        this.worker = worker;
        this.reverse = reverse;
    }

    public QuarkSnowflake(boolean reverse) {
        this((int) (Math.random() * 0x1F), (int) (Math.random() * 0x1F), reverse);
    }

    public QuarkSnowflake() {
        this(true);
    }

    public synchronized long next() {
        long now = System.currentTimeMillis();
        int serial = this.serialGenerator.getAndIncrement();
        return (now & 2199023255551L) << 22 | (long) this.dataCenter << 17 | (long) this.worker << 12 | (long) (serial & 4095);
    }

    public String nextStr(String prefix, String suffix) {
        long next = this.next();
        StringBuilder sb = new StringBuilder();
        do {
            int item = (int) (next % (long) CHARS.length);
            next /= CHARS.length;
            sb.append(CHARS[item]);
        } while (next >= (long) CHARS.length);

        sb.append(CHARS[(int) next]);
        if (reverse) {
            sb.reverse();
        }
        sb.insert(0, prefix);
        sb.append(suffix);
        return sb.toString();
    }

    public String nextStr() {
        return nextStr("", "");
    }
}
