package ankang.rpc.learn.handler;

import ankang.rpc.learn.service.UserServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 自定义的业务处理器
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class UserServiceHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端读取数据时，该方法会被调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx , Object msg) throws Exception {
        // 注意：客户端将来发送请求的时候会传递一个参数：UserServer#sayHello#are you ok
        // 1. 判断当前的请求是否符合规则
        if (msg.toString().startsWith("UserService")){
            // 2. 如果符合规则，调用实现类到一个result
            final UserServiceImpl service = new UserServiceImpl();
            final String result = service.sayHello(msg.toString().substring(msg.toString().lastIndexOf('#') + 1));

            // 3. 把调用实现类方法获得的结果写到客户端
            ctx.writeAndFlush(result);
        }

    }
}
