package ankang.rpc.learn.decoder;

import ankang.rpc.learn.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-19
 */
public class JsonDecoder extends ByteToMessageDecoder {
    protected void decode(ChannelHandlerContext ctx , ByteBuf in , List<Object> out) throws Exception {
        final int length = in.readInt();

        final byte[] bytes = new byte[length];
        in.readBytes(bytes);

        final Object user = JSON.parseObject(bytes , User.class , Feature.SafeMode);

        out.add(user);
    }
}
