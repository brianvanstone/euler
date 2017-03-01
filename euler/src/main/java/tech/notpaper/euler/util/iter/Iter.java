package tech.notpaper.euler.util.iter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tech.notpaper.euler.util.Generator;
import tech.notpaper.euler.util.GeneratorCallback;

public class Iter {
	
	@SafeVarargs
	public static <T> Iterable<List<T>> zip(Iterable<T>... iterables) {
		return new Generator<ZiperatorCallback<T>,List<T>>
                            (new ZiperatorCallback<T>().load(iterables));
	}
	
	@SafeVarargs
	public static <T> Iterable<T> chain(Iterable<T>... iterables) {
		return new Generator<ChainCallback<T>,T>
		                    (new ChainCallback<T>().load(iterables));
	}
	
	static class ChainCallback<T> extends GeneratorCallback<T> {
		
		private List<Iterator<T>> iterators = new LinkedList<>();
		
		@SuppressWarnings("unchecked")
		public ChainCallback<T> load(Iterable<T>... iterables) {
			for (Iterable<T> i : iterables) {
				iterators.add(i.iterator());
			}
			return this;
		}

		@Override
		public void go() throws InterruptedException {
			while(iterators.size() > 0) {
				if (!iterators.get(0).hasNext()) {
					iterators.remove(0);
					continue;
				}
				
				yield(iterators.get(0).next());
			}
		}
	}
	
	static class ZiperatorCallback<T> extends GeneratorCallback<List<T>> {
		
		private List<Iterator<T>> iterators = new LinkedList<>();
		
		@SuppressWarnings("unchecked")
		public ZiperatorCallback<T> load(Iterable<T>... iterables) {
			for (Iterable<T> i : iterables) {
				iterators.add(i.iterator());
			}
			return this;
		}

		@Override
		public void go() throws InterruptedException {
			while(hasNext()) {
				List<T> values = new LinkedList<T>();
				for (Iterator<T> i : iterators) {
					values.add(i.next());
				}
				yield(values);
			}
		}
		
		private boolean hasNext() {
			for (Iterator<T> i : iterators) {
				if (!i.hasNext()) {
					return false;
				}
			}
			return true;
		}
	}
}
