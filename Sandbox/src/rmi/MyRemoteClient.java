package rmi;

import java.rmi.Naming;

public class MyRemoteClient {
	public static void main(String[] args) {
		new MyRemoteClient().go();
	}

	public void go() {

		try {
			// It comes out of the registry as type Object, so you have to cast it
			MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/Remote Hello");
			String s = service.sayHello();
			System.out.println(s);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
