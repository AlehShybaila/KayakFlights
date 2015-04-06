
Project: Kayak.

About:
A framework that can be used to Assert the Origin and Destination Details in the Results screen are the same as the one in the Main screen.
This project has a Page Object Model (POM) based structure.
KayakFlights (Page class) - will find the WebElements of the web page and also contains Page method which perform operations on those WebElements
TestKayakFlights - contains all test Methods.

Installation:
git clone https://github.com/ashybaila/Kayak.git

Execution:
Change directory to the location of the project you cloned. Execute in command prompt:

mvn clean test

Report:
File report_01.txt is located in [your_path]/Kayak/src/test/reports

Surfire report is located in:
[your_path]/Kayak/target/site/surefire-report.html