package ankang.rpc.learn;

import ankang.rpc.learn.service.UserServiceImpl;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class ServiceBoot {

    public static void main(String[] args) throws InterruptedException {
        // 启动服务器
        UserServiceImpl.startServer("localhost" , 9999);
    }

}
