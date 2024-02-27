package edu.uwm.cs351;

import java.util.function.Consumer;

import edu.uwm.cs.util.Primes;

//Jiahui Yang, Comp SCI 351, Homework 11
//Colloborated with Christian Oropeza, Marawan Salama on helper methods
//Went to tutor room and talked to Matt

/**
 * A class to manage string instances.
 * All equal string instances that are interned will be identical.
 */
public class StringCache extends Primes{
	// even with a Spy, we still use "private":
	private String[] table;
	private int numEntries;
	

	// TODO: hash helper function used by wellFormed and intern
	 private int hash(String key)
	   {
		 int hasher = Math.abs(key.hashCode() % table.length);	 
		 while(table[hasher] != null && !(table[hasher].equals(key))) {
			 if (hasher == 0) hasher = table.length - 1;
			 else hasher = hasher-1;
		 }
		 return hasher;
	   }
	private static Consumer<String> reporter = (s) -> { System.err.println("Invariant error: " + s); };
	
	private boolean report(String error) {
		reporter.accept(error);
		return false;
	}
		
	private boolean wellFormed() {
		int count = 0;
		// 1. table is non-null and prime length
		if (table == null || !isPrime(table.length)) return report("not prime length");
		// 2. number of entries is never more half the table size
		if (numEntries > table.length/2) return report("# of entries more than half of table size");
		// 3. number of non-null entries in the table is numEntries
		//4. every string in the array is hashed into the correct place
		//using backward linear probing
		for (int i = 0; i<table.length;++i) {
			if (table[i] != null) {
				count++;
				if (i != hash(table[i])) return report("not hashed in the correct place");
			}
		}
		if (count != numEntries) return report("# of non-null entries in table is not numEntries");
		// TODO
		return true;
	}
	
	private StringCache(boolean ignored) {} // do not change
	
	/**
	 * Create an empty string cache.
	 */
	public StringCache() {
		// TODO
		//initialize variables
		numEntries = 0;
		table = new String[2];
		assert wellFormed() : "invariant broken in constructor"; 
	}
	
	// TODO: declare rehash helper method
	private void rehash(int minimumCapacity) {		
		String[] temp = table;
		table = new String[nextPrime(4 * minimumCapacity)];
		for (int i = 0; i<temp.length;++i) if (temp[i]!=null) table[hash(temp[i])] = temp[i];
	}
	/**
	 * Return a string equal to the argument.  
	 * For equal strings, the same (identical) result is always returned.
	 * As a special case, if null is passed, it is returned.
	 * @param value string, may be null
	 * @return a string equal to the argument (or null if the argument is null).
	 */
	public String intern(String value) {
		assert wellFormed() : "invariant broken before intern";
		// TODO, including calling rehash if needed
		if (value == null) return null;
		if (table[hash(value)] == null) {
			table[hash(value)] = value;
			numEntries++;
		}
		if (numEntries>table.length/2) rehash(numEntries);
		value = table[hash(value)];
		assert wellFormed() : "invariant broken after intern";
		return value;
	}
	
	public static class Spy { // do not modify (or use!) this class
		/**
		 * Create a String Cache with the given data structure,
		 * that has not been checked.
		 * @return new debugging version of a StringCache
		 */
		public StringCache create(String[] t, int c) {
			StringCache sc = new StringCache(false);
			sc.table = t;
			sc.numEntries = c;
			return sc;
		}
		
		/**
		 * Return the number of entries in the string cache
		 * @param sc string cache, must not be null
		 * @return number of entries in the cache.
		 */
		public int getSize(StringCache sc) {
			return sc.numEntries;
		}
		
		/**
		 * Return capacity of the table in the cache
		 * @param sc cache to examine, must not be null
		 * @return capacity
		 */
		public int getCapacity(StringCache sc) {
			return sc.table.length;
		}
		
		public boolean wellFormed(StringCache sc) {
			return sc.wellFormed();
		}
		
		public Consumer<String> getReporter() {
			return reporter;
		}
		
		public void setReporter(Consumer<String> c) {
			reporter = c;
		}

	}
}
