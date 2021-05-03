# BillableHours
Billable Hour for employee

Steps to set up the project
Install java jdk on your system and set the path on environment variables
Install Appium on your system and also set the environment variables for the sdk tools
Clone this project and import all the maven dependencies.
Clean and package the build.
Set your device on USB debugging mode and turn on your device USB debugging.
Connect your device to your system and unlock the device
Ensure the CSV files on this project is downloaded on your device (you can find the csv files in the files folder in this project)
Run the project using TestNG configuration
When the project is done executing the report is contained in the bundle.html file.
Open the bundle.html file using chrome or any other browser to view the results of the tests that was carried out.

Worthy of mention that the ParsCSV class  and other classes have errors because i delibrately added those expectations when an invalid CSV file path is added and when something that is meant to there is not there.( an example is the Total cost where it was just the amount without the Naira sign.)

Here is the link to the Testscripts for the mobile Application:- 
https://docs.google.com/spreadsheets/d/17K_USVNuFGR61kveP-HWFn2hO7mU7JZpds9EegoFB-8/edit#gid=1377291879

On the submodules o the spreadsheet is the feeback/Improvement.
