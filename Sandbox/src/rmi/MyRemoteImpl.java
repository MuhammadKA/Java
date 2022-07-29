package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * ================================== REMOTE SERVICE ==================================
 * In order to work as a remote service object, your object needs some
 * functionality related to ‘being remote’. The simplest way is to extend
 * UnicastRemoteObject (from the java.rmi.server package) and let that class
 * (your superclass) do the work for you.
 * 
 * UnicastRemoteObject constructor throws a RemoteException. The only way to
 * deal with this is to declare a constructor for your remote implementation,
 * just so that you have a place to declare the RemoteException
 * 
 * You MUST implement your remote interface. 
 */

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

	@Override
	public String sayHello() {
		return "Server says, 'Hey'";
	}

	/**
	 * Your superclass constructor (for UnicastRemoteObject) declares an exception,
	 * so YOU must write a constructor, because it means that your constructor is
	 * calling risky code (its super constructor)
	 */
	public MyRemoteImpl() throws RemoteException {
	}

	public static void main(String[] args) {

		try {
			MyRemote service = new MyRemoteImpl();
			/**
			 * Give your service a name (that clients can use to look it up in the registry)
			 * and register it with the RMI registry. When you bind the service object, RMI
			 * swaps the service for the stub and puts the stub in the registry.
			 */
			Naming.rebind("Remote Hello", service);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
