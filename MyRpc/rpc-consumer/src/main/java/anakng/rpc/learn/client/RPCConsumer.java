package anakng.rpc.learn.client;

import anakng.rpc.learn.handler.UserClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class RPCConsumer {

    // 1. 创建一个线程池对象 -- 它要处理我们自定义事件
    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    // 2. 声明一个自定义事件处理器 UserClientHandler
    private static UserClientHandler userClientHandler;

    // 3. 初始化客户端（创建连接池、Bootstrap 设置bootstrap 连接服务器）
    public static void initClient() throws InterruptedException {
        // 1. 初始化UserClientHandler
        userClientHandler = new UserClientHandler();

        // 2. 创建连接池对象
        final NioEventLoopGroup group = new NioEventLoopGroup();

        // 3. 创建客户端的引导对象
        final Bootstrap bootstrap = new Bootstrap();

        // 4. 配置启动引导对象
        bootstrap.group(group)
                // 设置通道为Nio
                .channel(NioSocketChannel.class)
                // 设置请求协议为TCP
                .option(ChannelOption.TCP_NODELAY , true)
                // 监听channel并初始化
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder())
                                .addLast(new StringDecoder())
                                .addLast(userClientHandler);
                    }
                });

        // 5. 连接服务器
        bootstrap.connect("localhost" , 9999).sync();
    }


    // 4. 使用JDK的动态代理创建对象
    // serviceClass 接口类型，根据哪个接口生成子类代理对象
    // providerParam UserService#sayHello#
    public static Object createProxy(Class<?> serviceClass , String providerParam) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader() , new Class[] {serviceClass} , new InvocationHandler() {
            @Override
            public Object invoke(Object proxy , Method method , Object[] args) throws Throwable {
                // 1. 初始化客户端
                if (userClientHandler == null) {
                    initClient();
                }

                // 2. 给UserClientHandler设置Param参数
                userClientHandler.setParam(providerParam + args[0]);

                // 3. 使用线程池，开启一个线程处理call()写操作，并返回结果
                final Object result = pool.submit(userClientHandler).get();

                // 4. 返回结果
                return result;
            }
        });
    }

}
