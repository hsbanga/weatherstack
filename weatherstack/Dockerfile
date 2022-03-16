FROM markhobson/maven-chrome:jdk-8

ENV MAVEN_OPTS="-Dmaven.repo.local=.m2/repository"



RUN mkdir -p /home/node/app
RUN mkdir -p /home/node/app/target
WORKDIR /home/node/app
#COPY ["pom.xml", "./"]

COPY . .
RUN mvn compile
#COPY entrypoint.sh /home/node/app/entrypoint.sh
RUN chmod 777 /home/node/app/demo.html
RUN chmod 777 /home/node/app/entrypoint.sh
ENTRYPOINT ["/home/node/app/entrypoint.sh"]