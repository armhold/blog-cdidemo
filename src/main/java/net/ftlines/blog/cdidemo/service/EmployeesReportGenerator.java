package net.ftlines.blog.cdidemo.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import net.ftlines.blog.cdidemo.model.EmployeeCriteria;
import net.ftlines.blog.cdidemo.model.EmployeesRepository;

@ApplicationScoped
public class EmployeesReportGenerator {
  @Inject
  EmployeesRepository employees;

  public void generate(@Observes GenerateSystemReportEvent e) {
    System.out.println("The system contains: " + employees.count(new EmployeeCriteria()) + " employees");
  }
}
