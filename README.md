# TSP
- The travelling salesman problem (TSP) asks the following question: 
- "Given a list of cities and the distances between each pair of cities, what is the shortest possible route that visits each city exactly once and returns to the origin city?"
-  Main purpose of this problem is to find the shortest path which is as close to the optimal tour as possible. 
- Since TSP problem is [NP-hard problem](https://en.wikipedia.org/wiki/NP-hardness), best approach for effiency is to find the approximate result in shortest amount of time.


![Opera Snapshot_2020-10-24_000552_upload wikimedia org](https://user-images.githubusercontent.com/62245004/97054132-e5cdb300-158c-11eb-999d-aae7977a35c1.png)

## What is used and how it works ?
- I decided to use [2-opt heuristic algorithm](https://en.wikipedia.org/wiki/2-opt).
- In this approach algorithm works by looking for search area of possible solutions that will result in different set of permutations of a tour. 
