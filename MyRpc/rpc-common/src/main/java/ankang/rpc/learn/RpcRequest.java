package ankang.rpc.learn;

import lombok.Data;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-20
 */
@Data
public class RpcRequest {

    private String cls;
    private String method;
    private String[] param;
    private String request;

}
