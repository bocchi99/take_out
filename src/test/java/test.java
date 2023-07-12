import java.net.InetAddress;

/**
 * @author diao 2023/7/3
 */

public class test {
    public static void main(String[] args) throws Exception{
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("localhost: " + localHost);
        System.out.println("getHostAddress:  " + localHost.getHostAddress());
        System.out.println("getHostName:  " + localHost.getHostName());
    }
}
