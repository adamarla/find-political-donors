Welcome to my submission for Find Political Donors!

Approach - I have used the Kotlin programming language to write this
application. The logic is fairly straightforward - a parser tokenizes
the input and passes it to the "distiller". The distiller is registered
with multiple aggregators (one each for 'By Date' and 'By Zip'). These
are responsible for aggregating the records and formatting them for
reports.

Dependencies -

  To Run
  - Gradle 4.1
  - Java 8 runtime

  To Compile
  - Gradle 4.1 (earlier might also work, but I haven't tried)
  - Kotlin 1.1.50 (or later)
  - JDK 1.8

Run Instructions -

  To Run -
  The run script is configured to run from top-level project directory  
  ./run.sh

  To Test -
  Test script has been modified to work as shown  
  cd insight_testsuite  
  ./run_tests.sh

