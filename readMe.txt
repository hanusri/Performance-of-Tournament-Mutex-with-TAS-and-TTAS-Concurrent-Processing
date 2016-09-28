Instruction to run the application
----------------------------------

Below is the script to run the application in stampede machine

#!/bin/bash
#SBATCH -J Project1      # job name
#SBATCH -o Project1.o%j  # output and error file name (%j=jobID)
#SBATCH -n 32           # total number of cpus requested
#SBATCH -p development  # queue -- normal, development, etc.
#SBATCH -t 00:05:00     # run time (hh:mm:ss) - 1.5 hours
#SBATCH --mail-user=srikanth.kannan@utdallas.edu
#SBATCH --mail-type=begin  # email me when the job starts
#SBATCH --mail-type=end    # email me when the job finishes
cd $HOME/Project1
javac ApplicationRunner.java
java ApplicationRunner 20 10000

Following are the understanding about the above script

a.	Project1 directory created under my home location in Stampede
b.	ApplicationRunner.java has the main method as starting point
c.	While executing, the application expects two parameters. First paramter being the number of threads and second paramter being number of requests that each thread will make.
For the above example, number of threads is 20 and number of requests is 10000.