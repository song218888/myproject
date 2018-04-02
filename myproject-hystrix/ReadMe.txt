HystrixCommand与HystrixObservable两者主要区别是：
	I：前者的命令逻辑写在run()；后者的命令逻辑写在construct()
    II：前者的run()是由新创建的线程执行；后者的construct()是由调用程序线程执行
    III：前者一个实例只能向调用程序发送（emit）单条数据，比如上面例子中run()只能返回一个String结果；后者一个实例可以顺序发送多条数据，比如demo中顺序调用多个onNext()，便实现了向调用程序发送多条数据，甚至还能发送一个范围的数据集

    

execute()、queue()、observe()、toObservable()这4个方法用来触发执行run()/construct()，一个实例只能执行一次这4个方法，特别说明的是HystrixObservableCommand没有execute()和queue()
4个方法的主要区别是：
execute()：以同步堵塞方式执行run()。以HystrixCommand4ExecuteTest为例，调用execute()后，hystrix先创建一个新线程运行run()，接着调用程序要在execute()调用处一直堵塞着，直到run()运行完成
queue()：以异步非堵塞方式执行run()。以HystrixCommand4QueueTest为例，一调用queue()就直接返回一个Future对象，同时hystrix创建一个新线程运行run()，调用程序通过Future.get()拿到run()的返回结果，而Future.get()是堵塞执行的
observe()：事件注册前执行run()/construct()。以demo为例，第一步是事件注册前，先调用observe()自动触发执行run()/construct()（如果继承的是HystrixCommand，hystrix将创建新线程非堵塞执行run()；如果继承的是HystrixObservableCommand，将以调用程序线程堵塞执行construct()），第二步是从observe()返回后调用程序调用subscribe()完成事件注册，如果run()/construct()执行成功则触发onNext()和onCompleted()，如果执行异常则触发onError()
toObservable()：事件注册后执行run()/construct()。以demo为例，第一步是事件注册前，一调用toObservable()就直接返回一个Observable<String>对象，第二步调用subscribe()完成事件注册后自动触发执行run()/construct()（如果继承的是HystrixCommand，hystrix将创建新线程非堵塞执行run()，调用程序不必等待run()；如果继承的是HystrixObservableCommand，将以调用程序线程堵塞执行construct()，调用程序等待construct()执行完才能继续往下走），如果run()/construct()执行成功则触发onNext()和onCompleted()，如果执行异常则触发onError()


fall back服务降级
非HystrixBadRequestException异常：以demo为例，当抛出HystrixBadRequestException时，调用程序可以捕获异常，没有触发getFallback()，而其他异常则会触发getFallback()，调用程序将获得getFallback()的返回
run()/construct()运行超时：以demo为例，使用无限while循环或sleep模拟超时，触发了getFallback()
熔断器启动：以demo为例，我们配置10s内请求数大于3个时就启动熔断器，请求错误率大于80%时就熔断，然后for循环发起请求，当请求符合熔断条件时将触发getFallback()。更多熔断策略见下文
线程池/信号量已满：以demo为例，我们配置线程池数目为3，然后先用一个for循环执行queue()，触发的run()sleep 2s，然后再用第2个for循环执行execute()，发现所有execute()都触发了fallback，这是因为第1个for的线程还在sleep，占用着线程池所有线程，导致第2个for的所有命令都无法获取到线程
 