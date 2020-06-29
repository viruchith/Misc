"""
Philaland Coin
Problem Description

The problem solvers have found a new Island for coding and named it as Philaland.

These smart people were given a task to make purchase of items at the Island easier by distributing various coins with different value.

Manish has come up with a solution that if we make coins category starting from $1 till the maximum price of item present on Island, then we$

Lets suppose the maximum price of an item is 5$ then we can make coins of {$1, $2, $3, $4, $5} to purchase any item ranging from $1 till $5.

Now Manisha, being a keen observer suggested that we could actually minimize the number of coins required and gave following distribution {$$

Your task is to help Manisha come up with minimum number of denominations for any arbitrary max price in Philaland.

Constraints

1<=T<=100

1<=N<=5000

Input Format

First line contains an integer T denoting the number of test cases.

Next T lines contains an integer N denoting the maximum price of the item present on Philaland.

Output

For each test case print a single line denoting the minimum number of denominations of coins required.

Timeout

1

Test Case

Example 1

Input

2

10

5

Output

4

3

Explanation

For test case 1, N=10.

According to Manish {$1, $2, $3,... $10} must be distributed.

But as per Manisha only {$1, $2, $3, $4} coins are enough to purchase any item ranging from $1 to $10. Hence minimum is 4. Likewise denomina$

For test case 2, N=5.

According to Manish {$1, $2, $3, $4, $5} must be distributed.

But as per Manisha only {$1, $2, $3} coins are enough to purchase any item ranging from $1 to $5. Hence minimum is 3. Likewise denominations$

"""

#Solution....
t=int(input())#No of test cases
res=[]
for _ in range(t):
    n= int(input())#Max price of product
    l=[x for x in range(1,n+1)]
    final=[]
    for j in range(0,n-1):
        if sum(l[:j])>=n:
            final=l[:j]
            break

    if not final:
        pass
    else:
        res.append(len(final))
        final.clear()
        l.clear()
[print(x) for x in res]
