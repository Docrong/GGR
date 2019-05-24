package com.boco.eoms.km.core.crimson.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashMapEx extends AbstractMap implements Map, Cloneable,
		Serializable {

	protected transient Entry table[];
	protected static final int CONCURRENCY_LEVEL = 32;
	protected static final int SEGMENT_MASK = 31;
	protected final Segment segments[];
	public static int DEFAULT_INITIAL_CAPACITY = 32;
	private static final int MINIMUM_CAPACITY = 32;
	private static final int MAXIMUM_CAPACITY = 0x40000000;
	public static final float DEFAULT_LOAD_FACTOR = 0.75F;
	protected final float loadFactor;
	protected int threshold;
	protected volatile transient int votesForResize;
	protected transient Set keySet;
	protected transient Set entrySet;
	protected transient Collection values;

	protected class ValueIterator extends HashIterator {

		protected Object returnValueOfNext() {
			return currentValue;
		}

		protected ValueIterator() {
		}
	}

	protected class KeyIterator extends HashIterator {

		protected Object returnValueOfNext() {
			return currentKey;
		}

		protected KeyIterator() {
		}
	}

	protected class HashIterator implements Iterator, Enumeration {

		public boolean hasMoreElements() {
			return hasNext();
		}

		public Object nextElement() {
			return next();
		}

		public boolean hasNext() {
			do {
				if (entry != null) {
					Object v = entry.value;
					if (v != null) {
						currentKey = entry.key;
						currentValue = v;
						return true;
					}
					entry = entry.next;
				}
				for (; entry == null && index >= 0; entry = tab[index--])
					;
			} while (entry != null);
			currentKey = currentValue = null;
			return false;
		}

		protected Object returnValueOfNext() {
			return entry;
		}

		public Object next() {
			if (currentKey == null && !hasNext()) {
				throw new NoSuchElementException();
			} else {
				Object result = returnValueOfNext();
				lastReturned = entry;
				currentKey = currentValue = null;
				entry = entry.next;
				return result;
			}
		}

		public void remove() {
			if (lastReturned == null) {
				throw new IllegalStateException();
			} else {
				HashMapEx.this.remove(lastReturned.key);
				lastReturned = null;
				return;
			}
		}

		protected final HashMapEx.Entry tab[];
		protected int index;
		protected HashMapEx.Entry entry;
		protected Object currentKey;
		protected Object currentValue;
		protected HashMapEx.Entry lastReturned;

		protected HashIterator() {
			entry = null;
			lastReturned = null;
			synchronized (segments[0]) {
				tab = table;
			}
			for (int i = 1; i < segments.length; i++)
				segments[i].synch();

			index = tab.length - 1;
		}
	}

	protected static class Entry implements java.util.Map.Entry {

		public Object getKey() {
			return key;
		}

		public Object getValue() {
			return value;
		}

		public Object setValue(Object value) {
			if (value == null) {
				throw new NullPointerException();
			} else {
				Object oldValue = this.value;
				this.value = value;
				return oldValue;
			}
		}

		public boolean equals(Object o) {
			if (!(o instanceof java.util.Map.Entry)) {
				return false;
			} else {
				java.util.Map.Entry e = (java.util.Map.Entry) o;
				return key.equals(e.getKey()) && value.equals(e.getValue());
			}
		}

		public int hashCode() {
			return key.hashCode() ^ value.hashCode();
		}

		public String toString() {
			return key + "=" + value;
		}

		protected final Object key;
		protected volatile Object value;
		protected final int hash;
		protected final Entry next;

		Entry(int hash, Object key, Object value, Entry next) {
			this.value = value;
			this.hash = hash;
			this.key = key;
			this.next = next;
		}
	}

	private class EntrySet extends AbstractSet {

		public Iterator iterator() {
			return new HashIterator();
		}

		public boolean contains(Object o) {
			if (!(o instanceof java.util.Map.Entry)) {
				return false;
			} else {
				java.util.Map.Entry entry = (java.util.Map.Entry) o;
				Object v = get(entry.getKey());
				return v != null && v.equals(entry.getValue());
			}
		}

		public boolean remove(Object o) {
			if (!(o instanceof java.util.Map.Entry)) {
				return false;
			} else {
				java.util.Map.Entry e = (java.util.Map.Entry) o;
				return HashMapEx.this.remove(e.getKey(), e.getValue()) != null;
			}
		}

		public int size() {
			return HashMapEx.this.size();
		}

		public void clear() {
			HashMapEx.this.clear();
		}

		public Object[] toArray() {
			Collection c = new ArrayList();
			for (Iterator i = iterator(); i.hasNext(); c.add(i.next()))
				;
			return c.toArray();
		}

		public Object[] toArray(Object a[]) {
			Collection c = new ArrayList();
			for (Iterator i = iterator(); i.hasNext(); c.add(i.next()))
				;
			return c.toArray(a);
		}

		private EntrySet() {
		}

	}

	private class Values extends AbstractCollection {
		private Values() {
		}

		public Iterator iterator() {
			return new ValueIterator();
		}

		public int size() {
			return HashMapEx.this.size();
		}

		public boolean contains(Object o) {
			return containsValue(o);
		}

		public void clear() {
			HashMapEx.this.clear();
		}

		public Object[] toArray() {
			Collection c = new ArrayList();
			for (Iterator i = iterator(); i.hasNext(); c.add(i.next()))
				;
			return c.toArray();
		}

		public Object[] toArray(Object a[]) {
			Collection c = new ArrayList();
			for (Iterator i = iterator(); i.hasNext(); c.add(i.next()))
				;
			return c.toArray(a);
		}
	}

	private class KeySet extends AbstractSet {
		private KeySet() {
		}

		public Iterator iterator() {
			return new KeyIterator();
		}

		public int size() {
			return HashMapEx.this.size();
		}

		public boolean contains(Object o) {
			return containsKey(o);
		}

		public boolean remove(Object o) {
			return HashMapEx.this.remove(o) != null;
		}

		public void clear() {
			HashMapEx.this.clear();
		}

		public Object[] toArray() {
			Collection c = new ArrayList();
			for (Iterator i = iterator(); i.hasNext(); c.add(i.next()))
				;
			return c.toArray();
		}

		public Object[] toArray(Object a[]) {
			Collection c = new ArrayList();
			for (Iterator i = iterator(); i.hasNext(); c.add(i.next()))
				;
			return c.toArray(a);
		}
	}

	protected static final class Segment implements Serializable {
		protected int count;

		protected Segment() {
		}

		protected synchronized int getCount() {
			return count;
		}

		protected synchronized void synch() {
		}
	}

	protected static int bitcount(int w) {
		w -= (0xaaaaaaaa & w) >>> 1;
		w = (w & 0x33333333) + (w >>> 2 & 0x33333333);
		w = w + (w >>> 4) & 0xf0f0f0f;
		w += w >>> 8;
		w += w >>> 16;
		return w & 0xff;
	}

	private int p2capacity(int initialCapacity) {
		int cap = initialCapacity;
		int result;
		if (cap > 0x40000000 || cap < 0)
			result = 0x40000000;
		else
			for (result = 32; result < cap; result <<= 1)
				;
		return result;
	}

	protected static int hash(Object x) {
		int h = x.hashCode();
		return ((h << 7) - h) + (h >>> 9) + (h >>> 17);
	}

	protected boolean eq(Object x, Object y) {
		return x == y || x.equals(y);
	}

	protected Entry[] newTable(int capacity) {
		threshold = (int) (((float) capacity * loadFactor) / 32F) + 1;
		return new Entry[capacity];
	}

	public HashMapEx(int initialCapacity, float loadFactor) {
		segments = new Segment[32];
		keySet = null;
		entrySet = null;
		values = null;
		if (loadFactor <= 0.0F)
			throw new IllegalArgumentException("Illegal Load factor: "
					+ loadFactor);
		this.loadFactor = loadFactor;
		for (int i = 0; i < segments.length; i++)
			segments[i] = new Segment();

		int cap = p2capacity(initialCapacity);
		table = newTable(cap);
	}

	public HashMapEx(int initialCapacity) {
		this(initialCapacity, 0.75F);
	}

	public HashMapEx() {
		this(DEFAULT_INITIAL_CAPACITY, 0.75F);
	}

	public HashMapEx(Map t) {
		this(Math.max((int) ((float) t.size() / 0.75F) + 1, 32), 0.75F);
		putAll(t);
	}

	public int size() {
		int c = 0;
		for (int i = 0; i < segments.length; i++)
			c += segments[i].getCount();

		return c;
	}

	public boolean isEmpty() {
		for (int i = 0; i < segments.length; i++)
			if (segments[i].getCount() != 0)
				return false;

		return true;
	}

	public Object get(Object key) {
		int hash = hash(key);

		Entry[] tab = this.table;
		int index = hash & tab.length - 1;
		Entry first = tab[index];
		Entry e;

		for (e = first; e != null; e = e.next) {
			if ((e.hash == hash) && (eq(key, e.key))) {
				Object value = e.value;
				if (value == null)
					break;
				return value;
			}

		}

		Segment seg = this.segments[(hash & 0x1F)];
		synchronized (seg) {
			tab = this.table;
			index = hash & tab.length - 1;
			Entry newFirst = tab[index];
			if ((e == null) && (first == newFirst))
				return null;
			;
			for (e = newFirst; e != null; e = e.next)
				if ((e.hash == hash) && (eq(key, e.key)))
					return e.value;

			return null;
		}
	}

	public boolean containsKey(Object key) {
		return get(key) != null;
	}

	public Object put(Object key, Object value) {
		int segcount;
		Entry[] tab;
		int votes;
		if (value == null)
			throw new NullPointerException();

		int hash = hash(key);
		Segment seg = this.segments[(hash & 0x1F)];

		synchronized (seg) {
			tab = this.table;
			int index = hash & tab.length - 1;
			Entry first = tab[index];

			for (Entry e = first; e != null; e = e.next) {
				if ((e.hash == hash) && (eq(key, e.key))) {
					Object oldValue = e.value;
					e.value = value;
					return oldValue;
				}

			}

			Entry newEntry = new Entry(hash, key, value, first);
			tab[index] = newEntry;

			if ((segcount = ++seg.count) >= this.threshold) {
				int bit = 1 << (hash & 0x1F);
				votes = this.votesForResize;
				if ((votes & bit) != 0) {
					votes = this.votesForResize |= bit;
				}
				if ((bitcount(votes) >= 8) || (segcount > this.threshold * 32)) {
					resize(0, tab);
				}
			}

		}

		return null;
	}

	protected void resize(int index, Entry assumedTab[]) {
		Segment seg = segments[index];
		synchronized (seg) {
			if (assumedTab == table) {
				int next = index + 1;
				if (next < segments.length)
					resize(next, assumedTab);
				else
					rehash();
			}
		}
	}

	protected void rehash() {
		votesForResize = 0;
		Entry oldTable[] = table;
		int oldCapacity = oldTable.length;
		if (oldCapacity >= 0x40000000) {
			threshold = 0x7fffffff;
			return;
		}
		int newCapacity = oldCapacity << 1;
		Entry newTable[] = newTable(newCapacity);
		int mask = newCapacity - 1;
		for (int i = 0; i < oldCapacity; i++) {
			Entry e = oldTable[i];
			if (e == null)
				continue;
			int idx = e.hash & mask;
			Entry next = e.next;
			if (next == null) {
				newTable[idx] = e;
				continue;
			}
			Entry lastRun = e;
			int lastIdx = idx;
			for (Entry last = next; last != null; last = last.next) {
				int k = last.hash & mask;
				if (k != lastIdx) {
					lastIdx = k;
					lastRun = last;
				}
			}

			newTable[lastIdx] = lastRun;
			for (Entry p = e; p != lastRun; p = p.next) {
				int k = p.hash & mask;
				newTable[k] = new Entry(p.hash, p.key, p.value, newTable[k]);
			}

		}

		table = newTable;
	}

	public Object remove(Object key) {
		return remove(key, null);
	}

	protected Object remove(Object key, Object value) {
		int hash = hash(key);
		Segment seg = this.segments[(hash & 0x1F)];

		synchronized (seg) {
			Entry[] tab = this.table;
			int index = hash & tab.length - 1;
			Entry first = tab[index];
			Entry e = first;
			while (true) {
				if (e == null)
					return null;
				if ((e.hash == hash) && (eq(key, e.key)))
					break;
				e = e.next;
			}

			Object oldValue = e.value;
			if ((value == null) || (value.equals(oldValue)))
				e.value = null;

			Entry head = e.next;
			for (Entry p = first; p != e; p = p.next)
				head = new Entry(p.hash, p.key, p.value, head);
			tab[index] = head;
			seg.count -= 1;
			return oldValue;
		}
	}

	public boolean containsValue(Object value) {
		if (value == null)
			throw new NullPointerException();
		for (int s = 0; s < segments.length; s++) {
			Segment seg = segments[s];
			Entry tab[];
			synchronized (seg) {
				tab = table;
			}
			for (int i = s; i < tab.length; i += segments.length) {
				for (Entry e = tab[i]; e != null; e = e.next)
					if (value.equals(e.value))
						return true;

			}

		}

		return false;
	}

	public boolean contains(Object value) {
		return containsValue(value);
	}

	public void putAll(Map t) {
		int n = t.size();
		if (n == 0)
			return;
		do {
			Entry tab[];
			int max;
			synchronized (segments[0]) {
				tab = table;
				max = threshold * 32;
			}
			if (n < max)
				break;
			resize(0, tab);
		} while (true);
		java.util.Map.Entry entry;
		for (Iterator it = t.entrySet().iterator(); it.hasNext(); put(entry
				.getKey(), entry.getValue()))
			entry = (java.util.Map.Entry) it.next();

	}

	public void clear() {
		int s = 0;
		do {
			if (s >= segments.length)
				break;
			Segment seg = segments[s];
			synchronized (seg) {
				Entry tab[] = table;
				for (int i = s; i < tab.length; i += segments.length) {
					for (Entry e = tab[i]; e != null; e = e.next)
						e.value = null;

					tab[i] = null;
					seg.count = 0;
				}

			}
			s++;
		} while (true);
	}

	public Object clone() {
		return new HashMapEx(this);
	}

	public Set keySet() {
		Set ks = keySet;
		return ks == null ? (keySet = new KeySet()) : ks;
	}

	public Collection values() {
		Collection vs = values;
		return vs == null ? (values = new Values()) : vs;
	}

	public Set entrySet() {
		Set es = entrySet;
		return es == null ? (entrySet = new EntrySet()) : es;
	}

	public Enumeration keys() {
		return new KeyIterator();
	}

	public Enumeration elements() {
		return new ValueIterator();
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		int cap;
		synchronized (segments[0]) {
			cap = table.length;
		}
		s.writeInt(cap);
		for (int k = 0; k < segments.length; k++) {
			Segment seg = segments[k];
			Entry tab[];
			synchronized (seg) {
				tab = table;
			}
			for (int i = k; i < tab.length; i += segments.length) {
				for (Entry e = tab[i]; e != null; e = e.next) {
					s.writeObject(e.key);
					s.writeObject(e.value);
				}

			}

		}

		s.writeObject(null);
		s.writeObject(null);
	}

	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
		int cap = s.readInt();
		table = newTable(cap);
		for (int i = 0; i < segments.length; i++)
			segments[i] = new Segment();

		do {
			Object key = s.readObject();
			Object value = s.readObject();
			if (key != null)
				put(key, value);
			else
				return;
		} while (true);
	}

}