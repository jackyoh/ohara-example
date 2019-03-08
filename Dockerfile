FROM gradle

RUN git clone https://github.com/jackyoh/ohara-example.git
RUN cd ohara-example;gradle clean build
RUN cd ohara-example;gradle jar
RUN mkdir -p /home/gradle/ohara
RUN cp /home/gradle/ohara-example/build/lib/* /home/gradle/ohara
RUN cp /home/gradle/ohara-example/build/libs/* /home/gradle/ohara
ADD run.sh /home/gradle
CMD ["/home/gradle/run.sh"]
