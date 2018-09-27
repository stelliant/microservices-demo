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

    String server;

    switch (args.length) {
      case 2:
        // Optionally set the HTTP port to listen on, overrides
        // value in the application-<server>.yml file
        System.setProperty("server.port", args[1]);
        // Fall through into ..

      case 1:
        server = args[0].toLowerCase();
        System.setProperty("spring.profiles.active", server);
        break;

      default:
        usage();
        return;
    }

    if ("reg".equals(server)) {
      RegistrationServer.main(args);
    } else if ("spo".equals(server)) {
      SinappsPoller.main(args);
    } else if ("ipu".equals(server)) {
      ItexPublisher.main(args);
    } else if ("spu".equals(server)) {
      SinappsPublisher.main(args);
    } else if ("ipo".equals(server)) {
      ItexPoller.main(args);
    } else {
      System.out.println("Unknown server: " + server);
      usage();
    }
  }

  protected static void usage() {
    System.out.println("Usage: java -jar ... <server> [server-port]");
    System.out.println(
        "   where server is 'reg', 'spo', 'ipu', 'ipo' or 'spu'"
            + " and server-port > 1024");
  }
}
