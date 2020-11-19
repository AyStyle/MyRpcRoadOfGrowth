package ankang.rpc.learn.encoder;

import ankang.rpc.learn.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-19
 */
public class JsonEncoder extends MessageToByteEncoder<User> {
    protected void encode(ChannelHandlerContext channelHandlerContext , User user , ByteBuf byteBuf) throws Exception {
        final byte[] bytes = JSON.toJSONBytes(user , SerializerFeature.IgnoreNonFieldGetter);

        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
