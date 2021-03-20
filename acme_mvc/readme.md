SOLUTION OVERVIEW

My solution starts with file opening and text parsing at line level.
Each input line should have an employee name, and several work session information
regarding day, start and end work session times.

Some special situations are managed while parsing work session information from lines;
Should an employee do several work sessions in a given day, those sessions shouldn't overlap;
any new work session overlapping and older one is discarded in my solution.
Special care was given to special cases that could arise when an employee do long work 
sessions across critical values such as 9h00 and 18h00, which indicate an hourly payment rate change.

For payment calculation a context is defined considering day and start/end times
of the work session; also, there were defined two payment strategies, one for week days and
another for week end days; similar things were defined for hour payment rates.

ARCHITECTURE EXPLANATION

An MVC architecture was chosen to implement my solution; in the model layer a composite
object EmployeeWorkSessionLog was declared to hold the information in every line of the input file.

The control layer is in charge of text parsing and payment calculation with the data registered in 
an EmployeeWorkSessionLog object; finally, a simple method for output console presentation is fed 
with employee name from a EmployeeWorkSessionLog and the total payment calculated with a payment 
calculator object (control layer).

EXPLANATION OF APPROACH AND METHODOLOGY

Firstly, every text line found in the input file is extracted through an iterator which extract them 
from a buffer (BufferedReader) wrapped in a container object; every text line that lacks an employee 
name is discarded.

Line extraction and parsing is performed inside a loop which ends when the buffer is not ready and 
it's automatically closed when that stage is reached.

While looping, raw text lines are parsed and stored in a ParsedLine object which holds a string with 
the employee name and an ArrayList of non-dynamic string arrays which hold string representations of 
day, start and end of a work session.

Raw text line parsing is done mainly leveraging the split method of the native String Java object.

Employee and WorkSessions data are hold in a EmployeeWorkSessionLog object which has a constructor 
defined to accept, again, String representations of an employee name and his/her working sessions.

While storing work session data, some considerations were taken in account, first, two equal work 
sessions could be stored in a Log object; the same applies for overlapping work sessions; also, a 
start time work session should be prior to an end time work session.

While working with LocalTime objects I realize that working session end times with value 00:00 caused
some troubles for working session duration calculations, so, every time a 00:00 is entered as end time,
its automatically changed to LocalTime.MAX which is equal to 23:59 provisionally and to 24 hours and zero
minutes when session duration must be calculated. 

prospective EmployeeWorkSessionLog objects which don't fulfill previous stated considerations are 
discarded through exceptions management.

Once a EmployeeWorkSessionLog object is properly defined its added to an ArrayList for further 
looping and payment calculation of every employee.

The PaymentCalculator object consists of only two methods, one for calculate payment of a single work 
session (GetWorkSessionPayment)and another for calculate overall payment for a single employee (several 
work sessions).

The GetWorkSessionPayment takes three input arguments a Day object, and two LocalTime objects: start 
and end which represent the lower and upper limits of a work session.

Day, start and end objects are members of a WorkSession object declared in the model package and 
along with Employee object conforms in composition the EmployeeWorkSessionLog.

For payment calculations there were defined two strategy interfaces, one for define hourly rates 
at day level and another one to define bonus to hourly rates; from the problem requirements, a regular hour rate
was identified for week days ($15) and for week end days ($20); early morning hours bonus for both cases is $10 and
$15 for nightly work hours; a day/hour context define the base hour rate and the correct bonus to be considered
for payment calculations.

For results display was necessary to consider whether resulting payment had a fractional part, otherwise, only an 
integer representation was considered.  It was used a tolerance less than one cent for the previously stated.

One minute gaps between critical time values results in some cents that are missing from the employee total payment.

INSTRUCTIONS TO RUN THE PROGRAM LOCALLY
A makefile was implemented to run the program; make, java and javac should be present in the PATH environment 
variable.

It's possible to type  make FILENAME=[path to input file] to specify another text file for input.

Input text files can have comments with double slashes, these are ignored by the line parser. 

If you just type make an ArrayOutOfBoundsException will arise and the default input text file 
(with five lines (sets) as required) will be processed. 

TESTING
To run tests just type make test
