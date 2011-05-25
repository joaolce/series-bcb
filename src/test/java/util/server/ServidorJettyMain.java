package util.server;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.webapp.WebAppClassLoader;
import org.mortbay.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServidorJettyMain {

  private static final int SERVER_PORT = 8080;
  private static final int CONNECT_TIMEOUT = 200;

  private static final int UM_SEGUNDO = 1000;
  private static final int UM_DECIMO_DE_SEGUNDO = UM_SEGUNDO / 10;
  private static final int MAX_IDLE_TIME_UMA_HORA = 1000 * 60 * 60;

  private static final Logger LOGGER = LoggerFactory.getLogger(ServidorJettyMain.class);

  private static final String CONTEXT_PATH = "/series";

  public static void main(String[] args) throws Exception {
    if (isOutraInstanciaIniciada()) {
      LOGGER.error(">>> Servidor Jetty já iniciado!");
    } else {
      Server server = configurarEIniciar();
      esperarParaDesligarServidor(server);
    }
  }

  private static boolean isOutraInstanciaIniciada() {
    try {
      try {
        new Socket().connect(new InetSocketAddress("localhost", SERVER_PORT), CONNECT_TIMEOUT);
      } catch (ConnectException e) {
        return false;
      } catch (SocketTimeoutException e) {
        return false;
      }
      return true;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static Server configurarEIniciar() {
    try {
      Server server = criarServidorJetty();

      server.start();
      esperarServidorIniciar(server);
      return server;
      // CHECKSTYLE:OFF - O lançamento de java.lang.Exception faz parte da assinatura do método
    } catch (Exception e) {
      // CHECKSTYLE:ON
      throw new RuntimeException(e);
    }
  }

  private static Server criarServidorJetty() throws IOException {
    Server server = new Server();

    SocketConnector connector = new SocketConnector();
    connector.setPort(SERVER_PORT);
    connector.setMaxIdleTime(MAX_IDLE_TIME_UMA_HORA);
    connector.setSoLingerTime(-1);
    server.setConnectors(new Connector[] {connector});

    WebAppContext webapp = new WebAppContext();
    webapp.setServer(server);
    webapp.setContextPath(CONTEXT_PATH);
    webapp.setResourceBase("src/main/webapp");
    webapp.setTempDirectory(new File("target/work"));

    WebAppClassLoader loader = new WebAppClassLoader(Thread.currentThread().getContextClassLoader(), webapp);
    webapp.setClassLoader(loader);

    HandlerCollection handlers = new HandlerCollection();
    handlers.setHandlers(new Handler[] {webapp, new DefaultHandler()});
    server.setHandler(handlers);
    return server;
  }

  private static void esperarServidorIniciar(Server server) throws InterruptedException {
    while (!server.isStarted()) {
      LOGGER.info(">>> Servidor não iniciado... esperando um pouco.");
      Thread.sleep(UM_DECIMO_DE_SEGUNDO);
    }
  }

  private static void esperarParaDesligarServidor(Server server) throws Exception {
    while (System.in.available() == 0) {
      Thread.sleep(UM_SEGUNDO);
    }
    desligarServidor(server);
  }

  private static void desligarServidor(Server server) {
    try {
      server.stop();
      // CHECKSTYLE:OFF - O lançamento de java.lang.Exception faz parte da assinatura do método
    } catch (Exception e) {
      // CHECKSTYLE:ON
      throw new RuntimeException(e);
    }
    try {
      server.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
