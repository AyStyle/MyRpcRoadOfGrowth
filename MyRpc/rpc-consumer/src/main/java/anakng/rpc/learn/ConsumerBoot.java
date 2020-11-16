package anakng.rpc.learn;

import anakng.rpc.learn.client.RPCConsumer;
import ankang.rpc.learn.UserService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-15
 */
public class ConsumerBoot {

    public static void main(String[] args) throws InterruptedException {
        // 1.创建代理对象
        final UserService proxy = (UserService) RPCConsumer.createProxy(UserService.class , "UserService#sayHello#");

        // 2.循环给服务器写数据
        while (true) {
            final String result = proxy.sayHello("are you ok !!");
            System.out.println(result);
            Thread.sleep(1000);
        }

    }

}
