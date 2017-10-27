# MouseSimulator
SUMMER PROJECT

Introduction:
1/ One among many fields of Human – computer interaction (HCI) projects aims at reducing frustration 
and improving the ability to manipulate the cursor when interacting with a computer that is not optimal for people with motor impairments.
2/ However, research on this field seems to be slow since they are required to conduct extensive testing and also involve human participation.
3/ My projects targeted on building a program to simulate an actual human mouse movement (myself)  to eliminating the time wasting on finding and testing subjects for these HCI projects.

Programs:
1/ The Tracker allows  to record the locations and time as the mouse moves, drags, (left, right, middle) clicking, press, releases and output these events into a csv files. 
2/ The Player allows to take the data record from the Tracker and display back the mouse events by drawing series of points and lines that where the mouse went. 
3/ The Tasks Generating creates 36 different test cases for recording moving actions from the point which is the center of the screen to the final target in various angle (8 directions: N, NE, SE, S, SW, W, E, NW) with different distances (large: 450 pixels, small: 200 pixels away) and target sizes (circle with 50 pixels or 20 pixels radius) 

Prodcedure:
1/ With the data collected from the Tasks Generating, I used R to compute the relation between V (The velocity from where the cursor at to next point) and Dist (The distance between where the cursor at and the target), Theta (The angle between where the cursor at to the final target and where the cursor at to the next possible point) and fitted its data into the univariable distribution (picture below). 
2/ The next simulated point can be calculated base on Theta and D (distance from the current location to the next simulated point)
3/ The mouse model confirmed Fitts’s Law: The movement of the cursor from the starting point is rapid and aimed to any directions and then slow down when the cursor is closer to the target. 

Conclusion: 
1/ The results shows I am able to simulate a series of mouse actions, which is visually similar to my actual mouse movements for only the South-direction task. 
2/ However, the research can be taken on by conducting extend statistical analysis to justify how good the simulated mouse movements is as well as testing the model on other directions. 
3/ There are few assumptions were made to make the program simple:
        Each move is independent from previous one.
        V and Dist is a linear relationship (Fitts’s Law) 
        Theta fitted well in normal distribution
        Time interval T = 1 millisecond.
        
References:
1/ “Fitts’ Law as a Research and Design Tool in Human – Computer Interaction” by I. Scott Mackenzie. 
2/ “Extending Fitts’ law to Two-dimensional Tasks” by I. Scott Mackenzie and William Buxton.
3/ “Point Assist: Assisting Individuals with Motor Impairments” by Guarionex Salivia and Juan Pablo Hourcade.

Acknowledgement:
     I would like to thanks Mr. and Mrs. Phelps for supporting with the donor, which covered my expenses while I was staying on campus last summer.  I would also like to thanks St Lawrence University and  Dr. Choong-soo Lee for his assistance and guidance with my project. 

