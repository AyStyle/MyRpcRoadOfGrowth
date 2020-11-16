package ankang.rpc.learn;

import ankang.rpc.learn.server.HelloService;
import ankang.rpc.learn.server.impl.HelloServiceImpl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-14
 */
public class RMIServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        // 1.创建HelloService实例
        final HelloService service = new HelloServiceImpl();
        // 2.获取注册表
        LocateRegistry.createRegistry(8888);

        // 3.对象绑定
        // bind方法的参数1：rmi://ip:port/name  参数2：服务对象
        Naming.bind("rmi://localhost:8888/rmiserver",service);
    }

}
