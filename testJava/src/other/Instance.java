package other;

/**
 * 懒汉式单例
 */
public class Instance {
    private static Instance mInstance;
    private Instance() {}

    public static Instance getInstance() {
        if (mInstance == null) {
            synchronized (Instance.class) {
                if (mInstance == null) {
                    mInstance = new Instance();
                }
            }
        }
        return mInstance;
    }
}

/**
 * 饿汉式单例
 */
class Instance1 {
    private static final Instance1 mInstance1 = new Instance1();
    private Instance1() {}

    public static Instance1 getInstance() {
        return mInstance1;
    }
}

/**
 * 静态类式单例
 */
class Instance2 {
    private static class Holder {
        private static final Instance2 instance2 = new Instance2();
    }

    private Instance2() {}
    public static final Instance2 getInstance() {
        return Holder.instance2;
    }
}
