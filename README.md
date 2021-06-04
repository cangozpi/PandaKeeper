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

Input 1 : 4, 0 - Best Runtime -> Case1: 325321, Case2: 10415
Input 2 : 0, 0 - Best Runtime: -> Case1: 120092, Case2: 12494
Input 3 : 0, 0, 10 - Best Runtime: -> Case1: 119756, Case2: 3563 , Case3: 17614
Input 4 : 1 - Best Runtime: 126503

How to run the code : 
There are 4 Input files , which our Input Parser class reads. Users need to manually change the desired Input file in order to get the necessary output. File name should be changed in Buffered Reader declaration.
Change the SOLUTION_NO variable to the desired value in main class to run the desired solution. 
SOLUTION_NO = 1 Distance vector inspired solution,
SOLUTION_NO = 2 Dynamic Programming recursion solution,
SOLUTION_NO = 3 Randomization solution

Summary:
We implemented our solution in 2 parts. First part was creating a  efficient list to use in distributing the bamboos. We came up with 2 approaches storing pandas in cells and storing cells in pandas. We later figured out that storing pandas was more efficient and used that for our part 2. 
In part 2 we came up with 3 algorithms distance vector, recursion and randomization. Distance vector and randomization use a greedy algorithm. Our best algorithm was the randomized one which runs the greedy algorithm for 100 times with different list combination and prints the smallest number.
