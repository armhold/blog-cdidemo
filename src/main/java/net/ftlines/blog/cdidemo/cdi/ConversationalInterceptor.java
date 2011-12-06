package net.ftlines.blog.cdidemo.cdi;

import java.util.HashMap;
import java.util.Map;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.jboss.weld.Container;
import org.jboss.weld.context.bound.BoundConversationContext;
import org.jboss.weld.context.bound.BoundRequest;
import org.jboss.weld.context.bound.MutableBoundRequest;

@Conversational
@Interceptor
public class ConversationalInterceptor {
  
  
  @AroundInvoke
  public Object wrapInConversation(InvocationContext invocation) throws Exception {
    BoundConversationContext context = Container.instance().deploymentManager().instance()
        .select(BoundConversationContext.class).get();
    
    BoundRequest storage = null;

    if (!context.isActive()) {
      System.out.println("=== ACTIVATING CONVERSATION");
      Map<String, Object> session = new HashMap<String, Object>();
      Map<String, Object> request = new HashMap<String, Object>();
      storage = new MutableBoundRequest(request, session);
      context.associate(storage);
      context.activate();
    }

    try {
      return invocation.proceed();
    } finally {
      if (storage != null) {
        System.out.println("=== DEACTIVATING CONVERSATION");
        context.deactivate();
        context.dissociate(storage);
      }
    }
  }
}
