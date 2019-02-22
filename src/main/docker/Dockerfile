FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD security*.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-Xmx256m -Dspring.profiles.active=docker -Djava.security.egd=file:/dev/./urandom"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
