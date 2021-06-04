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
<<<<<<< HEAD
  Uses brute force to search for the optimal solution. Algorithm calls itself recursively from every possible node that the panda which has eaten the least up to that iteration can eat. 
  -->Random Method
  This is an improvement on th earlier greedy approach we did. Greedy approach is modified so that after distributing the spots that can be eaten by only one panda, the rest of the spots are distributed to the least eaten panda in random order. Algorithm runs multiple times and tracks the optimal solution between iterations to come up with the global optimum solution by exploiting the random properties. 
=======
  By Assigning every spot to Pandas, check the overlapping spots and distrbute accordingly, recursion ends with when all spots are assigned.
  -->Random Method
  By Assigning overlapping spots randomly, and saving results we can find the optimal distribution with high percentage.
>>>>>>> 7f1e1635665223a778727196a5f5ca583b610fca
Results for:

Input 1 : 4, 0
Input 2 : 0, 0
Input 3 : 0, 0, 10
Input 4 : 1

How to run the code : 
There are 4 Input files , which our Input Parser class reads. Users need to manually change the desired Input file in order to get the necessary output. File name should be changed in Buffered Reader declaration.
