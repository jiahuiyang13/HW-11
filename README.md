
Summary:

Implemented a hash table implementation of a Dictionary in Java, using linear probing with backward movement. The implementation ensures close to constant-time access for elements. The design follows the textbook's technique, with a focus on sections 11.2–11.4, pages 581–602 (3rd ed., pp. 569–590).

Key Points:

Utilized linear probing with backward movement through the array for collision resolution.

Ensured the remainder operator % returned a positive number for array indexing using Math.abs().

Implemented a private helper function for searching an index to place a key, which is used for rehashing, the single public method, and the invariant check (wellFormed()).

Used a prime number for the array length to improve hash table performance.

Managed rehashing by reallocating a new array with a length equal to the smallest prime greater than 4 times the current number of entries.

Replaced all entries in the new array in the order they occurred in the current array to maintain correct placement.
Innovation:

The implementation handles rehashing efficiently, ensuring that the number of entries never exceeds half the capacity of the array.

Challenges:

Managing the rehashing process and ensuring that all entries are correctly placed in the new array posed a significant challenge.

Handling array indices and ensuring they were positive for array indexing required careful implementation.

Outcomes:

The implemented hash table provides close to constant-time access for elements, demonstrating effective use of linear probing and proper collision resolution techniques.

The rehashing strategy ensures that the hash table remains efficient even as the number of entries increases.

Future Improvements:

Consider implementing removal functionality to handle situations where entries need to be removed from the hash table.

Explore alternative collision resolution techniques to evaluate their impact on performance and efficiency.
