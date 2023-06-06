#!/bin/bash

REPOSITORY=/home/ec2-user/app2/step2/
PROJECT_NAME=inflearn #해당 위치에 properties에 작성한 프로젝트명과 동일하게 작성 *jar파일로 생성되는 인텔리제이 프로젝트명과 동일해야함.

echo "> 경로 이동"
cd $REPOSITORY
echo "> Build 파일 복사"
cp $REPOSITORY/zip/build/libs/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $PROJECT_NAME)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/| grep jar |tail -n 1)

echo "> JAR Name: $JAR_NAME"


echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app2/application-real.properties \
        -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &