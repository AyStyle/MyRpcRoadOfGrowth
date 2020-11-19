package ankang.rpc.learn;

import ankang.rpc.learn.encoder.JsonEncoder;
import ankang.rpc.learn.pojo.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 客户端给服务器发送数据
        // 1. 创建连接池对象
        final NioEventLoopGroup group = new NioEventLoopGroup();

        // 2. 创建客户端的启动引导类 BootStrap
        final Bootstrap bootstrap = new Bootstrap();

        // 3. 配置启动引导类
        bootstrap.group(group)
                // 设置通道为Nio
                .channel(NioSocketChannel.class)
                // 设置Channel初始化监听
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        // 设置编码
                        ch.pipeline().addLast(new JsonEncoder());
                    }
                });

        // 4. 使用启动引导类连接服务器，并获取一个Channel
        final Channel channel = bootstrap.connect("localhost" , 9999).channel();

        // 5. 循环写数据给服务器
        while (true) {
            // 给服务器写数据
            channel.writeAndFlush(new User("ankang" , (int) (Math.random() * 20)));
            Thread.sleep(1000);
        }
    }

}
