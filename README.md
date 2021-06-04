# PandaKeeper
comp305 Panda Keeper group project

TO DO LIST

1- Discussing the project and trying to find a solution.
---> Assigning pandas to spots
---> Assigning spots to pandas
2- Creating an interface that reads the input files
3- Defining basic classes that we will use such as Panda, Spot, PandaArea
4- After reading the Input files, send the fields to the solutions class and pass them to the solution methods.
5- After calling the solution methods in the main class the program outputs the correct results.
6- Prepare different test cases to test the program.
7- After testing edge cases the distribution was not correct for multiple neighbours.
  New Methods : 
  -->Updating assigning pandas
  Using Distance Vector Algorithm principles counting the neighbour pandas, look after each neighbour's bamboo count and distribute by taking from the most fed neighbour.
  -->Brute force with recursion
  Uses brute force to search for the optimal solution. Algorithm calls itself recursively from every possible node that the panda which has eaten the least up to that iteration can eat. 
  -->Random Method
  This is an improvement on th earlier greedy approach we did. Greedy approach is modified so that after distributing the spots that can be eaten by only one panda, the rest of the spots are distributed to the least eaten panda in random order. Algorithm runs multiple times and tracks the optimal solution between iterations to come up with the global optimum solution by exploiting the random properties. 
Results for:

Input 1 :
Input 2 :
Input 3 : 
