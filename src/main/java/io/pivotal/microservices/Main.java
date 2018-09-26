package io.pivotal.microservices;

import io.pivotal.microservices.itex.poller.ItexPoller;
import io.pivotal.microservices.itex.publisher.ItexPublisher;
import io.pivotal.microservices.registration.RegistrationServer;
import io.pivotal.microservices.sinapps.poller.SinappsPoller;
import io.pivotal.microservices.sinapps.publisher.SinappsPublisher;

/**
 * Allow the servers to be invoked from the command-line. The jar is built with this as the
 * <code>Main-Class</code> in the jar's <code>MANIFEST.MF</code>.
 *
 * @author Paul Chapman
 */
public class Main {

  public static void main(String[] args) {

    String serverName = "NO-VALUE";

    switch (args.length) {
      case 3:
        // Optionally set the HTTP port to listen on, overrides
        // value in the <server-name>-server.yml file
        System.setProperty("server.port", args[2]);
        // Fall through into ..

      case 2:
        // Do nothing, profiles.active

      case 1:
        serverName = args[0].toLowerCase();
        break;

      default:
        usage();
        return;
    }

    if (serverName.equals("registration") || serverName.equals("reg")) {
      RegistrationServer.main(args);
    } else if (serverName.equals("spo")) {
      SinappsPoller.main(args);
    } else if (serverName.equals("spu")) {
      SinappsPublisher.main(args);
    } else if (serverName.equals("ipu")) {
      ItexPublisher.main(args);
    } else if (serverName.equals("ipo")) {
      ItexPoller.main(args);
    } else {
      System.out.println("Unknown server type: " + serverName);
      usage();
    }
  }

  protected static void usage() {
    System.out.println("Usage: java -jar ... <server-name> [server-port]");
    System.out.println(
        "     where server-name is 'reg', 'registration', "
            + "'accounts' or 'web' and server-port > 1024");
  }
}
