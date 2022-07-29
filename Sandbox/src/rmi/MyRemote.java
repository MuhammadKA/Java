package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * =============================== REMOTE INTERFACE =============================
 * Remote is a ‘marker’ interface, which means it has no methods. It has special
 * meaning for RMI, though, so you must follow this rule. Your interface has to
 * announce that it’s for remote method calls. An interface can’t implement
 * anything, but it can extend other interfaces.
 * 
 * 
 * The remote interface is the one the client uses as the polymorphic type for
 * the service. The client invokes methods on something that implements the
 * remote interface. That something is the stub and since the stub is doing
 * networking and I/O, all kinds of Bad Things can happen. The client has to
 * acknowledge the risks by handling or declaring the remote exceptions.
 */
public interface MyRemote extends Remote {
	// Every remote method call is considered ‘risky’.
	public String sayHello() throws RemoteException;

}
