package anakng.rpc.learn.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class UserClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {

    // 1. 定义成员变量
    // 事件处理器上下文对象（。存储Handler信息，写操作）
    private ChannelHandlerContext ctx;

    // 记录服务器返回的数据
    private String result;

    // 记录将要发送给服务器的数据
    private String param;

    // 2. 实现ChannelActivate    客户端和服务器连接时，该方法自动执行
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 初始化ChannelHandlerContext
        this.ctx = ctx;
    }


    // 3. 实现ChannelRead    当读到服务器数据时，该方法自动执行
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx , Object msg) throws Exception {
        // 将读到的服务器数据msg，设置为成员变量的值
        result = msg.toString();
        notify();
    }

    // 4. 将客户端的数据写到服务器
    @Override
    public synchronized Object call() throws Exception {
        // ctx给服务写数据
        ctx.writeAndFlush(param);
        wait();

        return result;
    }

    // 5. 设置参数
    public void setParam(String param) {
        this.param = param;
    }

}
