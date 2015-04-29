#!/bin/sh

export TMPDIR=/mnt/.psplash

SN=$(cat /etc/reach-version  |grep "^meta-crank"|awk '{print $3}' | cut -c1-9)
                                                                              
/usr/bin/psplash-write "MSG $SN"
