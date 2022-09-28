README Bharath Sukesh DSA Assignment
===============================================================================
Files

healthSim
Handles command-line arguments for the program and performs any last-minute exception handling. 

UI
User Interface for healthSim
  
Network
Class representing a network of people. Contains all implementation for 
network operations such as adding or removing people (nodes), 
updating/timestepping. 

FileIO
File Input/Output operations for healthSim, handles reading and saving 
for network and edge files

DSAGraph
Implementation of a undirected graph ADT

DSAGraphVertex
Implementation of a graph vertex - part of the graph ADT

DSALinkedList
Implementation of a linked list ADT

DSAQueue
Implementation of a FILO queue ADT

TestDataDSAGraph
Test file needed for loading in graph - specifically needed for UnitTestDSAGraph.java

UnitTest<class>
Test program for <class>
===============================================================================
How To Run

healthSim
Interactive Testing Environment
    Run with -i
    eg. java healthSim -i

All menu options work as normal with the exception of timestep - works partially, can cause program to get stuck.
	
Simulation Mode
    Run with -s <netfile> <edgefile> <trans_rate> <recov_rate> <death_rate> <int_code>
    eg. java healthSim -s netTest.txt edgeTest.txt 0.7 0.4 0.5 1
	
Works partially, can cause program to get stuck.
	
UnitTest<class>
Run as normal java programs
eg. java UnitTestGraph