#!/bin/bash

# takes the QUERY STRING as input and launch the Java program Reverse
# returns an HTML page with the reversed string

IFS='=' read -ra PARAMETERS <<< "$QUERY_STRING"
OUTPUT="$(java Reverse ${PARAMETERS[1]})"

# set HTML contet type, structure and print the output
echo "Content-type: text/html"
echo
echo "<!DOCTYPE html><html><head></head><body>"
echo "<h1>$OUTPUT</h1>"
echo "</body></html>"
