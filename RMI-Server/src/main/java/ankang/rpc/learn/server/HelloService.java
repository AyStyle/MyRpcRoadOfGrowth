package ankang.rpc.learn.server;

import ankang.rpc.learn.pojo.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-14
 */
public interface HelloService extends Remote {

    /**
     * 定义一个说hello的方法
     * @param user
     * @return
     * @throws RemoteException
     */
    String sayHello(User user) throws RemoteException;

}
