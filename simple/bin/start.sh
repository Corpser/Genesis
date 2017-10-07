#!/bin/sh

JAVA_HOME="/data/service/java"

APP_HOME=/data/service/web/product/simple/

LOG_PATH=/data/logs/web/simple

APP_JAR="caixin-simple-biz.jar"
 
psid=0
 
checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_JAR`
 
   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}
 
start() {
  checkpid
 
   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_JAR already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_JAR ..."
      nohup $JAVA_HOME/bin/java -Xms256m -Xmx512m -jar $APP_HOME$APP_JAR >$LOG_PATH/simple.out 2>&1 &
   fi
}
 
stop() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_JAR ...(pid=$psid) "
      kill -9 $psid
   else
      echo "================================"
      echo "warn: $APP_JAR is not running"
      echo "================================"
   fi
}

case "$1" in
   "start")
      start
      ;;
   "stop")
     stop
     ;;
   "restart")
     stop
     start
     ;;
  *)

  "Usage: $0 {start|stop|restart}"
 esac
  exit 1;
