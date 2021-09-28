#!/bin/bash

# Run update-alternatives for a manual installed JDK

JAVA_HOME="$1"
java_bins=(appletviewer extcheck idlj jarsigner java javac javadoc javafxpackager javah javap javapackager java-rmi.cgi javaws jcmd jconsole jcontrol jdb jdeps jhat jinfo jjs jmap jps jrunscript jsadebugd jstat jstatd jvisualvm keytool native2ascii orbd pack200 policytool rmic rmid schemagen servertool tnameserv wsgen wsimport xjc jar jmc jmc.ini jstack rmiregistry serialver unpack200)

for java_bin in ${java_bins[@]}; do
    echo "Setting $java_bin..."
    update-alternatives --install /usr/bin/$java_bin $java_bin $JAVA_HOME/bin/$java_bin 1
    update-alternatives --set $java_bin $JAVA_HOME/bin/$java_bin
done

echo "Done."
