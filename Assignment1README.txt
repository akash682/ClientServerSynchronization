This is the ReadMe File for Assignment 1 
The project is tested on mac platform only

Make sure you have JAVA 1.8 installed on the machine 

1. Go to project directory 
$ cd logical-clock/

2. Compile Java Files 
2.1 First create a bin directory
$ mkdir bin
2.2 Compile the project java files to bin directory 
$ javac -d bin src/assignment1/*.java

3. Run the script
3.1 Assign execute permission to script
$ chmod u+x java.sh 
3.2 Run the shell script
$ ./java.sh


This will run 3 processes opened in 3 different terminals and demonstrate the output of 3 when delivered as
Current PID(i.e., pid of the process): (PID.EventID) where PID is message senders pid 


Testing Scenario: 
If you close one terminal, The processes will be stuck at particular output waiting for the ack (that it will never received)
When all terminals are open, the processes will keep issues messages and the messages will be delivered as per the totally ordered multicast algorithm. 