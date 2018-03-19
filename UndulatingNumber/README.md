An undulating number is a number that has only two types of digits and alternate digits are same, i.e., it is of the form “ababab….”. It is sometimes restricted to non-trivial undulating numbers which are required to have at least 3 digits and a is not equal to b.

The first few such numbers are: 101, 121, 131, 141, 151, 161, 171, 181, 191, 202, 212, 232, 242, 252, 262, 272, 282, 292, 303, 313, 323, 343, 353, 363, 373, 383, 393, 404, 414, 424, 434, 454, 464, 474, 484, 494, …
Some higher undulating numbers are: 6363, 80808, 1717171.

For any n >= 3, there are 9 × 9 = 81 non-trivial n-digit undulating numbers, since the first digit can have 9 values (it cannot be 0), and the second digit can have 9 values when it must be different from the first.
Given a number, check if it is Undulating numbers considering the definition of alternating digits, at least 3 digits and adjacent digits not same.

Examples :

Input : n = 121
Output : Yes

Input : n = 1991
Output : No