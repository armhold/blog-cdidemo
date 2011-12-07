package net.ftlines.blog.cdidemo.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import net.ftlines.blog.cdidemo.cdi.ApplicationStartedEvent;

public class SystemReportScheduler {

  @Inject
  Event<GenerateSystemReportEvent> event;

  void start(@Observes ApplicationStartedEvent ase) {
    Thread timer = new Thread() {
      public void run() {
        try {
          while (true) {
            Thread.sleep(5000);
            event.fire(new GenerateSystemReportEvent());
          }
        } catch (InterruptedException e) {
          return;
        }
      }
    };
    timer.setDaemon(true);
    timer.start();
  }

}
