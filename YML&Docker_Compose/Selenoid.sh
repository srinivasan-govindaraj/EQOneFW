#!/bin/bash
./cm selenoid start --args "-limit 25"
./cm selenoid-ui start
#Save this as a file (e.g., selenoid.sh),
#make it executable with chmod +x selenoid.sh,
#and then run it with ./start_selenoid.sh
