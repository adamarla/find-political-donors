#!/bin/bash
#
# required parameters are 'inputDir' and 'outputDir'
#

gradle build
gradle run -PinputDir=input -PoutputDir=output

