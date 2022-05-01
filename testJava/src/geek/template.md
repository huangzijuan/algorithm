package geek;

## 知识点记忆
1. 在一般的电脑中,int占用4字节,32比特,数据范围为-2147483648~2147483647[-2^31~2^31-1]
2. 常用的区间一般为左闭右开区间

```
public class template {
    // 递归代码模板
    public void recur(int level, int param) {
        
        // terminator 
        if (level > MAX_LEVEL) {
            // process result 
            return;
        }

        // process current logic 
        process(level, param);


        // drill down 
        recur( level: level + 1, newParam);


        // restore current status 
    }
}
```
