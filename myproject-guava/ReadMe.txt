guava工具包：
http://ifeve.com/google-guava/

限流三种算法
1：漏桶算法（限制数据的传输速率，固定速率）：对突发性的流量，缺发效率
2：令牌桶算法(请求来时，从桶里取令牌，获取令牌的数量是可以指定的，桶里没有令牌可取时，则拒绝服务,令牌桶的另外一个好处是可以方便的改变速度。 一旦需要提高速率，则按需提高放入桶中的令牌的速率，可动态调节取令牌的速率)
3：semphore(控制并发)
3：计数器：在前一毫秒后下一秒的第一时刻，会出现大量流量