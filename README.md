# BillableHours
Billable Hour for employee

Steps to set up the project, 
1. Install java jdk on your system and set the path on environment variables, 
2. Install Appium on your system and also set the environment variables for the sdk tools, 
3. Clone this project and import all the maven dependencies.
4. Clean and package the build.
5. Set your device on USB debugging mode and turn on your device USB debugging.
6. Connect your device to your system and unlock the device
7. Ensure the CSV files on this project is downloaded on your device (you can find the csv files in the files folder in this project)
8. Run the project using TestNG configuration
9. When the project is done executing the report is contained in the bundle.html file.
10. Open the bundle.html file using chrome or any other browser to view the results of the tests that was carried out.

Worthy of mention that the ParseCSV class  and other classes have errors because i delibrately added those expectations when an invalid CSV file path is added and when something that is meant to there is not there.( an example is the Total cost where it was just the amount without the Naira sign.)

Here is the link to the Testscripts for the mobile Application:- 
https://docs.google.com/spreadsheets/d/17K_USVNuFGR61kveP-HWFn2hO7mU7JZpds9EegoFB-8/edit#gid=1377291879

On the submodules of the spreadsheet is the feeback/Improvement.
