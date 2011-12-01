package net.ftlines.blog.cdidemo.util;

import java.util.Iterator;

/**
 * Adapter that transforms iterator of one type into interator of another type
 * 
 * @author igor
 * 
 * @param <O>
 *          old type
 * @param <N>
 *          new type
 */
public abstract class IteratorAdapter<O, N> implements Iterator<N> {
  private final Iterator<? extends O> delegate;

  public IteratorAdapter(Iterator<? extends O> delegate) {
    this.delegate = delegate;
  }

  public boolean hasNext() {
    return delegate.hasNext();
  }

  public N next() {
    return next(delegate.next());
  }

  protected abstract N next(O next);

  public void remove() {
    delegate.remove();
  }

}
