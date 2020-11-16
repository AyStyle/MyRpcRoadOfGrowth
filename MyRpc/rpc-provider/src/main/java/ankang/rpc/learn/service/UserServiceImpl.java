package ankang.rpc.learn.service;

import ankang.rpc.learn.UserService;
import ankang.rpc.learn.handler.UserServiceHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class UserServiceImpl implements UserService {

    /**
     * 客户端要连接调用的方法
     *
     * @param msg
     * @return
     */
    @Override
    public String sayHello(String msg) {
        System.out.println("are you ok ? " + msg);

        return "are you ok ? " + msg;
    }

    /**
     * 创建一个方法启动服务器
     *
     * @param ip
     * @param port
     */
    public static void startServer(String ip , int port) throws InterruptedException {
        // 1. 创建两个线程池对象
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        final NioEventLoopGroup workGroup = new NioEventLoopGroup();

        // 2. 穿件服务端的启动引导对象
        final ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 3. 配置启动引导对象
        serverBootstrap.group(bossGroup , workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast(new StringEncoder())
                                .addLast(new StringDecoder())
                                .addLast(new UserServiceHandler());
                    }
                });

        // 4. 绑定端口
        serverBootstrap.bind(ip , port).sync();


    }
}
