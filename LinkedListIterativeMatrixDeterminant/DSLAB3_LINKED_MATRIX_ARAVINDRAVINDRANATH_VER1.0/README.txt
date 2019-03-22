Project by Aravind Ravindranath

The source code was developed in JAVA 1.8. It was developed using Eclipse Neon.
Eclipse IDE details ( Version: Neon.3 Release (4.6.3)
					  Build id: 20170314-1500 )

To run the program, enter:  java com.jhu.ds.lab2.IOoperations.ProcessInputSquareMatrices
							[Input File Name] [Output File Name] [Type of Run].

The first two Strings in the square brackets depict the two arguments which should be passed when
the above program is executed. The file path is relative to the project folder and inputFile 
should be clearly specified. If there is a folder Input  under the project folder in which the 
input file LabInput1 is kept, then the command line should have ../Input/LabInput1 when 
executed from folder bin.If executed in an IDE like eclipse, then 
/Input/LabInput1 shall work. The output file contains square matrices along with its calculated 
determinants. The file path or the file names are not hard coded. A good test data set exists in 
file LabInput3 under Input folder.
The third String "Type of Run" is an optional argument which can be passed. If a value "I" is passed then the
determinant is calculated via an iterative logic using Gauss-Jordan elimination. If nothing is passed
or a value other than "I" is passed, then the determinant calculation happens via recursion logic.

An example to call this program with the current project set up and files included is as follows 
when executed in command prompt under folder bin. First one will lead to recursive call and the second one
will lead to iterative call:

java com.jhu.ds.lab2.IOoperations.ProcessInputSquareMatrices ../Input/Labinput1 
../Output/Laboutput1   

java com.jhu.ds.lab2.IOoperations.ProcessInputSquareMatrices ../Input/Labinput1 
../Output/Laboutput1 I   


Analysis document is provided in the Analysis Folder.