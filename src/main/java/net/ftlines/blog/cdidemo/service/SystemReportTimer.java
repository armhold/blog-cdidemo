package net.ftlines.blog.cdidemo.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import net.ftlines.blog.cdidemo.cdi.ApplicationStartedEvent;

@ApplicationScoped
public class SystemReportTimer {

  @Inject
  Event<GenerateSystemReportEvent> event;

  public void start(@Observes ApplicationStartedEvent ase) {
    Thread timer = new Thread() {
      public void run() {
        while (true) {
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {
            return;
          }
          event.fire(new GenerateSystemReportEvent());
        }

      }
    };
    timer.setDaemon(true);
    timer.start();
  }

}
