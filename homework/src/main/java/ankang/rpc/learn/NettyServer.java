package ankang.rpc.learn;

import ankang.rpc.learn.decoder.JsonDecoder;
import ankang.rpc.learn.encoder.JsonEncoder;
import ankang.rpc.learn.pojo.User;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
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
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        // 接受客户端请求，打印到控制台
        // 1. 创建2个线程池对象
        // 负责接受客户端连接
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        // 负责处理客户端连接请求
        final NioEventLoopGroup workGroup = new NioEventLoopGroup();

        // 2. 创建启动引导类
        final ServerBootstrap serverBootstrap = new ServerBootstrap();

        // 3. 设置启动引导类
        // 添加到组中，两个线程池，第一个位置的线程池负责接受请求，第二位置的线程池负责读写请求
        serverBootstrap.group(bossGroup , workGroup)
                // 设置通道类型
                .channel(NioServerSocketChannel.class)
                // 处理业务逻辑：绑定一个初始化监听
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    // 事件监听Channel通道
                    @Override
                    protected void initChannel(NioSocketChannel nch) throws Exception {
                        // 获取pipeline
                        final ChannelPipeline pipeline = nch.pipeline();

                        // 绑定事件：编码
                        pipeline.addFirst(new JsonEncoder());

                        // 绑定事件：解码
                        pipeline.addLast(new JsonDecoder());

                        // 绑定事件：业务逻辑
                        pipeline.addLast(new SimpleChannelInboundHandler<User>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx , User msg) throws Exception {
                                // 读取数据，打印客户端传递的数据
                                System.out.println(msg);

                            }
                        });
                    }
                });

        // 4. 启动引导类绑定端口
        final ChannelFuture future = serverBootstrap.bind(9999).sync();

        // 5. 关闭通道
        future.channel().closeFuture();

    }


}
