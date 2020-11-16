package ankang.rpc.learn.server.impl;

import ankang.rpc.learn.pojo.User;
import ankang.rpc.learn.server.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-11-14
 */
public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
    public HelloServiceImpl() throws RemoteException {
        super();
    }

    protected HelloServiceImpl(int port) throws RemoteException {
        super(port);
    }

    protected HelloServiceImpl(int port , RMIClientSocketFactory csf , RMIServerSocketFactory ssf) throws RemoteException {
        super(port , csf , ssf);
    }

    @Override
    public String sayHello(User user) throws RemoteException {
        System.out.println("this is server, say hello to " + user.getName());
        return "success";
    }
}
