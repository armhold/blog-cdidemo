This is an example project for a blog series that demonstrates how to build a tightly-integrated technology stack that simplifies web-application development.

Technologies:

 * [Apache Wicket](http://wicket.apache.org) - Web Interface
 * [CDI](http://jcp.org/en/jsr/summary?id=299)/[Weld](http://seamframework.org/Weld) - Application Glue (Contexts, Inversion of Control, Eventing, Interceptors, etc)
 * [JPA](http://jcp.org/en/jsr/detail?id=317)/[Hibernate](http://www.hibernate.org) - Relational Persistence

Features:

 * Dependency injection into Wicket components
 * JPA Persistence Unit setup
 * Injection of EntityManagerFactory and EntityManager
 * Conversational EntityManager which allows for Extended Persistence Units by propagating the conversation
 * Various integration classes for Wicket, such as base Entity models and data providers

Articles:

 1. [Integrating CDI Into Wicket](https://www.42lines.net/2011/11/15/integrating-cdi-into-wicket/)
 2. [Adding JPA/Hibernate into CDI and Wicket Mix](https://www.42lines.net/2011/11/21/adding-jpahibernate-into-the-cdi-and-wicket-mix/)
 3. [Leveraging Conversations to Reduce Plumbing](https://www.42lines.net/2011/11/29/leveraging-conversations/)


