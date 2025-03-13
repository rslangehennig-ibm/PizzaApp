FROM java:8u111-jdk
EXPOSE 8000
COPY . /app/
WORKDIR /app
RUN javac PizzaApp.java
CMD ["java", "PizzaApp"]
