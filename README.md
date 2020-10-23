# TSP
- The travelling salesman problem (TSP) asks the following question: 
- "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?"
-  Main purpose of this problem is to find the shortest path which is as close to the optimal tour as possible. 
- Since TSP problem is [NP-hard problem](https://en.wikipedia.org/wiki/NP-hardness), best approach for effiency is to find the approximate result in shortest amount of time.


![Opera Snapshot_2020-10-24_000552_upload wikimedia org](https://user-images.githubusercontent.com/62245004/97054132-e5cdb300-158c-11eb-999d-aae7977a35c1.png)

## What is used and how it works ?
- I decided to use [2-opt heuristic algorithm](https://en.wikipedia.org/wiki/2-opt).
- In this approach algorithm works by looking for search area of possible solutions that will result in different set of permutations of a tour. 


![2-opt](https://user-images.githubusercontent.com/62245004/97054287-37763d80-158d-11eb-884a-6d03b263ab26.gif)

## 2-opt Approach with Fast Local Search(FLS) Algorithm

- For optimizing this approach , in first step, I used Fast Local Search(FLS) optimization. Having
declared 2-opt algorithm will create a chance to explore all possible 2-opt moves. 

- One approach is to continuously select two pair of edges and check whether they would result a better tour.

- Instead of selecting random edges, FLS will iterate through all potential pair of edges and checking them for
potential moves. If we found a move that can be used, then the corresponding pair of edges will be
used in 2-opt algorithm and searching will be continued from the previous edge in the tour until
there is no possible moves left. Then at that point we found 2-optimal.

- The trouble that comes with this approach is wasting time to evaluating moves that will not give an
improvement. To overcome this situation if a move is found by the program, I simply declare the pair
of edges as used with using setUsedBits() and isUsed() functions. [So that program will not check
these edges ever again](http://tsp-basics.blogspot.com/2017/03/using-dont-look-bits-dlb-2-opt.html).


## 2-opt Approach with Guided Local Search(GLS) Algorithm

- In addition to FLS i decided to use [Guided Local Search](https://en.wikipedia.org/wiki/Guided_Local_Search) since  it works synchronously with FLS.
- Guided Local Search is a metaheuristic search method. This approach is a method that builds on top of a local
search algorithm to explore the search space for finding near-optimal solutions. 
- The main purpose of this approach is guiding a local search algorithm, FLS in this case, by improving the corresponding
objects that are declared and assigning a cost value to possible edges so that we can compare efficiently. 

## References
- https://en.wikipedia.org/wiki/Guided_Local_Search
- https://en.wikipedia.org/wiki/NP-hardness
- https://pdfs.semanticscholar.org/2210/d4c2694894da361baf9175ac358970b58033.pdf
- http://tsp-basics.blogspot.com/2017/03/using-dont-look-bits-dlb-2-opt.html
- https://en.wikipedia.org/wiki/2-opt
