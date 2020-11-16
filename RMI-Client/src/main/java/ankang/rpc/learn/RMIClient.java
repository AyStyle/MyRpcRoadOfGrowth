package ankang.rpc.learn;

import ankang.rpc.learn.pojo.User;
import ankang.rpc.learn.server.HelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-14
 */
public class RMIClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // 1.从注册表中获取远程对象，这里是一个代理对象
        final HelloService lookup = (HelloService) Naming.lookup("rmi://localhost:8888/rmiserver");

        // 2.准备参数
        final User user = new User();
        user.setName("ankang");
        user.setAge(18);

        // 3.调用远程方法sayHello
        final String hello = lookup.sayHello(user);
        System.out.println(hello);
    }

}
